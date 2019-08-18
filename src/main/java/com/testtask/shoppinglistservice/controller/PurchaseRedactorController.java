package com.testtask.shoppinglistservice.controller;


import com.testtask.shoppinglistservice.domain.Purchase;
import com.testtask.shoppinglistservice.domain.User;
import com.testtask.shoppinglistservice.repositories.PurchaseRepository;
import com.testtask.shoppinglistservice.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
public class PurchaseRedactorController {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/main")
    public String addPurchase(
            @AuthenticationPrincipal User currentUser,
            @Valid Purchase purchase,
            BindingResult bindingResult,
            Model model)
    {
        model = purchaseService.addPurchase(currentUser, purchase, bindingResult, model);
        return "main";
    }

    @GetMapping("/user-purchase/{user}")
    public String updatePurchase(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            Model model,
            @RequestParam Purchase purchase
    ) {

        Set<Purchase> purchases = user.getPurchases();
        model.addAttribute("purchases", purchases);
        model.addAttribute("purchase", purchase);
        model.addAttribute("isCurrentUser", currentUser.equals(user));

        return "updatePurchase";
    }

    @PostMapping("/updatePurchase")
    public String updatePurchase(
            @AuthenticationPrincipal User currentUser,
            @RequestParam("id") Purchase purchase,
            @RequestParam("name") String name,
            @RequestParam("category") String category,
            @RequestParam("description") String description,
            @RequestParam("cost") String cost,
            @RequestParam("purchaseDate") String date,
            Model model
    ) {

        model = purchaseService.updatePurchase(currentUser, purchase,
                                name, category, description, cost, date, model);

        return "updatePurchase";
    }


    @GetMapping("/delete-purchase/{user}")
    public String deletePurchase(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            @RequestParam Purchase purchase,
            Model model
    ) {
        purchaseRepository.deleteById(purchase.getId());
        List<Purchase> purchases = purchaseRepository.findByAuthor(currentUser);
        model.addAttribute("purchases", purchases);

        return "main";
    }

}
