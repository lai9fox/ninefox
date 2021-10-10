package top.ninefox.command.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.ninefox.command.entity.Content;
import top.ninefox.command.entity.Type;
import top.ninefox.command.mapper.ContentMapper;
import top.ninefox.command.mapper.TypeMapper;
import top.ninefox.command.service.ITypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lai9fox
 * @since 2021-10-07
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements ITypeService {

    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private ContentMapper contentMapper;

    @Override
    @Transactional
    public boolean cascadeDelete(Type type) {

        if (typeMapper.deleteById(type) != 1) {
            throw new RuntimeException();
        }

        QueryWrapper<Content> wrapper = new QueryWrapper<>();

        wrapper.eq("typeId", type.getTypeId());

        if (contentMapper.delete(wrapper) < 0) {
            throw new RuntimeException();
        }

        return true;
    }
}
