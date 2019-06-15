package com.longder.plant.entity;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class ImageDetail {
    /**
     * 植物名称
     */
    private String name;
    /**
     * 得分
     */
    private Double score;

    public ImageDetail() {
    }

    public ImageDetail(String name, Double score) {
        this.name = name;
        this.score = score;
    }
}
