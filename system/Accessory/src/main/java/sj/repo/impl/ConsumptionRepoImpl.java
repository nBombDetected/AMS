package sj.repo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sj.entity.ConsumptionRecord;
import sj.repo.ConsumptionRecordRepo;

import java.util.List;

@Component
public class ConsumptionRepoImpl {
    @Autowired private ConsumptionRecordRepo consumptionRecordRepo;

    public ConsumptionRecord findByIdAndValid(int id, boolean valid) {
        return consumptionRecordRepo.findByIdAndValidIs(id, valid);
    };

    public List<ConsumptionRecord> findAll() {
        return consumptionRecordRepo.findAll();
    }

    public void insertOrUpdateConsumptionRecord(ConsumptionRecord consumptionRecord) {
        ConsumptionRecord consumptionRecordInDB = consumptionRecordRepo.findByIdAndValidIs(consumptionRecord.getId(), true);
        if(consumptionRecordInDB !=null) {
            consumptionRecordInDB.setValid(false);
            consumptionRecordRepo.saveAndFlush(consumptionRecordInDB);
        }
        consumptionRecordRepo.save(consumptionRecord);
    }
}
