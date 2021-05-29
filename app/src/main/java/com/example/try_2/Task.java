
// Task class

// In SDD, this class was mentioned



package com.example.try_2;

/*public class Task {
}*/

public class Task implements Comparable<Task> {

    private String taskName;
    private int startTime;
    private int finishTime;

    public Task() {

    }


    public Task(int startTime, String taskName, int finishTime) {

        this.startTime = startTime;
        this.taskName = taskName;
        this.finishTime = finishTime;

    }

    public String getTaskName() {

        return this.taskName;
    }
    public void setTaskName(String taskName) {

        this.taskName = taskName;
    }
    public int getStartTime() {

        return this.startTime;
    }
    public void setStartTime(int startTime) {

        this.startTime = startTime;
    }
    public int getFinishTime() {

        return this.finishTime;
    }
    public void setFinishTime(int finishTime) {

        this.finishTime = finishTime;
    }


    @Override
    public int compareTo(Task comparestu) {

        int compareTask=((Task)comparestu).getFinishTime();

        return this.finishTime-compareTask;

        /* For Descending order do like this */
        //return compareage-this.studentage;
    }


    @Override
    public String toString() {
        return "[ Start Time = " + this.startTime + ", Task name = " + this.taskName + " , Finish Time = " + this.finishTime + "]";
    }


}
