package com.leafaries.financemanagerbackend.wallet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    public ResponseEntity<WalletDto> createWallet(@RequestBody CreateWalletDto createWalletDto) {
        WalletDto createdWallet = walletService.createWallet(createWalletDto);
//        return new ResponseEntity<>(createdWallet, HttpStatus.CREATED);
        return ResponseEntity.ok(createdWallet);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WalletDto> getWalletById(@PathVariable Long id) {
        WalletDto wallet = walletService.getWalletById(id);
//        return wallet != null ? new ResponseEntity<>(wallet, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(wallet);
    }

    @GetMapping
    public ResponseEntity<List<WalletDto>> getAllWallets() {
        List<WalletDto> wallets = walletService.getAllWallets();
//        return new ResponseEntity<>(wallets, HttpStatus.OK);
        return ResponseEntity.ok(wallets);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WalletDto> updateWallet(@PathVariable Long id, @RequestBody CreateWalletDto createWalletDto) {
        WalletDto updatedWallet = walletService.updateWallet(id, createWalletDto);
        return updatedWallet != null ? new ResponseEntity<>(updatedWallet, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWallet(@PathVariable Long id) {
        if (walletService.deleteWallet(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
