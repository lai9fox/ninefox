package top.ninefox.blog.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ArticleOri implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long articleId;

    private Long uid;

    private String title;

    private String articleSummary;

    private String content;

    private Long categoryId;

    private List<Long> tagIds;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;
}
