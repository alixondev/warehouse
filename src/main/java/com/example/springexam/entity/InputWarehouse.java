package com.example.springexam.entity;

import com.example.springexam.entity.Abs.AbsLongEntity;
import lombok.*;

import javax.persistence.*;
import java.security.Timestamp;
import java.util.List;

@Entity(name = "input_warehouse")
@Data
@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InputWarehouse extends AbsLongEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Warehouse warehouse;

    private String name;

//    @OneToOne(fetch = FetchType.LAZY)
//    private List<InputProduct> inputProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    private CurrencyType currencyType;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Provider provider;

    private Integer factureNum;
    @Column(unique = true)
    private Long uniqeId;


}



