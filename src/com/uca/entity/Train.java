package com.uca.entity;

public class Train {

    private int no;
    private String type;

    public Train(int no, String type) {
        this.no = no;
        this.type = type;
    }

    public int getNo() {
        return this.no;
    }

    public String getType() {
        return this.type;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Train{" + "no=" + no + ", type=" + type + '}';
    }
}
