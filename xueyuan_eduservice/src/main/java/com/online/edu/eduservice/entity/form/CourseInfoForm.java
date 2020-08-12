package com.online.edu.eduservice.entity.form;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CourseInfoForm {

    private String id;

    private String teacherId;

    private String subjectId;

    private String title;

    private BigDecimal price;

    private Integer lessonNum;

    private String cover;

    private String description;

    private String subjectParentId;
}
