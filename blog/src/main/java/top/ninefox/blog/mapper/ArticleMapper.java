package top.ninefox.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.ninefox.blog.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.ninefox.blog.entity.vo.ArticleDisplay;
import top.ninefox.blog.entity.vo.CategoryDisplay;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lai9fox
 * @since 2021-10-08
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    List<CategoryDisplay> selectCategory(Long cid);

    List<CategoryDisplay> selectCategoryUid(Long uid);

    List<ArticleDisplay> selectDetailAid(Long aid);
}
