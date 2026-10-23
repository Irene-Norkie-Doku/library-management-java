package com.thatgirlrene.model;

public class Staff extends Member{
    public Staff(String name){
        super(name);
    }
    @Override
    public int getMaxLoans() {
        return 4;
    }
}
