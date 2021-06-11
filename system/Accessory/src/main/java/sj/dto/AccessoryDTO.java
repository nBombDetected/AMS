package sj.dto;

import js.utils.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import sj.entity.Accessory;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Data
public class AccessoryDTO implements DTO<AccessoryDTO, Accessory>{
    private long itemId;
    private String name;
    private String unitOfMeasurement;
    private int amount;
    private String modifierName;
    private String modifyTimestamp;
    public AccessoryDTO(){}
    public AccessoryDTO(Accessory accessory){
        itemId = accessory.getItemId();
        name = accessory.getName();
        unitOfMeasurement = accessory.getUnitOfMeasurement();
        amount = accessory.getAmount();
        try {
            modifierName = accessory.getModifier().getRealName();
        } catch (Exception ignore){}
        modifyTimestamp = DateFormat.getDateInstance(DateFormat.LONG, Locale.CHINA).format(accessory.getModifyTimestamp());
    }
}
