package top.ninefox.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.ninefox.blog.entity.ArticleTag;
import top.ninefox.blog.entity.Tag;
import top.ninefox.blog.mapper.ArticleTagMapper;
import top.ninefox.blog.mapper.TagMapper;
import top.ninefox.blog.service.ITagService;
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
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {

    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private ArticleTagMapper articleTagMapper;


    @Override
    @Transactional
    public boolean cascadeDelete(Tag tag) {

        if (tagMapper.deleteById(tag.getTagId()) != 1) {
            throw new RuntimeException();
        }

        QueryWrapper<ArticleTag> wrapper = new QueryWrapper<>();

        wrapper.eq("tagId", tag.getTagId());

        if (articleTagMapper.delete(wrapper) < 0) {
            throw new RuntimeException();
        }

        return true;
    }
}
