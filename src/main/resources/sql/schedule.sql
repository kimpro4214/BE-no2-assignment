CREATE TABLE `schedules` (
                             `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
                             `title` VARCHAR(255) NOT NULL,
                             `writer` VARCHAR(100) NOT NULL,
                             `password` VARCHAR(255) NOT NULL,
                             `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
                             `modified_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
