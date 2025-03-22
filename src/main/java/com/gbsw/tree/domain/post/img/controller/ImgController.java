package com.gbsw.tree.domain.post.img.controller;

import com.gbsw.tree.domain.post.img.service.ImgService;
import com.gbsw.tree.domain.user.model.User;
import com.gbsw.tree.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/image")
public class ImgController {

    private final ImgService imgService;
    private final UserService userService;

    @GetMapping
    public List<String> getImageListByType(@RequestParam(value = "type", required = true) String type, Principal principal) {
        return imgService.getImageListByType(type);
    }
}
