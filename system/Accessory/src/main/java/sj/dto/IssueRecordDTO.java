package sj.dto;

import js.utils.DTO;
import lombok.Data;
import sj.entity.IssueRecord;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

@Data
public class IssueRecordDTO implements DTO<IssueRecordDTO, IssueRecord> {
    private int id;
    private long itemId;
    private String itemName;
    private String unitOfMeasurement;
    private int issueAmount;
    private int reclaimedAmount;
    private String claimer;
    private String modifier;
    private String modifyTimestamp;
    private String reclaimTimestamp;

    public IssueRecordDTO(){}
    public IssueRecordDTO(IssueRecord issueRecord){
        id = issueRecord.getId();
        itemId = issueRecord.getItemId();
        try {
            itemName = issueRecord.getAccessory().getName();
            unitOfMeasurement = issueRecord.getAccessory().getUnitOfMeasurement();
        } catch (Exception ignore) {}
        issueAmount = issueRecord.getIssueAmount();
        reclaimedAmount = issueRecord.getReclaimedAmount();
        claimer = issueRecord.getClaimer();

        try {
            modifier = issueRecord.getModifier().getRealName();
        } catch (Exception ignore) {}

        modifyTimestamp = DateFormat
                .getDateInstance(DateFormat.LONG, Locale.CHINA)
                .format(issueRecord.getModifyTimestamp());

        if(issueRecord.getReclaimTimestamp().compareTo(new Date(0))==0) {
            reclaimTimestamp = "-";
        } else {
            reclaimTimestamp = DateFormat
                    .getDateInstance(DateFormat.LONG, Locale.CHINA)
                    .format(issueRecord.getReclaimTimestamp());
        }
    }
}
