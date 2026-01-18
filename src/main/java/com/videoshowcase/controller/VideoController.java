package com.videoshowcase.controller;

import com.videoshowcase.entity.Video;
import com.videoshowcase.service.VideoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/videos")
@RequiredArgsConstructor
@Tag(name = "视频管理", description = "视频列表、详情、搜索等接口")
public class VideoController {
    private final VideoService videoService;

    @GetMapping
    @Operation(summary = "获取视频列表")
    public ResponseEntity<Page<Video>> getVideos(Pageable pageable) {
        return ResponseEntity.ok(videoService.getPublishedVideos(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取视频详情")
    public ResponseEntity<Video> getVideoById(@PathVariable Long id) {
        return ResponseEntity.ok(videoService.getVideoById(id));
    }

    @PostMapping("/{id}/views")
    @Operation(summary = "增加视频观看次数")
    public ResponseEntity<String> incrementViews(@PathVariable Long id) {
        videoService.incrementViews(id);
        return ResponseEntity.ok("观看次数已增加");
    }

    @GetMapping("/search")
    @Operation(summary = "搜索视频")
    public ResponseEntity<Page<Video>> searchVideos(
        @RequestParam String keyword,
        Pageable pageable
    ) {
        return ResponseEntity.ok(videoService.searchVideos(keyword, pageable));
    }

    @GetMapping("/{videoId}/tags")
    @Operation(summary = "获取视频的标签")
    public ResponseEntity<String> getVideoTags(@PathVariable Long videoId) {
        return ResponseEntity.ok("[]");
    }
}
