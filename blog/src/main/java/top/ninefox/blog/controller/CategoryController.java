package top.ninefox.blog.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import top.ninefox.blog.entity.Category;
import top.ninefox.blog.entity.Tag;
import top.ninefox.blog.service.ICategoryService;
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
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private UserService userService;

    @RequestMapping("/add")
    public boolean addCategory(Category category) {

        JSONObject json = JSON.parseObject(userService.decodeToken());

        category.setUid(json.getLong("uid"));

        return categoryService.save(category);
    }


    @RequestMapping("/update")
    public boolean updateCategory(Category category) {
        return categoryService.updateById(category);
    }


    @RequestMapping("/delete")
    public boolean deleteCategory(Category category) {
        return categoryService.cascadeDelete(category);
    }


    @RequestMapping("/select")
    public Category select(Category category) {
        return categoryService.getById(category.getCategoryId());
    }

    @RequestMapping("/all")
    public List<Category> all(Long uid){

        Long temp;

        if (uid == null) {
            JSONObject json = JSON.parseObject(userService.decodeToken());
            temp = json.getLong("uid");
        } else {
            temp = uid;
        }

        QueryWrapper<Category> wrapper = new QueryWrapper<>();

        wrapper.eq("uid", temp);

        return categoryService.list(wrapper);

    }
}

