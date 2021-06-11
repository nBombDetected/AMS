package sj.service;

import js.utils.SearchCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sj.entity.Supplier;
import sj.repo.impl.SupplierRepoImpl;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SupplierService {
    @Autowired private SupplierRepoImpl supplierRepo;

    public List<Supplier> findAll() {
        return supplierRepo.findAll();
    }

    public List<Supplier> findAllBy(SearchCommand searchCommand) {
        log.debug("开始执行条件搜索");
        String column = searchCommand.getColumn();
        switch (column) {
            case "name":
                String name = searchCommand.getContent();
                return supplierRepo.findAllByNameLike(name);
            default:
                log.debug("搜索列错误");
                return new ArrayList<Supplier>();
        }
    }
}
