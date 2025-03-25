package com.gbsw.tree.domain.post.img.service;

import com.gbsw.tree.domain.post.img.filter.ImageProcessingException;
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

        if (!file.exists()) {
            throw new ImageProcessingException(type + " 폴더가 존재하지 않습니다: " + file.getAbsolutePath());
        }
        if (!file.isDirectory()) {
            throw new ImageProcessingException(type + " 경로가 디렉토리가 아닙니다: " + file.getAbsolutePath());
        }
        if (!file.canRead()) {
            throw new ImageProcessingException(type + " 폴더를 읽을 수 없습니다. 권한 문제일 수 있습니다: " + file.getAbsolutePath());
        }

        if (files == null) {
            throw new ImageProcessingException(type + " 폴더의 파일 목록을 읽을 수 없습니다: " + file.getAbsolutePath());
        }

        return Arrays.stream(files)
                .filter(File::isFile)
                .collect(Collectors.toList());
                .map(File::getName)
    }
}
