package com.uca.entity;

public class Train {

    private int noTrain;
    private String type;

    public Train(int no, String type) {
        this.noTrain = no;
        this.type = type;
    }

    public int getNoTrain() {
        return this.noTrain;
    }

    public String getType() {
        return this.type;
    }

    public void setNoTrain(int no) {
        this.noTrain = no;
    }

    public void setType(String type) {
        this.type = type;
    }

}
