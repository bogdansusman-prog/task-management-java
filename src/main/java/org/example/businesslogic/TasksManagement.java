package org.example.businesslogic;

import org.example.model.Employee;
import org.example.model.Task;

import java.io.Serializable;
import java.util.*;

public class TasksManagement implements Serializable {
    private Map<Employee, List<Task>> employeeTasks;

    public TasksManagement() {
        employeeTasks = new HashMap<>();
    }

    public Map<Employee, List<Task>> getEmployeeTasks() {
        return employeeTasks;
    }

    public void addEmployee(Employee employee) {
        employeeTasks.putIfAbsent(employee, new ArrayList<>());
    }

    public void assignTaskToEmployee(int idEmployee, Task task) {
        for(Employee employee : employeeTasks.keySet()) {
            if(employee.getIdEmployee()==idEmployee) {
                employeeTasks.get(employee).add(task);
                return;
            }
        }
        throw new IllegalArgumentException("Employee not found");
    }

    public int calculateEmployeeWorkDuration(int idEmployee){
        for(Employee employee : employeeTasks.keySet()) {
            if(employee.getIdEmployee()==idEmployee) {
                int total = 0;
                for(Task task : employeeTasks.get(employee)) {
                    if("Completed".equalsIgnoreCase(task.getStatusTask())){
                        total+=task.estimateDuration();
                    }
                }
                return total;
            }
        }
        throw new IllegalArgumentException("Employee not found");
    }

    public void modifyTaskStatus(int idEmployee, int idTask) {
        for (Employee employee : employeeTasks.keySet()) {
            if (employee.getIdEmployee() == idEmployee) {
                for (Task task : employeeTasks.get(employee)) {
                    if (task.getIdTask() == idTask) {
                        if ("Completed".equalsIgnoreCase(task.getStatusTask())) {
                            task.setStatusTask("Uncompleted");
                        } else {
                            task.setStatusTask("Completed");
                        }
                        return;
                    }
                }
                throw new IllegalArgumentException("Task not found");
            }
        }
        throw new IllegalArgumentException("Employee not found");
    }

}