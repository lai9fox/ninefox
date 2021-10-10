package top.ninefox.command.service.impl;

import top.ninefox.command.entity.Content;
import top.ninefox.command.mapper.ContentMapper;
import top.ninefox.command.service.IContentService;
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
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements IContentService {

}
