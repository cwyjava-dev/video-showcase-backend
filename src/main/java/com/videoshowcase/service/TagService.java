package com.videoshowcase.service;

import com.videoshowcase.entity.VideoTag;
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

    public List<VideoTag> getAllTags() {
        return tagRepository.findAll();
    }

    public VideoTag getTagById(Long id) {
        return tagRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("标签不存在"));
    }
}
