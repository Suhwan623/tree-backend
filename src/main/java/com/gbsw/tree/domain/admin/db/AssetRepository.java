package com.gbsw.tree.domain.admin.db;

import com.gbsw.tree.domain.admin.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset, Long> {
}
