package sj.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import js.utils.ErrorEnum;
import js.utils.ErrorException;
import js.utils.Info;
import js.utils.SearchCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sj.dto.SupplierDTO;
import sj.entity.Supplier;
import sj.service.SupplierService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/supplier")
@CrossOrigin @Slf4j
public class SupplierController {
    @Autowired private SupplierService supplierService;

    @GetMapping("/findAll")
    @ResponseBody @Info
    public JSONObject findAll() {
        JSONObject responseInfo = new JSONObject();
        List<Supplier> suppliers = supplierService.findAll();
        responseInfo.put("suppliers", new SupplierDTO().parseList(suppliers, SupplierDTO.class, Supplier.class));
        return responseInfo;
    }

    @PostMapping("/findAll/by")
    @ResponseBody @Info
    public JSONObject findAllBy(@RequestBody JSONObject requestBody) throws ErrorException {
        JSONObject responseInfo = new JSONObject();
        List<Supplier> suppliers = new ArrayList<>();
        SearchCommand searchCommand = null;
        List<SearchCommand> searchCommands = null;
        try {
            searchCommand = requestBody.getObject("searchCommand", SearchCommand.class);
        } catch (Exception noCommand) {
            log.debug("未找到单一条件");
            try {
              searchCommands = requestBody.getJSONArray("searchCommands").toJavaList(SearchCommand.class);
            } catch (Exception noCommands) {
              throw new ErrorException(ErrorEnum.UNSPECIFIED);
            }
        }

        if(searchCommand!=null) {
            suppliers = supplierService.findAllBy(searchCommand);
        }

        responseInfo.put("suppliers", new SupplierDTO().parseList(suppliers, SupplierDTO.class, Supplier.class));
        return responseInfo;
    }

}
