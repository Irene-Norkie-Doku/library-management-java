package com.thatgirlrene.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Loan {
    private Member member;
    private LibraryItem item;
    private LocalDate dueDate;
    private LocalDate borrowDate;

    public Loan(Member member, LibraryItem item,  LocalDate borrowDate) {
        this.member = member;
        this.item = item;
        this.borrowDate = borrowDate;
        this.dueDate = borrowDate.plusDays(14);// A loan lasts 14 days
    }

    public Member getMember() {
        return member;
    }
    public LibraryItem getItem() {
        return item;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }
    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public int getDaysLate(LocalDate returnDate) {
        if (returnDate.isAfter(dueDate)) {
            return (int) ChronoUnit.DAYS.between(dueDate, returnDate);
        }
        return 0;
    }

}
