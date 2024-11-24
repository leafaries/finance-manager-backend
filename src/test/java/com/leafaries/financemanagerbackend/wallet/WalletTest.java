package com.leafaries.financemanagerbackend.wallet;

import com.leafaries.financemanagerbackend.currencyexchange.Currency;
import jakarta.validation.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class WalletTest {

    private final Validator validator;

    public WalletTest(Validator validator) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = validator;
    }

    @Test
    void testWalletNameNotBlank() {
        Wallet wallet = new Wallet("", 100.0, Currency.USD, null);
        var violations = validator.validate(wallet);
        assertFalse(violations.isEmpty());
    }

    @Test
    void testWalletCurrencyNotNull() {
        Wallet wallet = new Wallet("Test Wallet", 100.0, null, null);
        var violations = validator.validate(wallet);
        assertFalse(violations.isEmpty());
    }

    // TODO: Write more tests
}
