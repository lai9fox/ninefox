package top.ninefox.command.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import top.ninefox.command.entity.Content;
import top.ninefox.command.entity.Type;
import top.ninefox.command.service.IContentService;
import top.ninefox.command.service.ITypeService;
import top.ninefox.command.service.UserService;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private ITypeService typeService;

    @Autowired
    private UserService userService;


//    增加一个书签类型
    @RequestMapping("/put")
    public boolean putAType(Type type) {

        JSONObject json = JSON.parseObject(userService.decodeToken());
        type.setUid((Long) json.get("uid"));

        return typeService.save(type);
    }

    @RequestMapping("/update")
    public boolean updateType(Type type) {
        return typeService.updateById(type);
    }

    @RequestMapping("/delete")
    public boolean deleteType(Type type) {
        return typeService.removeById(type);
    }

    @RequestMapping("/detail")
    public Type getDetail(Type type) {
        return typeService.getById(type.getTypeId());
    }

//  根据 uid 获取某个用户的所有 速记类型
    @RequestMapping("/alltype")
    public List<Type> getAll(Long uid) {

        Long temp;

        if (uid == null) {
            //        远程服务，获得用户 uid
            JSONObject json = JSON.parseObject(userService.decodeToken());
            temp = (Long) json.get("uid");

        } else {
            temp = uid;
        }
        QueryWrapper<Type> wrapper = new QueryWrapper<>();

        wrapper.eq("uid", temp);
        return typeService.list(wrapper);

    }


}

