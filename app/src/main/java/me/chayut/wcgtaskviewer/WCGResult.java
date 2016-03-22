package me.chayut.wcgtaskviewer;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by chayut on 22/03/16.
 */
public class WCGResult {

    private final static String TAG = "WCGResult";



    private String DeviceName;
    private int ExitStatus;
    private int ModTime;
    private String ReportDeadLine;
    private int Outcome;
    private String Name;
    private String AppName;
    private int CpuTime;
    private int GrantedCredit;
    private int ElapsedTime;
    private String SentTime;

    public  WCGResult (JSONObject jObject){
        try {
            this.setName(jObject.getString("Name"));
            this.setDeviceID(jObject.getInt("DeviceId"));
            this.setDeviceName(jObject.getString("DeviceName"));
            this.setAppName(jObject.getString("AppName"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //region setter getter
    public int getDeviceID() {
        return DeviceID;
    }

    public void setDeviceID(int deviceID) {
        DeviceID = deviceID;
    }

    private int DeviceID;

    public static String getTAG() {
        return TAG;
    }

    public String getDeviceName() {
        return DeviceName;
    }

    public void setDeviceName(String deviceName) {
        DeviceName = deviceName;
    }

    public int getExitStatus() {
        return ExitStatus;
    }

    public void setExitStatus(int exitStatus) {
        ExitStatus = exitStatus;
    }

    public int getModTime() {
        return ModTime;
    }

    public void setModTime(int modTime) {
        ModTime = modTime;
    }

    public String getReportDeadLine() {
        return ReportDeadLine;
    }

    public void setReportDeadLine(String reportDeadLine) {
        ReportDeadLine = reportDeadLine;
    }

    public int getOutcome() {
        return Outcome;
    }

    public void setOutcome(int outcome) {
        Outcome = outcome;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAppName() {
        return AppName;
    }

    public void setAppName(String appName) {
        AppName = appName;
    }

    //endregion
}
