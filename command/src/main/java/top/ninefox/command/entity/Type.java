package top.ninefox.command.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author lai9fox
 * @since 2021-10-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("command_type")
public class Type implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 类型id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long typeId;

    /**
     * 用户主键
     */

    private Long uid;

    /**
     * 类型
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
