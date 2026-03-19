package org.example.model;

import java.io.Serializable;

sealed public abstract class Task implements Serializable permits ComplexTask, SimpleTask {
    private int idTask;
    private String statusTask;

    public Task(){

    }

    public Task(int idTask, String statusTask) {
        this.idTask = idTask;
        this.statusTask = statusTask;
    }

    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    public String getStatusTask() {
        return statusTask;
    }

    public void setStatusTask(String statusTask) {
        this.statusTask = statusTask;
    }

    //clasa abstracta => metoda abastracta(o folosim in SimpleTask si in ComplexTask
    public abstract int estimateDuration();
}
