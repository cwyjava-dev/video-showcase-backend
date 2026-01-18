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
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class VideoService {
    private final VideoRepository videoRepository;

    /**
     * 获取所有已发布的视频
     */
    public List<Video> getAllVideos() {
        return videoRepository.findAll().stream()
            .filter(v -> v.getStatus() == Video.VideoStatus.PUBLISHED)
            .collect(Collectors.toList());
    }

    /**
     * 获取已发布的视频（分页）
     */
    public Page<Video> getPublishedVideos(Pageable pageable) {
        return videoRepository.findByStatus(Video.VideoStatus.PUBLISHED, pageable);
    }

    /**
     * 根据 ID 获取视频
     */
    public Video getVideoById(Long id) {
        return videoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("视频不存在"));
    }

    /**
     * 增加视频观看次数
     */
    @Transactional
    public void incrementViews(Long videoId) {
        Video video = getVideoById(videoId);
        video.setViews(video.getViews() + 1);
        videoRepository.save(video);
    }

    /**
     * 搜索视频（不分页）
     */
    public List<Video> searchVideos(String keyword) {
        return videoRepository.findAll().stream()
            .filter(v -> v.getStatus() == Video.VideoStatus.PUBLISHED)
            .filter(v -> v.getTitle().toLowerCase().contains(keyword.toLowerCase()))
            .collect(Collectors.toList());
    }

    /**
     * 搜索视频（分页）
     */
    public Page<Video> searchVideos(String keyword, Pageable pageable) {
        return videoRepository.findByStatusAndTitleContainingIgnoreCase(
            Video.VideoStatus.PUBLISHED, keyword, pageable
        );
    }
}
