package sj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sj.dto.StockoutRecordDTO;
import sj.entity.ConsumptionRecord;
import sj.entity.IssueRecord;
import sj.repo.impl.AccessoryRepoImpl;
import sj.repo.impl.ConsumptionRepoImpl;
import sj.repo.impl.IssueRecordRepoImpl;

@Service
public class StockoutService {
    @Autowired
    private ConsumptionRepoImpl consumptionRecordRepo;
    @Autowired
    private IssueRecordRepoImpl issueRecordRepo;
    @Autowired
    private AccessoryRepoImpl accessoryRepo;

    @Transactional
    public void stockout(StockoutRecordDTO stockoutRecord, int userId) {
        int stockoutTypeCode = stockoutRecord.getTypeCode();

        if(stockoutTypeCode==StockoutRecordDTO.CONSUMPTION) {
            ConsumptionRecord consumptionRecord = stockoutRecord.parseConsumptionRecord();
            insertOrUpdateConsumptionRecord(consumptionRecord, userId);
        }
        if(stockoutTypeCode==StockoutRecordDTO.ISSUE) {
            IssueRecord issueRecord = stockoutRecord.parseIssueRecord();
            insertOrUpdateIssueRecord(issueRecord, userId);
        }
    }

    @Transactional
    public void insertOrUpdateConsumptionRecord(ConsumptionRecord consumptionRecord, int userId) {
        consumptionRecord.modifiedBy(userId);
        consumptionRecordRepo.insertOrUpdateConsumptionRecord(consumptionRecord);
    }

    @Transactional
    public void insertOrUpdateIssueRecord(IssueRecord issueRecord, int userId) {
        issueRecord.modifiedBy(userId);
        issueRecordRepo.insertOrUpdateIssueRecord(issueRecord);
    }
}
