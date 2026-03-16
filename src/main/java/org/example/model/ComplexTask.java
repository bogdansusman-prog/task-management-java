package org.example.model;

import java.util.ArrayList;
import java.util.List;

public final class ComplexTask extends Task{
    private List<Task> tasks;

    public ComplexTask() {
        this.tasks = new ArrayList<>();
    }

    public ComplexTask(int idTask, String statusTask) {
        super(idTask, statusTask);
        this.tasks = new ArrayList<>();
    }



    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }


    @Override
    public int estimateDuration() {
        int total = 0;
        for (Task task : tasks) {
            total += task.estimateDuration();
        }
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
