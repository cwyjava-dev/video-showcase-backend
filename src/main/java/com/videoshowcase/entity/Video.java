package com.videoshowcase.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "videos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    @Column(nullable = false)
    private String videoUrl;

    private String thumbnailUrl;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Transient
    @JsonProperty("categoryId")
    private Long categoryId;

    @ManyToMany
    @JoinTable(
            name = "video_tags",
            joinColumns = @JoinColumn(name = "video_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<VideoTag> tags;

    @Column(nullable = false)
    private Long views = 0L;

    private Long duration;
    private Long fileSize;
    private String mimeType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VideoStatus status = VideoStatus.DRAFT;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VideoType videoType = VideoType.LOCAL;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User createdBy;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    public enum VideoStatus {
        DRAFT, PUBLISHED, ARCHIVED
    }

    public enum VideoType {
        LOCAL, YOUTUBE, BILIBILI
    }

    @PrePersist
    @PreUpdate
    public void prePersistUpdate() {
        // 如果前端发送了 categoryId，但 category 为 null，则需要设置 category
        // 这个逻辑会在 Controller 中处理
    }

    @PostLoad
    public void postLoad() {
        // 从 category 对象中提取 id 到 categoryId
        if (this.category != null) {
            this.categoryId = this.category.getId();
        }
    }
}
