package com.example.geekster.project.StockManagementApplication.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.geekster.project.StockManagementApplication.Model.Stock;
import com.example.geekster.project.StockManagementApplication.Model.StockType;
import com.example.geekster.project.StockManagementApplication.Repository.IStockRepo;

import jakarta.transaction.Transactional;

@Service
public class StockService {

    @Autowired
    private IStockRepo stockRepo;

    // Get stocks by type
    public List<Stock> getStocksByType(StockType stockType) {
        return stockRepo.findByStockType(stockType);
    }

    // Add new stocks
    public String addStocks(List<Stock> stockList) {
        stockRepo.saveAll(stockList);
        return "Stocks added successfully";
    }

    // Get stocks above price and before date
    public List<Stock> getStocksAbovePriceAndLowerDate(Double price, String date) {
        LocalDateTime parsedDate = LocalDateTime.parse(date);
        return stockRepo.findByStockPriceGreaterThanAndStockBirthTimeStampLessThanOrderByStockName(
                price, parsedDate
        );
    }

    // Update market cap
    @Transactional
    public void updateMarketCap(Double marketCap, Integer id) {
        stockRepo.updateMarketCapById(marketCap, id);
    }

    // Delete stocks by owner count
    @Transactional
    public void deleteStocksBasedOnCount(Integer count) {
        stockRepo.deleteStocksBasedOnCount(count);
    }

    // Update full stock by ID
    @Transactional
    public void updateStockById(Integer id, Stock stock) {
        stockRepo.updateStockById(id, stock);
    }
}
