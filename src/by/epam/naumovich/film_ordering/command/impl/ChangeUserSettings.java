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
			String name = request.getParameter(RequestAndSessionAttributes.NAME);
			String surname = request.getParameter(RequestAndSessionAttributes.SURNAME);
			String pwd = request.getParameter(RequestAndSessionAttributes.PASSWORD);
			String pwdRepeat = request.getParameter(RequestAndSessionAttributes.PASSWORD_REPEATED);
			String bDate = request.getParameter(RequestAndSessionAttributes.BDATE);
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
			updUser.setBirthDate(Date.valueOf(bDate));
			updUser.setEmail(email);
			updUser.setPhone(phone);
			updUser.setAbout(about);
			
			System.out.println(name + surname + pwd + pwdRepeat + bDate + email + phone + about + avatar);	
		}
	}

}
