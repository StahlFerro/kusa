package com.stahlferro.kusa.controllers.web;

import com.stahlferro.kusa.resource.OLibAuthor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping(path="/olib")
public class WebOLibController {

    private RestTemplate restTemplate = new RestTemplate();

    @RequestMapping(path="/authorinfo")
    public String getRandomB(@RequestParam(name="author", required = true) String authorName, Model model) {
        String url = String.format("http://openlibrary.org/search.json?author=%s", authorName);
        OLibAuthor author = restTemplate.getForObject(url, OLibAuthor.class);
        model.addAttribute("author", author);
        return "authors";
    }
}
