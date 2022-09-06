package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class TaskFeaturesImpl implements TaskFeatures {

    public final PrintWriter out = new PrintWriter(System.out);
    @Override
    public TaskEntity getTaskUsingId(List<ProjectEntity> projects, String taskId) {
        int id = Integer.parseInt(taskId);
        for(ProjectEntity project: projects){
            for(TaskEntity task: project.getTaskList()){
                if (task.getId() == id) {
                    return task;
                }
            }
        }
        return null;
    }

    @Override
    public void deleteTaskUsingId(List<ProjectEntity> projects, String taskId) {
        TaskEntity task = null;
        int id = Integer.parseInt(taskId);
        for(ProjectEntity project: projects){
            List<TaskEntity> projectTaskList = project.getTaskList();
            for(TaskEntity tempTask: projectTaskList){
                if (tempTask.getId() == id) {
                    task = tempTask;
                    break;
                }
            }
            if(task != null){
                System.out.println("Deleted task with ID: " + task.getId());
                projectTaskList.remove(task);
            }
            project.setTaskList(projectTaskList);
        }
    }

    @Override
    public TaskEntity createTask(long taskId, String taskDescription, boolean status) {
        return new TaskEntity(taskId, taskDescription, status);
    }

    @Override
    public void updateTask(TaskEntity task, boolean done){
        if(task!=null){
            task.setDone(done);
        }
        else {
            out.printf("Could not find a task with ID:  %d.", task.getId());
            out.println();
        }
    }

    @Override
    public void showTasksByDate(List<ProjectEntity> projects){
        for(ProjectEntity project: projects){
            System.out.println(project.getName());
            for (TaskEntity task : project.getTaskList()) {
                System.out.printf("    [%c] %d: %s %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription(), task.getDeadline());
            }
            System.out.println();
        }
    }

    @Override
    public void showTasksByDeadline(List<ProjectEntity> projects){
        for(ProjectEntity project: projects){
            System.out.println(project.getName());
            List<TaskEntity> sortedTasks = project.getTaskList();
            sortedTasks.sort(Comparator.comparing(TaskEntity::getDeadline));
            for (TaskEntity task : sortedTasks) {
                System.out.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }
            System.out.println();
        }
    }

    @Override
    public void showTasksDueToday(List<ProjectEntity> projects) {
        for(ProjectEntity project: projects){
            System.out.println(project.getName());
            for (TaskEntity task : project.getTaskList()) {
                if(task.getDeadline().isEqual(LocalDate.now())) {
                    System.out.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
                }
            }
            System.out.println();
        }
    }
}
