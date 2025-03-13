package mypackage;

import java.util.Scanner;

public class StockTradingPlatform {
    private StockMarket market;
    private Portfolio portfolio;
    private Scanner scanner;

    public StockTradingPlatform() {
        market = new StockMarket();
        portfolio = new Portfolio(10000.0);  
        scanner = new Scanner(System.in);
    }

    private void displayMenu() {
        System.out.println("1. View Market Data");
        System.out.println("2. View Portfolio");
        System.out.println("3. Buy Stock");
        System.out.println("4. Sell Stock");
        System.out.println("5. Exit");
        System.out.print("Enter choice: ");
    }

    private void handleBuyStock() {
        System.out.print("Enter stock symbol to buy: ");
        String symbol = scanner.nextLine().toUpperCase();
        System.out.print("Enter amount: ");
        int amount = scanner.nextInt();
        scanner.nextLine(); 

        Stock stock = market.getStock(symbol);
        if (stock != null) {
            double cost = stock.getPrice() * amount;
            if (cost <= portfolio.getCash()) {
                portfolio.addStock(symbol, amount);
                portfolio.deductCash(cost);
                System.out.println("Bought " + amount + " shares of " + symbol + ".");
            } else {
                System.out.println("Insufficient cash.");
            }
        } else {
            System.out.println("Stock not found.");
        }
    }

    private void handleSellStock() {
        System.out.print("Enter stock symbol to sell: ");
        String symbol = scanner.nextLine().toUpperCase();
        System.out.print("Enter amount: ");
        int amount = scanner.nextInt();
        scanner.nextLine(); 

        Stock stock = market.getStock(symbol);
        if (stock != null) {
            if (portfolio.getStocks().containsKey(symbol) && portfolio.getStocks().get(symbol) >= amount) {
                double revenue = stock.getPrice() * amount;
                portfolio.removeStock(symbol, amount);
                portfolio.addCash(revenue);
                System.out.println("Sold " + amount + " shares of " + symbol + ".");
            } else {
                System.out.println("Insufficient shares.");
            }
        } else {
            System.out.println("Stock not found.");
        }
    }

    public void start() {
        while (true) {
            market.updateMarket();
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    System.out.println(market);
                    break;
                case 2:
                    System.out.println(portfolio);
                    break;
                case 3:
                    handleBuyStock();
                    break;
                case 4:
                    handleSellStock();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static void main(String[] args) {
        StockTradingPlatform platform = new StockTradingPlatform();
        platform.start();
    }
}






