package com.learndesign.concurrencydbo;

import com.learndesign.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface OptimisticLockingRepo extends JpaRepository<Inventory, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Inventory i SET i.quantity = :quantity, i.version = i.version + 1 WHERE i.id = :id AND i.version = :version")
    int updateWithOptimisticLock(@Param("id") Long id, @Param("quantity") int quantity, @Param("version") int version);
}
