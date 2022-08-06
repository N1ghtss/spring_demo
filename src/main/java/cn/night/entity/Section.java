package cn.night.entity;

import cn.night.utils.Entity;

public class Section extends Entity {
    private Integer id;
    private Integer year;
    private String type;
    private Integer clazzId;
    private Integer teacherId;
    private Integer courseId;
    private String remark;
    private Course course;

    @Override
    public String toString() {
        return "Section{" +
                "id=" + id +
                ", year=" + year +
                ", type='" + type + '\'' +
                ", clazzId=" + clazzId +
                ", teacherId=" + teacherId +
                ", courseId=" + courseId +
                ", remark='" + remark + '\'' +
                ", course=" + course +
                ", teacher=" + teacher +
                ", clazz=" + clazz +
                ", selected=" + selected +
                '}';
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
    }

    private Teacher teacher;
    private Clazz clazz;
    private Integer selected; // 逃课数量，辅助查询

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getClazzId() {
        return clazzId;
    }

    public Section setClazzId(Integer clazzId) {
        this.clazzId = clazzId;
        return this;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
