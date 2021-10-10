package top.ninefox.blog.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import top.ninefox.blog.entity.Article;
import top.ninefox.blog.entity.ArticleCategory;
import top.ninefox.blog.entity.ArticleTag;
import top.ninefox.blog.entity.vo.ArticleDisplay;
import top.ninefox.blog.entity.vo.ArticleOri;
import top.ninefox.blog.entity.vo.CategoryDisplay;
import top.ninefox.blog.service.IArticleCategoryService;
import top.ninefox.blog.service.IArticleService;
import top.ninefox.blog.service.IArticleTagService;
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
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private UserService userService;
    @Autowired
    private IArticleService articleService;
    @Autowired
    private IArticleCategoryService articleCategoryService;
    @Autowired
    private IArticleTagService articleTagService;

    @RequestMapping("/add")
    public boolean add(ArticleOri articleOri) {

        articleOri.setUid(JSON.parseObject(userService.decodeToken()).getLong("uid"));

        return articleService.add(articleOri);
    }

    @RequestMapping("/delete")
    public boolean cascadeDelete(Article article) {
        return articleService.cascadeDelete(article);
    }


    @RequestMapping("/update")
    public boolean cascadeUpdate(ArticleOri ao) {
        return articleService.cascadeUpdate(ao);
    }

    @RequestMapping("/update/category")
    public boolean updateCategory(ArticleCategory ac) {
        return articleCategoryService.updateById(ac);
    }

    @RequestMapping("/update/tag/delete")
    public boolean deleteTag(ArticleTag at) {
        return articleTagService.removeById(at);
    }

    @RequestMapping("/update/tag/add")
    public boolean addTag(ArticleTag at) {
        return articleTagService.save(at);
    }

    @RequestMapping("/select")
    public void select(Long articleId){


    }

    @RequestMapping("/select/category")
    public List<CategoryDisplay> selectCategory(Long categoryId) {
        return articleService.selectCategory(categoryId);
    }
    @RequestMapping("/select/uid")
    public List<CategoryDisplay> selectCategoryUid(Long uid) {

        if (uid == null) {
            JSONObject json = JSON.parseObject(userService.decodeToken());
            uid = json.getLong("uid");
        }
        return articleService.selectCategoryUid(uid);
    }
}

