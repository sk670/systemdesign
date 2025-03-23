package com.learndesign.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.*;
import org.springframework.stereotype.Component;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {

    @Id
    private Long id;
    private int quantity;

    @Version// Enables optimistic locking
    @Column(name="OPTLOCK")
    private int version;

}
