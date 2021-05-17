package com.library.management.repository.Impl;

import com.library.management.mapper.TransactionMapper;
import com.library.management.model.Transaction;
import com.library.management.repository.Inter.TransactionRepository;
import com.library.management.service.Inter.BookService;
import com.library.management.service.Inter.TransactionService;
import com.library.management.sql.AppSql;
import com.library.management.util.DataConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

    public NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public TransactionMapper transactionMapper;
    public DataConverter dataConverter;
    public TransactionService transactionService;
    public BookService bookService;

    @Autowired
    public TransactionRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate, TransactionMapper transactionMapper, DataConverter dataConverter, TransactionService transactionService, BookService bookService) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.transactionMapper = transactionMapper;
        this.dataConverter = dataConverter;
        this.transactionService = transactionService;
        this.bookService = bookService;
    }

    @Override
    public List<Transaction> getTransactionList() throws Exception {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        List trlist = namedParameterJdbcTemplate.query(AppSql.GET_TRANSACTİON_MAIN, mapSqlParameterSource, transactionMapper::getTransactions);


        return trlist;
    }

    @Override
    public boolean addTransaction(Transaction transaction) throws Exception {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("book_id", transaction.getBook().getId());
        mapSqlParameterSource.addValue("user_id", transaction.getUser().getUserId());
        mapSqlParameterSource.addValue("status", 1);
        int count = namedParameterJdbcTemplate.update(AppSql.ADD_TRANSACTION, mapSqlParameterSource);
        return count > 0;
    }

    @Override
    public boolean updateTransactionStatus(Long statusId, Long trId) throws Exception {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("status", statusId);
        mapSqlParameterSource.addValue("tr_id", trId);
        int count = namedParameterJdbcTemplate.update(AppSql.UPDATE_TR_STATUS, mapSqlParameterSource);

        return count > 0;
    }

    @Override
    public List<Transaction> getPendingTransactionList() throws Exception {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        List trlist = namedParameterJdbcTemplate.query(AppSql.GET_TR_PENDING, mapSqlParameterSource, transactionMapper::getTransactions);


        return trlist;
    }

    @Override
    public boolean markTransactionDelivery(Long trId) throws Exception {

        Transaction transaction = transactionService.getTransactionById(trId);

        long nocopiesCurrent = bookService.getBookById(transaction.getBook().getId()).getNoCopiesCurrent();
        nocopiesCurrent = nocopiesCurrent - 1;
        System.out.println("no copi- -    -  " + nocopiesCurrent);
        boolean result = bookService.updateBookCurrentCount(transaction.getBook().getId(), nocopiesCurrent);
        if (result) {
            result = transactionService.updateTransactionStatus(3L, trId);
        }

        return result;
    }

    @Override
    public Transaction getTransactionById(Long trId) throws Exception {
        Transaction transaction = new Transaction();
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("tr_id", trId);
        transaction = namedParameterJdbcTemplate.query(AppSql.GET_TRANSACTION_BY_ID, mapSqlParameterSource, transactionMapper::getTransaction);

        return transaction;
    }

    @Override
    public List<Transaction> getDeliveryTransactionList() throws Exception {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        List<Transaction> transactionList = namedParameterJdbcTemplate.query(AppSql.GET_TR_DELIVERY, mapSqlParameterSource, transactionMapper::getTransactions);
        return transactionList;
    }

    @Override
    public boolean unMarkTransactionDelivery(Long trId) throws Exception {
        Transaction transaction = transactionService.getTransactionById(trId);

        long nocopiesCurrent = bookService.getBookById(transaction.getBook().getId()).getNoCopiesCurrent();
        nocopiesCurrent = nocopiesCurrent + 1;
        boolean result = bookService.updateBookCurrentCount(transaction.getBook().getId(), nocopiesCurrent);
        if (result) {
            result = transactionService.updateTransactionStatus(4L, trId);
        }

        return result;
    }

    @Override
    public List<Transaction> getDeliveryTransactionByUserId(Long userId) throws Exception {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("userId", userId);
        List<Transaction> transactionList = namedParameterJdbcTemplate.query(AppSql.GET_TR_DELIVERY_BY_USER, mapSqlParameterSource, transactionMapper::getTransactions);
        return transactionList;
    }

    @Override
    public List<Transaction> getPendingTransactionByUserId(Long userId) throws Exception {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("userId", userId);
        List<Transaction> transactionList = namedParameterJdbcTemplate.query(AppSql.GET_TR_PENDING_BY_USER, mapSqlParameterSource, transactionMapper::getTransactions);
        return transactionList;
    }

    @Override
    public List<Transaction> getDeliveryTransactionSearch(String keyword) throws Exception {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("keyword", "%" + keyword + "%");
        List<Transaction> transactionList = namedParameterJdbcTemplate.query(AppSql.GET_TR_DELIVERY_SEARCH, mapSqlParameterSource, transactionMapper::getTransactions);
        return transactionList;
    }

    @Override
    public List<Transaction> getPendingTransactionSearch(String keyword) throws Exception {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("keyword", "%" + keyword + "%");
        List<Transaction> transactionList = namedParameterJdbcTemplate.query(AppSql.GET_TR_PENDING_SEARCH, mapSqlParameterSource, transactionMapper::getTransactions);
        return transactionList;


    }


}