package sj.controller;

import com.alibaba.fastjson.JSONObject;
import js.utils.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sj.dto.StockinRecordDTO;
import sj.entity.StockinRecord;
import sj.service.StockinService;

@RestController
@RequestMapping("/stockin")
public class StockinController {
    @Autowired private StockinService stockinService;

    @PostMapping("/insert")
    @ResponseBody @Info
    public JSONObject insert(
            @RequestBody JSONObject requestBody,
            @RequestHeader(value = "X-user-id") int userId) {
        JSONObject responseInfo = new JSONObject();
        StockinRecordDTO stockinRecordDTO = requestBody.getObject("stockinRecord", StockinRecordDTO.class);
        StockinRecord stockinRecord = stockinRecordDTO.parseStockinRecord();
        stockinService.insertOrUpdateStockinRecord(stockinRecord, userId);
        return responseInfo;
    }
}
