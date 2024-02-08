package com.stu.manage.entiry;

/**
 * @author: Sparks
 * @Date: 2024/2/7 21:26
 * @Description
 */
public class StuScoreSummary {

    private long ssid;

    private String academicYear;

    private int semester;

    private String columns;


    public long getSsid() {
        return ssid;
    }

    public void setSsid(long ssid) {
        this.ssid = ssid;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public String getColumns() {
        return columns;
    }

    public void setColumns(String columns) {
        this.columns = columns;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}
