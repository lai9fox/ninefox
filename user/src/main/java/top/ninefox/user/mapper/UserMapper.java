package top.ninefox.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.ninefox.user.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lai9fox
 * @since 2021-10-04
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
