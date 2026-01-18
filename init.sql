CREATE DATABASE IF NOT EXISTS video_showcase;
USE video_showcase;

CREATE TABLE IF NOT EXISTS users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    display_name VARCHAR(255),
    role VARCHAR(50) NOT NULL DEFAULT 'USER',
    avatar LONGTEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS categories (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) UNIQUE NOT NULL,
    description TEXT,
    color VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS tags (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) UNIQUE NOT NULL,
    description TEXT,
    color VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS videos (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description LONGTEXT,
    video_url VARCHAR(500) NOT NULL,
    thumbnail_url VARCHAR(500),
    category_id BIGINT,
    views BIGINT DEFAULT 0,
    duration BIGINT,
    file_size BIGINT,
    mime_type VARCHAR(100),
    status VARCHAR(50) NOT NULL DEFAULT 'DRAFT',
    created_by BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES categories(id),
    FOREIGN KEY (created_by) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS video_tags (
    video_id BIGINT NOT NULL,
    tag_id BIGINT NOT NULL,
    PRIMARY KEY (video_id, tag_id),
    FOREIGN KEY (video_id) REFERENCES videos(id) ON DELETE CASCADE,
    FOREIGN KEY (tag_id) REFERENCES tags(id) ON DELETE CASCADE
);

-- 创建默认管理员用户 (密码: admin123)
INSERT INTO users (username, email, password, display_name, role) VALUES 
('admin', 'admin@example.com', '$2a$10$slYQmyNdGzin7olVN3p5Be7DlH.PKZbv5H8KnzzVgXXbVxzy990qm', 'Administrator', 'ADMIN');

-- 创建示例分类
INSERT INTO categories (name, description, color) VALUES 
('教程', '各类教程视频', '#FF6B6B'),
('演讲', '演讲和分享视频', '#4ECDC4'),
('音乐', '音乐相关视频', '#45B7D1'),
('其他', '其他视频', '#96CEB4');

-- 创建示例标签
INSERT INTO tags (name, description, color) VALUES 
('热门', '热门视频', '#FF6B6B'),
('新上线', '最新上线视频', '#4ECDC4'),
('推荐', '推荐视频', '#45B7D1'),
('精选', '精选视频', '#96CEB4');
