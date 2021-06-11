package sj.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sj.dto.ConsumptionRecordDTO;
import sj.entity.ConsumptionRecord;
import sj.service.ConsumptionService;

import java.util.List;

@RestController
@RequestMapping("/consumption")
@CrossOrigin @Slf4j
public class ConsumptionController {
    @Autowired
    private ConsumptionService consumptionService;

    @GetMapping("/findAll")
    public JSONObject findAll() {
        JSONObject responseInfo = new JSONObject();
        List<ConsumptionRecord> consumptionRecords = consumptionService.findAll();
        responseInfo.put(
                "consumptionRecords",
                new ConsumptionRecordDTO()
                        .parseList(consumptionRecords, ConsumptionRecordDTO.class, ConsumptionRecord.class)
        );
     return responseInfo;
    }
}
