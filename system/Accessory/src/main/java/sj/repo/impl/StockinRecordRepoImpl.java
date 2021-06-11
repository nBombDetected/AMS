package sj.repo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sj.entity.StockinRecord;
import sj.repo.StockinRecordRepo;

@Component
public class StockinRecordRepoImpl {
    @Autowired private StockinRecordRepo stockinRecordRepo;

    public StockinRecord findByIdAndValid(int id, boolean valid) {
        return stockinRecordRepo.findByIdAndValidIs(id, valid);
    };

    public void insertOrUpdateStockinRecord(StockinRecord stockinRecord) {
        StockinRecord stockinRecordInDB = stockinRecordRepo.findByIdAndValidIs(stockinRecord.getId(), true);
        if(stockinRecordInDB!=null) {
            stockinRecordInDB.setValid(false);
            stockinRecordRepo.saveAndFlush(stockinRecordInDB);
        }
        stockinRecordRepo.save(stockinRecord);
    }
}
