package com.gbsw.tree.domain.admin.db;

import com.gbsw.tree.domain.admin.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssetRepository extends JpaRepository<Asset, Long> {
    Optional<Asset> findByImgNameAndType(String imgName, String type);
}
