package top.ninefox.blog.service;

import top.ninefox.blog.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lai9fox
 * @since 2021-10-08
 */
public interface ICategoryService extends IService<Category> {

    boolean cascadeDelete(Category category);

}
