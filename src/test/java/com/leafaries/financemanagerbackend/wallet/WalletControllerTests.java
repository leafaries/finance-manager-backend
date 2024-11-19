//package com.leafaries.financemanagerbackend.wallet;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultMatcher;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.hamcrest.Matchers.is;
//
//import java.math.BigDecimal;
//import java.util.Arrays;
//import java.util.List;
//
//@SpringBootTest
//@AutoConfigureWebMvc
//public class WalletControllerTests {
//
//    private final MockMvc mvc;
//
//    @MockBean
//    private WalletService walletService;
//
//    public WalletControllerTests(MockMvc mvc) {
//        this.mvc = mvc;
//    }
//
//    @Test
//    public void contextLoads() {
//    }
//
//    @Test
//    public void testCreateWallet() throws Exception {
//        WalletHttpRequestDto newWallet = new WalletHttpRequestDto("My Wallet", 100, "USD");
//        WalletResponseDto createdWallet = new WalletResponseDto(1L, newWallet.getName(), BigDecimal.valueOf(newWallet.getBalance()), newWallet.getCurrency());
//        given(walletService.createWallet(any(WalletHttpRequestDto.class))).willReturn(createdWallet);
//
//        MockHttpServletRequestBuilder request = post("/api/wallets")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(newWallet));
//
//        mvc.perform(request)
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.name", is(createdWallet.getName())))
//                .andExpect(jsonPath("$.balance", is(createdWallet.getBalance().intValue())))
//                .andExpect(jsonPath("$.currency", is(createdWallet.getCurrency())));
//    }
//
//    @Test
//    public void testGetWalletById() throws Exception {
//        Long id = 1L;
//        WalletResponseDto wallet = new WalletResponseDto(id, "My Wallet", BigDecimal.valueOf(100), "USD");
//        given(walletService.getWalletById(id)).willReturn(wallet);
//
//        mvc.perform(get("/wallets/{id}", id))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", is(wallet.getName())))
//                .andExpect(jsonPath("$.balance", is(wallet.getBalance())))
//                .andExpect(jsonPath("$.currency", is(wallet.getCurrency())));
//    }
//
//    @Test
//    public void testGetAllWallets() throws Exception {
//        WalletResponseDto wallet1 = new WalletResponseDto(1L, "Wallet 1", BigDecimal.valueOf(100), "USD");
//        WalletResponseDto wallet2 = new WalletResponseDto(2L, "Wallet 2", BigDecimal.valueOf(200), "EUR");
//        List<WalletResponseDto> wallets = Arrays.asList(wallet1, wallet2);
//        given(walletService.getAllWallets()).willReturn(wallets);
//
//        mvc.perform(get("/wallets"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.[0].name", is(wallet1.getName())))
//                .andExpect(jsonPath("$.[0].balance", is(wallet1.getBalance())))
//                .andExpect(jsonPath("$.[0].currency", is(wallet1.getCurrency())))
//                .andExpect(jsonPath("$.[1].name", is(wallet2.getName())))
//                .andExpect(jsonPath("$.[1].balance", is(wallet2.getBalance())))
//                .andExpect(jsonPath("$.[1].currency", is(wallet2.getCurrency())));
//    }
//
//    @Test
//    public void testUpdateWallet() throws Exception {
//        Long id = 1L;
//        WalletResponseDto updatedWallet = new WalletResponseDto("Updated Wallet", 150, "GBP");
//        given(walletService.updateWallet(eq(id), any(WalletHttpRequestDto.class))).willReturn(updatedWallet);
//
//        MockHttpServletRequestBuilder request = put("/wallets/{id}", id)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(updatedWallet));
//
//        mvc.perform(request)
//                .andExpect(status().isOk())
//                .andExpect((ResultMatcher) jsonPath("$.name", is(updatedWallet.getName())))
//                .andExpect((ResultMatcher) jsonPath("$.balance", is(updatedWallet.getBalance())))
//                .andExpect((ResultMatcher) jsonPath("$.currency", is(updatedWallet.getCurrency())));
//    }
//
//    @Test
//    public void testDeleteWallet() throws Exception {
//        Long id = 1L;
//        mvc.perform(delete("/wallets/{id}", id))
//                .andExpect(status().isOk());
//    }
//
//    private String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
