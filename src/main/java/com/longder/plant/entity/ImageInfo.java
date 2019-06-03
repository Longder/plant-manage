package com.longder.plant.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

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

    @Transient
    private String imageValue;

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

}
