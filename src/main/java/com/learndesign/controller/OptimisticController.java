package com.learndesign.controller;


import com.learndesign.concurrencydto.InventoryDto;
import com.learndesign.concurrencyserviceImpl.OptimisticLockingServiceImpl;
import com.learndesign.entities.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CountDownLatch;

@RestController
@RequestMapping("/optimistic")
@ComponentScan
public class OptimisticController {

    @Autowired
    OptimisticLockingServiceImpl optimisticLockingService;


    @GetMapping("/simulate")
    public String simulateConcurrency(@RequestParam Long id) throws InterruptedException {
        int threads = 5;
        CountDownLatch latch = new CountDownLatch(threads);

        for (int i = 0; i < threads; i++) {
            optimisticLockingService.updateInventory(id,4,latch);
        }
        return "Simulation complete!";
      //  latch.await(); // Wait for all threads to complete

    }

    /*@PostMapping("/update/inventory")
    public String saveInventory(@RequestBody InventoryDto inventory) {
        System.out.println(inventory.getId());

        optimisticLockingService.updateInventory(inventory.getId(), inventory.getQuantity());
        return "Updated Suceessfully";
    }*/

    @GetMapping("/hellotest")
    public String getStringInfo()
    {
        return "hello";
    }

}
