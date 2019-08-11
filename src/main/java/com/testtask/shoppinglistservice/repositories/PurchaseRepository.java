package com.testtask.shoppinglistservice.repositories;


import com.testtask.shoppinglistservice.domain.PurchaseObject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PurchaseRepository extends JpaRepository<PurchaseObject, Long> {

    List<PurchaseObject>findByCategory(String cat);

}
