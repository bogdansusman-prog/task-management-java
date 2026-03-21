package org.example;

import org.example.businesslogic.TasksManagement;
import org.example.gui.MainFrame;

public class Main{
    public static void main(String[] args) {
        TasksManagement tasksManagement = new TasksManagement();
        MainFrame frame = new MainFrame(tasksManagement);
        frame.setVisible(true);
    }
}