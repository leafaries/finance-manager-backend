package com.leafaries.financemanagerbackend.wallet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Collections;
import java.math.BigDecimal;

@WebMvcTest(WalletController.class)
class WalletControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WalletService walletService;

    @Test
    void testGetAllWallets() throws Exception {
        given(walletService.getAllWallets()).willReturn(Collections.emptyList());
        mockMvc.perform(get("/api/wallets")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testCreateWallet() throws Exception {
        WalletHttpRequestDto walletDto = new WalletHttpRequestDto("Test Wallet", 100.0, "USD");
        WalletResponseDto responseDto = new WalletResponseDto(1L, "Test Wallet", BigDecimal.valueOf(100.0), "USD");
        given(walletService.createWallet(walletDto)).willReturn(responseDto);
        mockMvc.perform(post("/api/wallets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(walletDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Wallet"));
    }

    // More tests can be added for other endpoints and scenarios
}
