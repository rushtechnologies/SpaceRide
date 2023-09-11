package com.rushtechnologies.spaceride.Models;

import java.sql.Timestamp;

/**
 *
 * @author CARLOSHG
 */
public class EventReport {

    private int ER_Id;
    private int ER_U_Id;
    private String ER_Transcript;
    private String ER_Description;
    private String ER_Category;
    private String ER_Status;
    private String ER_Solution;
    private int ER_S_Id;
    private Timestamp ER_Timestamp;

    public EventReport() {
    }

    public EventReport(int ER_Id, int ER_U_Id, String ER_Transcript, String ER_Description, String ER_Category, String ER_Status, String ER_Solution, int ER_S_Id, Timestamp timestamp) {
        this.ER_Id = ER_Id;
        this.ER_U_Id = ER_U_Id;
        this.ER_Transcript = ER_Transcript;
        this.ER_Description = ER_Description;
        this.ER_Category = ER_Category;
        this.ER_Status = ER_Status;
        this.ER_Solution = ER_Solution;
        this.ER_S_Id = ER_S_Id;
        this.ER_Timestamp = ER_Timestamp;
    }

    public int getER_Id() {
        return ER_Id;
    }

    public void setER_Id(int ER_Id) {
        this.ER_Id = ER_Id;
    }

    public int getER_U_Id() {
        return ER_U_Id;
    }

    public void setER_U_Id(int ER_U_Id) {
        this.ER_U_Id = ER_U_Id;
    }

    public String getER_Transcript() {
        return ER_Transcript;
    }

    public void setER_Transcript(String ER_Transcript) {
        this.ER_Transcript = ER_Transcript;
    }

    public String getER_Description() {
        return ER_Description;
    }

    public void setER_Description(String ER_Description) {
        this.ER_Description = ER_Description;
    }

    public String getER_Category() {
        return ER_Category;
    }

    public void setER_Category(String ER_Category) {
        this.ER_Category = ER_Category;
    }

    public String getER_Status() {
        return ER_Status;
    }

    public void setER_Status(String ER_Status) {
        this.ER_Status = ER_Status;
    }

    public String getER_Solution() {
        return ER_Solution;
    }

    public void setER_Solution(String ER_Solution) {
        this.ER_Solution = ER_Solution;
    }

    public int getER_S_Id() {
        return ER_S_Id;
    }

    public void setER_S_Id(int ER_S_Id) {
        this.ER_S_Id = ER_S_Id;
    }

    public Timestamp getER_Timestamp() {
        return ER_Timestamp;
    }

    public void setER_Timestamp(Timestamp ER_Timestamp) {
        this.ER_Timestamp = ER_Timestamp;
    }

    @Override
    public String toString() {
        return "EventReport{" + "ER_Id=" + ER_Id + ", ER_U_Id=" + ER_U_Id + ", ER_Transcript=" + ER_Transcript + ", ER_Description=" + ER_Description + ", ER_Category=" + ER_Category + ", ER_Status=" + ER_Status + ", ER_Solution=" + ER_Solution + ", ER_S_id=" + ER_S_Id + ", ER_Timestamp=" + ER_Timestamp + '}';
    }

}
