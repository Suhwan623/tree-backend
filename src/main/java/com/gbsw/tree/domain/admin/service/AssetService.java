package com.gbsw.tree.domain.admin.service;

import com.gbsw.tree.domain.admin.db.AssetRepository;
import com.gbsw.tree.domain.admin.model.Asset;
import com.gbsw.tree.domain.admin.model.CreateAssetDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class AssetService {
    @Value("${image.directory}")
    private String imageDir;

    private final AssetRepository assetRepository;

    public Asset CreateAsset(CreateAssetDto dto) throws Exception {
        Path filepath = Paths.get(imageDir + "/" + dto.getType() + "/" + dto.getImgName());

        if(!Files.exists(filepath)) {
            throw new Exception("이미지 파일이 존재하지 않습니다");
        }
        Asset asset = Asset.builder()
                .type(dto.getType())
                .imgName(dto.getImgName())
                .build();

        return assetRepository.save(asset);
    }
}
