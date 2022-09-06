package com.codurance.training.tasks;


import java.time.LocalDate;
import java.util.List;

public class DefaultFeaturesImpl implements DefaultFeatures {

    private final List<ProjectEntity> projectList;
    public DefaultFeaturesImpl(List<ProjectEntity> projectList) {
        this.projectList = projectList;
    }
    private final TaskFeaturesImpl taskFeaturesImpl = new TaskFeaturesImpl();

    private long lastId = 0;

    private final ProjectFeaturesImpl projectFeaturesImpl = new ProjectFeaturesImpl();

    @Override
    public void add(String command) {
        String[] subcommandRest = command.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            projectList.add(projectFeaturesImpl.createProject(projectList.size()+1, subcommandRest[1]));
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split(" ", 2);
            List<TaskEntity> taskList = projectFeaturesImpl.getTaskListUsingProjectName(projectList, projectTask[0]);
            taskList.add(taskFeaturesImpl.createTask(++lastId, projectTask[1], false));
        }
    }

    @Override
    public void viewTasks(String command){
        String[] subcommandRest = command.split(" ", 2);
        String subcommand = subcommandRest[1];
        if(subcommand.equals("date")) {
            taskFeaturesImpl.showTasksByDate(projectList);
        }
        else if(subcommand.equals("deadline")) {
            taskFeaturesImpl.showTasksByDeadline(projectList);
        }
        else if(subcommand.equals("project")){

            projectFeaturesImpl.viewProjects(projectList);
        }
        else {
            System.out.println("Option \""+subcommand+"\" not available. Sorting by project\n");
            projectFeaturesImpl.viewProjects(projectList);
        }
    }

    @Override
    public void viewTasksDueToday() {
        taskFeaturesImpl.showTasksDueToday(projectList);
    }

    @Override
    public void check(String id) {
        TaskEntity task = taskFeaturesImpl.getTaskUsingId(projectList, id);
        taskFeaturesImpl.updateTask(task, true);
    }

    @Override
    public void uncheck(String id) {
        TaskEntity task = taskFeaturesImpl.getTaskUsingId(projectList, id);
        taskFeaturesImpl.updateTask(task, false);
    }

    @Override
    public void addDeadline(String command){
        String[] subcommandRest = command.split(" ", 2);
        String id = subcommandRest[0];
        TaskEntity task = taskFeaturesImpl.getTaskUsingId(projectList, id);
        task.setDeadline(LocalDate.parse(subcommandRest[1]));
    }

    @Override
    public void delete(String id){
        taskFeaturesImpl.deleteTaskUsingId(projectList, id);
    }

    @Override
    public void help() {
        System.out.println("This is a bash tool for the GUI equivalent for the Jira® software.\nBelow are the available options under release v7.0.24 :");
        System.out.println("-> view by project");
        System.out.println("-> view by deadline");
        System.out.println("-> view with date");
        System.out.println("-> add project <project_name>");
        System.out.println("-> add task <project_name> <task_description>");
        System.out.println("-> deadline <task ID> <date>");
        System.out.println("-> check <task_ID>");
        System.out.println("-> uncheck <task_ID>");
        System.out.println();
    }

    @Override
    public void error(String command) {
        System.out.printf("I don't know what the command \"%s\" is.", command);
        System.out.println();
    }
}
