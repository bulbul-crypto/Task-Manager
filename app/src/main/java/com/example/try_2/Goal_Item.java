package com.example.try_2;

public class Goal_Item {
    private int id, status;
    private String task;

    public int getId() {
        return id;
    }

    public Goal_Item(int id,int status,String task)
    {
        this.id = id;
        this.status = status;
        this.task = task;
    }
    public Goal_Item()
    {

    }



    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTask() {
        return task;
    }
    public void setTask(String task) {
        this.task = task;
    }
}
