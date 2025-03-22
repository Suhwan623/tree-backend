package com.gbsw.tree.domain.admin.controller;

import com.gbsw.tree.domain.admin.model.Asset;
import com.gbsw.tree.domain.admin.model.CreateAssetDto;
import com.gbsw.tree.domain.admin.model.CreateAssetResult;
import com.gbsw.tree.domain.admin.service.AssetService;
import com.gbsw.tree.domain.user.model.User;
import com.gbsw.tree.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

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

    @GetMapping("/asset")
    public ResponseEntity<Map<String, Object>> getAssetId(
            @RequestParam String imgName,
            @RequestParam String type,
            Principal principal) {
        try {
            User user = userService.getUser(principal.getName());
            // assetService.getAsset 호출
            Long assetId = assetService.getAsset(imgName, type);

            // 성공 응답
            Map<String, Object> response = new HashMap<>();
            response.put("id", assetId);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            // Asset가 존재하지 않을 경우
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Asset not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
}
