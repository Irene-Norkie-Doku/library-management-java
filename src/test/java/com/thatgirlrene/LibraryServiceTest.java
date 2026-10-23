package com.thatgirlrene;

import com.thatgirlrene.exception.ItemNotAvailableException;
import com.thatgirlrene.exception.BorrowLimitExceededException;
import com.thatgirlrene.model.Book;
import com.thatgirlrene.model.Magazine;
import com.thatgirlrene.model.Student;
import com.thatgirlrene.model.Staff;

import com.thatgirlrene.service.LibraryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

 class LibraryServiceTest {

     private Book book1;
     private Book book2;
     private Book book3;
     private Book book4;
     private Magazine magazine;
     private LibraryService service;

     //Runs before every test starts, so every test starts with a new object
     @BeforeEach
     void setUp() {
         book1 = new Book("B1", "Java Basics");
         book2 = new Book("B2", "Cybersecurity Fundamentals");
         book3 = new Book("B3", "Data Structures");
         book4 = new Book("B4", "Algorithms");
         magazine = new Magazine("M1", "Tech Weekly");
         service = new LibraryService(List.of(book1, book2, book3, book4));
     }

     //Normal Situations Case Test
     @Test
     void shouldBorrowAvailableItem() throws Exception {
         Student student = new Student("Irem Mika");
         service.borrowItem(student, book1);

         assertFalse(book1.isAvailable());
         assertEquals(1, student.getBorrowedItems().size());
     }

     @Test
     void shouldMakeItemAvailableAgainAfterReturn() throws Exception {
         Student student = new Student("Irem Mika");
         service.borrowItem(student, book1);
         service.returnItem(student, book1, 0);

         assertTrue(book1.isAvailable());
         assertEquals(0, student.getBorrowedItems().size());
     }

     @Test
     void shouldCalculateLateFeeForBook() throws Exception {
         Student student = new Student("Irem Mika");
         service.borrowItem(student, book1);

         double fee = service.returnItem(student, book1, 4);
         assertEquals(2.0, fee);
     }

     @Test
     void shouldCalculateLateFeesForMagazine() throws Exception {
         Student student = new Student("Irem Mika");
         service.borrowItem(student, magazine);
         double fee = service.returnItem(student, magazine, 4);

         assertEquals(1.0, fee);
     }

     // Edge Cases
     @Test
     void shouldChargeNoFeesWhenReturnedOnTime() throws Exception {
         Student student = new Student("Irem Mika");
         service.borrowItem(student, book1);
         double fee = service.returnItem(student, book1, 0);
         assertEquals(0, fee);
     }

     @Test
     void shouldAllowStudentExactlyThree() throws Exception {
         Student student = new Student("Irem Mika");
         service.borrowItem(student, book1);
         service.borrowItem(student, book2);
         service.borrowItem(student, book3);
         assertEquals(3, student.getBorrowedItems().size());
     }

     @Test
     void shouldAllowStaffBorrowMoreThanThree() throws Exception {
         Staff staff = new Staff("Mr Zyan Felix");
         service.borrowItem(staff, book1);
         service.borrowItem(staff, book2);
         service.borrowItem(staff, book3);
         service.borrowItem(staff, book4);
         assertEquals(4, staff.getBorrowedItems().size());
     }

     //Invalid Situations
     @Test
     void shouldThrowWhenItemIsAlreadyBorrowed() throws Exception {
         Student student1 = new Student("Irem Mika");
         Student student2 = new Student("Kofi Banks");
         service.borrowItem(student1, book1);

         assertThrows(ItemNotAvailableException.class, () -> service.borrowItem(student2, book1));

     }

     @Test
     void shouldThrowWhenStudentExceedsBorrowingLimit() throws Exception {
         Student student = new Student("Irem Mika");
         service.borrowItem(student, book1);
         service.borrowItem(student, book2);
         service.borrowItem(student, book3);

         assertThrows(BorrowLimitExceededException.class, () -> service.borrowItem(student, book4));
     }

     @Test
     void shouldNotChangeItemStatusWhenItemBorrowFails() throws Exception {
         Student student = new Student("Irem Mika");
         service.borrowItem(student, book1);
         service.borrowItem(student, book2);
         service.borrowItem(student, book3);

         //only check what happened to the book-->expected
         try {
             service.borrowItem(student, book4);
         } catch (BorrowLimitExceededException e) {
         }

         assertTrue(book4.isAvailable());
     }
 }


