package com.longder.plant.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 评论表
 */
@Entity
@Table(name = "comment")
@Data
public class Comment {

    /**
     * id 主键
     */
    @Id
    @Column(name = "id_")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 评论时间
     */
    @Column(name = "comment_time_")
    private LocalDateTime commentTime;
    /**
     * 内容
     */
    @Column(name = "content_")
    private String content;
    /**
     * 关联的图片id
     */
    @Column(name = "image_info_id_")
    private Long imageInfoId;
}
