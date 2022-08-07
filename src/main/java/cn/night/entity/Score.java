package cn.night.entity;

import cn.night.utils.Entity;

import java.math.BigDecimal;

public class Score extends Entity {
    private Integer id;
    private Integer sectionId;
    private Integer courseId;
    private Integer stuId;
    private BigDecimal score;
    private String stuName;
    private Student student;
    private Course course;
    private Section section;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", sectionId=" + sectionId +
                ", courseId=" + courseId +
                ", stuId=" + stuId +
                ", score=" + score +
                ", stuName='" + stuName + '\'' +
                '}';
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getStuId() {
        return stuId;
    }

    public Score setStuId(Integer stuId) {
        this.stuId = stuId;
        return this;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public Score setSectionid(Integer sectionId) {
        this.sectionId = sectionId;
        return this;
    }

    public Score setCourseid(Integer courseId) {
        this.courseId = courseId;
        return this;
    }
}
