package com.thatgirlrene;


import com.thatgirlrene.exception.BorrowLimitExceededException;
import com.thatgirlrene.exception.ItemNotAvailableException;
import com.thatgirlrene.model.LibraryItem;
import com.thatgirlrene.utils.CsvItemLoader;
import com.thatgirlrene.utils.ItemLoader;
import com.thatgirlrene.model.Member;
import com.thatgirlrene.model.Student;
import com.thatgirlrene.service.LibraryService;

import java.io.IOException;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        ItemLoader loader = new CsvItemLoader();

        try {
            List<LibraryItem> items = loader.loadItems("src/main/resources/items.csv");
            System.out.println("Loaded " + items.size() + " items:");

            LibraryService libraryService = new LibraryService(items);
            Student irem = new Student("Irem Mika");
            Student kofi = new Student("Kofi Banks");

            //Rene borrows 3 items(Her limit). the 4th must fail
            borrow(libraryService, irem, "B1");
            borrow(libraryService, irem, "B2");
            borrow(libraryService, irem, "B3");
            borrow(libraryService, irem, "B4");

            //Kofi tries borrowing an item that has already been borrowed
            borrow(libraryService, kofi, "B1");

            //Using an id that is not in the file
            borrow(libraryService, irem, "X9");

            //Returning 2 books; one on time and one late
            giveBack(libraryService, irem, "B1", 4);
            giveBack(libraryService, irem, "B2", 0);

        } catch (IOException e) {
            System.out.println("Could not read this file:" + e.getMessage());
        }
    }

      private static void borrow(LibraryService service,Member member, String itemId) {
        LibraryItem item = service.findItemById(itemId);
        if (item == null) {
            System.out.println("Could not find item with id:" + itemId);
            return;
        }

        try {
            service.borrowItem(member, item);
            System.out.println(member.getName() + " borrowed " + item.getTitle());
        }catch (ItemNotAvailableException e){
            System.out.println("Error:" + e.getMessage());
        } catch (BorrowLimitExceededException e){
            System.out.println("Error:" + e.getMessage());
        }
      }
      private static void giveBack(LibraryService service, Member member, String itemId,int daysLate) {
        LibraryItem item = service.findItemById(itemId);
        if (item == null) {
            System.out.println("Could not find item with id:" + itemId);
            return;
        }
        double fee = service.returnItem(member,item,daysLate);
        System.out.println(member.getName() + " returned " + item.getTitle() + " with late fee " + fee);
      }

}

