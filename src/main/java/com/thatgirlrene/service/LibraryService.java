package com.thatgirlrene.service;

import java.time.LocalDate;
import java.util.ArrayList;

import com.thatgirlrene.exception.BorrowLimitExceededException;
import com.thatgirlrene.exception.ItemNotAvailableException;
import com.thatgirlrene.model.LibraryItem;
import com.thatgirlrene.model.Loan;
import com.thatgirlrene.model.Member;

import java.util.List;

public class LibraryService {
    private List<LibraryItem> items;

    public LibraryService(List<LibraryItem> items) {
        this.items = items;
    }
    private List<Loan>loans=new ArrayList<>();


    public void borrowItem(Member member, LibraryItem item)
            throws ItemNotAvailableException, BorrowLimitExceededException {
        if (!item.isAvailable()) {
            throw new ItemNotAvailableException(item.getTitle() + "is already borrowed");
        }
        if (member.getBorrowedItems().size() >= member.getMaxLoans()) {
            throw new BorrowLimitExceededException(member.getName() + "you have reached your limit");
        }
        item.setAvailable(false);
        member.getBorrowedItems().add(item);
        loans.add(new Loan(member,item,LocalDate.now()));
    }

    public double returnItem(Member member, LibraryItem item, int daysLate) {
        member.getBorrowedItems().remove(item);
        item.setAvailable(true);
        if (daysLate > 0) {
            return item.calculateLateFee(daysLate);
        }
        return 0;
    }

    public double returnItemOnDate(Member member, LibraryItem item, LocalDate returnDate) {
        for (Loan loan : loans) {
            if (loan.getItem() == item && loan.getMember() == member) {
                int daysLate = loan.getDaysLate(returnDate);
                loans.remove(loan);
                return returnItem(member, item, daysLate);
            }
        }
        throw new IllegalArgumentException(member.getName() + " has not borrowed " + item.getTitle());
    }

    public LibraryItem findItemById(String id) {
        for (LibraryItem item : items) {
            if (item.getId().equals(id)) {
                return item;
            }
        }

        return null; // returns no item with this id
    }
}

