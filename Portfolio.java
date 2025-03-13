package mypackage;

import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    private Map<String, Integer> stocks;
    private double cash;

    public Portfolio(double initialCash) {
        stocks = new HashMap<>();
        cash = initialCash;
    }

    public void addStock(String symbol, int amount) {
        stocks.put(symbol, stocks.getOrDefault(symbol, 0) + amount);
    }

    public void removeStock(String symbol, int amount) {
        if (stocks.containsKey(symbol) && stocks.get(symbol) >= amount) {
            stocks.put(symbol, stocks.get(symbol) - amount);
            if (stocks.get(symbol) == 0) {
                stocks.remove(symbol);
            }
        }
    }

    public void addCash(double amount) {
        cash += amount;
    }

    public void deductCash(double amount) {
        cash -= amount;
    }

    public double getCash() {
        return cash;
    }

    public Map<String, Integer> getStocks() {
        return stocks;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Portfolio:\n");
        for (Map.Entry<String, Integer> entry : stocks.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append(" shares\n");
        }
        sb.append("Cash: $").append(String.format("%.2f", cash)).append("\n");
        return sb.toString();
    }
}


