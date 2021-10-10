package top.ninefox.comp.service;

import top.ninefox.comp.entity.ComponentType;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lai9fox
 * @since 2021-10-08
 */
public interface IComponentTypeService extends IService<ComponentType> {

    boolean cascadeDelete(ComponentType type);

}
