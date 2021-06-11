package sj.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import sj.entity.Accessory;

import java.util.List;

public interface AccessoryRepo extends JpaRepository<Accessory, Integer>, JpaSpecificationExecutor<Accessory> {
    Accessory findByValidAndItemIdIs(boolean flag, long itemId);
    List<Accessory> findByValid(boolean flag);
    boolean existsByItemIdAndValidIs(long itemId, boolean flag);
    boolean existsByItemId(long itemId);
}
