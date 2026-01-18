package com.videoshowcase.service;

import com.videoshowcase.dto.VideoDto;
import com.videoshowcase.entity.Video;
import com.videoshowcase.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class VideoService {
    private final VideoRepository videoRepository;

    public Page<Video> getPublishedVideos(Pageable pageable) {
        return videoRepository.findByStatus(Video.VideoStatus.PUBLISHED, pageable);
    }

    public Video getVideoById(Long id) {
        return videoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("视频不存在"));
    }

    @Transactional
    public void incrementViews(Long videoId) {
        Video video = getVideoById(videoId);
        video.setViews(video.getViews() + 1);
        videoRepository.save(video);
    }

    public Page<Video> searchVideos(String keyword, Pageable pageable) {
        return videoRepository.findByStatusAndTitleContainingIgnoreCase(
            Video.VideoStatus.PUBLISHED, keyword, pageable
        );
    }
}
