package sj.controller;

import com.alibaba.fastjson.JSONObject;
import js.utils.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sj.dto.StockoutRecordDTO;
import sj.service.StockoutService;

@RestController
@RequestMapping("/stockout")
@CrossOrigin @Slf4j
public class StockoutController {
    @Autowired
    StockoutService stockoutService;

    @PostMapping("/insertStockoutRecord")
    @ResponseBody
    @Info
    public JSONObject insertStockoutRecord(
            @RequestBody JSONObject requestBody,
            @RequestHeader(value = "X-user-id") int userId) {
        JSONObject responseInfo = new JSONObject();

        StockoutRecordDTO stockoutRecord = requestBody.getObject("stockoutRecord", StockoutRecordDTO.class);
        stockoutService.stockout(stockoutRecord, userId);

        return responseInfo;
    }
}
