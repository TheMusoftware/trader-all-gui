/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package trade.all.gui;

import java.awt.event.ItemListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author musoftware
 */
public class MainPage extends javax.swing.JFrame {

    /**
     * Creates new form MainPage
     */
    User user;
    public void setUser(User user){
        this.user = user;
    }
    String selected = "";
    public void updateSelected(){
        if(usd.isSelected()) selected = "USD";
        else if(eur.isSelected()) selected = "EUR";
        else if(gbp.isSelected()) selected = "GBP";
        else if(xau.isSelected()) selected = "XAU";
        else selected = "";
    }
    public void updatePrices() throws IOException{
        usdPrice.setText(CurrenciesPrice.getUSD());
        eurPrice.setText(CurrenciesPrice.getEUR());
        gbpPrice.setText(CurrenciesPrice.getGBP());
        xauPrice.setText(CurrenciesPrice.getXAU());
        
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss  dd MMMM yy");
        Date date = new Date();
        lastTime.setText("Last updated: "+sdf.format(date));

    }
    public MainPage(User user) throws IOException {
       
        initComponents();
        this.setLocationRelativeTo(null);
        setUser(user);
        userInfo.setText(user.getName());
        updatePrices();
        FileOperations.updateInfos(user);

    }
    public MainPage(){
        initComponents();
    }
    public double getAmount(boolean buy){
        String operation = buy ? "Buy" : "Sell";
        double amount = 0;
        if(!selected.equals("")){
          String userEntered = JOptionPane.showInputDialog(rootPane, "Enter Amount", operation+" " +selected, 3);  
          
          try{
              if(buy){
                  amount = Double.parseDouble(userEntered);
              }
              else{
                  amount = Double.parseDouble(userEntered) * -1;
              }
              
          }
          catch(Exception e){
                          JOptionPane.showMessageDialog(rootPane, "Please enter a valid amount", "Warning", 2);
                          amount = getAmount(buy);

          }
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "Please select the currency to "+operation.toLowerCase()+".", "Warning", 2);
            return 0;
        }
       
        
        return amount;
    }
    
    public double getPrice() throws IOException{
        double price;
        updatePrices();
        if(usd.isSelected()) price = Double.parseDouble(usdPrice.getText());
        else if(eur.isSelected()) price = Double.parseDouble(eurPrice.getText());
        else if(gbp.isSelected()) price = Double.parseDouble(gbpPrice.getText());
        else if(xau.isSelected()) price = Double.parseDouble(xauPrice.getText());
        else price = 0;
        int s = 0;
        if(price != 0){
             s = JOptionPane.showConfirmDialog(rootPane, "Do you want to use the current price? \nCurrent Price: "+price);
        }
        
        switch(s){
            case 0 :
                break;
            case 1:
                String prc = JOptionPane.showInputDialog(rootPane, "Enter price");
                try{
                    price = Double.parseDouble(prc);
                    
                }
                catch (Exception e){
                price = getPrice();
            }
                break;
            case 2:
                price = 0;
                break;
            default :
                break;
                
        }
        return price;
    }
    public void setInfo(String code, double amount, double price){
        switch(code){
            case "USD":
                user.setUsdBalance(user.getUsdBalance() + amount);
                user.setUsdDeposit(user.getUsdDeposit() + price * amount);
                user.setUsdValue(user.getUsdBalance() * getCurrentPrice(code));
                break;
            case "EUR":
                user.setEurBalance(user.getEurBalance() + amount);
                user.setEurDeposit(user.getEurDeposit() + price * amount);
                user.setEurValue(user.getEurBalance() * getCurrentPrice(code));
                break;
            case "GBP":
                user.setGbpBalance(user.getGbpBalance() + amount);
                user.setGbpDeposit(user.getGbpDeposit() + price * amount);
                user.setGbpValue(user.getGbpBalance() * getCurrentPrice(code));
                break;
            case "XAU":
                user.setXauBalance(user.getXauBalance() + amount);
                user.setXauDeposit(user.getXauDeposit() + price * amount);
                user.setXauValue(user.getXauBalance() * getCurrentPrice(code));
                break;
            default:
                break;
                
        }
        
    }
    public double getCurrentPrice(String code){
        switch(code){
            case "USD":
                return Double.parseDouble(usdPrice.getText());
            case "EUR":
                return Double.parseDouble(eurPrice.getText());
            case "GBP":
                return Double.parseDouble(gbpPrice.getText());
            case "XAU":
                return Double.parseDouble(xauPrice.getText());
        }
        return 0.0;
    }
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        currenciesList = new javax.swing.ButtonGroup();
        eur = new javax.swing.JRadioButton();
        xau = new javax.swing.JRadioButton();
        usd = new javax.swing.JRadioButton();
        gbp = new javax.swing.JRadioButton();
        usdIcon = new javax.swing.JLabel();
        eurIcon = new javax.swing.JLabel();
        gbpICon = new javax.swing.JLabel();
        xauIcon = new javax.swing.JLabel();
        userInfo = new javax.swing.JLabel();
        cPrice = new javax.swing.JLabel();
        lastTime = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        operationsArea = new javax.swing.JTextArea();
        buyButton = new javax.swing.JButton();
        sellButton = new javax.swing.JButton();
        usdPrice = new javax.swing.JLabel();
        eurPrice = new javax.swing.JLabel();
        gbpPrice = new javax.swing.JLabel();
        xauPrice = new javax.swing.JLabel();
        summary = new javax.swing.JRadioButton();
        investment = new javax.swing.JToggleButton();
        logOut = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        currenciesList.add(eur);
        eur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eurMouseClicked(evt);
            }
        });

        currenciesList.add(xau);
        xau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                xauMouseClicked(evt);
            }
        });

        currenciesList.add(usd);
        usd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usdMouseClicked(evt);
            }
        });

        currenciesList.add(gbp);
        gbp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gbpMouseClicked(evt);
            }
        });

        usdIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/trade/all/gui/usd.png"))); // NOI18N

        eurIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/trade/all/gui/eur.png"))); // NOI18N

        gbpICon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/trade/all/gui/gbp.png"))); // NOI18N

        xauIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/trade/all/gui/gold.png"))); // NOI18N

        userInfo.setFont(new java.awt.Font("AppleGothic", 0, 13)); // NOI18N
        userInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/trade/all/gui/user.png"))); // NOI18N
        userInfo.setText("User Full Name");

        cPrice.setFont(new java.awt.Font("AppleGothic", 0, 13)); // NOI18N
        cPrice.setText("Current Price");

        lastTime.setFont(new java.awt.Font("AppleGothic", 0, 10)); // NOI18N
        lastTime.setText("Last Updated: hh:mm:ss   dd/mm/yy ");

        operationsArea.setEditable(false);
        operationsArea.setColumns(20);
        operationsArea.setFont(new java.awt.Font("AppleGothic", 0, 13)); // NOI18N
        operationsArea.setRows(5);
        operationsArea.setText("Please select currency.");
        jScrollPane1.setViewportView(operationsArea);

        buyButton.setFont(new java.awt.Font("AppleGothic", 0, 13)); // NOI18N
        buyButton.setText("Buy");
        buyButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buyButtonMouseClicked(evt);
            }
        });

        sellButton.setFont(new java.awt.Font("AppleGothic", 0, 13)); // NOI18N
        sellButton.setText("Sell");
        sellButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sellButtonMouseClicked(evt);
            }
        });

        usdPrice.setFont(new java.awt.Font("AppleGothic", 0, 13)); // NOI18N
        usdPrice.setText("usdPrice");

        eurPrice.setFont(new java.awt.Font("AppleGothic", 0, 13)); // NOI18N
        eurPrice.setText("eurPrice");

        gbpPrice.setFont(new java.awt.Font("AppleGothic", 0, 13)); // NOI18N
        gbpPrice.setText("gbpPrice");

        xauPrice.setFont(new java.awt.Font("AppleGothic", 0, 13)); // NOI18N
        xauPrice.setText("xauPrice");

        currenciesList.add(summary);
        summary.setFont(new java.awt.Font("AppleGothic", 0, 13)); // NOI18N
        summary.setText("Show Summary");
        summary.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                summaryMouseClicked(evt);
            }
        });

        currenciesList.add(investment);
        investment.setFont(new java.awt.Font("AppleGothic", 0, 13)); // NOI18N
        investment.setText("Investment Propal");
        investment.setToolTipText("");
        investment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                investmentMouseClicked(evt);
            }
        });

        logOut.setFont(new java.awt.Font("AppleGothic", 0, 12)); // NOI18N
        logOut.setText("Click here to log out");
        logOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logOutMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cPrice))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(buyButton)
                                        .addGap(106, 106, 106)
                                        .addComponent(sellButton))
                                    .addComponent(investment)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE))
                                .addGap(18, 18, 18))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(summary)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lastTime)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(logOut))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(userInfo)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(eur, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(usd, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(gbp, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(xau, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(gbpICon, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(eurIcon, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(usdIcon, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(xauIcon, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(usdPrice)
                                            .addComponent(eurPrice)
                                            .addComponent(gbpPrice)
                                            .addComponent(xauPrice))))))))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(cPrice)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lastTime)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(usdIcon)
                                            .addComponent(usdPrice))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(eurIcon))
                                    .addComponent(eurPrice))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(gbpICon)
                                    .addComponent(gbpPrice)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(usd)
                                .addGap(18, 18, 18)
                                .addComponent(eur)
                                .addGap(18, 18, 18)
                                .addComponent(gbp)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(xauIcon)
                            .addComponent(xau)
                            .addComponent(xauPrice, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buyButton)
                    .addComponent(sellButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(userInfo)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(summary)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(investment)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logOut)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buyButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buyButtonMouseClicked
        String code = selected;
        double amount = getAmount(true);
      double price = 0;
        try {
            price = getPrice();
        } catch (IOException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            FileOperations.buy(user, code, amount, price);
        } catch (IOException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if(code.equals("")){
                operationsArea.setText("");
                currenciesList.clearSelection();
            }
            else{
                operationsArea.setText(FileOperations.getInfo(user,code));
            }
            
        } catch (IOException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        setInfo(code,amount,price);
    }//GEN-LAST:event_buyButtonMouseClicked

    private void sellButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sellButtonMouseClicked
      String code = selected;
      double amount = getAmount(false);
      double price = 0;
        try {
            price = getPrice();
        } catch (IOException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            FileOperations.sell(user, code, amount, price);
        } catch (IOException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if(code.equals("")){
                operationsArea.setText("");
                currenciesList.clearSelection();
            }
            else{
                operationsArea.setText(FileOperations.getInfo(user,code));
            }
        } catch (IOException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        setInfo(code,amount,price);
    }//GEN-LAST:event_sellButtonMouseClicked

    private void investmentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_investmentMouseClicked
    currenciesList.clearSelection();
    updateSelected();

try {
    // TODO add your handling code here:
    updatePrices();
} catch (IOException ex) {
    Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
}

double usdPriceValue = getCurrentPrice("USD");
double eurPriceValue = getCurrentPrice("EUR");
double gbpPriceValue = getCurrentPrice("GBP");
double xauPriceValue = getCurrentPrice("XAU");

try {
    FileOperations.setAllInfo(user, usdPriceValue, eurPriceValue, gbpPriceValue, xauPriceValue);
} catch (IOException ex) {
    Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
}

StringBuilder sb = new StringBuilder();

double averageUSD = user.getUsdBalance() != 0 ? user.getUsdDeposit() / user.getUsdBalance() : 0;
double averageEUR = user.getEurBalance() != 0 ? user.getEurDeposit() / user.getEurBalance() : 0;
double averageGBP = user.getGbpBalance() != 0 ? user.getGbpDeposit() / user.getGbpBalance() : 0;
double averageXAU = user.getXauBalance() != 0 ? user.getXauDeposit() / user.getXauBalance() : 0;

if(user.getUsdBalance() < 0)averageUSD*=-1;
if(user.getEurBalance() < 0)averageEUR*=-1;
if(user.getGbpBalance() < 0)averageGBP*=-1;
if(user.getXauBalance() < 0)averageXAU*=-1;


double lossRate = 0.8;
double profitRate = 1.25;

String loss = "Gradually buy and lower your average.";
String profit = "There is no loss of money. If you want, you can switch to gradual sales.";
String patient = "Don't be impatient, investment means transferring money from the impatient to the patient.";

sb.append("USD: ");
if (!Double.isNaN(averageUSD) && usdPriceValue != 0 && averageUSD != 0 && usdPriceValue / averageUSD <= lossRate) {
    sb.append(loss + "\n");
} else if (!Double.isNaN(averageUSD) && usdPriceValue != 0 && averageUSD != 0 && usdPriceValue / averageUSD >= profitRate) {
    sb.append(profit + "\n");
} else {
    sb.append(patient + "\n");
}
sb.append("Average: (with earned profits) ").append(String.format("%.2f", averageUSD)).append("\n\n");

sb.append("EUR: ");
if (!Double.isNaN(averageEUR) && eurPriceValue != 0 && averageEUR != 0 && eurPriceValue / averageEUR <= lossRate) {
    sb.append(loss + "\n");
} else if (!Double.isNaN(averageEUR) && eurPriceValue != 0 && averageEUR != 0 && eurPriceValue / averageEUR >= profitRate) {
    sb.append(profit + "\n");
} else {
    sb.append(patient + "\n");
}
sb.append("Average: (with earned profits)").append(String.format("%.2f", averageEUR)).append("\n\n");

sb.append("GBP: ");
if (!Double.isNaN(averageGBP) && gbpPriceValue != 0 && averageGBP != 0 && gbpPriceValue / averageGBP <= lossRate) {
    sb.append(loss + "\n");
} else if (!Double.isNaN(averageGBP) && gbpPriceValue != 0 && averageGBP != 0 && gbpPriceValue / averageGBP >= profitRate) {
    sb.append(profit + "\n");
} else {
    sb.append(patient + "\n");
}
sb.append("Average: (with earned profits)").append(String.format("%.2f", averageGBP)).append("\n\n");

sb.append("XAU: ");
if (!Double.isNaN(averageXAU) && xauPriceValue != 0 && averageXAU != 0 && xauPriceValue / averageXAU <= lossRate) {
    sb.append(loss + "\n");
} else if (!Double.isNaN(averageXAU) && xauPriceValue != 0 && averageXAU != 0 && xauPriceValue / averageXAU >= profitRate) {
    sb.append(profit + "\n");
} else {
    sb.append(patient + "\n");
}
sb.append("Average: (with earned profits)").append(String.format("%.2f", averageXAU)).append("\n\n");

operationsArea.setText(sb.toString());


        
    }//GEN-LAST:event_investmentMouseClicked

    private void usdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usdMouseClicked
        // TODO add your handling code here:
        updateSelected();
        operationsArea.setText("No buy/sell transactions found.");
        try {
           operationsArea.setText(FileOperations.getInfo(this.user, "USD"));
        } catch (IOException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
         
        }
        
    }//GEN-LAST:event_usdMouseClicked

    private void eurMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eurMouseClicked
        // TODO add your handling code here:
        updateSelected();
        operationsArea.setText("No buy/sell transactions found.");
        try {
            operationsArea.setText(FileOperations.getInfo(this.user, "EUR"));
        } catch (IOException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace(); // Hatanın ayrıntılarını konsola yazdır

            
        }
       
    }//GEN-LAST:event_eurMouseClicked

    private void gbpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gbpMouseClicked
        // TODO add your handling code here:
        updateSelected();
        operationsArea.setText("No buy/sell transactions found.");
        try {
           operationsArea.setText(FileOperations.getInfo(this.user, "GBP"));
        } catch (IOException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       
    }//GEN-LAST:event_gbpMouseClicked

    private void xauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xauMouseClicked
        // TODO add your handling code here:
        updateSelected();
        operationsArea.setText("No buy/sell transactions found.");
        try {
           operationsArea.setText(FileOperations.getInfo(this.user, "XAU"));
        } catch (IOException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       
    }//GEN-LAST:event_xauMouseClicked

    private void summaryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_summaryMouseClicked
        // TODO add your handling code here:
        updateSelected();
        try {
            updatePrices();
        } catch (IOException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        double usdPriceValue = Double.parseDouble(usdPrice.getText());
        double eurPriceValue = Double.parseDouble(eurPrice.getText());
        double gbpPriceValue = Double.parseDouble(gbpPrice.getText());
        double xauPriceValue = Double.parseDouble(xauPrice.getText());

        operationsArea.setText("No buy/sell transactions found");
        try {
            operationsArea.setText(FileOperations.setAllInfo(user,usdPriceValue,eurPriceValue,gbpPriceValue,xauPriceValue));
        } catch (IOException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_summaryMouseClicked

    private void logOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logOutMouseClicked
        loginAndSignin loginPage = new loginAndSignin();
        this.setVisible(false);
        this.dispose();
        loginPage.setVisible(true);
    }//GEN-LAST:event_logOutMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
       java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buyButton;
    private javax.swing.JLabel cPrice;
    private javax.swing.ButtonGroup currenciesList;
    private javax.swing.JRadioButton eur;
    private javax.swing.JLabel eurIcon;
    private javax.swing.JLabel eurPrice;
    private javax.swing.JRadioButton gbp;
    private javax.swing.JLabel gbpICon;
    private javax.swing.JLabel gbpPrice;
    private javax.swing.JToggleButton investment;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lastTime;
    private javax.swing.JLabel logOut;
    private javax.swing.JTextArea operationsArea;
    private javax.swing.JButton sellButton;
    private javax.swing.JRadioButton summary;
    private javax.swing.JRadioButton usd;
    private javax.swing.JLabel usdIcon;
    private javax.swing.JLabel usdPrice;
    private javax.swing.JLabel userInfo;
    private javax.swing.JRadioButton xau;
    private javax.swing.JLabel xauIcon;
    private javax.swing.JLabel xauPrice;
    // End of variables declaration//GEN-END:variables
}
