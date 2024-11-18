package com.leafaries.financemanagerbackend.wallet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    private static final Logger logger = LoggerFactory.getLogger(WalletController.class);

    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    public ResponseEntity<WalletDto> createWallet(@RequestBody CreateWalletDto createWalletDto) {
        logger.debug("Received request to create wallet with data: {}", createWalletDto);
        WalletDto createdWallet = walletService.createWallet(createWalletDto);
        logger.debug("Created wallet: {}", createdWallet);
        return ResponseEntity.ok(createdWallet);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WalletDto> getWalletById(@PathVariable Long id) {
        logger.debug("Received request to fetch wallet with id: {}", id);
        WalletDto wallet = walletService.getWalletById(id);
        logger.debug("Fetched wallet: {}", wallet);
        return ResponseEntity.ok(wallet);
    }

    @GetMapping
    public ResponseEntity<List<WalletDto>> getAllWallets() {
        logger.debug("Received request to fetch all wallets");
        List<WalletDto> wallets = walletService.getAllWallets();
        logger.debug("Fetched all wallets: {}", wallets);
        return ResponseEntity.ok(wallets);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WalletDto> updateWallet(@PathVariable Long id, @RequestBody CreateWalletDto createWalletDto) {
        logger.debug("Received request to update wallet with id: {} and data: {}", id, createWalletDto);
        WalletDto updatedWallet = walletService.updateWallet(id, createWalletDto);
        logger.debug("Updated wallet: {}", updatedWallet);
        return ResponseEntity.ok(updatedWallet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWallet(@PathVariable Long id) {
        logger.debug("Received request to delete wallet with id: {}", id);
        boolean isDeleted = walletService.deleteWallet(id);
        if (isDeleted) {
            logger.debug("Deleted wallet with id: {}", id);
            return ResponseEntity.noContent().build();
        } else {
            logger.warn("Failed to delete wallet with id: {}", id);
            return ResponseEntity.notFound().build();
        }
    }
}
