package top.ninefox.comp.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author lai9fox
 * @since 2021-10-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ComponentType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 组件分类id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long typeId;

    /**
     * 属于的用户
     */
    private Long uid;

    /**
     * 分类名称
     */
    private String title;

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
