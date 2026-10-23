package com.thatgirlrene.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Member {
    private String name;
    private List<LibraryItem> borrowedItems=new ArrayList<>();

    public Member(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public List<LibraryItem> getBorrowedItems() {
        return borrowedItems;
    }
    public abstract int getMaxLoans();
}
