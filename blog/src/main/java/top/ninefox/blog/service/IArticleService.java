package top.ninefox.blog.service;

import top.ninefox.blog.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import top.ninefox.blog.entity.vo.ArticleDisplay;
import top.ninefox.blog.entity.vo.ArticleOri;
import top.ninefox.blog.entity.vo.CategoryDisplay;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lai9fox
 * @since 2021-10-08
 */
public interface IArticleService extends IService<Article> {

    boolean add(ArticleOri articleOri);

    boolean cascadeDelete(Article article);

    boolean cascadeUpdate(ArticleOri ao);

    List<CategoryDisplay> selectCategory(Long cid);

    List<CategoryDisplay> selectCategoryUid(Long uid);

    List<ArticleDisplay> selectDetailAid(Long aid);
}
