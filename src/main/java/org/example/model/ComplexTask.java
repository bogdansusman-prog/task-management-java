package org.example.model;

import java.util.ArrayList;
import java.util.List;

//clasa finala deoarece Task este sealed
public final class ComplexTask extends Task{
    private List<Task> tasks;

    public ComplexTask() {
        this.tasks = new ArrayList<>();
    }

    public ComplexTask(int idTask, String statusTask) {
        super(idTask, statusTask);
        this.tasks = new ArrayList<>();
    }
    //adaug in lista Task-ul dorit
    public void addTask(Task task) {
        tasks.add(task);
    }

    //elimin din lista Task-ul dorit
    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }


    @Override
    public int estimateDuration() {
        int total = 0;
        //folosim polimorfism: fiecare task isi calculeaza durata
        for (Task task : tasks) {
            total += task.estimateDuration();
        }
        //durata unui complex task este suma tuturor subtaskurilor din interiorul complexTask
        return total;
    }

    @Override
    public String toString() {
        return "ComplexTask{" +
                "idTask=" + getIdTask() +
                ", statusTask='" + getStatusTask() + '\'' +
                ", tasks=" + tasks +
                '}';
    }
}
