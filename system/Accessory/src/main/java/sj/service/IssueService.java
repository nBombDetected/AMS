package sj.service;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sj.entity.IssueRecord;
import sj.repo.impl.IssueRecordRepoImpl;

import java.util.List;

@Service @Slf4j
public class IssueService {
    @Autowired
    IssueRecordRepoImpl issueRecordRepo;

    public List<IssueRecord> findAllUnfinished(){
        return issueRecordRepo.findByFinished(false);
    }

    @Transactional
    public void insertIssueRecordReclaim(int issueRecordId, int amount, int userId){
        IssueRecord issueRecord = issueRecordRepo.findById(issueRecordId);
        String issueRecordString = JSONObject.toJSONString(issueRecord);
        IssueRecord updatedIssueRecord = JSONObject.parseObject(issueRecordString, IssueRecord.class);

        updatedIssueRecord.reclaim(amount);
        updatedIssueRecord.modifiedBy(userId);

        log.debug("打印交还物品后的信息：{}", issueRecordRepo);
        issueRecordRepo.insertOrUpdateIssueRecord(updatedIssueRecord);
    }
}
