package top.ninefox.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author lai9fox
 * @since 2021-10-08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("blog_article_category")
public class ArticleCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 类别 id
     */
    private Long categoryId;

    /**
     * 博客 id
     */
    private Long articleId;


}
