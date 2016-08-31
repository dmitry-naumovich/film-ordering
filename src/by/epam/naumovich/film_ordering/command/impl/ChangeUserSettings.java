package by.epam.naumovich.film_ordering.command.impl;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.naumovich.film_ordering.bean.User;
import by.epam.naumovich.film_ordering.command.Command;
import by.epam.naumovich.film_ordering.command.util.JavaServerPageNames;
import by.epam.naumovich.film_ordering.command.util.QueryUtil;
import by.epam.naumovich.film_ordering.command.util.RequestAndSessionAttributes;
import by.epam.naumovich.film_ordering.service.IUserService;
import by.epam.naumovich.film_ordering.service.ServiceFactory;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;
import by.epam.naumovich.film_ordering.service.exception.WrongEmailFormException;

public class ChangeUserSettings implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(true);
		String query = QueryUtil.createHttpQueryString(request);
		session.setAttribute(RequestAndSessionAttributes.PREV_QUERY, query);
		System.out.println(query);
		
		if (session.getAttribute(RequestAndSessionAttributes.AUTHORIZED_USER) == null) {
			request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, "Sign in for changing your profile settings");
			request.getRequestDispatcher(JavaServerPageNames.LOGINATION_PAGE).forward(request, response);
		}
		else {
			int userID = (int)session.getAttribute(RequestAndSessionAttributes.USER_ID);
			String name = request.getParameter(RequestAndSessionAttributes.NAME);
			String surname = request.getParameter(RequestAndSessionAttributes.SURNAME);
			String pwd = request.getParameter(RequestAndSessionAttributes.PASSWORD);
			String pwdRepeat = request.getParameter(RequestAndSessionAttributes.PASSWORD_REPEATED);
			String bDate = request.getParameter(RequestAndSessionAttributes.BDATE);
			String sex = request.getParameter(RequestAndSessionAttributes.SEX);
			String email = request.getParameter(RequestAndSessionAttributes.EMAIL);
			String phone = request.getParameter(RequestAndSessionAttributes.PHONE);
			String about = request.getParameter(RequestAndSessionAttributes.ABOUT);
			String avatar = request.getParameter(RequestAndSessionAttributes.AVATAR);
			
			if (!pwd.equals(pwdRepeat)) {
				request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, "Passwords do not match! Please, try again.");
				request.getRequestDispatcher(JavaServerPageNames.PROFILE_SETTINGS_PAGE).forward(request, response);
			}
			User updUser = new User();
			updUser.setName(name);
			updUser.setSurname(surname);
			updUser.setPassword(pwd);
			if (bDate != null && !bDate.equals("")) { updUser.setBirthDate(Date.valueOf(bDate)); }
			if (email != null && !email.equals("")) { updUser.setEmail(email); }
			updUser.setSex(sex.charAt(0));
			if (phone != null && !phone.equals("")) { updUser.setPhone(phone); }
			if (about != null && !about.equals("")) { updUser.setAbout(about); }
			
			try {
				IUserService userService = ServiceFactory.getInstance().getUserService();
				userService.updateUser(userID, updUser);
				request.setAttribute(RequestAndSessionAttributes.SUCCESS_MESSAGE, "Your profile settings were successfully updated");
				request.getRequestDispatcher("/Controller?command=open_user_profile&userID=" + userID).forward(request, response);
				
			} catch (WrongEmailFormException e) {
				System.out.println("WrongEmailFormException occured in ChangeUserSettings Command");
				request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, "The e-mail you entered is not valid! Try again.");
				response.sendRedirect(query);
			} catch (ServiceException e) {
				request.getRequestDispatcher(JavaServerPageNames.ERROR_PAGE).forward(request, response);
			}
		}
	}

}
