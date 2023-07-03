package trade.all.gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author musoftware
 */
public class FileOperations {
    static String usersFile = "src/trade/all/gui/users.txt";
    static String logsFile = "src/trade/all/gui/logs.txt";
    public static void addUser(User user) throws IOException{
        File file = new File(usersFile);
        FileWriter fWriter = new FileWriter(file,true);
        PrintWriter writer = new PrintWriter(fWriter);
        writer.println(user.toString());
        writer.close();
        fWriter.close();
    }
    public static String findUserLine(String username) throws FileNotFoundException, IOException {
    File file = new File(usersFile);
    if (!file.exists()) {
        file.createNewFile();
    }
    FileReader reader = new FileReader(file);
    BufferedReader bfReader = new BufferedReader(reader);
    String line = bfReader.readLine();
    while (line != null) {
        if (line.contains(username)) {
            break;
        }
        line = bfReader.readLine();
    }
    bfReader.close();
    reader.close();
    return line;
}
    
   public static void buy(User user, String code, double amount, double price) throws IOException{
       if(amount <= 0 || price <= 0) return;
       SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss  dd MMMM yy");
        Date date = new Date();
       File file = new File(logsFile);
       FileWriter fWriter = new FileWriter(file,true);
       PrintWriter writer = new PrintWriter(fWriter);
       
       /*String prc = String.valueOf(price);
       int start = prc.indexOf(".");
       int length = prc.length();
       while(length - start <= 3){
           prc+="0";
           length = prc.length();
       }
       if(length - start > 3){
           prc = prc.substring(0, start+3);
       }
       
       String amt = String.valueOf(amount);
       start = amt.indexOf(".");
       length = amt.length();
       while(length - start <= 3){
           amt+="0";
           length = amt.length();
       }
       if(length - start > 3){
           amt = amt.substring(0, start+3);
       }*/
       
       
       
       String line = user.getUserName()+"<>"+"true"+"<>"+code+"<>"+amount+"<>"+price+"<>"+sdf.format(date);
       writer.println(line);
       writer.close();
       fWriter.close();
   }
   public static void sell(User user, String code, double amount, double price) throws IOException{
       SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss  dd MMMM yy");
        Date date = new Date();
       File file = new File(logsFile);
       FileWriter fWriter = new FileWriter(file,true);
       PrintWriter writer = new PrintWriter(fWriter);
       String line = user.getUserName()+"<>"+"false"+"<>"+code+"<>"+amount+"<>"+price+"<>"+sdf.format(date);
       writer.println(line);
       writer.close();
       fWriter.close();
       
   }
    
   public static String getInfo(User user, String code) throws FileNotFoundException, IOException{
       File file = new File(logsFile);
       FileReader reader = new FileReader(file);
       BufferedReader bfReader = new BufferedReader(reader);
       ArrayList <String> lines = new ArrayList<>();
       String line = bfReader.readLine();
       while(line != null){
       
           if(line.contains(user.getUserName())){
               lines.add(line);
           }
           
           line = bfReader.readLine();
       }
       bfReader.close();
       reader.close();
       
       if(lines.isEmpty()) return "No buy/sell transactions found";
       StringBuilder sb = new StringBuilder();
       for(int i = 0; i < lines.size(); i++){
           if(lines.get(i).contains(code)){
               sb.append(lines.get(i)+"\n");
           }
       }
       String [] infos = sb.toString().split("\n");
       if(infos.length == 0) return "No buy/sell transactions found";
       StringBuilder inf = new StringBuilder();
       for(int i = 0; i < infos.length; i++){
           String [] printedLine = infos[i].split("<>");
           /*
           0 - UserName
           1 - Buy/Sell
           2 - Code
           3 - Amount
           4 - Price
           5 - Date
           */
           if(Double.parseDouble(printedLine[3]) != 0 || Double.parseDouble(printedLine[4]) != 0){
               inf.append(String.format("Amount: %.2f   \t Price: %.2f   \t Total: %.2f   \t Date: %s%n",
                Double.valueOf(printedLine[3]), Double.parseDouble(printedLine[4]),
                (Double.parseDouble(printedLine[3]) * Double.parseDouble(printedLine[4])),
                printedLine[5]));
           }
           
               
           }
       
       return inf.toString();
       
       }
   
   
   public static void updateInfos(User user) throws IOException {
    File file = new File(logsFile);
    if(!file.exists()) file.createNewFile();
    FileReader reader = new FileReader(file);
    BufferedReader bfReader = new BufferedReader(reader);
    ArrayList<String> lines = new ArrayList<>();
    String line = bfReader.readLine();
    while (line != null) {
        if (line.contains(user.getUserName())) {
            lines.add(line);
        }
        line = bfReader.readLine();
    }
    bfReader.close();
    reader.close();
    if (lines.isEmpty()) {
        return;
    }
    /*
       0 - UserName
       1 - Buy/Sell
       2 - Code
       3 - Amount
       4 - Price
       5 - Date
    */
    StringBuilder sb = new StringBuilder();
    for (String info : lines) {
        String[] infos = info.split("<>");
        String code = infos[2];
        
        double amount = Double.parseDouble(infos[3]);
        double price = Double.parseDouble(infos[4]);
        
        /*if(infos[1].equals("false")){
            amount = amount*-1;
        }*/

        switch (code) {
            case "USD":
                user.setUsdBalance(user.getUsdBalance() + amount);
                user.setUsdDeposit(user.getUsdDeposit() + amount * price  );
                break;
            case "EUR":
                user.setEurBalance(user.getEurBalance() + amount );
                user.setEurDeposit(user.getEurDeposit() + amount * price);
                break;
            case "GBP":
                user.setGbpBalance(user.getGbpBalance() + amount );
                user.setGbpDeposit(user.getGbpDeposit() + amount * price);
                break;
            case "XAU":
                user.setXauBalance(user.getXauBalance() + amount );
                user.setXauDeposit(user.getXauDeposit() +  amount * price);
                break;
            case "":
                break;
            default:
                break;
        }
    }
}
   
   public static String setAllInfo(User user, double usdPrice, double eurPrice, double gbpPrice, double xauPrice) throws IOException {
    user.setUsdValue(user.getUsdBalance() * usdPrice);
    user.setEurValue(user.getEurBalance() * eurPrice);
    user.setGbpValue(user.getGbpBalance() * gbpPrice);
    user.setXauValue(user.getXauBalance() * xauPrice);
    user.setTotalDeposit(user.getUsdDeposit() + user.getEurDeposit() + user.getGbpDeposit() + user.getXauDeposit());
    user.setTotalValue(user.getUsdValue() + user.getEurValue() + user.getGbpValue() + user.getXauValue());

    StringBuilder sb = new StringBuilder();

    double ratio = user.getTotalDeposit() != 0 ? (user.getTotalValue() / user.getTotalDeposit() * 100) - 100 : 0;
    double earned = user.getTotalValue() - user.getTotalDeposit();

    sb.append("Total profit ratio: ");
    if (user.getTotalDeposit() != 0 && user.getTotalValue() != 0) {
        sb.append(String.format("%.2f%%", ratio));
        sb.append("\nTotal Balance:");
        sb.append(String.format(" %.2f TL",user.getTotalValue()));
    } 
    else if(user.getTotalValue() == 0){
        sb.append("Your balance is 0");
    }
    else {
        sb.append("N/A");
    }
    sb.append("\n");
    sb.append("Total earned: ");
    sb.append(String.format("%.2f TL", earned));
    sb.append("\n\n");

    if (user.getUsdDeposit() != 0) {
        sb.append("---USD---\n");
        ratio = user.getUsdValue() / user.getUsdDeposit() * 100 - 100;
        earned = user.getUsdValue() - user.getUsdDeposit();
        if (user.getUsdValue() != 0) {
            sb.append("USD profit ratio: ");
            sb.append(String.format("%.2f%%", ratio));
            sb.append("\n");
        } else {
            sb.append("(You do not have a balance in this currency.)\n");
        }
        sb.append("USD profit: ");
        sb.append(String.format("%.2f TL", earned));
        sb.append("\n");
        sb.append("USD Balance: ");
        sb.append(user.getUsdBalance());
        sb.append("\n");
        sb.append("---USD---\n\n");
    }

    if (user.getEurDeposit() != 0) {
        sb.append("---EUR---\n");
        ratio = user.getEurValue() / user.getEurDeposit() * 100 - 100;
        earned = user.getEurValue() - user.getEurDeposit();
        sb.append("EUR profit ratio: ");
        if (user.getEurValue() != 0) {
            sb.append(String.format("%.2f%%", ratio));
        } else {
            sb.append("You do not have a balance in this currency.");
        }
        sb.append("\n");
        sb.append("EUR profit: ");
        sb.append(String.format("%.2f TL", earned));
        sb.append("\n");
        sb.append("EUR: Balance: ");
        sb.append(user.getEurBalance());
        sb.append("\n");
        sb.append("---EUR---\n\n");
    }

    if (user.getGbpDeposit() != 0) {
        sb.append("---GBP---\n");
        ratio = user.getGbpValue() / user.getGbpDeposit() * 100 - 100;
        earned = user.getGbpValue() - user.getGbpDeposit();
        sb.append("GBP profit ratio: ");
        if (user.getGbpValue() != 0) {
            sb.append(String.format("%.2f%%", ratio));
        } else {
            sb.append("You do not have a balance in this currency.");
        }
        sb.append("\n");
        sb.append("GBP profit: ");
        sb.append(String.format("%.2f TL", earned));
        sb.append("\n");
        sb.append("GBP balance: ");
        sb.append(user.getGbpBalance());
        sb.append("\n");
        sb.append("---GBP---\n\n");
    }

    if (user.getXauDeposit() != 0 ) {
        sb.append("---XAU---\n");
        ratio = user.getXauValue() / user.getXauDeposit() * 100 - 100;
        earned = user.getXauValue() - user.getXauDeposit();
        sb.append("XAU profit ratio: ");
        if (user.getXauValue() != 0 ) {
            sb.append(String.format("%.2f%%", ratio));
        } else {
            sb.append("You do not have a balance in this currency.");
        }
        sb.append("\n");
        sb.append("XAU profit: ");
        sb.append(String.format("%.2f TL", earned));
        sb.append("\n");
        sb.append("XAU Balance: ");
        sb.append(user.getXauBalance());
        sb.append("\n");
        sb.append("---XAU---\n\n");
    }

    return sb.toString();
}

}
