package com.leafaries.financemanagerbackend.wallet;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public WalletService(WalletRepository walletRepository, ModelMapper modelMapper) {
        this.walletRepository = walletRepository;
        this.modelMapper = modelMapper;
    }

    public WalletDto createWallet(CreateWalletDto createWalletDto) {
        if (createWalletDto == null) {
            throw new IllegalArgumentException("CreateWalletDto cannot be null");
        }

        Wallet walletEntity = modelMapper.map(createWalletDto, Wallet.class);

        Wallet savedWalletEntity = walletRepository.save(walletEntity);

        return modelMapper.map(savedWalletEntity, WalletDto.class);
    }

    public WalletDto getWalletById(Long id) {
        return walletRepository.findById(id)
                .map(wallet -> modelMapper.map(wallet, WalletDto.class))
                .orElse(null);
    }

    public List<WalletDto> getAllWallets() {
        return walletRepository.findAll().stream()
                .map(wallet -> modelMapper.map(wallet, WalletDto.class))
                .collect(Collectors.toList());
    }

    public WalletDto updateWallet(Long id, CreateWalletDto createWalletDto) {
        if (!walletRepository.existsById(id)) {
            return null;
        }

        Wallet wallet = modelMapper.map(createWalletDto, Wallet.class);
        wallet.setId(id); // Ensure the ID is set for updating
        Wallet updatedWallet = walletRepository.save(wallet);
        return modelMapper.map(updatedWallet, WalletDto.class);
    }

    public boolean deleteWallet(Long id) {
        if (!walletRepository.existsById(id)) {
            return false;
        }

        walletRepository.deleteById(id);
        return true;
    }
}
