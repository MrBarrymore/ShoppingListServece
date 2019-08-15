package com.testtask.shoppinglistservice.controller;

import com.testtask.shoppinglistservice.domain.PurchaseObject;
import com.testtask.shoppinglistservice.domain.User;
import com.testtask.shoppinglistservice.repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;
import java.util.Set;

@Controller
public class MainController {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/main")
    public String GetList(@RequestParam(required = false, defaultValue = "") String filter, Model model) {

        Iterable<PurchaseObject> purchases = purchaseRepository.findAll();

        if (filter != null && !filter.isEmpty()) {
            purchases = purchaseRepository.findByCategory(filter);
        } else {
            purchases = purchaseRepository.findAll();
        }

        model.addAttribute("purchases", purchases);
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @Valid PurchaseObject purchaseObject,
            BindingResult bindingResult,
            Model model)
    {
        purchaseObject.setAuthor(user);

        if (bindingResult.hasErrors()) {

            Map<String, String> erorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(erorsMap);
            model.addAttribute("purchaseObject", purchaseObject);

        } else {
            purchaseRepository.save(purchaseObject);
        }

        model.addAttribute("purchaseObject", null);

        Iterable<PurchaseObject> purchases = purchaseRepository.findAll();
        model.addAttribute("purchases", purchases);

        return "main";
    }


    @GetMapping("/user-purchases/{user}")
    public String getListOfPurchases(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            Model model,
            @RequestParam(required = false) PurchaseObject purchase
    ) {

        Set<PurchaseObject> purchases = user.getPurchases();


        model.addAttribute("purchases", purchases);
        model.addAttribute("isCurrentUser", currentUser.equals(user));

        return "main";
    }


    @PostMapping("/user-purchases/{user}")
    public String updatePurchase(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long user,
            @RequestParam("id") PurchaseObject purchase,
            @RequestParam("name") String name,
            @RequestParam("category") String category,
            @RequestParam("description") String description,
            @RequestParam("cost") String cost,
            @RequestParam("purchaseDate") String date
    ) {

        if (purchase.getAuthor().equals(currentUser)) {

            if (!StringUtils.isEmpty(name)) purchase.setName(name);
            if (!StringUtils.isEmpty(name)) purchase.setCategory(category);
            if (!StringUtils.isEmpty(name)) purchase.setDescription(description);
            if (!StringUtils.isEmpty(name)) purchase.setCost(cost);
//            if (!StringUtils.isEmpty(name)) purchase.setPurchaseDate(date);

            purchaseRepository.save(purchase);
        }

        return "redirect:/user-purchase/" + user;
    }

}
