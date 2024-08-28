package com.example.message;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="buildDetails")
public class JenkinsData {

    @Id
    private int id;
    private int buildNumber;
    private String jobName;
    private String buildResult;
    private long buildDuration;
    private int buildDay;
    private int buildMonth;
    private int buildYear;

//    public JenkinsData(int id, int buildNumber, String jobName, String buildResult, long buildDuration, int buildDay, int buildMonth, int buildYear) {
//        this.id = id;
//        this.buildNumber = buildNumber;
//        this.jobName = jobName;
//        this.buildResult = buildResult;
//        this.buildDuration = buildDuration;
//        this.buildDay = buildDay;
//        this.buildMonth = buildMonth;
//        this.buildYear = buildYear;
//    }

    public int getId() {
        return id;
    }

    public int setId() {
        return id;
    }


    public int getBuildNumber() {
        return buildNumber;
    }

    public String getJobName() {
        return jobName;
    }


    public String getBuildResult() {
        return buildResult;
    }

    public long getBuildDuration() {
        return buildDuration;
    }

    public int getBuildDay() {
        return buildDay;
    }

    public int getBuildMonth() {
        return buildMonth;
    }

    public int getBuildYear() {
        return buildYear;
    }

}
