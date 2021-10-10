package top.ninefox.command.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import top.ninefox.command.entity.Content;
import top.ninefox.command.service.IContentService;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lai9fox
 * @since 2021-10-07
 */
@RestController
@RequestMapping("/content")
public class ContentController {


    @Autowired
    private IContentService contentService;


//  增加一个类型
    @RequestMapping("/put")
    public boolean putAContent(Content content) {
        return contentService.save(content);
    }

    @RequestMapping("/delete")
    public boolean deleteContent(Content content) {
        return contentService.removeById(content);
    }

    @RequestMapping("/update")
    public boolean updateContent(Content content) {
        return contentService.updateById(content);
    }

    @RequestMapping("/detail")
    public Content getDetail(Content content) {
        return contentService.getById(content.getCommandId());
    }

    @RequestMapping("/getcontents")
    public List<Content> getContentByTypeId(Content content) {
        QueryWrapper<Content> wrapper = new QueryWrapper<>();
        wrapper.eq("type_id", content.getTypeId());
        return contentService.list(wrapper);
    }


}

