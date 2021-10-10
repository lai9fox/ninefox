package top.ninefox.comp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import top.ninefox.comp.entity.ComponentContent;
import top.ninefox.comp.service.IComponentContentService;
import top.ninefox.comp.service.IComponentTypeService;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lai9fox
 * @since 2021-10-08
 */
@RestController
@RequestMapping("/content")
public class ComponentContentController {

    @Autowired
    private IComponentContentService contentService;

//    添加一个内容
    @RequestMapping("/add")
    public boolean addContent(ComponentContent content) {
        return contentService.save(content);
    }

//    修改内容
    @RequestMapping("/update")
    public boolean update(ComponentContent content) {
        return contentService.updateById(content);
    }

//    删除内容
    @RequestMapping("/delete")
    public boolean delete(Long contentId) {
        return contentService.removeById(contentId);
    }

//    查询内容
    @RequestMapping("/select")
    public ComponentContent select(Long contentId) {
        return contentService.getById(contentId);
    }

//    查询分类下的所有记录，只返回 contentId、title 字段
    @RequestMapping("/all")
    public List<ComponentContent> all(Long typeId) {
        QueryWrapper<ComponentContent> wrapper = new QueryWrapper<>();
        wrapper.select("contentId","title").eq("typeId", typeId);
        return contentService.list(wrapper);
    }

}

