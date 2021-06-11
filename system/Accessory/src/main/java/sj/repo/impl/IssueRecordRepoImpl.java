package sj.repo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sj.entity.IssueRecord;
import sj.repo.IssueRecordRepo;

import java.util.List;

@Component
public class IssueRecordRepoImpl {
    @Autowired private IssueRecordRepo issueRecordRepo;

    public IssueRecord findById(int id) {
        return issueRecordRepo.findByIdAndValidIs(id, true);
    };
    public List<IssueRecord> findByFinishedAndValid(boolean finished, boolean valid) {
        return issueRecordRepo.findByFinishedAndValidIs(finished, valid);
    };

    public void insertOrUpdateIssueRecord(IssueRecord issueRecord) {
        IssueRecord issueRecordInDB = issueRecordRepo.findByIdAndValidIs(issueRecord.getId(), true);
        if(issueRecordInDB!=null) {
            issueRecordInDB.setValid(false);
            issueRecordRepo.saveAndFlush(issueRecordInDB);
            issueRecord.setId(issueRecordInDB.getId());
        }
        issueRecordRepo.save(issueRecord);
    }

    public List<IssueRecord> findByFinished(boolean finished){
        return issueRecordRepo.findByFinishedAndValidIs(finished, true);
    }
}
