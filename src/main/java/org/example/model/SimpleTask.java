package org.example.model;

//clasa finala deoarece Task este sealed
public final class SimpleTask extends Task {

    private int startHour;
    private int endHour;

    public SimpleTask() {
    }

    public SimpleTask(int idTask, String statusTask, int startHour,int endHour) {
        super(idTask, statusTask);
        if(startHour < 0 || startHour > 23){
            throw new IllegalArgumentException("Invalid start hour");
        }
        if(endHour < 0 || endHour > 23){
            throw new IllegalArgumentException("Invalid end hour");
        }
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        if(startHour < 0 || startHour > 23){
            throw new IllegalArgumentException("Invalid start hour");
        }
        this.startHour = startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        if(endHour < 0 || endHour > 23){
            throw new IllegalArgumentException("Invalid end hour");
        }
        this.endHour = endHour;
    }

    @Override
    public int estimateDuration() {
        // a task can start today and end tomorrow. eg start = 23, end = 2 => total time = 3
        if(endHour>=startHour){
            return endHour-startHour;
        }
        else {
            //adaugat!
            return (24-startHour)+endHour;
        }
    }

    @Override
    public String toString() {
        return "SimpleTask{" +
                "idTask=" + getIdTask() +
                ", statusTask='" + getStatusTask() + '\'' +
                ", startHour=" + startHour +
                ", endHour=" + endHour +
                ", duration=" + estimateDuration() +
                '}';
    }
}
