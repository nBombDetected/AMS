package sj.dto;

import js.utils.DTO;
import lombok.Data;
import sj.entity.ConsumptionRecord;

import java.text.DateFormat;
import java.util.Locale;

@Data
public class ConsumptionRecordDTO implements DTO<ConsumptionRecordDTO, ConsumptionRecord> {
    private int id;
    private long itemId;
    private String itemName;
    private int amount;
    private String receiver;
    private String comment;
    private String modifier;
    private String modifyTimestamp;

    public ConsumptionRecordDTO(){}
    public ConsumptionRecordDTO(ConsumptionRecord consumptionRecord) {
        id = consumptionRecord.getId();
        itemId = consumptionRecord.getItemId();
        try {
            itemName = consumptionRecord.getAccessory().getName();
        } catch (Exception ignore) {}

        receiver = consumptionRecord.getReceiver();
        amount = consumptionRecord.getAmount();
        comment = consumptionRecord.getComment();
        try {
            modifier = consumptionRecord.getModifier().getRealName();
        } catch (Exception ignore) {}

        modifyTimestamp = DateFormat
                .getDateInstance(DateFormat.LONG, Locale.CHINA)
                .format(consumptionRecord.getModifyTimestamp());
    }
}
