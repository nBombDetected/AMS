package sj.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import sj.entity.ConsumptionRecord;

public interface ConsumptionRecordRepo extends JpaRepository<ConsumptionRecord, Integer>, JpaSpecificationExecutor<ConsumptionRecord> {
    ConsumptionRecord findByIdAndValidIs(int id, boolean valid);
}
