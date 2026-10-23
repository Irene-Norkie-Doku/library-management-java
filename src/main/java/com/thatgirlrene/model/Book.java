package com.thatgirlrene.model;

public class Book extends LibraryItem{
    public Book(String id, String title) {
        super(id, title);
    }
    @Override
    public double calculateLateFee(int daysLate) {
        return daysLate * 0.50;
    }


}


