package by.epam.naumovich.film_ordering.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.naumovich.film_ordering.bean.User;
import by.epam.naumovich.film_ordering.command.Command;
import by.epam.naumovich.film_ordering.command.util.JavaServerPageNames;
import by.epam.naumovich.film_ordering.command.util.RequestAndSessionAttributes;
import by.epam.naumovich.film_ordering.service.IUserService;
import by.epam.naumovich.film_ordering.service.ServiceFactory;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;
import by.epam.naumovich.film_ordering.service.exception.user.ServiceSignUpException;

public class SignUp implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(true);
		/*String query = QueryUtil.createHttpQueryString(request);
		session.setAttribute(RequestAndSessionAttributes.PREV_QUERY, query);
		System.out.println(query);*/
		
		if (session.getAttribute(RequestAndSessionAttributes.AUTHORIZED_USER) != null) {
			int userID = Integer.parseInt(session.getAttribute(RequestAndSessionAttributes.USER_ID).toString());
			request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, "Log out to be able to sign up!");
			request.getRequestDispatcher("/Controller?command=open_user_profile&userID=" + userID).forward(request, response);
		} else {
			String login = request.getParameter(RequestAndSessionAttributes.LOGIN);
			String name = request.getParameter(RequestAndSessionAttributes.NAME);
			String surname = request.getParameter(RequestAndSessionAttributes.SURNAME);
			String pwd = request.getParameter(RequestAndSessionAttributes.PASSWORD);
			String sex = request.getParameter(RequestAndSessionAttributes.SEX);
			String bDate = request.getParameter(RequestAndSessionAttributes.BDATE);
			String phone = request.getParameter(RequestAndSessionAttributes.PHONE);
			String email = request.getParameter(RequestAndSessionAttributes.EMAIL);
			String about = request.getParameter(RequestAndSessionAttributes.ABOUT);
			String avatar = request.getParameter(RequestAndSessionAttributes.AVATAR);
			
			int userID = 0;
			try {
				IUserService userService = ServiceFactory.getInstance().getUserService();
				userID = userService.addUser(login, name, surname, pwd, sex, bDate, phone, email, about, avatar);
				
				User user = userService.getUserByLogin(login);
				session.setAttribute(RequestAndSessionAttributes.AUTHORIZED_USER, login);
				session.setAttribute(RequestAndSessionAttributes.USER_ID, user.getId());
				session.setAttribute(RequestAndSessionAttributes.IS_ADMIN, 'a' == user.getType());
				
				request.setAttribute(RequestAndSessionAttributes.SUCCESS_MESSAGE, "Registration successfull! Welcome! ");
				request.getRequestDispatcher("/Controller?command=open_user_profile&userID=" + userID).forward(request, response);
				
			} catch (ServiceSignUpException e) {
				System.out.println("SignUpServiceException occured in SignUp Command");
				request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
				request.getRequestDispatcher("/Controller?command=open_sign_up_page").forward(request, response);
			} catch (ServiceException e) {
				System.out.println("ServiceException occured in SignUp Command");
				request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
				request.getRequestDispatcher(JavaServerPageNames.ERROR_PAGE).forward(request, response);
			}
		}

	}

}
