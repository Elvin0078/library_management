package com.library.management.controller;

import com.library.management.model.Book;
import com.library.management.model.BookCategory;
import com.library.management.service.Inter.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    public BookService bookService;


    @GetMapping("/bookList")

    public ResponseEntity<?> getbookList() {
        List<Book> bookList = bookService.getAllBook();

        return new ResponseEntity<>(bookList, HttpStatus.OK);

    }


    @GetMapping("/bookCategoriList")
    public ResponseEntity<?> getBookCategoryList() {
        List<BookCategory> bookCategoryList = bookService.getBookCategories();
        return new ResponseEntity<>(bookCategoryList, HttpStatus.OK);
    }


//    @RequestMapping(value = "/bookList", method = RequestMethod.GET)
//    public ResponseEntity <?> bookList() {
//
//        List<Book> bookList = bookService.getAllBook();
//        System.out.println("----------------  "+bookList.get(0).getName());
//
//        return new ResponseEntity<>(bookList, HttpStatus.OK);
//    }


    @GetMapping("/newBook")
    @PostMapping("/newBook")
    public ResponseEntity<?> addBook(
            @RequestParam("bookName") String bookName,
            @RequestParam("language") String language,
            @RequestParam("author") String bookAuthor,
            @RequestParam("noCopiesActual") int noCopiesActual,
            @RequestParam("noCopiesCurrent") int noCopiesCurrent,
            @RequestParam("bookCategoryId") Long bookCategoryId,
            @RequestParam("publicationYear") int publicationyear

    ) {
        BookCategory bookCategory = new BookCategory();
        bookCategory.setId(bookCategoryId);
        Book book = new Book(null, bookName, bookAuthor, language, noCopiesActual, noCopiesCurrent, bookCategory, publicationyear);
        boolean result = bookService.addBook(book);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/updateBook")
//    @PostMapping("/updateBook")
    public ResponseEntity<?> updateBook(@RequestParam("bookId") Long bookId,
                                        @RequestParam("name") String bookName,
                                        @RequestParam("author") String bookAuthor,
                                        @RequestParam("language") String language,
                                        @RequestParam("noCopiesActual") int noCopiesActual,
                                        @RequestParam("noCopiesCurrent") int noCopiesCurrent,
                                        @RequestParam("bookCategoryId") Long bookCategoryId,
                                        @RequestParam("publicationyear") int publicationyear) {

        BookCategory bookCategory = new BookCategory();
        bookCategory.setId(bookCategoryId);
        Book book = new Book(bookId, bookName, bookAuthor, language, noCopiesActual, noCopiesCurrent, bookCategory, publicationyear);
        boolean result = bookService.addBook(book);

        return new ResponseEntity<>(bookService.uptadeBook(book), HttpStatus.OK);
    }


    @GetMapping("/bookById")
    public ResponseEntity<?> bookById(@RequestParam("bookId") Long bookId) {
        Book book = bookService.getBookById(bookId);
        return new ResponseEntity<>(book, HttpStatus.OK);


    }


    @GetMapping("/deleteBook")
    public ResponseEntity<?> deleteBook(@RequestParam("bookId") Long bookId) {
        boolean result = bookService.deleteBook(bookId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/searchBook")
    public  ResponseEntity<?> searchBook(@RequestParam("keyword")String keyword){
        List<Book>bookList = bookService.getBookSearch(keyword);
        return new ResponseEntity<>(bookList,HttpStatus.OK);

    }



}