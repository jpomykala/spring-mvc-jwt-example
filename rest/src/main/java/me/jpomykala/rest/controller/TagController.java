package me.jpomykala.rest.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import me.jpomykala.model.tag.Tag;
import me.jpomykala.model.tag.view.TagView;
import me.jpomykala.service.tag.TagService;

import java.util.List;

/**
 * Created by Evelan-E6540 on 02.02.2016.
 */
@Slf4j
@RestController
public class TagController {

    @NonNull
    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    @JsonView(TagView.ListRow.class)
    public List<Tag> getTags(@RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                             @RequestParam(value = "page", required = false, defaultValue = "0") Integer page) {
        PageRequest pageRequest = new PageRequest(page, size);
        return tagService.findAll(pageRequest);
    }

    @RequestMapping(value = "/tags/search", method = RequestMethod.GET)
    @JsonView(TagView.FullView.class)
    public Tag search(@RequestParam(value = "name") String name) {
        return tagService.findByName(name);
    }

    @RequestMapping(value = "/tags", method = RequestMethod.POST)
    @JsonView(TagView.FullView.class)
    @PreAuthorize("hasRole('ROLE_USER')")
    public Tag create(
            @RequestHeader(name = "Authorization") String jwtToken, //just for Swagger
            String name) {
        return tagService.create(name);
    }
}
