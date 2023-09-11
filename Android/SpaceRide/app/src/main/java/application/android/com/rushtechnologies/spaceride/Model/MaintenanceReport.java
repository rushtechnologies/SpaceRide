package application.android.com.rushtechnologies.spaceride.Model;

import java.sql.Timestamp;

public class MaintenanceReport {

    private int MR_Id;
    private String MR_Module;
    private String MR_Description;
    private String MR_Status;
    private String MR_Solution;
    private int MR_S_Id;
    private Timestamp MR_Timestamp;

    public MaintenanceReport() {
    }

    public MaintenanceReport(int MR_Id, String MR_Module, String MR_Description, String MR_Status, String MR_Solution, int MR_S_Id, Timestamp MR_Timestamp) {
        this.MR_Id = MR_Id;
        this.MR_Module = MR_Module;
        this.MR_Description = MR_Description;
        this.MR_Status = MR_Status;
        this.MR_Solution = MR_Solution;
        this.MR_S_Id = MR_S_Id;
        this.MR_Timestamp = MR_Timestamp;
    }

    public int getMR_Id() {
        return MR_Id;
    }

    public void setMR_Id(int MR_Id) {
        this.MR_Id = MR_Id;
    }

    public String getMR_Module() {
        return MR_Module;
    }

    public void setMR_Module(String MR_Module) {
        this.MR_Module = MR_Module;
    }

    public String getMR_Description() {
        return MR_Description;
    }

    public void setMR_Description(String MR_Description) {
        this.MR_Description = MR_Description;
    }

    public String getMR_Status() {
        return MR_Status;
    }

    public void setMR_Status(String MR_Status) {
        this.MR_Status = MR_Status;
    }

    public String getMR_Solution() {
        return MR_Solution;
    }

    public void setMR_Solution(String MR_Solution) {
        this.MR_Solution = MR_Solution;
    }

    public int getMR_S_Id() {
        return MR_S_Id;
    }

    public void setMR_S_Id(int MR_S_Id) {
        this.MR_S_Id = MR_S_Id;
    }

    public Timestamp getMR_Timestamp() {
        return MR_Timestamp;
    }

    public void setMR_Timestamp(Timestamp MR_Timestamp) {
        this.MR_Timestamp = MR_Timestamp;
    }

    @Override
    public String toString() {
        return "MaintenanceReport{" +
                "MR_Id=" + MR_Id +
                ", MR_Module=" + MR_Module +
                ", MR_Description=" + MR_Description +
                ", MR_Status=" + MR_Status +
                ", MR_Solution=" + MR_Solution +
                ", MR_S_Id=" + MR_S_Id +
                ", MR_Timestamp=" + MR_Timestamp +
                '}';
    }

}
