package com.proyecto.abanca.model.account;

import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

@Embeddable
public class Money {
    private static final Currency USD = Currency.getInstance("USD");
    private static final RoundingMode DEFAULT_ROUNDING = RoundingMode.HALF_EVEN;
    private Currency currency;
    private BigDecimal amount;

    public Money(BigDecimal amount, Currency currency, RoundingMode rounding) {
        this.currency = currency;
        setAmount(amount.setScale(currency.getDefaultFractionDigits(), rounding));
    }

    public Money(BigDecimal amount, Currency currency) {
        this(amount, currency, DEFAULT_ROUNDING);
    }

    public Money(BigDecimal amount) {
        this(amount, USD, DEFAULT_ROUNDING);
    }

    public Money() {
    }

    public BigDecimal increaseAmount(Money money) {
        setAmount(this.amount.add(money.amount));
        return this.amount;
    }

    public BigDecimal increaseAmount(BigDecimal addAmount) {
        setAmount(this.amount.add(addAmount));
        return this.amount;
    }

    public BigDecimal decreaseAmount(Money money) {
        setAmount(this.amount.subtract(money.getAmount()));
        return this.amount;
    }

    public BigDecimal decreaseAmount(BigDecimal addAmount) {
        setAmount(this.amount.subtract(addAmount));
        return this.amount;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    private void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String toString() {
        return getCurrency().getSymbol() + " " + getAmount();
    }
}
