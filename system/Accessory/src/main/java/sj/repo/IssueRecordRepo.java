package sj.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import sj.entity.IssueRecord;

import java.util.List;

public interface IssueRecordRepo extends JpaRepository<IssueRecord, Integer>, JpaSpecificationExecutor<IssueRecord> {
    IssueRecord findByIdAndValidIs(int id, boolean valid);
    List<IssueRecord> findByFinishedAndValidIs(boolean finished, boolean valid);
}
