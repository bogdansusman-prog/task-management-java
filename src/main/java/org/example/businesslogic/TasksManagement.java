package org.example.businesslogic;

import org.example.model.Employee;
import org.example.model.Task;

import java.io.Serializable;
import java.io.Serial;
import java.util.*;

public class TasksManagement implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    // initializam o variabila de tip map care are in interior si o lista...
    //de ce? Pentru ca noi vrem sa vedem la angajat employee si "lista" acestuia de task-uri
    private Map<Employee, List<Task>> employeeTasks;

    public TasksManagement() {
        employeeTasks = new HashMap<>();
    }


    public Map<Employee, List<Task>> getEmployeeTasks() {
        return employeeTasks;
    }

    // adauga un angajat daca nu exista deja
    //public void addEmployee(Employee employee) {
    //    employeeTasks.putIfAbsent(employee, new ArrayList<>());
    //}
    //abordez altfel(de la mine de data asta)
    public void addEmployee(Employee employee) {
        for(Employee existingEmployee : employeeTasks.keySet()){
            if(existingEmployee.getIdEmployee() ==  employee.getIdEmployee()){
                throw new IllegalArgumentException("Employee already exists");
            }
        }
        employeeTasks.put(employee, new ArrayList<>());
    }
    //am facut altcumva decat putIfAbsent... pot explica mai logic acum ce se intampla

    public void assignTaskToEmployee(int idEmployee, Task task) {
        for(Employee employee : employeeTasks.keySet()) {
            //cautam angajatul dupa id-ul specific acestuia
            if(employee.getIdEmployee()==idEmployee) {
                //verificam daca nu are deja angajatul acel task pe care noi vrem sa i-l signam
                for(Task existingTask : employeeTasks.get(employee)) {
                    if(existingTask.getIdTask() == task.getIdTask()) {
                        throw new IllegalArgumentException("Task already already assigned to this employee");
                    }
                }
                //adaugam taskul
                employeeTasks.get(employee).add(task);
                return;
            }
        }
        throw new IllegalArgumentException("Employee not found");
    }


    //metoda folosita in Utility
    public int calculateEmployeeWorkDuration(int idEmployee){
        //caut angajatul dupa id
        for(Employee employee : employeeTasks.keySet()) {
            if(employee.getIdEmployee()==idEmployee) {
                int total = 0;
                //suma duratiilor doar pentru task-urile notate Completed
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
        //caut angajatu dupa id
        for (Employee employee : employeeTasks.keySet()) {
            if (employee.getIdEmployee() == idEmployee) {
                //caut task-urile asignate la angajatul cautat
                for (Task task : employeeTasks.get(employee)) {
                    if (task.getIdTask() == idTask) {
                        //schimb statusul
                        if ("Completed".equalsIgnoreCase(task.getStatusTask())) {
                            task.setStatusTask("Uncompleted");
                        } else if ("Uncompleted".equalsIgnoreCase(task.getStatusTask())) {
                            task.setStatusTask("Completed");
                        } else {
                            throw new IllegalArgumentException("Invalid task status:  " + task.getStatusTask());
                        }
                        return; //opresc modificarea
                    }
                }
                throw new IllegalArgumentException("Task not found");
            }
        }
        throw new IllegalArgumentException("Employee not found");
    }

}