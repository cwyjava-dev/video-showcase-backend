package com.videoshowcase.service;

import com.videoshowcase.entity.Tag;
import com.videoshowcase.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public Tag getTagById(Long id) {
        return tagRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("标签不存在"));
    }
}
