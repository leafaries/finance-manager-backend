package com.leafaries.financemanagerbackend.wallet;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

public class WalletServiceTest {
    /*

    @InjectMocks
    private WalletService walletService;

    @Mock
    private WalletRepository walletRepository;

    private Wallet wallet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        wallet = new Wallet("Personal Wallet", BigDecimal.valueOf(1000.00), "USD");
    }

    @Test
    void testCreateWallet() {
        when(walletRepository.save(any(Wallet.class))).thenReturn(wallet);

        Wallet createdWallet = walletService.createWallet(wallet);

        assertNotNull(createdWallet);
        assertEquals(wallet.getName(), createdWallet.getName());
        verify(walletRepository, times(1)).save(wallet);
    }

    @Test
    void testGetWalletById() {
        when(walletRepository.findById(1L)).thenReturn(Optional.of(wallet));

        Wallet foundWallet = walletService.getWalletById(1L);

        assertNotNull(foundWallet);
        assertEquals(wallet.getId(), foundWallet.getId());
    }
    */
}
