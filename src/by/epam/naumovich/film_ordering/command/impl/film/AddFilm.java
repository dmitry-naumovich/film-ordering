package by.epam.naumovich.film_ordering.command.impl.film;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
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

import by.epam.naumovich.film_ordering.command.Command;
import by.epam.naumovich.film_ordering.command.util.ErrorMessages;
import by.epam.naumovich.film_ordering.command.util.JavaServerPageNames;
import by.epam.naumovich.film_ordering.command.util.LogMessages;
import by.epam.naumovich.film_ordering.command.util.RequestAndSessionAttributes;
import by.epam.naumovich.film_ordering.command.util.SuccessMessages;
import by.epam.naumovich.film_ordering.service.IFilmService;
import by.epam.naumovich.film_ordering.service.ServiceFactory;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;
import by.epam.naumovich.film_ordering.service.exception.film.AddFilmServiceException;

/**
 * Performs the command that reads new film parameters from the JSP and sends them to the relevant service class.
 * Uploads images to the required directories.
 * Checks the access rights of the user who is performing this action.
 * 
 * @author Dmitry Naumovich
 * @version 1.0
 */
public class AddFilm implements Command {

	private static final Logger logger = LogManager.getLogger(Logger.class.getName());
	private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 6;
	private static final String UPLOAD_FOLDER = "D:/java-work/film-ordering/WebContent/img/films/";
	private static final String UTF_8 = "UTF-8";
	private static final String REPOSITORY = "java.io.tmpdir";
	private static final String FRAME_FILE_NAME = "01.jpg";
	private static final String FOLDER_FILE_NAME = "folder.jpg";
	
	@SuppressWarnings("unchecked")
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(true);
		if (session.getAttribute(RequestAndSessionAttributes.AUTHORIZED_USER) == null) {
			request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, ErrorMessages.ADD_FILM_RESTRICTION);
			request.getRequestDispatcher(JavaServerPageNames.LOGINATION_PAGE).forward(request, response);
		}
		else if (!Boolean.parseBoolean(session.getAttribute(RequestAndSessionAttributes.IS_ADMIN).toString())) {
			request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, ErrorMessages.ADD_FILM_RESTRICTION);
			request.getRequestDispatcher("/Controller?command=open_all_films&pageNum=1").forward(request, response);
		}
		else {				
			String name = null;
			String year = null;
			String director = null;
			String cast = null;
			String[] countriesArray = null;
			List<String> countries = new ArrayList<String>();
			String composer = null;
			String[] genresArray = null;
			List<String> genres = new ArrayList<String>();
			String length = null;
			String price = null;
			String description = null;
			FileItem folderItem = null;
			File folder = null;
			FileItem frameItem = null;
			File frame = null;
			
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
				        	switch (item.getFieldName()) {
				        	case RequestAndSessionAttributes.FOLDER:
				        		folderItem = item;
				        		String folderFileName = new File(item.getName()).getName();
				        		String folderFilePath = UPLOAD_FOLDER + File.separator + folderFileName;
				        		folder = new File(folderFilePath);
				        		break;
				        	case RequestAndSessionAttributes.FRAME:
				        		frameItem = item;
				        		String frameFileName = new File(item.getName()).getName();
				        		String frameFilePath = UPLOAD_FOLDER + File.separator + frameFileName;
				        		frame = new File(frameFilePath);
				        		break;
				        	}
				        	
				        } else {
				        	switch (item.getFieldName()) {
				        	case RequestAndSessionAttributes.NAME:
				        		name = item.getString(UTF_8);
				        		break;
				        	case RequestAndSessionAttributes.YEAR:
				        		year = item.getString();
				        		break;
				        	case RequestAndSessionAttributes.DIRECTOR:
				        		director = item.getString(UTF_8);
				        		break;
				        	case RequestAndSessionAttributes.CAST:
				        		cast = item.getString(UTF_8);
				        		break;
				        	case RequestAndSessionAttributes.COUNTRY:
				        		countries.add(item.getString());
				        		break;
				        	case RequestAndSessionAttributes.COMPOSER:
				        		composer = item.getString();
				        		break;
				        	case RequestAndSessionAttributes.GENRE:
				        		genres.add(item.getString());
				        		break;
				        	case RequestAndSessionAttributes.LENGTH:
				        		length = item.getString();
				        		break;
				        	case RequestAndSessionAttributes.PRICE:
				        		price = item.getString();
				        		break;
				        	case RequestAndSessionAttributes.DESCRIPTION:
				        		description = item.getString(UTF_8);
				        		break;
				        	}
				        }
				    }
				}
				if (!countries.isEmpty()) {
					countriesArray = new String[countries.size()];
					countriesArray = countries.toArray(countriesArray);
				}
			    if (!genres.isEmpty()) {
			    	genresArray = new String[genres.size()];
				    genresArray = genres.toArray(genresArray);
			    }
			    
			    
				IFilmService filmService = ServiceFactory.getInstance().getFilmService();
				int filmID = filmService.addNewFilm(name, year, director, cast, countriesArray, composer, 
						genresArray, length, price, description);
				
				new File(UPLOAD_FOLDER + filmID).mkdir();
				if (folder != null) {
					try {
						folderItem.write(folder);
					    Files.move(folder.toPath(), new File(UPLOAD_FOLDER + filmID + "/" + File.separator + FOLDER_FILE_NAME).toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
					} catch (IOException e) {
						logger.error(String.format(LogMessages.EXCEPTION_IN_COMMAND, e.getClass().getSimpleName(), this.getClass().getSimpleName(), e.getMessage()));
						request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
					}
				}
				if (frame != null) {
					try {
						frameItem.write(frame);
					    Files.move(frame.toPath(), new File(UPLOAD_FOLDER + filmID + "/" + File.separator + FRAME_FILE_NAME).toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
					} catch (IOException e) {
						logger.error(String.format(LogMessages.EXCEPTION_IN_COMMAND, e.getClass().getSimpleName(), this.getClass().getSimpleName(), e.getMessage()));
						request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
					}
				}
				
				logger.debug(String.format(LogMessages.FILM_ADDED, name, filmID));
				request.setAttribute(RequestAndSessionAttributes.SUCCESS_MESSAGE, SuccessMessages.FILM_ADDED);
				request.getRequestDispatcher("/Controller?command=open_single_film&filmID=" + filmID).forward(request, response);
			} catch (AddFilmServiceException e) {
				logger.error(String.format(LogMessages.EXCEPTION_IN_COMMAND, e.getClass().getSimpleName(), this.getClass().getSimpleName(), e.getMessage()));
				request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
				request.getRequestDispatcher(JavaServerPageNames.FILM_ADDING_PAGE).forward(request, response);
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
