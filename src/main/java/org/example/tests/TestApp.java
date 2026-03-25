package org.example.tests;

import org.example.businesslogic.TasksManagement;
import org.example.businesslogic.Utility;
import org.example.dataaccess.SerializationService;
import org.example.model.ComplexTask;
import org.example.model.Employee;
import org.example.model.SimpleTask;

import java.util.List;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class TestApp {
    public static void main(String[] args) {
        TasksManagement tasksManagement = new TasksManagement();

        Employee employee1 = new Employee(1, "Bogdan");
        Employee employee2 = new Employee(2, "Mara");

        tasksManagement.addEmployee(employee1);
        tasksManagement.addEmployee(employee2);

        SimpleTask simpleTask1 = new SimpleTask(101, "Completed", 8, 16);
        //8 ore
        SimpleTask simpleTask2 = new SimpleTask(102, "Completed", 9, 17);
        //8 ore
        SimpleTask simpleTask3 = new SimpleTask(103, "Uncompleted", 10, 18);
        //8 ore

        ComplexTask complexTask1 = new ComplexTask(201, "Completed");
        complexTask1.addTask(new SimpleTask(104, "Completed", 8, 20)); // 12 ore
        complexTask1.addTask(new SimpleTask(105, "Completed", 9, 21)); // 12 ore

        tasksManagement.assignTaskToEmployee(1, simpleTask1);
        tasksManagement.assignTaskToEmployee(1, simpleTask2);
        tasksManagement.assignTaskToEmployee(1, complexTask1);

        tasksManagement.assignTaskToEmployee(2, simpleTask3);

        System.out.println("===== DURATA MUNCA =======");
        System.out.println("Employee 1 duration: " +
                tasksManagement.calculateEmployeeWorkDuration(1));
        System.out.println("Employee 2 duration " +
                tasksManagement.calculateEmployeeWorkDuration(2));

        System.out.println("\n========= EMPLOYEES > 40 HOURS =======");
        List<Employee> employeesOver40 = Utility.filterEmployeesWorkedMoreThan40Hours(tasksManagement);
        for(Employee employee : employeesOver40) {
            System.out.println(employee);
        }

        System.out.println("\n========== TASK STATISTICS ========");
        Map<String, Map<String, Integer>> statistics = Utility.createTaskStatistics(tasksManagement);

        for (Map.Entry<String, Map<String, Integer>> entry : statistics.entrySet()) {
            System.out.println("Employee: " + entry.getKey());

            for (Map.Entry<String, Integer> stat : entry.getValue().entrySet()) {
                System.out.println(stat.getKey() + ": " + stat.getValue());
            }
        }

        SerializationService service = new SerializationService();

        try{
            service.save(tasksManagement, "data.ser");

            TasksManagement loaded = service.load("data.ser");

            System.out.println("Loaded duration: " +
                    loaded.calculateEmployeeWorkDuration(1));

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
