package top.ninefox.blog.service.impl;

import top.ninefox.blog.entity.ArticleCategory;
import top.ninefox.blog.mapper.ArticleCategoryMapper;
import top.ninefox.blog.service.IArticleCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lai9fox
 * @since 2021-10-08
 */
@Service
public class ArticleCategoryServiceImpl extends ServiceImpl<ArticleCategoryMapper, ArticleCategory> implements IArticleCategoryService {

}
