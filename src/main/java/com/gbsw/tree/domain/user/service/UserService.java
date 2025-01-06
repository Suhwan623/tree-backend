package com.gbsw.tree.domain.user.service;


import com.gbsw.tree.domain.admin.db.AssetRepository;
import com.gbsw.tree.domain.admin.model.Asset;
import com.gbsw.tree.domain.user.filter.DataNotFoundException;
import com.gbsw.tree.domain.user.model.CreateTreeDto;
import com.gbsw.tree.domain.user.model.CreateUserDto;
import com.gbsw.tree.domain.user.model.UpdateUserDto;
import com.gbsw.tree.domain.user.model.User;
import com.gbsw.tree.domain.user.db.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AssetRepository assetRepository;

    public User createUser(CreateUserDto dto) {
        User user = User.builder()
                .password(passwordEncoder.encode(dto.getPassword()))
                .username(dto.getUsername())
                .nickname(dto.getNickname())
                .email(dto.getEmail())
                .build();

        return userRepository.save(user);
    }

    public User getUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new DataNotFoundException("User not found"));
        return user;
    }


    public void UpdateUser(User user, UpdateUserDto dto) {
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setNickname(dto.getNickname());
        user.setEmail(dto.getEmail());

        userRepository.save(user);
    }

    public User CreateTree(User user, CreateTreeDto dto) {

        Asset background = assetRepository.findById(dto.getBackground())
                .orElseThrow(() -> new IllegalArgumentException("Background not found with ID: " + dto.getBackground()));

        Asset tree = assetRepository.findById(dto.getTree())
                .orElseThrow(() -> new IllegalArgumentException("Tree not found with ID: " + dto.getTree()));

        Asset treePoint = assetRepository.findById(dto.getTreePoint())
                .orElseThrow(() -> new IllegalArgumentException("Tree point not found with ID: " + dto.getTreePoint()));

        user.setBackground(background);
        user.setTree(tree);
        user.setTreePoint(treePoint);

        return userRepository.save(user);
    }

    public List<String> getUserTreeById(User user) {
        List<Asset> assets = Arrays.asList(user.getBackground(), user.getTree(), user.getTreePoint());
        List<String> result = new ArrayList<>();

        for (Asset asset : assets) {
            if (asset == null) {
                result.add(null);
            } else {
                result.add(asset.getImgName());
            }
        }

        return result;
    }
}
