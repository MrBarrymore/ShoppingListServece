package com.testtask.shoppinglistservice.controller;

import com.testtask.shoppinglistservice.domain.PurchaseObject;
import com.testtask.shoppinglistservice.repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


@Controller
public class MainController {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {

        return "greeting";
    }

    @GetMapping("/main")
    public String GetList(Model model) {

        ModelAndView modelAndView = new ModelAndView();
        Iterable<PurchaseObject> purchases = purchaseRepository.findAll();

        model.addAttribute("purchases", purchases);

        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String text, @RequestParam String cat, Model model)
    {
        PurchaseObject purchase = new PurchaseObject(text, cat);

        purchaseRepository.save(purchase);

        Iterable<PurchaseObject> purchases = purchaseRepository.findAll();
        model.addAttribute("purchases", purchases);

        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {

        Iterable<PurchaseObject> purchases;
        if (filter != null && !filter.isEmpty()) {
            purchases = purchaseRepository.findByCategory(filter);
        } else {
            purchases = purchaseRepository.findAll();
        }
        model.put("purchases", purchases);

        return "main";
    }
}
