package org.example.businesslogic;

import org.example.model.Employee;
import org.example.model.Task;

import java.util.*;

public class Utility {
    public static List<Employee> filterEmployeesWorkedMoreThan40Hours(TasksManagement tasksManagement) {
        List<Employee> result = new ArrayList<>();
        List<Map.Entry<Employee, Integer>> workedTime = new ArrayList<>();

        for(Map.Entry<Employee, List<Task>> entry : tasksManagement.getEmployeeTasks().entrySet()){
            int total = 0;
            for(Task task : entry.getValue()){
                if("Completed".equalsIgnoreCase(task.getStatusTask())){
                    total+=task.estimateDuration();
                }
            }

            if(total>40){
                workedTime.add(new AbstractMap.SimpleEntry<>(entry.getKey(), total));
            }
        }
        workedTime.sort(Comparator.comparingInt(Map.Entry::getValue));
        for(Map.Entry<Employee, Integer> entry : workedTime){
            result.add(entry.getKey());
        }
        return result;
    }

    public static Map<Employee, Map<String, Integer>> createTaskStatistics(TasksManagement tasksManagement) {
        Map<Employee, Map<String, Integer>> statistics = new HashMap<>();
        for(Map.Entry<Employee, List<Task>> entry : tasksManagement.getEmployeeTasks().entrySet()){
            int completed = 0;
            int uncompleted = 0;
            for(Task task : entry.getValue()){
                if("Completed".equalsIgnoreCase(task.getStatusTask())){
                    completed++;
                }
                else{
                    uncompleted++;
                }
            }

            Map<String, Integer> statusMap = new HashMap<>();
            statusMap.put("Completed", completed);
            statusMap.put("Uncompleted", uncompleted);

            statistics.put(entry.getKey(), statusMap);
        }
        return statistics;
    }
}
