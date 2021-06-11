package sj.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import sj.entity.StockinRecord;

public interface StockinRecordRepo extends JpaRepository<StockinRecord, Integer>, JpaSpecificationExecutor<StockinRecord> {
    StockinRecord findByIdAndValidIs(int id, boolean valid);
}
