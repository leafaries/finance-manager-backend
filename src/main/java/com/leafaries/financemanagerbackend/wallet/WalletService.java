package com.leafaries.financemanagerbackend.wallet;

import com.leafaries.financemanagerbackend.security.SecurityUtils;
import com.leafaries.financemanagerbackend.user.ResourceNotFoundException;
import com.leafaries.financemanagerbackend.user.User;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletService {

    private final static Logger logger = LoggerFactory.getLogger(WalletService.class);

    private final WalletRepository walletRepository;
    private final ModelMapper modelMapper;
    private final SecurityUtils securityUtils;

    @Autowired
    public WalletService(WalletRepository walletRepository,
                         ModelMapper modelMapper,
                         SecurityUtils securityUtils) {
        this.walletRepository = walletRepository;
        this.modelMapper = modelMapper;
        this.securityUtils = securityUtils;
    }

    public WalletDto createWallet(CreateWalletDto createWalletDto) {
        logger.debug("Creating wallet with data: {}", createWalletDto);
        Wallet wallet = modelMapper.map(createWalletDto, Wallet.class);
        User currentUser = securityUtils.getCurrentUser();
        wallet.setUser(currentUser);
        Wallet savedWallet = walletRepository.save(wallet);
        logger.debug("Saved wallet entity: {}", savedWallet);
        return modelMapper.map(savedWallet, WalletDto.class);
    }

    public WalletDto getWalletById(Long id) {
        logger.debug("Fetching wallet with id: {}", id);
        Wallet wallet = walletRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Wallet not found"));
        if (!wallet.getUser().equals(securityUtils.getCurrentUser())) {
            throw new AccessDeniedException("Access denied");
        }
        logger.debug("Fetched wallet entity: {}", wallet);
        return modelMapper.map(wallet, WalletDto.class);
    }

    public List<WalletDto> getAllWallets() {
        logger.debug("Fetching all wallets");
        User currentuser = securityUtils.getCurrentUser();
        List<Wallet> wallets = walletRepository.findAllByUserId(currentuser.getId());
        logger.debug("Fetched all wallets entities: {}", wallets);
        return wallets.stream().map(wallet -> modelMapper.map(wallet, WalletDto.class)).toList();
    }

    public WalletDto updateWallet(Long id, CreateWalletDto createWalletDto) {
        logger.debug("Updating wallet with id: {} and data: {}", id, createWalletDto);
        Wallet wallet = walletRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Access denied"));
        if (!wallet.getUser().equals(securityUtils.getCurrentUser())) {
            throw new AccessDeniedException("Access denied");
        }
        modelMapper.map(createWalletDto, wallet);
        Wallet updatedWallet = walletRepository.save(wallet);
        logger.debug("Updated wallet entity: {}", updatedWallet);
        return modelMapper.map(updatedWallet, WalletDto.class);
    }

    public boolean deleteWallet(Long id) {
        logger.debug("Deleting wallet with id: {}", id);
        Wallet wallet = walletRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Wallet not found"));
        if (!wallet.getUser().equals(securityUtils.getCurrentUser())) {
            throw new AccessDeniedException("Access denied");
        }
        if (walletRepository.existsById(id)) {
            walletRepository.delete(wallet);
            logger.debug("Deleted wallet with id: {}", id);
            return true;
        } else {
            logger.warn("Wallet with id: {} not found for deletion", id);
            return false;
        }
    }
}
