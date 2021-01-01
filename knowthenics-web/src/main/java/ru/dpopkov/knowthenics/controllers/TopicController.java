package ru.dpopkov.knowthenics.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/topics")
public class TopicController {

    @RequestMapping({"", "/", "list", "list.html", "index", "index.html"})
    public String list() {
        return "topics/list";
    }
}
