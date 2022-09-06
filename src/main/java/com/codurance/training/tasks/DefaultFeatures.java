package com.codurance.training.tasks;


public interface DefaultFeatures {
    void add(String command);
    void delete(String id);
    void help();
    void error(String command);
    void viewTasks(String command);
    void viewTasksDueToday();
    void check(String id);
    void uncheck(String id);
    void addDeadline(String command);
}
