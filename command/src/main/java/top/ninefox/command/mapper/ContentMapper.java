package top.ninefox.command.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.ninefox.command.entity.Content;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lai9fox
 * @since 2021-10-07
 */
@Mapper
public interface ContentMapper extends BaseMapper<Content> {

}
