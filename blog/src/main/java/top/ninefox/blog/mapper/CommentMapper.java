package top.ninefox.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.ninefox.blog.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lai9fox
 * @since 2021-10-08
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}
