package com.testtask.shoppinglistservice.repositories;


import com.testtask.shoppinglistservice.domain.Purchase;
import com.testtask.shoppinglistservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    List<Purchase>findByCategory(String cat);

    List<Purchase>findByAuthor(User author);

    void deleteById(Long id);

}
