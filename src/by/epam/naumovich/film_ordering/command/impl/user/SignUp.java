package by.epam.naumovich.film_ordering.command.impl.user;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.naumovich.film_ordering.bean.User;
import by.epam.naumovich.film_ordering.command.Command;
import by.epam.naumovich.film_ordering.command.util.ErrorMessages;
import by.epam.naumovich.film_ordering.command.util.JavaServerPageNames;
import by.epam.naumovich.film_ordering.command.util.LogMessages;
import by.epam.naumovich.film_ordering.command.util.RequestAndSessionAttributes;
import by.epam.naumovich.film_ordering.command.util.SuccessMessages;
import by.epam.naumovich.film_ordering.service.IUserService;
import by.epam.naumovich.film_ordering.service.ServiceFactory;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;
import by.epam.naumovich.film_ordering.service.exception.user.ServiceSignUpException;

/**
 * Performs the command that reads new user parameters from the JSP and sends them to the relevant service class.
 * Uploads images to the required directories.
 * Checks the access rights of the user who is performing this action.
 * 
 * @author Dmitry Naumovich
 * @version 1.0
 */
public class SignUp implements Command {

	private static final Logger logger = LogManager.getLogger(Logger.class.getName());
	private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 6;
	private static final String UPLOAD_FOLDER = "D:/java-work/film-ordering/WebContent/img/avatars/";
	private static final String UTF_8 = "UTF-8";
	private static final String REPOSITORY = "java.io.tmpdir";
	private static final String FILE_NAME_TEMPLATE = "avatars";
	private static final String FILE_EXTENSION = ".gif";
	
	@SuppressWarnings("unchecked")
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(true);
		if (session.getAttribute(RequestAndSessionAttributes.AUTHORIZED_USER) != null) {
			int userID = Integer.parseInt(session.getAttribute(RequestAndSessionAttributes.USER_ID).toString());
			request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, ErrorMessages.LOG_OUT_FOR_SIGN_UP);
			request.getRequestDispatcher("/Controller?command=open_user_profile&userID=" + userID).forward(request, response);
		} else {
			String login = null;
			String name = null;
			String surname = null;
			String pwd = null;
			String sex = null;
			String bDate = null;
			String phone = null;
			String email = null;
			String about = null;
			FileItem avatarItem = null;
			File avatar = null;
			try {
				if (ServletFileUpload.isMultipartContent(request)) {
					DiskFileItemFactory factory = new DiskFileItemFactory();
				    factory.setSizeThreshold(MAX_MEMORY_SIZE);
				    factory.setRepository(new File(System.getProperty(REPOSITORY)));
				    ServletFileUpload  fileUpload = new ServletFileUpload(factory);
				    fileUpload.setSizeMax(MAX_REQUEST_SIZE);
				    List<FileItem> items = fileUpload.parseRequest(request);
				    Iterator<FileItem> iter = items.iterator();
				    
				    while (iter.hasNext()) {
				        FileItem item = iter.next();
				        if (!item.isFormField() && item.getName() != null && !item.getName().isEmpty()) {
				        	avatarItem = item;
				        	String fileName = new File(item.getName()).getName();
			                String filePath = UPLOAD_FOLDER + File.separator + fileName;
			                avatar = new File(filePath);
			                
				        } else {
				        	switch (item.getFieldName()) {
				        	case RequestAndSessionAttributes.LOGIN:
				        		login = item.getString(UTF_8);
				        		break;
				        	case RequestAndSessionAttributes.NAME:
				        		name = item.getString(UTF_8);
				        		break;
				        	case RequestAndSessionAttributes.SURNAME:
				        		surname = item.getString(UTF_8);
				        		break;
				        	case RequestAndSessionAttributes.PASSWORD:
				        		pwd = item.getString(UTF_8);
				        		break;
				        	case RequestAndSessionAttributes.SEX:
				        		sex = item.getString();
				        		break;
				        	case RequestAndSessionAttributes.BDATE:
				        		bDate = item.getString();
				        		break;
				        	case RequestAndSessionAttributes.PHONE:
				        		phone = item.getString();
				        		break;
				        	case RequestAndSessionAttributes.EMAIL:
				        		email = item.getString();
				        		break;
				        	case RequestAndSessionAttributes.ABOUT:
				        		about = item.getString(UTF_8);
				        		break;
				        	}
				        }
				    }
				}
				
				
				IUserService userService = ServiceFactory.getInstance().getUserService();
				int userID = userService.addUser(login, name, surname, pwd, sex, bDate, phone, email, about);

				if (avatar != null) {
					try {
						avatarItem.write(avatar);
					    Files.move(avatar.toPath(), new File(UPLOAD_FOLDER + File.separator + FILE_NAME_TEMPLATE + userID + FILE_EXTENSION).toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
					} catch (IOException e) {
						logger.error(String.format(LogMessages.EXCEPTION_IN_COMMAND, e.getClass().getSimpleName(), this.getClass().getSimpleName(), e.getMessage()));
						request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
					}
				}
				
				
				User user = userService.getUserByLogin(login);
				session.setAttribute(RequestAndSessionAttributes.AUTHORIZED_USER, login);
				session.setAttribute(RequestAndSessionAttributes.USER_ID, user.getId());
				session.setAttribute(RequestAndSessionAttributes.IS_ADMIN, 'a' == user.getType());
				logger.debug(String.format(LogMessages.USER_REGISTRATED, login, userID));
				request.setAttribute(RequestAndSessionAttributes.SUCCESS_MESSAGE, SuccessMessages.SUCCESSFULL_SIGN_UP);
				request.getRequestDispatcher("/Controller?command=open_user_profile&userID=" + userID).forward(request, response);
			} catch (ServiceSignUpException e) {
				logger.error(String.format(LogMessages.EXCEPTION_IN_COMMAND, e.getClass().getSimpleName(), this.getClass().getSimpleName(), e.getMessage()));
				request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
				request.getRequestDispatcher("/Controller?command=open_sign_up_page").forward(request, response);
			} catch (ServiceException e) {
				logger.error(String.format(LogMessages.EXCEPTION_IN_COMMAND, e.getClass().getSimpleName(), this.getClass().getSimpleName(), e.getMessage()));
				request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
				request.getRequestDispatcher(JavaServerPageNames.ERROR_PAGE).forward(request, response);
			} catch (Exception e) {
				logger.error(String.format(LogMessages.EXCEPTION_IN_COMMAND, e.getClass().getSimpleName(), this.getClass().getSimpleName(), e.getMessage()));
				request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
				request.getRequestDispatcher(JavaServerPageNames.ERROR_PAGE).forward(request, response);
			}
		}
	}
}
