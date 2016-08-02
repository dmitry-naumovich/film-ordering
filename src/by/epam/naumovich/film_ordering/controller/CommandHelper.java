package by.epam.naumovich.film_ordering.controller;

import java.util.HashMap;
import java.util.Map;

import by.epam.naumovich.film_ordering.command.Command;
import by.epam.naumovich.film_ordering.command.impl.ChangeLanguage;
import by.epam.naumovich.film_ordering.command.impl.GetNovelty;
import by.epam.naumovich.film_ordering.command.impl.Login;
import by.epam.naumovich.film_ordering.command.impl.Logout;
import by.epam.naumovich.film_ordering.command.impl.OpenFilmList;
import by.epam.naumovich.film_ordering.command.impl.OpenFilmPage;
import by.epam.naumovich.film_ordering.command.impl.OpenOrderPage;

public class CommandHelper {

	private static final CommandHelper instance = new CommandHelper();
	private Map<CommandName, Command> commands = new HashMap<>();
	
	public CommandHelper() {
		commands.put(CommandName.LOGIN, new Login());
		commands.put(CommandName.LOGOUT, new Logout());
		commands.put(CommandName.CHANGE_LANGUAGE, new ChangeLanguage());
		commands.put(CommandName.GET_NOVELTY, new GetNovelty());
		commands.put(CommandName.OPEN_FILM_PAGE, new OpenFilmPage());
		commands.put(CommandName.OPEN_FILM_LIST, new OpenFilmList());
		commands.put(CommandName.OPEN_ORDER_PAGE, new OpenOrderPage());
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
