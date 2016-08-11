package by.epam.naumovich.film_ordering.controller;

import java.util.HashMap;
import java.util.Map;

import by.epam.naumovich.film_ordering.command.Command;
import by.epam.naumovich.film_ordering.command.impl.*;

public class CommandHelper {

	private static final CommandHelper instance = new CommandHelper();
	private Map<CommandName, Command> commands = new HashMap<>();
	
	public CommandHelper() {
		commands.put(CommandName.LOGIN, new Login());
		commands.put(CommandName.LOGOUT, new Logout());
		commands.put(CommandName.CHANGE_LANGUAGE, new ChangeLanguage());
		commands.put(CommandName.GET_NOVELTY, new GetNovelty());
		commands.put(CommandName.GET_SIDEBAR_NEWS, new GetSidebarNews());
		commands.put(CommandName.OPEN_FILM_PAGE, new OpenFilmPage());
		commands.put(CommandName.OPEN_FILM_LIST, new OpenFilmList());
		commands.put(CommandName.OPEN_NEWS_LIST, new OpenNewsList());
		commands.put(CommandName.OPEN_ORDER_PAGE, new OpenOrderPage());
		commands.put(CommandName.OPEN_PROFILE, new OpenProfile());
		commands.put(CommandName.OPEN_ALL_REVIEWS, new OpenAllReviews());
		commands.put(CommandName.OPEN_USER_REVIEWS, new OpenUserReviews());
		commands.put(CommandName.OPEN_ALL_ORDERS, new OpenAllOrders());
		commands.put(CommandName.OPEN_SINGLE_NEWS, new OpenSingleNews());
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
