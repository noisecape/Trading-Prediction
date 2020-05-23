/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trading_prediction;

import java.net.MalformedURLException;
import java.util.LinkedList;

/**
 *
 * @author tommasocapecchi
 */
public class Trading_Prediction {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException {
        Trading_Prediction price_forcaster = new Trading_Prediction();
        CurrentPriceDataFetcher fetcher = new CurrentPriceDataFetcher("BTC","USD");
        DailyPriceDataFetcher dailyFetcher = new DailyPriceDataFetcher("BTC","USD");
        price_forcaster.get_current_price(fetcher.getCrypto());
    }
    
    public void get_current_price(Crypto crypto){
        System.out.println(crypto.getExchange_rate());
    }
}
