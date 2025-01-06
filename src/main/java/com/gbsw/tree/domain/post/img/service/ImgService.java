package com.gbsw.tree.domain.post.img.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImgService {
    @Value("${image.directory}")
    private String imageDir;

    public List<String> getImageListByType(String type){
        File file = new File(imageDir + "/" + type);
        File[] files = file.listFiles();

        if (files == null) {
            throw new RuntimeException(type + " 폴더를 읽을 수 없습니다.");
        }

        return Arrays.stream(files)
                .filter(File::isFile)
                .map(File::getName)
                .collect(Collectors.toList());
    }
}
