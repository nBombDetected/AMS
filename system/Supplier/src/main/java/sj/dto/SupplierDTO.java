package sj.dto;

import js.utils.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sj.entity.Supplier;

@Data
@AllArgsConstructor
public class SupplierDTO implements DTO<SupplierDTO, Supplier> {
    private long id;
    private String name;
    private long phone;

    public SupplierDTO(){}
    public SupplierDTO(Supplier supplier) {
        id = supplier.getId();
        name = supplier.getName();
        phone = supplier.getPhone();
    }
}
