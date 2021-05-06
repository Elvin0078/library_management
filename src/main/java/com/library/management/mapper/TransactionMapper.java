package com.library.management.mapper;

import com.library.management.model.Book;
import com.library.management.model.Transaction;
import com.library.management.model.User;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionMapper {

    public List<Transaction> getTransactions(ResultSet rs) throws SQLException {
        List<Transaction> transactionList = new ArrayList();
        while (rs.next()) {


            Book book = new Book();
            Transaction transaction = new Transaction();
            User user = new User();
            user.setUserId(rs.getLong("u.User_Id"));
            user.setFullname(rs.getString("u.Full_Name"));
            user.setUsername(rs.getString("u.username"));
            user.setDOB(rs.getDate("u.Date_Of_Birthday"));
            user.setRegistrationDate(rs.getDate("u.Registration_Date"));
            user.setPhone(rs.getString("u.phone"));

            book.setId(rs.getLong("b.Book_Id"));
            book.setName(rs.getString("b.Name"));
            book.setAuthor(rs.getString("b.Author"));
            book.setLanguage(rs.getString("b.Language"));
            book.setNoCopiesActual(rs.getInt("b.No_Copies_Actual"));
            book.setNoCopiesCurrent(rs.getInt("b.No_Copies_Actual"));

            transaction.setTrId(rs.getLong("t.tr_id"));
            transaction.setTrDate(rs.getDate("t.Tr_Date"));
//          transaction.setStatus(rs.getInt(":t.status"));
            transaction.setBook(book);
            transaction.setUser(user);
            transactionList.add(transaction);
        }

        return transactionList;
    }

    public Transaction getTransaction(ResultSet rs) throws SQLException {

        Transaction transaction = new Transaction();
        if (rs.next()) {

            Book book = new Book();
            User user = new User();
            user.setUserId(rs.getLong("u.User_Id"));
            user.setFullname(rs.getString("u.Full_Name"));
            user.setUsername(rs.getString("u.username"));
            user.setDOB(rs.getDate("u.Date_Of_Birthday"));
            user.setRegistrationDate(rs.getDate("u.Registration_Date"));
            user.setPhone(rs.getString("u.phone"));

            book.setId(rs.getLong("b.Book_Id"));
            book.setName(rs.getString("b.Name"));
            book.setAuthor(rs.getString("b.Author"));
            book.setLanguage(rs.getString("b.Language"));
            book.setNoCopiesActual(rs.getInt("b.No_Copies_Actual"));
            book.setNoCopiesCurrent(rs.getInt("b.No_Copies_Actual"));

            transaction.setTrId(rs.getLong("t.tr_id"));
            transaction.setTrDate(rs.getDate("t.Tr_Date"));
//          transaction.setStatus(rs.getInt(":t.status"));
            transaction.setBook(book);
            transaction.setUser(user);

        }

        return transaction;
    }


}
