package com.library.management.mapper;

import com.library.management.model.Book;
import com.library.management.model.BookCategory;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookMapper {

    public List<Book> getBookList(ResultSet resultSet) throws SQLException {
        List<Book> books = new ArrayList();

        while (resultSet.next()) {

            Book book = new Book();
            BookCategory bookCategory = new BookCategory();

            book.setId(resultSet.getLong("b.Book_Id"));
            book.setName(resultSet.getString("b.Name"));
            book.setAuthor(resultSet.getString("b.Author"));
            book.setLanguage(resultSet.getString("b.Language"));
            book.setNoCopiesActual(resultSet.getInt("b.No_Copies_Actual"));
            book.setNoCopiesCurrent(resultSet.getInt("b.No_Copies_Current"));
            book.setPublicationyear(resultSet.getInt("b.Publication_year"));
            bookCategory.setId(resultSet.getLong("b.Category_Id"));
            bookCategory.setName(resultSet.getString("c.category_Name"));
            book.setBookCategory(bookCategory);
            books.add(book);

        }

        return books;

    }

    public Book getBookById(ResultSet resultSet) {

        try {
            Book book = new Book();
            if (resultSet.next()) {
                BookCategory bookCategory = new BookCategory();

                book.setId(resultSet.getLong("b.Book_Id"));
                book.setName(resultSet.getString("b.Name"));
                book.setLanguage(resultSet.getString("b.Language"));
                book.setAuthor(resultSet.getString("b.author"));
                book.setNoCopiesActual(resultSet.getInt("b.No_Copies_Actual"));
                book.setNoCopiesCurrent(resultSet.getInt("b.No_Copies_Current"));
                book.setPublicationyear(resultSet.getInt("b.Publication_year"));

                bookCategory.setId(resultSet.getLong("b.Category_id"));
                bookCategory.setName(resultSet.getString("c.Category_name"));

                book.setBookCategory(bookCategory);
            }
            return book;

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        }


    }


    public List<BookCategory> getBookCategoryList(ResultSet rs) throws SQLException {
        List<BookCategory> bookCategoryList = new ArrayList<>();

        while (rs.next()) {
            BookCategory bookCategory = new BookCategory();
            bookCategory.setId(rs.getLong("Category_id"));
            bookCategory.setName(rs.getString("Category_Name"));
            bookCategoryList.add(bookCategory);
        }


        return bookCategoryList;
    }


}




