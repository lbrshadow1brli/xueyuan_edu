package com.online.edu.eduservice.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author dxq
 * @since 2020-08-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EduCourseDescription对象", description="")
public class EduCourseDescription implements Serializable {

    private static final long serialVersionUID = 1L;

    //INPUT是手动输入
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    private String description;

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
