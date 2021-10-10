package top.ninefox.comp.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class ComponentContent implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 组件内容id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long contentId;

    /**
     * 分类id
     */
    private Long typeId;

    /**
     * 组件标题
     */
    private String title;

    /**
     * html 文本
     */
    private String html;

    /**
     * css 样式
     */
    private String css;

    /**
     * js 代码
     */
    private String js;

    /**
     * 对组件的描述
     */
    private String description;

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
