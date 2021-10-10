package top.ninefox.blog.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import top.ninefox.blog.entity.Tag;
import top.ninefox.blog.service.ITagService;
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
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private ITagService tagService;

    @Autowired
    private UserService userService;

//    添加一个标签
    @RequestMapping("/add")
    public boolean addTag(Tag tag) {

        JSONObject json = JSON.parseObject(userService.decodeToken());

        tag.setUid(json.getLong("uid"));

        return tagService.save(tag);
    }

    @RequestMapping("/update")
    public boolean updateTag(Tag tag) {
        return tagService.updateById(tag);
    }

    @RequestMapping("/delete")
    public boolean deleteTag(Tag tag) {
        return tagService.cascadeDelete(tag);
    }

    @RequestMapping("/select")
    public Tag selectTag(Tag tag) {
        return tagService.getById(tag.getTagId());
    }

    @RequestMapping("/all")
    public List<Tag> selectAll() {
        JSONObject json = JSON.parseObject(userService.decodeToken());

        QueryWrapper<Tag> wrapper = new QueryWrapper<>();

        wrapper.eq("uid", json.get("uid"));

        return tagService.list(wrapper);
    }

}

