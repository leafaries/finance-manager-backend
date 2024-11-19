package com.leafaries.financemanagerbackend.wallet;

import com.leafaries.financemanagerbackend.security.SecurityUtils;
import com.leafaries.financemanagerbackend.exception.ResourceNotFoundException;
import com.leafaries.financemanagerbackend.user.User;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing wallet operations.
 * Provides methods to create, retrieve, update, and delete wallets.
 */
@Service
public class WalletService {

    private final static Logger logger = LoggerFactory.getLogger(WalletService.class);

    private final WalletRepository walletRepository;
    private final ModelMapper modelMapper;
    private final SecurityUtils securityUtils;

    /**
     * Constructs a new WalletService with the specified dependencies.
     *
     * @param walletRepository the repository for wallet persistence
     * @param modelMapper the model mapper for converting between entities and DTOs
     * @param securityUtils the security utilities for authorization checks
     */
    public WalletService(WalletRepository walletRepository,
                         ModelMapper modelMapper,
                         SecurityUtils securityUtils) {
        this.walletRepository = walletRepository;
        this.modelMapper = modelMapper;
        this.securityUtils = securityUtils;
    }

    /**
     * Creates a new wallet.
     *
     * @param walletHttpRequestDto the data transfer object wallet details
     * @return the created wallet
     */
    public WalletResponseDto createWallet(WalletHttpRequestDto walletHttpRequestDto) {
        logger.debug("Creating wallet with data: {}", walletHttpRequestDto);
        Wallet wallet = modelMapper.map(walletHttpRequestDto, Wallet.class);
        User currentUser = securityUtils.getCurrentUser();
        wallet.setUser(currentUser);
        Wallet savedWallet = walletRepository.save(wallet);
        logger.debug("Saved wallet entity: {}", savedWallet);
        return modelMapper.map(savedWallet, WalletResponseDto.class);
    }

    /**
     * Retrieves a wallet by its ID.
     *
     * @param id the ID of the wallet to retrieve
     * @return the wallet with the specified ID
     */
    public WalletResponseDto getWalletById(Long id) {
        logger.debug("Fetching wallet with id: {}", id);
        Wallet wallet = walletRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Wallet not found"));
        if (!wallet.getUser().equals(securityUtils.getCurrentUser())) {
            throw new AccessDeniedException("Access denied");
        }
        logger.debug("Fetched wallet entity: {}", wallet);
        return modelMapper.map(wallet, WalletResponseDto.class);
    }

    /**
     * Retrieves all wallets.
     *
     * @return a list of all wallets
     */
    public List<WalletResponseDto> getAllWallets() {
        logger.debug("Fetching all wallets");
        User currentuser = securityUtils.getCurrentUser();
        List<Wallet> wallets = walletRepository.findAllByUserId(currentuser.getId());
        logger.debug("Fetched all wallets entities: {}", wallets);
        return wallets.stream().map(wallet -> modelMapper.map(wallet, WalletResponseDto.class)).toList();
    }

    /**
     * Updates an existing wallet.
     *
     * @param id the ID of the wallet to update
     * @param walletHttpRequestDto the data transfer object containing new wallet details
     * @return the updated wallet
     */
    public WalletResponseDto updateWallet(Long id, WalletHttpRequestDto walletHttpRequestDto) {
        logger.debug("Updating wallet with id: {} and data: {}", id, walletHttpRequestDto);
        Wallet wallet = walletRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Access denied"));
        if (!wallet.getUser().equals(securityUtils.getCurrentUser())) {
            throw new AccessDeniedException("Access denied");
        }
        modelMapper.map(walletHttpRequestDto, wallet);
        Wallet updatedWallet = walletRepository.save(wallet);
        logger.debug("Updated wallet entity: {}", updatedWallet);
        return modelMapper.map(updatedWallet, WalletResponseDto.class);
    }

    /**
     * Deletes a wallet by its ID.
     *
     * @param id the ID of the wallet to delete
     * @return true if the wallet was deleted, false otherwise
     */
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
