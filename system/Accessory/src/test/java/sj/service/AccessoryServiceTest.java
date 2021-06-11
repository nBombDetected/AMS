package sj.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sj.repo.AccessoryRepo;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AccessoryServiceTest {
    @Autowired private AccessoryRepo accessoryRepo;
    @Autowired private AccessoryService accessoryService;
    @Test
    public void findAll() {
        System.out.println(accessoryRepo.findByValid(true));
    }

    @Test
    public void findItemId() {
        System.out.println(accessoryRepo.existsByItemIdAndValidIs(900000001, true));
    }

    @Test
    public void findItem() {
        System.out.println(accessoryRepo.findByValidAndItemIdIs(true, 900000001));
    }
}