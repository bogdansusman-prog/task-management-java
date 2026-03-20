package org.example.businesslogic;

import org.example.model.Employee;
import org.example.model.Task;

import java.util.*;

public class Utility {
    public static List<Employee> filterEmployeesWorkedMoreThan40Hours(TasksManagement tasksManagement) {
        //List<Employee> result = new ArrayList<>();
        //lista pentru a da store la perechile (Employee, totalWorkedHours)
        List<Map.Entry<Employee, Integer>> workedTime = new ArrayList<>();

        for(Map.Entry<Employee, List<Task>> entry : tasksManagement.getEmployeeTasks().entrySet()){
            //int total = 0;
//            for(Task task : entry.getValue()){
//                if("Completed".equalsIgnoreCase(task.getStatusTask())){
//                    total+=task.estimateDuration();
//                }
//            }
            // ai deja o metoda care face asta

            //am schimbat acum:
            int total = tasksManagement.calculateEmployeeWorkDuration(entry.getKey().getIdEmployee());
            //filtrez employees care au mai mult de 40 de ore lucrate
            if(total>40){
                workedTime.add(new AbstractMap.SimpleEntry<>(entry.getKey(), total));
            }
        }
        //sortez employees crescator avand in vedere workedTime( sau hours ca idee)
        workedTime.sort(Comparator.comparingInt(Map.Entry::getValue));
        //FAC O CONVERSIE LA LISTA CU EMPLOYEE
        List<Employee> result = new ArrayList<>();
        for(Map.Entry<Employee, Integer> entry : workedTime){
            result.add(entry.getKey());
        }
        return result;
    }

    public static Map<String, Map<String, Integer>> createTaskStatistics(TasksManagement tasksManagement) {
        //Key = numele employee, Value = map pentru numararea statusurilor
        Map<String, Map<String, Integer>> statistics = new HashMap<>();
        for(Map.Entry<Employee, List<Task>> entry : tasksManagement.getEmployeeTasks().entrySet()){
            int completed = 0;
            int uncompleted = 0;
            for(Task task : entry.getValue()){
                //numar taskurile pe baza statusului acestora
                if("Completed".equalsIgnoreCase(task.getStatusTask())){
                    completed++;
                }
                else if("Uncompleted".equalsIgnoreCase(task.getStatusTask())){
                    uncompleted++;
                }
                else {
                    //profu a zis la lab sa avem si o parte de default, eu am zis ca fac mai bine cu
                    //exceptie pentru a arata mai dragut in interiorul interfetei
                    throw new IllegalArgumentException("Invalid task status: " + task.getStatusTask());
                }
            }

            //dau store la "counts for current employee"
            Map<String, Integer> statusMap = new HashMap<>();
            statusMap.put("Completed", completed);
            statusMap.put("Uncompleted", uncompleted);

            //folosesc numele ca si Key(asa se cere)
            statistics.put(entry.getKey().getName(), statusMap);
        }
        return statistics;
    }
}
