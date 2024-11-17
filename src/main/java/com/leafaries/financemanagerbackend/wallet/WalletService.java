package com.leafaries.financemanagerbackend.wallet;

import com.leafaries.financemanagerbackend.user.ResourceNotFoundException;
import com.leafaries.financemanagerbackend.user.UserRepository;
import com.leafaries.financemanagerbackend.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Autowired
    public WalletService(WalletRepository walletRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.walletRepository = walletRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public WalletDto createWallet(CreateWalletDto createWalletDto) {
        Wallet wallet = modelMapper.map(createWalletDto, Wallet.class);
        User currentuser = getCurrentUser();
        wallet.setUser(currentuser);
        Wallet savedWallet = walletRepository.save(wallet);
        return modelMapper.map(savedWallet, WalletDto.class);
    }

    public WalletDto getWalletById(Long id) {
        Wallet wallet = walletRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Wallet not found"));
        if (!wallet.getUser().equals(getCurrentUser())) {
            throw new AccessDeniedException("Access denied");
        }
        return modelMapper.map(wallet, WalletDto.class);
    }

    public List<WalletDto> getAllWallets() {
        User currentuser = getCurrentUser();
        List<Wallet> wallets = walletRepository.findAllByUserId(currentuser.getId());
        return wallets.stream().map(wallet -> modelMapper.map(wallet, WalletDto.class)).toList();
    }

    public WalletDto updateWallet(Long id, CreateWalletDto createWalletDto) {
        Wallet wallet = walletRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Access denied"));
        if (!wallet.getUser().equals(getCurrentUser())) {
            throw new AccessDeniedException("Access denied");
        }
        modelMapper.map(createWalletDto, wallet);
        Wallet updatedWallet = walletRepository.save(wallet);
        return modelMapper.map(updatedWallet, WalletDto.class);
    }

    public boolean deleteWallet(Long id) {
        Wallet wallet = walletRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Wallet not found"));
        if (!wallet.getUser().equals(getCurrentUser())) {
            throw new AccessDeniedException("Access denied");
        }
        walletRepository.delete(wallet);
        return true;
    }

    // Helper method to get the currently logged-in user
    private User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            String username = ((UserDetails) principal).getUsername();
            return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        }
        throw new IllegalArgumentException("User not authenticated");
    }
}
