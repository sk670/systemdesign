package com.learndesign.concurrencyserviceImpl;

import com.learndesign.concurrencydbo.OptimisticLockingRepo;
import com.learndesign.entities.Inventory;
import jakarta.persistence.OptimisticLockException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CountDownLatch;


@Service
public class OptimisticLockingServiceImpl {
    private final OptimisticLockingRepo optimisticLockingRepo;


    public OptimisticLockingServiceImpl(OptimisticLockingRepo optimisticLockingRepo) {
        this.optimisticLockingRepo = optimisticLockingRepo;
    }


    @Transactional
    @Async
    public void updateInventory(Long id, int newQuantity, CountDownLatch latch) throws InterruptedException {

        try {
            Inventory inventory = optimisticLockingRepo.findById(id).orElseThrow(() -> new RuntimeException("Inventory not found"));
            int updatedRows = optimisticLockingRepo.updateWithOptimisticLock(id, newQuantity, inventory.getVersion());
            if (updatedRows == 0) {
                throw new OptimisticLockingFailureException("❌ Update failed due to version mismatch!");
            }

            System.out.println("✅ Inventory updated successfully: " + newQuantity);
        } catch (OptimisticLockingFailureException e) {
            System.err.println("❌ Update failed: ");
        } finally {
            latch.countDown();
        }


    }
}
