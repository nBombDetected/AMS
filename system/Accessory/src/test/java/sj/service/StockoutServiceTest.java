package sj.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sj.dto.StockoutRecordDTO;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class StockoutServiceTest {
    @Autowired
    StockoutService stockoutService;

    @Test
    public void stockout(){
        StockoutRecordDTO stockoutRecord = new StockoutRecordDTO(
                900000001,
                "测试物品",
                10,
                StockoutRecordDTO.ISSUE,
                "仝牧宇",
                "测试"
        );
        stockoutService.stockout(stockoutRecord, 1);
    }
}