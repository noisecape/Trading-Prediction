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
import java.util.Date;

/** 
 * This class retrieve and holds the daily prices of the currency you want to check
 * @author tommasocapecchi
 */
public class DailyPriceDataFetcher {
    
    private String crypto_alias;
    private String to_currency; 
    private URL url;
    private final String API_KEY = "RVI6PE2AR87XNGZF";
    private Crypto crypto;
    
    DailyPriceDataFetcher(String crypto_alias, String to_currency) {
        this.crypto_alias = crypto_alias;
        this.to_currency = to_currency;
        crypto = new Crypto();
        try {
            this.url = createURL();
        }catch(MalformedURLException e){
            System.out.println("Malformed URL: " + e.toString());
        }
        
        try {
            retrieveCryptoData();
        }catch(IOException io_e){
            System.out.println("Error while opening a connection: " + io_e.toString());
        }
    }
    
    private void retrieveCryptoData() throws IOException{
        URLConnection url_connection = this.url.openConnection();
        try (BufferedReader input = new BufferedReader(new InputStreamReader(url_connection.getInputStream()))) {
            String current_line_of_data;
            int index = 1;
            while((current_line_of_data = input.readLine()) != null){
                  System.out.println(current_line_of_data);
//                if (current_line_of_data.contains("\""+index+".")) {
//                    processData(current_line_of_data, index);
//                    index++;
//                }
            }
        }
    }
    
    private void processData(String data){
        //store data in csv file
    }
    
    private URL createURL() throws MalformedURLException {
        return new URL("https://www.alphavantage.co/query?function=DIGITAL_CURRENCY_DAILY&symbol="+crypto_alias+"&market="+to_currency+"&apikey="+API_KEY);
    }
    
}
