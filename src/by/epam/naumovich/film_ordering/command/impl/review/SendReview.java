package by.epam.naumovich.film_ordering.command.impl.review;

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
import by.epam.naumovich.film_ordering.service.IReviewService;
import by.epam.naumovich.film_ordering.service.ServiceFactory;
import by.epam.naumovich.film_ordering.service.exception.ServiceException;
import by.epam.naumovich.film_ordering.service.exception.review.SendReviewServiceException;

public class SendReview implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(true);
		String query = QueryUtil.createHttpQueryString(request);
		session.setAttribute(RequestAndSessionAttributes.PREV_QUERY, query);
		System.out.println(query);
		
		if (session.getAttribute(RequestAndSessionAttributes.AUTHORIZED_USER) == null) {
			request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, ErrorMessages.SIGN_IN_FOR_REVIEWING);
			request.getRequestDispatcher(JavaServerPageNames.LOGINATION_PAGE).forward(request, response);
		}
		else {
			int userID = Integer.parseInt(request.getParameter(RequestAndSessionAttributes.USER_ID));
			int filmID = Integer.parseInt(request.getParameter(RequestAndSessionAttributes.FILM_ID));
			String mark = request.getParameter(RequestAndSessionAttributes.REVIEW_MARK);
			String type = request.getParameter(RequestAndSessionAttributes.REVIEW_TYPE);
			String text = request.getParameter(RequestAndSessionAttributes.REVIEW_TEXT);
			
			try {
				IReviewService reviewService = ServiceFactory.getInstance().getReviewService();
				reviewService.addReview(userID, filmID, mark, type, text);
				request.setAttribute(RequestAndSessionAttributes.SUCCESS_MESSAGE, SuccessMessages.REVIEW_ADDED);
				request.getRequestDispatcher("/Controller?command=open_single_review&userID=" + userID + "&filmID=" + filmID).forward(request, response);
			} catch (SendReviewServiceException e) {
				request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
				request.getRequestDispatcher("/Controller?command=open_new_review_page&filmID=" + filmID).forward(request, response);
			} catch (ServiceException e) {
				request.setAttribute(RequestAndSessionAttributes.ERROR_MESSAGE, e.getMessage());
				request.getRequestDispatcher(JavaServerPageNames.ERROR_PAGE).forward(request, response);
			}
			
			
		}
	}

}
