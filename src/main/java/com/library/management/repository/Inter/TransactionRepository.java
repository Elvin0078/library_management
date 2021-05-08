package com.library.management.repository.Inter;

import com.library.management.model.Transaction;
import com.library.management.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface TransactionRepository {

List <Transaction>getTransactionList()throws Exception;

boolean addTransaction(Transaction transaction) throws Exception;

boolean updateTransactionStatus(Long statusId,Long trId)throws Exception;

List <Transaction>getPendingTransactionList()throws Exception;

boolean markTransactionDelivery (Long  trId) throws Exception;

Transaction getTransactionById(Long trId)throws  Exception;

List <Transaction>getDeliveryTransactionList()throws Exception;

boolean unMarkTransactionDelivery (Long  trId) throws Exception;

 List<Transaction> getDeliveryTransactionByUserId(Long userId) throws Exception;

 List<Transaction> getPendingTransactionByUserId(Long userId) throws Exception;



}
