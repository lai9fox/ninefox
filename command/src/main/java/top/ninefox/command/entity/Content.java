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
@TableName("command_content")
public class Content implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 快速命令id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long commandId;

    /**
     * 类型id
     */
    private Long typeId;

    /**
     * 命令概述
     */
    private String title;

    /**
     * 命令代码
     */
    private String content;

    /**
     * 命令的说明
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
