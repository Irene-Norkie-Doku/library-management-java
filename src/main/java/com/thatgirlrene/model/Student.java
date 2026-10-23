package com.thatgirlrene.model;

public class Student extends Member{
    public Student(String name){
        super(name);
    }
    @Override
    public int getMaxLoans() {
        return 3;
    }
}
