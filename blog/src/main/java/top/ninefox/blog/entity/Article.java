package top.ninefox.blog.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
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
@TableName("blog_article")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 博客文章 id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long articleId;

    /**
     * 发表用户的id
     */
    private Long uid;

    /**
     * 博客标题
     */
    private String title;

    /**
     * 文章摘要
     */
    private String articleSummary;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;


}
