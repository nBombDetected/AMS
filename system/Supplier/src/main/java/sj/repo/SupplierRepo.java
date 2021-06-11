package sj.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import sj.entity.Supplier;

import java.util.List;

public interface SupplierRepo extends JpaRepository<Supplier, Integer>, JpaSpecificationExecutor<Supplier> {
    List<Supplier> findByNameIsLike(String name);
}
