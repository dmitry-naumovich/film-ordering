package by.epam.naumovich.film_ordering.controller;

import java.util.HashMap;
import java.util.Map;

import by.epam.naumovich.film_ordering.command.Command;
import by.epam.naumovich.film_ordering.command.impl.*;
import by.epam.naumovich.film_ordering.command.impl.film.GetNovelty;
import by.epam.naumovich.film_ordering.command.impl.film.OpenFilmList;
import by.epam.naumovich.film_ordering.command.impl.film.OpenFilmPage;
import by.epam.naumovich.film_ordering.command.impl.navigation.OpenAboutUsPage;
import by.epam.naumovich.film_ordering.command.impl.navigation.OpenFeedbackPage;
import by.epam.naumovich.film_ordering.command.impl.navigation.OpenHelpPage;
import by.epam.naumovich.film_ordering.command.impl.navigation.OpenLoginationPage;
import by.epam.naumovich.film_ordering.command.impl.navigation.OpenSignUpPage;
import by.epam.naumovich.film_ordering.command.impl.navigation.OpenWidenSearchPage;
import by.epam.naumovich.film_ordering.command.impl.news.GetSidebarNews;
import by.epam.naumovich.film_ordering.command.impl.news.OpenNewsList;
import by.epam.naumovich.film_ordering.command.impl.news.OpenSingleNews;
import by.epam.naumovich.film_ordering.command.impl.order.OpenAllOrders;
import by.epam.naumovich.film_ordering.command.impl.order.OpenOrderPage;
import by.epam.naumovich.film_ordering.command.impl.order.OpenUserOrders;
import by.epam.naumovich.film_ordering.command.impl.review.OpenAllReviews;
import by.epam.naumovich.film_ordering.command.impl.review.OpenNewReviewPage;
import by.epam.naumovich.film_ordering.command.impl.review.OpenSingleReview;
import by.epam.naumovich.film_ordering.command.impl.review.OpenUserReviews;
import by.epam.naumovich.film_ordering.command.impl.review.SendReview;
import by.epam.naumovich.film_ordering.command.impl.user.ChangeUserSettings;
import by.epam.naumovich.film_ordering.command.impl.user.OpenUserProfile;
import by.epam.naumovich.film_ordering.command.impl.user.OpenUserSettings;

public class CommandHelper {

	private static final CommandHelper instance = new CommandHelper();
	private Map<CommandName, Command> commands = new HashMap<>();
	
	public CommandHelper() {
		commands.put(CommandName.CHANGE_LANGUAGE, new ChangeLanguage());
		commands.put(CommandName.CHANGE_USER_SETTINGS, new ChangeUserSettings());
		commands.put(CommandName.GET_NOVELTY, new GetNovelty());
		commands.put(CommandName.GET_SIDEBAR_NEWS, new GetSidebarNews());
		commands.put(CommandName.LOGIN, new Login());
		commands.put(CommandName.LOGOUT, new Logout());
		commands.put(CommandName.OPEN_ABOUT_US_PAGE, new OpenAboutUsPage());
		commands.put(CommandName.OPEN_ALL_ORDERS, new OpenAllOrders());
		commands.put(CommandName.OPEN_ALL_REVIEWS, new OpenAllReviews());
		commands.put(CommandName.OPEN_FEEDBACK_PAGE, new OpenFeedbackPage());
		commands.put(CommandName.OPEN_FILM_LIST, new OpenFilmList());
		commands.put(CommandName.OPEN_FILM_PAGE, new OpenFilmPage());
		commands.put(CommandName.OPEN_HELP_PAGE, new OpenHelpPage());
		commands.put(CommandName.OPEN_LOGINATION_PAGE, new OpenLoginationPage());
		commands.put(CommandName.OPEN_NEW_REVIEW_PAGE, new OpenNewReviewPage());
		commands.put(CommandName.OPEN_NEWS_LIST, new OpenNewsList());
		commands.put(CommandName.OPEN_ORDER_PAGE, new OpenOrderPage());
		commands.put(CommandName.OPEN_SIGN_UP_PAGE, new OpenSignUpPage());
		commands.put(CommandName.OPEN_SINGLE_NEWS, new OpenSingleNews());
		commands.put(CommandName.OPEN_SINGLE_REVIEW, new OpenSingleReview());
		commands.put(CommandName.OPEN_USER_PROFILE, new OpenUserProfile());		
		commands.put(CommandName.OPEN_USER_SETTINGS, new OpenUserSettings());
		commands.put(CommandName.OPEN_USER_ORDERS, new OpenUserOrders());
		commands.put(CommandName.OPEN_USER_REVIEWS, new OpenUserReviews());
		commands.put(CommandName.OPEN_WIDEN_SEARCH_PAGE, new OpenWidenSearchPage());
		commands.put(CommandName.SIGN_UP, new SignUp());
		commands.put(CommandName.SEND_REVIEW, new SendReview());
	}
	
	public Command getCommand(String name) {
		CommandName commandName = CommandName.valueOf(name);
		Command command = commands.get(commandName);
		return command;
	}
	
	public static CommandHelper getInstance() {
		return instance;
	}
}
