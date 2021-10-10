package top.ninefox.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("blog_article_detail")
public class ArticleDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 博客文章 id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long articleId;

    /**
     * 博客具体内容
     */
    private String content;


}
