package top.ninefox.comp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.ninefox.comp.entity.ComponentContent;
import top.ninefox.comp.entity.ComponentType;
import top.ninefox.comp.mapper.ComponentContentMapper;
import top.ninefox.comp.mapper.ComponentTypeMapper;
import top.ninefox.comp.service.IComponentTypeService;
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
public class ComponentTypeServiceImpl extends ServiceImpl<ComponentTypeMapper, ComponentType> implements IComponentTypeService {

    @Autowired
    private ComponentTypeMapper typeMapper;

    @Autowired
    private ComponentContentMapper contentMapper;

    @Override
    @Transactional
    public boolean cascadeDelete(ComponentType type) {

        if (typeMapper.deleteById(type.getTypeId()) != 1) {
            throw new RuntimeException();
        }

        QueryWrapper<ComponentContent> wrapper = new QueryWrapper<>();

        wrapper.eq("typeId", type.getTypeId());

        if (contentMapper.delete(wrapper) < 0) {
            throw new RuntimeException();
        }

        return true;
    }
}
