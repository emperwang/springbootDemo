package com.stu.manage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author: Sparks
 * @Date: 2024/1/17 23:29
 * @Description
 */
@TableName("stu_score")
public class Score {
    @TableId(value = "sid", type = IdType.AUTO)
    private Long sid;

    private Long uid;

    private String username;
    //
    private String academicYear;

    // 1 上学期,2下学期
    private int semester;

    private float score;

    private String course;

    private float credit;

    // 1 必修, 2 选修
    private int courseNature;

    private float gradePointAverage;

    private String createTime;

    private String updateTime;

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }

    public int getCourseNature() {
        return courseNature;
    }

    public void setCourseNature(int courseNature) {
        this.courseNature = courseNature;
    }

    public float getGradePointAverage() {
        return gradePointAverage;
    }

    public void setGradePointAverage(float gradePointAverage) {
        this.gradePointAverage = gradePointAverage;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
