package sj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sj.entity.ConsumptionRecord;
import sj.entity.IssueRecord;

@Data
@AllArgsConstructor @NoArgsConstructor
public class StockoutRecordDTO {
    public static int CONSUMPTION = 0;
    public static int ISSUE = 1;

    private long itemId;
    private String itemName;
    private int amount;
    private int typeCode;
    private String receiver;
    private String comment;

    public ConsumptionRecord parseConsumptionRecord() {
        return new ConsumptionRecord(itemId, amount, receiver, comment);
    }

    public IssueRecord parseIssueRecord() {
        return new IssueRecord(itemId, amount, receiver, comment);
    }
}
