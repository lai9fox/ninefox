package top.ninefox.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.ninefox.blog.entity.*;
import top.ninefox.blog.mapper.*;
import top.ninefox.blog.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lai9fox
 * @since 2021-10-08
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleCategoryMapper articleCategoryMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private ArticleDetailMapper articleDetailMapper;


    @Override
    @Transactional
    public boolean cascadeDelete(Category category) {

//        删除AC表中记录
        QueryWrapper<ArticleCategory> wrapper = new QueryWrapper<>();
        wrapper.eq("categoryId", category.getCategoryId());
        List<ArticleCategory> acLists = articleCategoryMapper.selectList(wrapper);
        if (articleCategoryMapper.delete(wrapper) < 0) {
            throw new RuntimeException();
        }

        List<Long> aIds = new ArrayList<>();
        acLists.forEach(e -> {
            aIds.add(e.getArticleId());
        });

//        删除评论
        QueryWrapper<Comment> wrapper1 = new QueryWrapper<>();
        wrapper1.in("articleId", aIds);
        if (commentMapper.delete(wrapper1) < 0) {
            throw new RuntimeException();
        }
//        删除标签记录
        QueryWrapper<Tag> wrapper2 = new QueryWrapper<>();
        wrapper2.in("articleId", aIds);
        if (tagMapper.delete(wrapper2) < 0) {
            throw new RuntimeException();
        }


        QueryWrapper<ArticleDetail> wrapper3 = new QueryWrapper<>();
        wrapper3.in("articleId", aIds);
        if (articleDetailMapper.delete(wrapper3) < 0) {
            throw new RuntimeException();
        }

        if (articleMapper.deleteBatchIds(aIds) < 0 ) {
            throw new RuntimeException();
        }

        if (categoryMapper.deleteById(category) != 1) {
            throw new RuntimeException();
        }

        return true;
    }
}
