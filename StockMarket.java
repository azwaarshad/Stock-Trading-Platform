package mypackage;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class StockMarket {
    private Map<String, Stock> marketStocks;
    private Random random;

    public StockMarket() {
        marketStocks = new HashMap<>();
        random = new Random();
        initializeMarket();
    }

    private void initializeMarket() {
        marketStocks.put("AAPL", new Stock("AAPL", 150.0));
        marketStocks.put("GOOGL", new Stock("GOOGL", 2800.0));
        marketStocks.put("TSLA", new Stock("TSLA", 700.0));
        marketStocks.put("AMZN", new Stock("AMZN", 3400.0));
    }

    public void updateMarket() {
        for (Stock stock : marketStocks.values()) {
            double change = 0.95 + (1.05 - 0.95) * random.nextDouble();
            stock.setPrice(stock.getPrice() * change);
        }
    }

    public Stock getStock(String symbol) {
        return marketStocks.get(symbol);
    }

    public Map<String, Stock> getMarketStocks() {
        return marketStocks;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Market Data:\n");
        for (Stock stock : marketStocks.values()) {
            sb.append(stock).append("\n");
        }
        return sb.toString();
    }
}


