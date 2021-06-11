package sj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sj.entity.IssueRecord;
import sj.entity.StockinRecord;
import sj.repo.impl.IssueRecordRepoImpl;
import sj.repo.impl.StockinRecordRepoImpl;

@Service
public class StockinService {
    @Autowired
    private StockinRecordRepoImpl stockinRecordRepo;
    @Autowired
    private IssueRecordRepoImpl issueRecordRepo;

    @Transactional
    public void insertOrUpdateStockinRecord(StockinRecord stockinRecord, int userId) {
        stockinRecord.modifiedBy(userId);
        stockinRecordRepo.insertOrUpdateStockinRecord(stockinRecord);
    }
}
