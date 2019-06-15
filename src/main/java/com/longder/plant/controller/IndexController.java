package com.longder.plant.controller;

import com.longder.plant.entity.Comment;
import com.longder.plant.entity.ImageDetail;
import com.longder.plant.entity.ImageInfo;
import com.longder.plant.repository.CommentRepository;
import com.longder.plant.repository.ImageInfoRepository;
import com.longder.plant.util.BaiduApiUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;

@Controller
public class IndexController {

    @Resource
    private BaiduApiUtil baiduApiUtil;
    @Resource
    private ImageInfoRepository imageInfoRepository;
    @Resource
    private CommentRepository commentRepository;

    /**
     * 主页默认跳转
     * @return
     */
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("list",imageInfoRepository.findAll());
        return "index";
    }

    /**
     * 图片上传
     * @param file
     * @param model
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public String plantUpload(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        if (file.isEmpty()) {
            return "index";
        }
        //检查是不是植物
        JSONObject res = baiduApiUtil.checkImage(file.getBytes());
        JSONArray array = res.getJSONArray("result");
        if(Double.parseDouble(array.getJSONObject(0).get("score").toString())!=0){
            //入库
            ImageInfo imageInfo = new ImageInfo();
            imageInfo.setImage("data:image/jpeg;base64,"+DatatypeConverter.printBase64Binary(file.getBytes()));
            imageInfo.setLikeCount(0);
            imageInfo.setUnlikeCount(0);
            imageInfo.setImageDetails(new HashSet<>());
            for(int i=0;i<array.length();i++){
                imageInfo.getImageDetails().add(new ImageDetail(array.getJSONObject(i).get("name").toString(),
                        Double.parseDouble(array.getJSONObject(i).get("score").toString())));
            }
            imageInfo.setUploadTime(LocalDateTime.now());
            imageInfoRepository.save(imageInfo);
        }else{
            model.addAttribute("error","您上传的不是植物，请重新上传");
        }
        model.addAttribute("list",imageInfoRepository.findAll());
        return "redirect:/";
    }

    /**
     * 点赞图片
     * @param imageId
     * @return
     */
    @PostMapping("/like")
    public String likeImage(Long imageId){
        ImageInfo imageInfo = imageInfoRepository.getOne(imageId);
        imageInfo.setLikeCount(imageInfo.getLikeCount()+1);
        imageInfoRepository.save(imageInfo);
        return "redirect:/";
    }

    /**
     * 踩图片
     * @param imageId
     * @return
     */
    @PostMapping("/unlike")
    public String unlikeImage(Long imageId){
        ImageInfo imageInfo = imageInfoRepository.getOne(imageId);
        imageInfo.setUnlikeCount(imageInfo.getUnlikeCount() + 1);
        imageInfoRepository.save(imageInfo);
        return "redirect:/";
    }

    /**
     * 给图片添加一个评论
     * @param comment
     * @return
     */
    @PostMapping("/comment")
    public String addComment(Comment comment){
        comment.setCommentTime(LocalDateTime.now());
        commentRepository.save(comment);
        return "redirect:/";
    }
}
