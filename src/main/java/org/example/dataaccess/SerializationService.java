package org.example.dataaccess;
//TOATE CLASELE CARE IMPLEMENTEAZA SERIALIZABLE TREBUIE SA AIBE LA INCEPUT:
//TasksManagement
import org.example.businesslogic.TasksManagement;

import java.io.*;

public class SerializationService {
    public void save(TasksManagement tasksManagement, String fileName) throws IOException {
        //creeam un stream catre fisier
        // inchide automat fisierul deci nu trebuie close()
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(tasksManagement);
            //scriem tot obiectul din fisier(nu doar obiectul ci si tot ce contine
            //MAP
            //EMPLOYEE
            //TASK
            //..
        }
    }

    public TasksManagement load(String fileName) throws IOException, ClassNotFoundException {
        //deschidem fisierul
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            //citim obiecul si il transformam inapoi (cu cast) in TasksManagement
            return (TasksManagement) in.readObject();
        }
    }
}
