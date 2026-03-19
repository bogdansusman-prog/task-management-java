package org.example.model;

//clasa finala deoarece Task este sealed
public final class SimpleTask extends Task {

    private int startHour;
    private int endHour;

    public SimpleTask() {
    }

    public SimpleTask(int idTask, String statusTask, int startHour,int endHour) {
        super(idTask, statusTask);
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    @Override
    public int estimateDuration() {
        // a task can start today and end tomorrow. eg start = 23, end = 2 => total time = 3
        if(endHour>=startHour){
            return endHour-startHour;
        }
        else {
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
