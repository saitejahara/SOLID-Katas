package com.codurance.training.tasks;


import java.util.List;

public interface TaskFeatures {
    TaskEntity createTask(long taskId, String taskDescription, boolean status);
    TaskEntity getTaskUsingId(List<ProjectEntity> projects, String taskId);
    void showTasksByDate(List<ProjectEntity> projects);
    void showTasksByDeadline(List<ProjectEntity> projects);
    void showTasksDueToday(List<ProjectEntity> projects);
    void updateTask(TaskEntity task, boolean done);
    void deleteTaskUsingId(List<ProjectEntity> projects, String taskId);

}
