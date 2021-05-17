package com.library.management.repository.Inter;

import com.library.management.model.Book;
import com.library.management.model.BookCategory;

import java.util.List;

public interface BookRepository {

    List<Book> getAllBook() throws Exception;

    boolean addBook(Book book) throws Exception;

    Book getBookById(Long bookId) throws Exception;

    boolean uptadeBook(Book book) throws Exception;

    boolean deleteBook(long bookId) throws Exception;

    List<BookCategory> getBookCategories() throws Exception;

    boolean updateBookCurrentCount(Long bookId,Long count) throws Exception;

    List<Book> getBookSearch(String keyword) throws Exception;



}
