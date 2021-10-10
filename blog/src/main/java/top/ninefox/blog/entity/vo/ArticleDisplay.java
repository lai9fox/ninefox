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
public class ArticleDisplay implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long articleId;

    private Long uid;

    private String title;

    private String articleSummary;

    private String content;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;
}
