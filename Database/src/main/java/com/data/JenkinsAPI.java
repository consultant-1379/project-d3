package com.data;

import com.google.common.collect.Lists;
import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.*;

import java.io.IOException;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class JenkinsAPI {
    private JenkinsServer jenkinsServer;
    JenkinsData jenkinsData;
    Connection conn;
    Statement stmt;
    List<String> data = new ArrayList<>();

    public JenkinsAPI() throws IOException, SQLException {
        List<String> jobs = Lists.newArrayList(
                "eric-oss-ran-topology-adapter_Publish",
                "eric-oss-ran-topology-adapter_PreCodeReview",
                "ENM-Adapter_release",
                "ENM-Adapter_PreCodeReview",
                "ENM-Stub_release",
                "ENM-Stub_PreCodeReview",
                "eric-oss-enm-discovery-adapter_Publish",
                "eric-oss-enm-discovery-adapter_PodeReview",
                "eric-oss-enm-model-adapter_Publish",
                "eric-oss-enm-model-adapter_PreCodeReview",
                "eric-oss-enm-notification-adapter_Publish",
                "eric-oss-enm-notification-adapter_PreCodeReview"
        );

        conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","mysqldb");
        stmt = (Statement)conn.createStatement();

        String createTable = "create table buildDetails(id INT NOT NULL, buildNumber INT NOT NULL, jobName varchar(200) NOT NULL, buildResult varchar(100) NOT NULL, buildDuration INT NOT NULL, buildDay INT NOT NULL, buildMonth INT NOT NULL, buildYear INT NOT NULL, PRIMARY KEY(id));";
        stmt.execute(createTable);

        String jobName="";
        int buildNumber=0;
        int id = 1;
        for(int i=0; i<jobs.size(); i++){
            if(jobs.get(i).contains("ENM-")){
                this.jenkinsServer = new JenkinsServer(URI.create("https://fem2s11-eiffel052.eiffel.gic.ericsson.se:8443/jenkins/"), "EJNMKII","Rhfksl12!!");
            }
            else{
                this.jenkinsServer = new JenkinsServer(URI.create("https://fem1s11-eiffel216.eiffel.gic.ericsson.se:8443/jenkins/"), "EJNMKII","Rhfksl12!!");
            }

            JobWithDetails jobDetails = jenkinsServer.getJob(jobs.get(i)).details();
            List<Build> builds = jobDetails.getBuilds();


            for(Build build:builds) {
                jobName = jobs.get(i);
                buildNumber = build.details().getNumber(); //int buildID
                long buildDuration = build.details().getDuration(); //long buildDuration
                String buildResult = String.valueOf(build.details().getResult()); //string buildResult

                long buildTimestamp = build.details().getTimestamp();//long buildTimestamp
                java.sql.Date date = new java.sql.Date(buildTimestamp); // year - month - day
                String convertDate = date.toString();

                int year = Integer.parseInt(convertDate.split("-")[0]); //int year
                int month = Integer.parseInt(convertDate.split("-")[1]);//int month
                int day = Integer.parseInt(convertDate.split("-")[2]); //int day

//                System.out.println("JOB NAME : "+jobs.get(i) +", "
//                        +buildNumber + "'s build duration :" + buildDuration +
//                        ", build timestamp : " + year +"-" + month + "-" + day +
//                        ", build result : " + buildResult);
//                System.out.println(jobs.get(i));

//                jenkinsData = (List<JenkinsData>) new JenkinsData(buildNumber, jobName, buildResult, buildDuration, day, month, year, buildTimestamp);
                jenkinsData = new JenkinsData(id, buildNumber, jobName, buildResult, buildDuration, day, month, year);
                id++;
                insertData(jenkinsData);

            }

        }
        try {
            if (stmt != null)
                conn.close();
        } catch (SQLException se) {}
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }

    }

    public void insertData (JenkinsData jenkinsData){
//        System.out.println(jenkinsData);
//        Connection conn = null;
//        Statement stmt = null;
   //     String createTable = "create table buildDetails(id INT NOT NULL, buildNumber INT NOT NULL, jobName varchar(200) NOT NULL, buildResult varchar(100) NOT NULL, buildDuration INT NOT NULL, buildDay INT NOT NULL, buildMonth INT NOT NULL, buildYear INT NOT NULL, PRIMARY KEY(id));";


        try{
//                stmt.execute(createTable);

            String query = "INSERT INTO buildDetails " + "VALUES ("
                    +jenkinsData.getId()+","
                    +jenkinsData.getBuildNumber()+","
                    +"'"+jenkinsData.getJobName()+"',"
                    +"'"+jenkinsData.getBuildResult()+"',"
                    +jenkinsData.getBuildDuration()+","
                    +jenkinsData.getBuildDay()+","
                    +jenkinsData.getBuildMonth()+","
                    +jenkinsData.getBuildYear()+");";


            System.out.println(query);
            stmt.executeUpdate(query);

            System.out.println("Record is inserted in the table successfully..................");
        } catch (SQLException excep) {
            excep.printStackTrace();
        } catch (Exception excep) {
            excep.printStackTrace();
        }

        System.out.println("Please check it in the MySQL Table......... ……..");

    }



}
