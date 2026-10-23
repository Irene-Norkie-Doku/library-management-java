package com.thatgirlrene.model;

public class Magazine extends LibraryItem{
    public Magazine(String id, String title) {
        super(id, title);
    }
    @Override
    public double calculateLateFee(int daysLate) {
        return daysLate * 0.25;
    }
}
