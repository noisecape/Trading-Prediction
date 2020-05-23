/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trading_prediction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tommasocapecchi
 */

enum ProcessString {
    FROM_CURRENCY , TO_CURRENCY, EXCHANGE_RATE, LAST_REFRESHED, BID_PRICE, ASK_PRICE;
}

public class CurrentPriceDataFetcher {
    
    private String crypto_alias;
    private String to_currency; 
    private URL url;
    private final String API_KEY = "RVI6PE2AR87XNGZF";
    private Crypto crypto;
    
    CurrentPriceDataFetcher(String crypto_alias, String to_currency) throws MalformedURLException, UnsupportedOperationException {
        
        this.crypto_alias = crypto_alias;
        this.to_currency = to_currency;
        crypto = new Crypto();
        
        try {
            this.url = createURL();
            
        }catch (MalformedURLException e){
            System.out.println("Error while fetching data from url: "+ e.toString());
        }
        try {
            retrieveCryptoData();
        }catch (IOException e) {
            System.out.println("Error: "+e.toString()+" while reading data from:"+this.url.getPath());
        } catch (ParseException ex) {
            Logger.getLogger(CurrentPriceDataFetcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void retrieveCryptoData() throws MalformedURLException, IOException, UnsupportedOperationException, ParseException {
        URLConnection url_connection = this.url.openConnection();
        try(BufferedReader input = new BufferedReader(new InputStreamReader(url_connection.getInputStream()))){
            String current_line_of_data;
            int index = 1;
            while((current_line_of_data = input.readLine()) != null){
                if (current_line_of_data.contains("\""+index+".")) {
                    processData(current_line_of_data, index);
                    index++;
                }
            }
        }
    }
    
    private void processData(String data, int withIndex) throws UnsupportedOperationException, ParseException{
        switch (withIndex){
            case 1:
                crypto.setAlias(crypto_alias);
                break;
            case 2:
                crypto.setName(retrieveNameFromData(data));
                break;
            case 3:
                crypto.setTo_currency(to_currency);
                break;
            case 5:
                float price = convertPriceFromStringToFloat(retrieveStringForExchangeRate(data));
                crypto.setExchange_rate(price);
                break;
            case 6:
                Date date = retrieveDateFromString(data);
                crypto.setLast_refreshed(date);
                break;
            case 8:
                float bid_price = convertPriceFromStringToFloat(retrieveBidPrice(data));//to implement
                crypto.setBid_price(bid_price);
                break;
            case 9:
                float ask_price = convertPriceFromStringToFloat(retrieveAskPrice(data));//to implement
                crypto.setAsk_price(ask_price);
                break;
        }
    }
    
    private String retrieveNameFromData(String data){
        String crypto_name = null;
        for(String piece_of_string : data.split("\"")){
            if (piece_of_string.matches("[A-Z][a-z]+")){
                crypto_name = piece_of_string;
            }
        }
        return crypto_name;
    }
    
    private float convertPriceFromStringToFloat(String price) throws UnsupportedOperationException {
         return new Float(price);
    } 
    
    private URL createURL() throws MalformedURLException {
        return new URL("https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency="+crypto_alias+"&to_currency="+to_currency+"&apikey="+API_KEY);
    }

    private Date retrieveDateFromString(String data) throws UnsupportedOperationException, ParseException {//to implement
        String last_update = null;
        for(String piece_of_string : data.split("\"")){
            if (piece_of_string.matches("(\\d+)-(\\d+)-(\\d+)+(\\s(\\d+):(\\d+):(\\d+))")){
                last_update = piece_of_string;
            }
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.parse(last_update);
    }

    private String retrieveStringForExchangeRate(String data) throws UnsupportedOperationException{//to implement
        String exchange_price = null;
        for(String piece_of_string : data.split("\"")){
            if (piece_of_string.matches("^(\\d*\\.)?\\d+$")){
                exchange_price = piece_of_string;
            }
        }
        return exchange_price;
    }
    
    private String retrieveAskPrice(String data) throws UnsupportedOperationException {
       String ask_price = null;
        for(String piece_of_string : data.split("\"")){
            if (piece_of_string.matches("^(\\d*\\.)?\\d+$")){
                ask_price = piece_of_string;
            }
        }
        return ask_price;
    }

    private String retrieveBidPrice(String data) throws UnsupportedOperationException{
        String bid_price = null;
        for(String piece_of_string : data.split("\"")){
            if (piece_of_string.matches("^(\\d*\\.)?\\d+$")){
                bid_price = piece_of_string;
            }
        }
        return bid_price;
    }
    
    public Crypto getCrypto(){
        return this.crypto;
    }
    
}
