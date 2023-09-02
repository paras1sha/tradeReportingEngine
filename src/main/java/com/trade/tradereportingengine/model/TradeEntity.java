package com.trade.tradereportingengine.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.lang.reflect.Field;
import java.math.BigDecimal;

@Entity
public class TradeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String buyerParty;
    @Column(nullable = false)
    private String sellerParty;
    @Column(nullable = false)
    private BigDecimal premiumAmount;
    @Column(nullable = false)
    private String premiumCurrency;

    public TradeEntity(){
        //Default constructor
    }

    public TradeEntity(String buyerParty, String sellerParty, BigDecimal premiumAmount, String premiumCurrency) {
        this.buyerParty = buyerParty;
        this.sellerParty = sellerParty;
        this.premiumAmount = premiumAmount;
        this.premiumCurrency = premiumCurrency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuyerParty() {
        return buyerParty;
    }

    public void setBuyerParty(String buyerParty) {
        this.buyerParty = buyerParty;
    }

    public String getSellerParty() {
        return sellerParty;
    }

    public void setSellerParty(String sellerParty) {
        this.sellerParty = sellerParty;
    }

    public BigDecimal getPremiumAmount() {
        return premiumAmount;
    }

    public void setPremiumAmount(BigDecimal premiumAmount) {
        this.premiumAmount = premiumAmount;
    }

    public String getPremiumCurrency() {
        return premiumCurrency;
    }

    public void setPremiumCurrency(String premiumCurrency) {
        this.premiumCurrency = premiumCurrency;
    }

    public void setField(String fieldName, String value) throws NoSuchFieldException, IllegalAccessException {
        Field field = getClass().getDeclaredField(fieldName);
        if (field != null) {
            field.setAccessible(true);
            if (field.getType() == BigDecimal.class) {
                field.set(this, new BigDecimal(value));
            } else {
                field.set(this, value);
            }
        }
    }



}
