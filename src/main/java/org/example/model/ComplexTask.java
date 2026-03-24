package org.example.model;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

//clasa finala deoarece Task este sealed
public final class ComplexTask extends Task{
    @Serial
    private static final long serialVersionUID = 1L;

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
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }
        if (task == this) {
            throw new IllegalArgumentException("A complex task cannot be the same or contain itself");
        }

        for(Task existingTask : tasks) {
            if (existingTask.getIdTask() == task.getIdTask()) {
                throw new IllegalArgumentException("Task already exists in complex task");
            }
        }
        tasks.add(task);
    }

    //elimin din lista Task-ul dorit
    public void deleteTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }
        Task taskToDelete = null;

        for(Task existingTask : tasks) {
            if (existingTask.getIdTask() == task.getIdTask()) {
                taskToDelete = existingTask;
                break;
            }
        }
        if(taskToDelete == null) {
            throw new IllegalArgumentException("Task does not exist in complex task");
        }
        tasks.remove(taskToDelete);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public  void setTasks(List<Task> tasks) {
        this.tasks = tasks;
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
