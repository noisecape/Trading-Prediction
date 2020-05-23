/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trading_prediction;

import java.util.Date;

/**
 *
 * @author tommasocapecchi
 */ 

public class Crypto {
    
    private String alias;
    private String name;
    private String to_currency;
    private float exchange_rate;
    private Date last_refreshed;
    private float bid_price;
    private float ask_price;
    
    /**
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * @param alias the alias to set
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the to_currency
     */
    public String getTo_currency() {
        return to_currency;
    }

    /**
     * @param to_currency the to_currency to set
     */
    public void setTo_currency(String to_currency) {
        this.to_currency = to_currency;
    }

    /**
     * @return the exchange_rate
     */
    public Float getExchange_rate() {
        return exchange_rate;
    }

    /**
     * @param exchange_rate the exchange_rate to set
     */
    public void setExchange_rate(float exchange_rate) {
        this.exchange_rate = exchange_rate;
    }

    /**
     * @return the last_refreshed
     */
    public Date getLast_refreshed() {
        return last_refreshed;
    }

    /**
     * @param last_refreshed the last_refreshed to set
     */
    public void setLast_refreshed(Date last_refreshed) {
        this.last_refreshed = last_refreshed;
    }

    /**
     * @return the bid_price
     */
    public float getBid_price() {
        return bid_price;
    }

    /**
     * @param bid_price the bid_price to set
     */
    public void setBid_price(float bid_price) {
        this.bid_price = bid_price;
    }

    /**
     * @return the ask_price
     */
    public float getAsk_price() {
        return ask_price;
    }

    /**
     * @param ask_price the ask_price to set
     */
    public void setAsk_price(float ask_price) {
        this.ask_price = ask_price;
    }
    
}
