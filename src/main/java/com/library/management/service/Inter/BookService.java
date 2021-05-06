package com.library.management.service.Inter;

import com.library.management.model.Book;
import com.library.management.model.BookCategory;

import java.util.List;

public interface BookService {
    List<Book> getAllBook() ;

    boolean addBook(Book book);

    Book getBookById(Long bookId);

    boolean uptadeBook (Book book);

    boolean deleteBook (long bookId);

    List<BookCategory> getBookCategories();

    boolean updateBookCurrentCount(Long bookId,Long count);

}
