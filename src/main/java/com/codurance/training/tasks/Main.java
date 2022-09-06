package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        out.println("Welcome to JIRA.cmd!\n" +
                "Enter \"help\" to view available actions");

        DefaultTaskList defaultTaskList = new TaskList(in,out);
        defaultTaskList.run();

    }
}



// Single Responsibility : TaskList.execute(), DefaultFeaturesImpl.help()

// Open-Closed : ProjectFeaturesImpl implements ProjectFeatures interface

// Liskov's : Main class

// Interface Segregation : ProjectFeatures interface has only related interfaces,
// and doesn't force the developer to implement any features that he/she doesn't want to.

// Dependency Inversion : DefaultFeaturesImpl