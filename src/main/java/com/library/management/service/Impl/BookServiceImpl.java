package com.library.management.service.Impl;

import com.library.management.model.Book;
import com.library.management.model.BookCategory;
import com.library.management.repository.Inter.BookRepository;
import com.library.management.service.Inter.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    public BookRepository bookRepository;

    @Override
    public List<Book> getAllBook() {
        try {

            return bookRepository.getAllBook();
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addBook(Book book) {
        try {
            return bookRepository.addBook(book);

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Book getBookById(Long bookId) {
        try {
            return bookRepository.getBookById(bookId);

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean uptadeBook(Book book) {
        try {
            return bookRepository.uptadeBook(book);

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteBook(long bookId) {
        try {
            return bookRepository.deleteBook(bookId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;


        }
    }

    @Override
    public List<BookCategory> getBookCategories() {
        try {
            return bookRepository.getBookCategories();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    @Override
    public boolean updateBookCurrentCount(Long bookId, Long count) {
        try {
            return bookRepository.updateBookCurrentCount(bookId, count);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Book> getBookSearch(String keyword) {
        try {
            return bookRepository.getBookSearch(keyword);
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }
}
