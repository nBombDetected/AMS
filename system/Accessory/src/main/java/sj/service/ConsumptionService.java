package sj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sj.entity.ConsumptionRecord;
import sj.repo.impl.ConsumptionRepoImpl;

import java.util.List;

@Service
public class ConsumptionService {
    @Autowired
    private ConsumptionRepoImpl consumptionRecordRepo;

    public List<ConsumptionRecord> findAll() {return consumptionRecordRepo.findAll();}
}
