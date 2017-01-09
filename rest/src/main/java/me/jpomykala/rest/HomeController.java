package me.jpomykala.rest;

import lombok.NonNull;
import me.jpomykala.model.tag.Tag;
import me.jpomykala.repository.tag.TagRepository;
import me.jpomykala.service.tag.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

/**
 * Created by Evelan on 11/06/16.
 */
@Controller
public class HomeController {

    @NonNull
    private final TagService tagService;

    @Autowired
    public HomeController(TagService tagService) {
        this.tagService = tagService;
    }

    @RequestMapping("/")
    public String home() {
        return "redirect:/swagger-ui.html";
    }


    @PostConstruct
    public void insertTags() {
        tagService.create("hello");
        tagService.create("world");
    }
}
