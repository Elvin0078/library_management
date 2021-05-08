package com.library.management.controller;

import com.library.management.model.Book;
import com.library.management.model.Transaction;
import com.library.management.model.User;
import com.library.management.security.CustomUserDetails;
import com.library.management.service.Inter.AuthenticationFacade;
import com.library.management.service.Inter.BookService;
import com.library.management.service.Inter.TransactionService;
import com.library.management.service.Inter.UserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/api/tr")
public class TransactionController {

    public UserService userService;
    public BookService bookService;
    public TransactionService transactionService;
    public AuthenticationFacade authenticationFacade;


    @Autowired
    public TransactionController(UserService userService, BookService bookService, TransactionService transactionService, AuthenticationFacade authenticationFacade) {
        this.userService = userService;
        this.bookService = bookService;
        this.transactionService = transactionService;
        this.authenticationFacade = authenticationFacade;
    }


    @GetMapping("/getTransactions")
    ResponseEntity<?> getTransactions() {
        List trList = transactionService.getTransactionList();
        return new ResponseEntity<>(trList, HttpStatus.OK);
    }

    @GetMapping("/addTransaction")
    public ResponseEntity<?> addTransaction(@RequestParam("bookId") Long bookId) {

        CustomUserDetails customUserDetails = (CustomUserDetails) authenticationFacade.getAuthentication().getPrincipal();
        val rs = userService.getUserByLogin(customUserDetails.getUsername());

        Transaction transaction = new Transaction();
        User user = new User();
        user.setUserId(rs.getUserId());
        Book book = new Book();
        book.setId(bookId);
        transaction.setUser(user);
        transaction.setBook(book);

        boolean result = transactionService.addTransaction(transaction);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/updateTransactionStatus")
    public ResponseEntity<?> updateTransactionStatus(@RequestParam("statusId") Long statusId,
                                                     @RequestParam("trId") Long trId) {
        boolean result = transactionService.updateTransactionStatus(statusId, trId);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @GetMapping("/getPendingTransactions")
    ResponseEntity<?> getPendingTransactions() {
        List trList = transactionService.getPendingTransactionList();
        return new ResponseEntity<>(trList, HttpStatus.OK);
    }

    @GetMapping("/markTransactionDelivery")
    ResponseEntity<?> markTransactionDelivery(@RequestParam("trId") Long trId) {
        boolean result = transactionService.markTransactionDelivery(trId);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @GetMapping("/unMarkTransactionDelivery")
    ResponseEntity<?> unMarkTransactionDelivery(@RequestParam("trId") Long trId) {
        boolean result = transactionService.unMarkTransactionDelivery(trId);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @GetMapping("/getDeliveryTransaction")
    ResponseEntity<?> getDeliveryTransactions() {
        List trList = transactionService.getDeliveryTransactionList();
        return new ResponseEntity<>(trList, HttpStatus.OK);
    }

    @GetMapping("/getDeliveryTransactionByUserId")
    ResponseEntity<?> getDeliveryTransactionByUserId() {
        CustomUserDetails customUserDetails = (CustomUserDetails) authenticationFacade.getAuthentication().getPrincipal();
        val rs = userService.getUserByLogin(customUserDetails.getUsername());
        List<Transaction> trList = transactionService.getDeliveryTransactionByUserId(rs.getUserId());
        return new ResponseEntity<>(trList, HttpStatus.OK);
    }

    @GetMapping("/getPendingTransactionByUserId")
    ResponseEntity<?> getPendingTransactionByUserId() {
        CustomUserDetails customUserDetails = (CustomUserDetails) authenticationFacade.getAuthentication().getPrincipal();
        val rs = userService.getUserByLogin(customUserDetails.getUsername());
        List<Transaction> trList = transactionService.getPendingTransactionByUserId(rs.getUserId());
        return new ResponseEntity<>(trList, HttpStatus.OK);
    }


}



