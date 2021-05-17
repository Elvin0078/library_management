package com.library.management.service.Impl;

import com.library.management.model.Transaction;
import com.library.management.repository.Inter.TransactionRepository;
import com.library.management.service.Inter.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    public TransactionRepository transactionRepository;


    @Override
    public List<Transaction> getTransactionList() {
        try {
            return transactionRepository.getTransactionList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    @Override
    public boolean addTransaction(Transaction transaction) {
        try {
            return transactionRepository.addTransaction(transaction);
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }


    }

    @Override
    public boolean updateTransactionStatus(Long statusId, Long trId) {
        try {
            return transactionRepository.updateTransactionStatus(statusId, trId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    @Override
    public List<Transaction> getPendingTransactionList() {
        try {
            return transactionRepository.getPendingTransactionList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean markTransactionDelivery(Long trId) {
        try {
            return transactionRepository.markTransactionDelivery(trId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Transaction getTransactionById(Long trId) {
        try {
            return transactionRepository.getTransactionById(trId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Transaction> getDeliveryTransactionList() {
        try {
            return transactionRepository.getDeliveryTransactionList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean unMarkTransactionDelivery(Long trId) {
        try {
            return transactionRepository.unMarkTransactionDelivery(trId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Transaction> getDeliveryTransactionByUserId(Long userId) {
        try {
            return transactionRepository.getDeliveryTransactionByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Transaction> getPendingTransactionByUserId(Long userId) {
        try {
            return transactionRepository.getPendingTransactionByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Transaction> getDeliveryTransactionSearch(String keyword) {
        try {
            return transactionRepository.getDeliveryTransactionSearch(keyword);
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    @Override
    public List<Transaction> getPendingTransactionSearch(String keyword) {
        try {
            return transactionRepository.getPendingTransactionSearch(keyword);
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    @Override
    public List<Transaction> getDeliveryTransactionByUserIdSearch(Long userId, String keyword) {
        try {
          return  transactionRepository.getDeliveryTransactionByUserIdSearch(userId,keyword);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
