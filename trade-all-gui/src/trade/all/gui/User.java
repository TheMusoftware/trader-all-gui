/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trade.all.gui;

/**
 *
 * @author musoftware
 */
public class User {
    String name;
    String userName;
    String password;
    public User(String name, String userName, String password){
     this.name = name;
     this.userName = userName;
     this.password = password;
    }
    public String getName(){
        return name;
    }
    public String getUserName(){
        return userName;
    }
    public String getPassword(){
        return password;
    }
    @Override
    public String toString(){
        String data = name+"<>"+userName+"<>"+password;
        return data;
    }
    private double usdBalance = 0;
    private double eurBalance = 0;
    private double gbpBalance = 0;
    private double xauBalance = 0;
    
    private double usdDeposit = 0;
    private double eurDeposit = 0;
    private double gbpDeposit = 0;
    private double xauDeposit = 0;
    private double totalDeposit = 0;

    
    
    private double usdValue = 0;
    private double eurValue = 0;
    private double gbpValue = 0;
    private double xauValue = 0;
    private double totalValue = 0;

    public double getTotalDeposit() {
        return totalDeposit;
    }

    public void setTotalDeposit(double totalDeposit) {
        this.totalDeposit = totalDeposit;
    }
    
    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }
    
    

    public double getUsdBalance() {
        return usdBalance;
    }

    public void setUsdBalance(double usdBalance) {
        this.usdBalance = usdBalance;
    }

    public double getEurBalance() {
        return eurBalance;
    }

    public void setEurBalance(double eurBalance) {
        this.eurBalance = eurBalance;
    }

    public double getGbpBalance() {
        return gbpBalance;
    }

    public void setGbpBalance(double gbpBalance) {
        this.gbpBalance = gbpBalance;
    }

    public double getXauBalance() {
        return xauBalance;
    }

    public void setXauBalance(double xauBalance) {
        this.xauBalance = xauBalance;
    }

    public double getUsdDeposit() {
        return usdDeposit;
    }

    public void setUsdDeposit(double usdDeposit) {
        this.usdDeposit = usdDeposit;
    }

    public double getEurDeposit() {
        return eurDeposit;
    }

    public void setEurDeposit(double eurDeposit) {
        this.eurDeposit = eurDeposit;
    }

    public double getGbpDeposit() {
        return gbpDeposit;
    }

    public void setGbpDeposit(double gbpDeposit) {
        this.gbpDeposit = gbpDeposit;
    }

    public double getXauDeposit() {
        return xauDeposit;
    }

    public void setXauDeposit(double xauDeposit) {
        this.xauDeposit = xauDeposit;
    }

    public double getUsdValue() {
        return usdValue;
    }

    public void setUsdValue(double usdValue) {
        this.usdValue = usdValue;
    }

    public double getEurValue() {
        return eurValue;
    }

    public void setEurValue(double eurValue) {
        this.eurValue = eurValue;
    }

    public double getGbpValue() {
        return gbpValue;
    }

    public void setGbpValue(double gbpValue) {
        this.gbpValue = gbpValue;
    }

    public double getXauValue() {
        return xauValue;
    }

    public void setXauValue(double xauValue) {
        this.xauValue = xauValue;
    }
    
    
}