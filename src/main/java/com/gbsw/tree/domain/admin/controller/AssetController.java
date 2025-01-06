package com.gbsw.tree.domain.admin.controller;

import com.gbsw.tree.domain.admin.model.Asset;
import com.gbsw.tree.domain.admin.model.CreateAssetDto;
import com.gbsw.tree.domain.admin.model.CreateAssetResult;
import com.gbsw.tree.domain.admin.service.AssetService;
import com.gbsw.tree.domain.user.model.User;
import com.gbsw.tree.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AssetController {

    private final AssetService assetService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<CreateAssetResult> createAsset(
            @RequestBody @Valid CreateAssetDto dto,
            Principal principal) {
        try {
            User user = userService.getUser(principal.getName());
            Asset asset = assetService.CreateAsset(dto);
            return ResponseEntity.ok(new CreateAssetResult("ok", asset.getId()));
        } catch (Exception e) {
            // 예외 처리
            return ResponseEntity.badRequest().body(new CreateAssetResult(e.toString(), null));
        }
    }
}
