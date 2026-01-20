package com.example.geekster.project.StockManagementApplication.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.geekster.project.StockManagementApplication.Model.Stock;
import com.example.geekster.project.StockManagementApplication.Model.StockType;

@Repository
public interface IStockRepo extends JpaRepository<Stock, Integer> {

    // Get stocks by type
    List<Stock> findByStockType(StockType stockType);

    // Get stocks above price and before date
    List<Stock> findByStockPriceGreaterThanAndStockBirthTimeStampLessThanOrderByStockName(
            Double price, LocalDateTime date);

    // Update market cap
    @Modifying
    @Query("update Stock s set s.stockMarketCap = :cap where s.stockId = :id")
    void updateMarketCapById(@Param("cap") Double cap, @Param("id") Integer id);

    // Delete by owner count
    @Modifying
    @Query("delete from Stock s where s.stockOwnerCount <= :count")
    void deleteStocksBasedOnCount(@Param("count") Integer count);

    // Update stock fully by ID
    @Modifying
    @Query("update Stock s set s.stockName=:#{#stock.stockName}, s.stockPrice=:#{#stock.stockPrice}, " +
           "s.stockOwnerCount=:#{#stock.stockOwnerCount}, s.stockType=:#{#stock.stockType}, " +
           "s.stockMarketCap=:#{#stock.stockMarketCap}, s.stockBirthTimeStamp=:#{#stock.stockBirthTimeStamp} " +
           "where s.stockId = :id")
    void updateStockById(@Param("id") Integer id, @Param("stock") Stock stock);
}
