package com.leafaries.financemanagerbackend.transaction;

import com.leafaries.financemanagerbackend.wallet.Wallet;
import com.leafaries.financemanagerbackend.wallet.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, WalletRepository walletRepository) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
    }

    public TransactionDto createTransaction(TransactionDto transactionDto) {
        Wallet wallet = walletRepository.findById(transactionDto.getWalletId())
                .orElseThrow(() -> new IllegalArgumentException("Wallet not found"));

        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDto.getAmount());
        transaction.setDate(transactionDto.getDate());
        transaction.setCategory(transactionDto.getCategory());
        transaction.setNotes(transactionDto.getNotes());
        transaction.setWallet(wallet);

        Transaction savedTransaction = transactionRepository.save(transaction);
        return mapToDto(savedTransaction);
    }

    public TransactionDto getTransactionById(Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return transaction.map(this::mapToDto).orElse(null);
    }

    public List<TransactionDto> getAllTransactions() {
        return transactionRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public TransactionDto updateTransaction(Long id, TransactionDto transactionDto) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found"));

        Wallet wallet = walletRepository.findById(transactionDto.getWalletId())
                .orElseThrow(() -> new IllegalArgumentException("Wallet not found"));

        transaction.setAmount(transactionDto.getAmount());
        transaction.setDate(transactionDto.getDate());
        transaction.setCategory(transactionDto.getCategory());
        transaction.setNotes(transactionDto.getNotes());
        transaction.setWallet(wallet);

        Transaction updatedTransaction = transactionRepository.save(transaction);
        return mapToDto(updatedTransaction);
    }

    public boolean deleteTransaction(Long id) {
        if (transactionRepository.existsById(id)) {
            transactionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Helper method to map a Transaction entity to a TransactionDto
    private TransactionDto mapToDto(Transaction transaction) {
        return new TransactionDto(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getDate(),
                transaction.getCategory(),
                transaction.getNotes(),
                transaction.getWallet().getId()
        );
    }
}
