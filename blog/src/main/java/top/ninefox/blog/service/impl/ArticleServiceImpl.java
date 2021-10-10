package top.ninefox.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.ninefox.blog.entity.*;
import top.ninefox.blog.entity.vo.ArticleDisplay;
import top.ninefox.blog.entity.vo.ArticleOri;
import top.ninefox.blog.entity.vo.CategoryDisplay;
import top.ninefox.blog.mapper.*;
import top.ninefox.blog.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleDetailMapper articleDetailMapper;
    @Autowired
    private ArticleCategoryMapper articleCategoryMapper;
    @Autowired
    private ArticleTagMapper articleTagMapper;
    @Autowired
    private CommentMapper commentMapper;

    @Override
    @Transactional
    public boolean add(ArticleOri ao) {

        Article article = new Article();
        article.setUid(ao.getUid());
        article.setTitle(ao.getTitle());
        article.setArticleSummary(ao.getArticleSummary());
        if (articleMapper.insert(article) != 1) {
            throw new RuntimeException();
        }

        ArticleDetail ad = new ArticleDetail();
        ad.setArticleId(article.getArticleId());
        ad.setContent(ao.getContent());
        if (articleDetailMapper.insert(ad) != 1) {
            throw new RuntimeException();
        }

        ArticleCategory ac = new ArticleCategory();
        ac.setArticleId(article.getArticleId());
        ac.setCategoryId(ao.getCategoryId());
        if (articleCategoryMapper.insert(ac) != 1) {
            throw new RuntimeException();
        }

        ArticleTag at = new ArticleTag();
        at.setArticleId(article.getArticleId());
        List<Long> lists = ao.getTagIds();
        if (lists != null && lists.size() > 0) {
            for (Long e : lists) {
                at.setTagId(e);
                if (articleTagMapper.insert(at) != 1) {
                    throw new RuntimeException();
                }
            }
        }
        return true;
    }

    @Override
    @Transactional
    public boolean cascadeDelete(Article article) {

        if (articleCategoryMapper.deleteById(article.getArticleId()) != 1) {
            throw new RuntimeException();
        }

        QueryWrapper<ArticleTag> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("articleId", article.getArticleId());
        if (articleTagMapper.delete(wrapper1) < 0) {
            throw new RuntimeException();
        }

        if (articleDetailMapper.deleteById(article.getArticleId()) != 1) {
            throw new RuntimeException();
        }

        QueryWrapper<Comment> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("articleId", article.getArticleId());
        if (commentMapper.delete(wrapper2) < 0) {
            throw new RuntimeException();
        }

        return true;
    }

    @Override
    @Transactional
    public boolean cascadeUpdate(ArticleOri ao) {

        Article article = new Article();
        article.setArticleId(ao.getArticleId());
        article.setTitle(ao.getTitle());
        article.setArticleSummary(ao.getArticleSummary());

        if (articleMapper.updateById(article) != 1) {
            throw new RuntimeException();
        }

        ArticleDetail detail = new ArticleDetail();
        detail.setArticleId(ao.getArticleId());
        detail.setContent(ao.getContent());

        if (articleDetailMapper.updateById(detail) != 1) {
            throw new RuntimeException();
        }

        return true;
    }


    @Override
    public List<CategoryDisplay> selectCategory(Long cid) {
        return articleMapper.selectCategory(cid);
    }

    @Override
    public List<CategoryDisplay> selectCategoryUid(Long uid) {
        return articleMapper.selectCategoryUid(uid);
    }

    @Override
    public List<ArticleDisplay> selectDetailAid(Long aid) {
        return articleMapper.selectDetailAid(aid);
    }
}
