package by.epam.naumovich.film_ordering.command.impl.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.naumovich.film_ordering.command.Command;
import by.epam.naumovich.film_ordering.command.util.ErrorMessages;
import by.epam.naumovich.film_ordering.command.util.JavaServerPageNames;
import by.epam.naumovich.film_ordering.command.util.QueryUtil;
import by.epam.naumovich.film_ordering.command.util.RequestAndSessionAttributes;
import by.epam.naumovich.film_ordering.command.util.SuccessMessages;
import by.epam.naumovich.film_ordering.service.IUserService;
import by.epam.naumovich.film_ordering.service.ServiceFactory;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;
import by.epam.naumovich.film_ordering.service.exception.user.UserUpdateServiceException;

public class ChangeUserSettings implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(true);
		String query = QueryUtil.createHttpQueryString(request);
		session.setAttribute(RequestAndSessionAttributes.PREV_QUERY, query);
		System.out.println(query);
		
		if (session.getAttribute(RequestAndSessionAttributes.AUTHORIZED_USER) == null) {
			request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, ErrorMessages.SIGN_IN_FOR_SETTINGS);
			request.getRequestDispatcher(JavaServerPageNames.LOGINATION_PAGE).forward(request, response);
		}
		else {
			int userID = (int)session.getAttribute(RequestAndSessionAttributes.USER_ID);
			String name = request.getParameter(RequestAndSessionAttributes.NAME);
			String surname = request.getParameter(RequestAndSessionAttributes.SURNAME);
			String pwd = request.getParameter(RequestAndSessionAttributes.PASSWORD);
			String sex = request.getParameter(RequestAndSessionAttributes.SEX);
			String bDate = request.getParameter(RequestAndSessionAttributes.BDATE);
			String phone = request.getParameter(RequestAndSessionAttributes.PHONE);
			String email = request.getParameter(RequestAndSessionAttributes.EMAIL);
			String about = request.getParameter(RequestAndSessionAttributes.ABOUT);
			String avatar = request.getParameter(RequestAndSessionAttributes.AVATAR);
			try {
				IUserService userService = ServiceFactory.getInstance().getUserService();
				userService.updateUser(userID, name, surname, pwd, sex, bDate, phone, email, about, avatar);
				request.setAttribute(RequestAndSessionAttributes.SUCCESS_MESSAGE, SuccessMessages.SETTINGS_UPDATED);
				request.getRequestDispatcher("/Controller?command=open_user_profile&userID=" + userID).forward(request, response);
				
			} catch (UserUpdateServiceException e) {
				request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
				request.getRequestDispatcher("/Controller?command=open_user_settings&userID=" + userID).forward(request, response);
			} catch (ServiceException e) {
				request.getRequestDispatcher(JavaServerPageNames.ERROR_PAGE).forward(request, response);
			}
		}
	}

}
