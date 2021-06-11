package sj.controller;

import com.alibaba.fastjson.JSONObject;
import js.utils.ErrorException;
import js.utils.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sj.dto.AccessoryDTO;
import sj.entity.Accessory;
import sj.service.AccessoryService;
import java.util.List;

@RestController
@RequestMapping("/accessory")
@CrossOrigin @Slf4j
public class AccessoryController {
    @Autowired
    private AccessoryService accessoryService;

    @PostMapping("/insert")
    @ResponseBody @Info
    public JSONObject insert(
            @RequestBody JSONObject requestBody,
            @RequestHeader(value = "X-user-id") int userId) throws ErrorException {
        JSONObject responseInfo = new JSONObject();
        Accessory accessory = requestBody.getObject("accessory", Accessory.class);

        accessoryService.insertOrUpdateAccessory(accessory, userId);

        return responseInfo;
    }

    @GetMapping("/findAll")
    @ResponseBody @Info
    public JSONObject findAll() {
        JSONObject responseInfo = new JSONObject();
        List<Accessory> accessories = accessoryService.findAll();
        responseInfo.put("accessories", new AccessoryDTO().parseList(accessories, AccessoryDTO.class, Accessory.class));
        return responseInfo;
    }

    @GetMapping("/findItemId")
    @ResponseBody @Info
    public JSONObject findId(@RequestParam long itemId) {
        JSONObject responseInfo = new JSONObject();

        responseInfo.put("itemIdFlag", accessoryService.findItemId(itemId));

        return responseInfo;
    }
}
