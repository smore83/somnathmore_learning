package com.Backend.pragramming.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Finance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Long totalAmount;
    private Long recurringAmount;
    @OneToMany(mappedBy = "finance" ,cascade = CascadeType.ALL)
    private List<Income> source;


}
