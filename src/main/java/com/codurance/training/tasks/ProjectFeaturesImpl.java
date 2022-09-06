package com.codurance.training.tasks;

import java.util.List;

public class ProjectFeaturesImpl implements ProjectFeatures {
    @Override
    public ProjectEntity createProject(int projectId, String projectName) {
        return new ProjectEntity(projectId, projectName);
    }

    @Override
    public List<TaskEntity> getTaskListUsingProjectName(List<ProjectEntity> projects, String projectName) {
        ProjectEntity tempProject = projects.stream().filter(project -> project.getName().equals(projectName)).findAny().orElse(null);
        if(tempProject != null){
            return tempProject.getTaskList();
        }
        else {
            throw new NullPointerException("Project does not exist");
        }
    }

    @Override
    public void viewProjects(List<ProjectEntity> projects) {
        for(ProjectEntity project: projects){
            System.out.println(project.getName());
            for (TaskEntity task : project.getTaskList()) {
                System.out.printf("    [%c] %d: %s %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription(), task.getDeadline());
            }
            System.out.println();
        }
    }
}
