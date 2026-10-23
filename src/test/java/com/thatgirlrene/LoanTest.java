package com.thatgirlrene;

import com.thatgirlrene.model.Book;
import com.thatgirlrene.model.Loan;
import com.thatgirlrene.model.Student;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

 class LoanTest {

     @Test
     void shouldSetDueDateFourteenDaysAfterBorrowing(){
         Loan loan = new Loan(new Student("Irem Mika"),new Book("B1","Java"),LocalDate.of(2026,11,1));
         assertEquals(LocalDate.of(2026,11,15),loan.getDueDate());
     }

     @Test
     void shouldCountDaysLate(){
         Loan loan = new Loan(new Student("Irem Mika"),new Book("B1","Java"),LocalDate.of(2026,11,1));
         assertEquals(4,loan.getDaysLate(LocalDate.of(2026,11,19)));
     }

     @Test
     void shouldReturnZeroWhenReturnedOnTime(){
         Loan loan = new Loan(new Student("Irem Mika"),new Book("B1","Java"),LocalDate.of(2026,11,1));
         assertEquals(0,loan.getDaysLate(LocalDate.of(2026,11,15)));
     }
}
