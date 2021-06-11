package sj.repo.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SupplierRepoImplTest {

    @Autowired private SupplierRepoImpl supplierRepo;
    @Test
    public void findAllByNameLike() {
        System.out.println(supplierRepo.findAllByNameLike("测试"));
    }
}