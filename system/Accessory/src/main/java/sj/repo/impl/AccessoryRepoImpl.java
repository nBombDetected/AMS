package sj.repo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sj.entity.Accessory;
import sj.repo.AccessoryRepo;

import java.util.List;

@Component
public class AccessoryRepoImpl {
    @Autowired private AccessoryRepo accessoryRepo;

    public Accessory findByValidAndItemId(boolean flag, long itemId) {
        return accessoryRepo.findByValidAndItemIdIs(flag, itemId);
    };

    public List<Accessory> findAllByValid(boolean flag) {
        return accessoryRepo.findByValid(flag);
    };

    public boolean existsByItemIdAndValid(long itemId, boolean flag) {
        return accessoryRepo.existsByItemIdAndValidIs(itemId, flag);
    };

    public void save(Accessory accessory) {
        accessoryRepo.save(accessory);
    }

    public void insertOrUpdateAccessory(Accessory accessory) {
        Accessory accessoryInDB = accessoryRepo.findByValidAndItemIdIs(true, accessory.getItemId());
        if(accessoryInDB!=null){
            accessoryInDB.setValid(null);
            accessoryRepo.saveAndFlush(accessoryInDB);
        }

        accessory.setValid(true);
        accessoryRepo.save(accessory);
    }
}
