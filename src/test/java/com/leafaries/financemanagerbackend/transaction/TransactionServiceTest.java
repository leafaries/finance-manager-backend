package com.leafaries.financemanagerbackend.transaction;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.leafaries.financemanagerbackend.wallet.WalletService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class TransactionServiceTest {
    /*

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private WalletService walletService;

    private Transaction transaction;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        transaction = new Transaction(1L, 100.0, "2024-11-05", "Food", "Lunch", 1L);
    }

    @Test
    void testCreateTransaction() {
        when(walletService.getWalletById(transaction.getWalletId())).thenReturn(Optional.of(new Wallet()));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        Transaction createdTransaction = transactionService.createTransaction(transaction);

        assertNotNull(createdTransaction);
        assertEquals(transaction.getAmount(), createdTransaction.getAmount());
        verify(transactionRepository, times(1)).save(transaction);
    }

    @Test
    void testGetTransactionById() {
        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));

        Transaction foundTransaction = transactionService.getTransactionById(1L);

        assertNotNull(foundTransaction);
        assertEquals(transaction.getId(), foundTransaction.getId());
    }

     */
}
