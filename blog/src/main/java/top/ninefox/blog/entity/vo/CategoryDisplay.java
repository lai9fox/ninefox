package top.ninefox.blog.entity.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CategoryDisplay implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long articleId;

    private Long uid;

    private String title;

    private Long categoryId;
//    分类名称
    private String name;

    private String articleSummary;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;
}
