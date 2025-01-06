package com.gbsw.tree.domain.post.img.controller;

import com.gbsw.tree.domain.post.img.service.ImgService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/image")
public class ImgController {

    private final ImgService imgService;

    @GetMapping
    public List<String> getImageListByType(@RequestParam(value = "type", required = true) String type) {
        return imgService.getImageListByType(type);
    }
}
