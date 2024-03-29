package com.longder.plant.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * 图片信息
 */
@Entity
@Table(name = "image_info")
@Data
public class ImageInfo implements Serializable {

    @Id
    @Column(name = "id_")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 图片的base64值
     */
    @Column(name = "image_")
    private String image;

    /**
     * 点赞数
     */
    @Column(name = "like_count_")
    private Integer likeCount;
    /**
     * 踩数
     */
    @Column(name = "unlike_count_")
    private Integer unlikeCount;
    /**
     * 上传时间
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "upload_time_")
    private LocalDateTime uploadTime;
    /**
     * 详情
     */
    @ElementCollection
    private Set<ImageDetail> imageDetails;

    /**
     * 评论
     */
    @JoinColumn(name = "image_info_id_")
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Comment> commentList ;

}
