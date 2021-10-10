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
@TableName("blog_article_tag")
public class ArticleTag implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 博客文章 id
     */
    private Long articleId;

    /**
     * 标签id
     */
    private Long tagId;


}
