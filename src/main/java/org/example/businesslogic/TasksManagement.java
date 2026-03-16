package org.example.businesslogic;

import org.example.model.Employee;
import org.example.model.Task;

import java.util.*;

public class TasksManagement {
    private Map<Employee, List<Task>> employeeTasks;

    public TasksManagement() {
        employeeTasks = new HashMap<>();
    }

    public void addEmployee(Employee employee) {

    }

    public void assignTaskToEmployee(int idEmployee, Task task) {

    }

    public int calculateEmployeeWorkDuration(int idEmployee) {
        return 0;
    }

    public void modifyTaskStatus(int idEmployee, int idTask) {

    }
}