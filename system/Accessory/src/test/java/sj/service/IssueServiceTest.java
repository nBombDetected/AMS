package sj.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sj.entity.IssueRecord;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class IssueServiceTest {
    @Autowired private IssueService issueService;

    @Test
    public void insertIssueRecordReclaim() {
        int issueRecordId = 0;
        int issueAmount = 10;
        int userId = 1;
        issueService.insertIssueRecordReclaim(issueRecordId, issueAmount, userId);
    }
}