package top.ninefox.comp.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import top.ninefox.comp.entity.ComponentType;
import top.ninefox.comp.service.IComponentTypeService;
import top.ninefox.comp.service.UserService;

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
@RequestMapping("/type")
public class ComponentTypeController {

    @Autowired
    private IComponentTypeService typeService;

//    远程 User 服务
    @Autowired
    private UserService userService;

//    添加一个分类
    @RequestMapping("/add")
    public boolean addType(ComponentType type) {

        JSONObject json = JSON.parseObject(userService.decodeToken());

        type.setUid((Long) json.get("uid"));

        return typeService.save(type);
    }

//    修改类型
    @RequestMapping("/update")
    public boolean updateType(ComponentType type) {
        return typeService.updateById(type);
    }

//    删除一个类型，同时删除类型下所属的所有内容，使用事务保证一致性
    @RequestMapping("/delete")
    public boolean deleteType(ComponentType type) {
        return typeService.cascadeDelete(type);
    }

//    查询一条类型
    @RequestMapping("/select")
    public ComponentType selectOne(ComponentType type) {
        return typeService.getById(type);
    }

//    根据 uid 查询用户所有的类型
    @RequestMapping("/all")
    public List<ComponentType> selectAll(Long uid) {
        Long temp;
        if (uid == null) {
            String res = userService.decodeToken();
            JSONObject json = JSON.parseObject(res);
            temp = (Long) json.get("uid");
        } else {
            temp = uid;
        }
        QueryWrapper<ComponentType> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", temp);
        return typeService.list(wrapper);
    }



}

