package by.epam.naumovich.film_ordering.command.impl.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.naumovich.film_ordering.command.Command;
import by.epam.naumovich.film_ordering.command.util.ErrorMessages;
import by.epam.naumovich.film_ordering.command.util.JavaServerPageNames;
import by.epam.naumovich.film_ordering.command.util.RequestAndSessionAttributes;

public class EditDiscount implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(true);
		int discountID = Integer.parseInt(request.getParameter(RequestAndSessionAttributes.DISCOUNT_ID));
		
		if (session.getAttribute(RequestAndSessionAttributes.AUTHORIZED_USER) == null) {
			request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, ErrorMessages.EDIT_DISCOUNT_RESTRICTION);
			request.getRequestDispatcher(JavaServerPageNames.LOGINATION_PAGE).forward(request, response);
		}
		else if (!Boolean.parseBoolean(session.getAttribute(RequestAndSessionAttributes.IS_ADMIN).toString())) {
			request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, ErrorMessages.EDIT_DISCOUNT_RESTRICTION);
			request.getRequestDispatcher("/Controller?command=open_user_profile&userID=" + userID).forward(request, response);
		}
		else {
		
		}
	}

}
