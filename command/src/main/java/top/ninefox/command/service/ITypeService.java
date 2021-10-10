package top.ninefox.command.service;

import top.ninefox.command.entity.Type;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lai9fox
 * @since 2021-10-07
 */
public interface ITypeService extends IService<Type> {

    boolean cascadeDelete(Type type);

}
