package ru.dpopkov.knowthenics.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/companies")
public class CompanyController {

    @RequestMapping({"", "/", "list", "list.html", "index", "index.html"})
    public String list() {
        return "companies/list";
    }
}
