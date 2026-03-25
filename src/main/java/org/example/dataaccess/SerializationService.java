package org.example.dataaccess;

import org.example.businesslogic.TasksManagement;

import java.io.*;

public class SerializationService {
    public void save(TasksManagement tasksManagement, String fileName) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(tasksManagement);
        }
    }

    public TasksManagement load(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            return (TasksManagement) in.readObject();
        }
    }
}
