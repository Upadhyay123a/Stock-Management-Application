package com.example.geekster.project.StockManagementApplication.Controller;

import com.example.geekster.project.StockManagementApplication.Model.Stock;
import com.example.geekster.project.StockManagementApplication.Model.StockType;
import com.example.geekster.project.StockManagementApplication.Service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    // GET: Stocks by type
    @GetMapping("/type/{stockType}")
    public ResponseEntity<List<Stock>> getStocksByType(@PathVariable StockType stockType) {
        List<Stock> stocks = stockService.getStocksByType(stockType);
        return stocks.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(stocks);
    }

    // GET: Stocks above price and before date
    @GetMapping("/filter")
    public ResponseEntity<List<Stock>> getStocksAbovePriceAndLowerDate(
            @RequestParam Double price,
            @RequestParam String date) {

        return ResponseEntity.ok(stockService.getStocksAbovePriceAndLowerDate(price, date));
    }

    // POST: Add new stocks
    @PostMapping
    public ResponseEntity<String> addStocks(@RequestBody List<Stock> stocks) {
        return ResponseEntity.ok(stockService.addStocks(stocks));
    }

    // PUT: Update market cap
    @PutMapping("/{id}/market-cap")
    public ResponseEntity<Void> updateMarketCap(
            @PathVariable Integer id,
            @RequestParam Double marketCap) {

        stockService.updateMarketCap(marketCap, id);
        return ResponseEntity.noContent().build();
    }

    // PUT: Update full stock by ID
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateStockById(
            @PathVariable Integer id,
            @RequestBody Stock stock) {

        stockService.updateStockById(id, stock);
        return ResponseEntity.noContent().build();
    }

    // DELETE: Stocks by owner count
    @DeleteMapping("/owner-count/{count}")
    public ResponseEntity<Void> deleteByOwnerCount(@PathVariable Integer count) {
        stockService.deleteStocksBasedOnCount(count);
        return ResponseEntity.noContent().build();
    }
}
