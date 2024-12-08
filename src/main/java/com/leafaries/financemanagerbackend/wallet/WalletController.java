package com.leafaries.financemanagerbackend.wallet;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing wallet operations.
 * Provides endpoints to create, read, update, and delete wallet information.
 */
@Slf4j
@AllArgsConstructor
@RestController("/wallets")
public class WalletController {

    private final WalletService walletService;

    /**
     * Creates a new wallet.
     *
     * @param walletHttpRequestDto the data transfer object containing wallet details
     * @return the created wallet
     */
    @PostMapping
    public ResponseEntity<WalletResponseDto> createWallet(@RequestBody WalletHttpRequestDto walletHttpRequestDto) {
        log.debug("Received request to create wallet with data: {}", walletHttpRequestDto);
        WalletResponseDto createdWallet = walletService.createWallet(walletHttpRequestDto);
        log.debug("Created wallet: {}", createdWallet);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWallet);
    }

    /**
     * Retrieves a wallet by its ID.
     *
     * @param id the ID of the wallet to retrieve
     * @return the wallet with the specified ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<WalletResponseDto> getWalletById(@PathVariable Long id) {
        log.debug("Received request to fetch wallet with id: {}", id);
        WalletResponseDto wallet = walletService.getWalletById(id);
        log.debug("Fetched wallet: {}", wallet);
        return ResponseEntity.ok(wallet);
    }

    /**
     * Retrieved all wallets.
     *
     * @return a list of all wallets
     */
    @GetMapping
    public ResponseEntity<List<WalletResponseDto>> getAllWallets() {
        log.debug("Received request to fetch all wallets");
        List<WalletResponseDto> wallets = walletService.getAllWalletsForCurrentUser();
        log.debug("Fetched all wallets: {}", wallets);
        return ResponseEntity.ok(wallets);
    }

    /**
     * Updates an existing wallet.
     *
     * @param id the ID of the wallet to update
     * @param walletHttpRequestDto the data transfer object containing new wallet details
     * @return the updated wallet
     */
    @PutMapping("/{id}")
    public ResponseEntity<WalletResponseDto> updateWallet(@PathVariable Long id,
                                                          @RequestBody WalletHttpRequestDto walletHttpRequestDto) {
        log.debug("Received request to update wallet with id: {} and data: {}", id, walletHttpRequestDto);
        WalletResponseDto updatedWallet = walletService.updateWallet(id, walletHttpRequestDto);
        log.debug("Updated wallet: {}", updatedWallet);
        return ResponseEntity.ok(updatedWallet);
    }

    /**
     * Deletes a wallet by its ID.
     *
     * @param id the ID of the wallet to delete
     * @return no content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWallet(@PathVariable Long id) {
        log.debug("Received request to delete wallet with id: {}", id);
        boolean isDeleted = walletService.deleteWallet(id);
        if (isDeleted) {
            log.debug("Deleted wallet with id: {}", id);
            return ResponseEntity.noContent().build();
        } else {
            log.warn("Failed to delete wallet with id: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

}
