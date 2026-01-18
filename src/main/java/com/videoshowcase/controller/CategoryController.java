package com.videoshowcase.controller;

import com.videoshowcase.entity.Category;
import com.videoshowcase.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Tag(name = "分类管理", description = "视频分类相关接口")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    @Operation(summary = "获取所有分类")
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取分类详情")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }
}
