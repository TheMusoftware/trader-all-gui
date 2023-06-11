package trade.all.gui;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author musoftware
 */
public class CurrenciesPrice {
    private static String USD = "https://kur.doviz.com/serbest-piyasa/amerikan-dolari";
    private static String EUR = "https://kur.doviz.com/serbest-piyasa/euro";
    private static  String XAU = "https://altin.doviz.com/gram-altin";
    private static  String GBP = "https://kur.doviz.com/serbest-piyasa/sterlin";
    
    
   private static String getPrice(String page) throws IOException{
       Document d =  Jsoup.connect(page).get();
       Elements e = d.selectXpath("/html/body/div[3]/div[2]/div[1]/div[6]/div[1]/div/div/table/tbody/tr[3]/td[3]");
        String price = e.text();
        price = price.replace(".","");
        price = price.replace(",",".");
        return price;
   }
   public static String getUSD() throws IOException{
       return getPrice(USD);
   }
   public static String getEUR() throws IOException{
       return getPrice(EUR);
   }
   public static String getGBP() throws IOException{
       return getPrice(GBP);
   }
   public static String getXAU() throws IOException{
       return getPrice(XAU);
   }
        

}
