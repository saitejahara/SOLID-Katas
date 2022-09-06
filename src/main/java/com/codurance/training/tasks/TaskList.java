package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TaskList extends  DefaultTaskList implements Runnable {
    private static final String QUIT = "quit";
    private static final String EXIT = "exit";
    private final List<ProjectEntity> projects = new ArrayList<>();
    private final DefaultFeatures driver = new DefaultFeaturesImpl(projects);
    private final BufferedReader in;
    private final PrintWriter out;

    public TaskList(BufferedReader reader, PrintWriter writer) {
        this.in = reader;
        this.out = writer;
    }
    @Override
    public void run() {
        while (true) {
            out.print("admin@ZeMoSo:~$ ");
            out.flush();
            String command;
            try {
                command = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (command.equals(QUIT) || command.equals(EXIT)) {
                break;
            }
            execute(command);
        }
    }

    public void execute(String commandLine) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];

            switch (command) {

                case "view":
                    driver.viewTasks(commandRest[1]);
                    break;

                case "add":
                    driver.add(commandRest[1]);
                    break;

                case "check":
                    driver.check(commandRest[1]);
                    break;

                case "uncheck":
                    driver.uncheck(commandRest[1]);
                    break;

                case "help":
                    driver.help();
                    break;

                case "deadline":
                    driver.addDeadline(commandRest[1]);
                    break;

                case "delete":
                    driver.delete(commandRest[1]);
                    break;

                case "today":
                    driver.viewTasksDueToday();
                    break;

                default:
                    driver.error(command);
                    break;
            }
    }
}
