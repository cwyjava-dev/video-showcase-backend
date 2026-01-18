package com.videoshowcase.controller;

import com.videoshowcase.entity.Video;
import com.videoshowcase.service.VideoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
@Tag(name = "视频管理", description = "视频相关接口")
public class VideoController {
    private final VideoService videoService;

    @GetMapping
    @Operation(summary = "获取视频列表")
    public ResponseEntity<List<Video>> getAllVideos() {
        return ResponseEntity.ok(videoService.getAllVideos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取视频详情")
    public ResponseEntity<Video> getVideoById(@PathVariable Long id) {
        return ResponseEntity.ok(videoService.getVideoById(id));
    }

    @GetMapping("/search")
    @Operation(summary = "搜索视频")
    public ResponseEntity<List<Video>> searchVideos(@RequestParam String keyword) {
        return ResponseEntity.ok(videoService.searchVideos(keyword));
    }

    @PostMapping("/{id}/views")
    @Operation(summary = "增加观看次数")
    public ResponseEntity<String> incrementViews(@PathVariable Long id) {
        videoService.incrementViews(id);
        return ResponseEntity.ok("观看次数已增加");
    }
}
