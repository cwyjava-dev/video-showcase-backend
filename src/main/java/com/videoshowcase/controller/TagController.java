package com.videoshowcase.controller;

import com.videoshowcase.entity.Tag;
import com.videoshowcase.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
@Tag(name = "标签管理", description = "视频标签相关接口")
public class TagController {
    private final TagService tagService;

    @GetMapping
    @Operation(summary = "获取所有标签")
    public ResponseEntity<List<Tag>> getAllTags() {
        return ResponseEntity.ok(tagService.getAllTags());
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取标签详情")
    public ResponseEntity<Tag> getTagById(@PathVariable Long id) {
        return ResponseEntity.ok(tagService.getTagById(id));
    }
}
