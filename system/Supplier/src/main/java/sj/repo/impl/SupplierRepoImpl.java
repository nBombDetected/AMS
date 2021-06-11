package sj.repo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sj.entity.Supplier;
import sj.repo.SupplierRepo;

import java.util.List;

@Component
public class SupplierRepoImpl {
    @Autowired private SupplierRepo supplierRepo;

    public List<Supplier> findAll(){
        return supplierRepo.findAll();
    }

    public List<Supplier> findAllByNameLike(String name) {
        return supplierRepo.findByNameIsLike("%"+name+"%");
    }
}
