package com.longder.plant.repository;

import com.longder.plant.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    /**
     * 根据图片id查询该图片的所有评论
     * @param imageId
     * @return
     */
    @Query("select c from Comment c where c.imageInfoId = :imageId")
    List<Comment> listByImageId(@Param("imageId") Long imageId);
}
