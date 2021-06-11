package sj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sj.entity.StockinRecord;

@Data
@AllArgsConstructor @NoArgsConstructor
public class StockinRecordDTO {
    private int id;
    private long itemId;
    private String itemName;
    private String unitOfMeasurement;
    private int amount;
    private int supplierId;
    private String comment;

    public StockinRecord parseStockinRecord() {
        return new StockinRecord(id, itemId, amount, supplierId, comment);
    }
}
