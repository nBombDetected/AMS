package sj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sj.entity.Accessory;
import sj.repo.impl.AccessoryRepoImpl;

import java.util.List;

@Service
public class AccessoryService {
    @Autowired
    private AccessoryRepoImpl accessoryRepo;

    @Transactional
    public void insertOrUpdateAccessory(Accessory accessory, int userId) {
        accessory.modifiedBy(userId);
        accessoryRepo.insertOrUpdateAccessory(accessory);
    }


    public void stockout(long itemId, int amount) {
        Accessory accessory = accessoryRepo.findByValidAndItemId(true, itemId);
        accessory.stockout(amount);
        accessoryRepo.save(accessory);
    }

    public List<Accessory> findAll() {
        return accessoryRepo.findAllByValid(true);
    }
    public boolean findItemId(long itemId) {return accessoryRepo.existsByItemIdAndValid(itemId, true);}
}
