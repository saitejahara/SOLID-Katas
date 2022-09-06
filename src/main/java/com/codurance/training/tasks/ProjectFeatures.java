package com.codurance.training.tasks;

import java.util.List;

public interface ProjectFeatures {
    ProjectEntity createProject(int projectId, String projectName);
    List<TaskEntity> getTaskListUsingProjectName(List<ProjectEntity> projects, String projectName);
    void viewProjects(List<ProjectEntity> projects);
}
