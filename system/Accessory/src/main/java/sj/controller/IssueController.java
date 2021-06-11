package sj.controller;

import com.alibaba.fastjson.JSONObject;
import js.utils.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sj.dto.AccessoryDTO;
import sj.dto.IssueRecordDTO;
import sj.entity.Accessory;
import sj.entity.IssueRecord;
import sj.service.IssueService;

import java.util.List;

@RestController
@RequestMapping("/issue")
@CrossOrigin @Slf4j
public class IssueController {
    @Autowired
    IssueService issueService;

    @GetMapping("/findAll/unfinished")
    public JSONObject findAllUnfinished(){
        JSONObject responseInfo = new JSONObject();
        List<IssueRecord> issueRecords = issueService.findAllUnfinished();
        responseInfo.put(
                "issueRecords",
                new IssueRecordDTO().parseList(issueRecords, IssueRecordDTO.class, IssueRecord.class)
        );
        return responseInfo;
    }

    @PostMapping("/reclaim")
    @ResponseBody @Info
    public JSONObject issueRecordReclaim(
            @RequestBody JSONObject requestBody,
            @RequestHeader(value = "X-user-id") int userId){
        JSONObject responseInfo = new JSONObject();
        int issueRecordId = requestBody.getInteger("issueRecordId");
        int reclaimAmount = requestBody.getInteger("reclaimAmount");

        issueService.insertIssueRecordReclaim(issueRecordId, reclaimAmount, userId);

        return responseInfo;
    }
}
