package top.ninefox.blog.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import top.ninefox.blog.entity.Comment;
import top.ninefox.blog.service.ICommentService;
import top.ninefox.blog.service.UserService;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lai9fox
 * @since 2021-10-08
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @Autowired
    private UserService userService;

    @RequestMapping("/add")
    public boolean add(Comment comment){

        JSONObject json = JSON.parseObject(userService.decodeToken());
        comment.setUid(json.getLong("uid"));
        return commentService.save(comment);

    }

    @RequestMapping("/delete")
    public boolean delete(Comment comment) {
        return commentService.removeById(comment);
    }

    @RequestMapping("/update")
    public boolean update(Comment comment) {
        return commentService.updateById(comment);
    }

    @RequestMapping("/select")
    public Comment select(Comment comment) {
        return commentService.getById(comment.getCommentId());
    }


    @RequestMapping("/selectuid")
    public List<Comment> selectUid(Long uid) {

        QueryWrapper<Comment> wrapper = new QueryWrapper<>();

        if (uid == null) {
            JSONObject json = JSON.parseObject(userService.decodeToken());
            wrapper.eq("uid", json.getLong("uid"));
        } else {
            wrapper.eq("uid", uid);
        }

        return commentService.list(wrapper);
    }

    @RequestMapping("/selectaid")
    public List<Comment> selectAid(Comment comment) {

        QueryWrapper<Comment> wrapper = new QueryWrapper<>();

        wrapper.eq("articleId", comment.getArticleId());

        return commentService.list(wrapper);
    }



}

