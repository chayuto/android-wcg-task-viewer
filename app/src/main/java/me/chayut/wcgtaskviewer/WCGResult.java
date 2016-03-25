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
    private int ServerState;
    private int ValidateState;
    private int GrantedCredit;
    private int ElapsedTime;
    private String SentTime;


    private String ReceivedTime;

    public  WCGResult (JSONObject jObject){
        try {
            Name = (jObject.getString("Name"));
            DeviceID = (jObject.getInt("DeviceId"));
            DeviceName=(jObject.getString("DeviceName"));
            AppName=(jObject.getString("AppName"));
            Outcome=(jObject.getInt("Outcome"));
            ValidateState = jObject.getInt("ValidateState");
            ServerState = jObject.getInt("ServerState");

            if(jObject.has("ReceivedTime")) {
                ReceivedTime=(jObject.getString("ReceivedTime"));
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //region setter getter
    public int getDeviceID() {
        return DeviceID;
    }

    private int DeviceID;


    public String getDeviceName() {
        return DeviceName;
    }

    public int getExitStatus() {
        return ExitStatus;
    }

    public int getModTime() {
        return ModTime;
    }

    public String getReportDeadLine() {
        return ReportDeadLine;
    }


    public int getOutcome() {
        return Outcome;
    }



    public String getName() {
        return Name;
    }



    public String getAppName() {
        return AppName;
    }


    public String getReceivedTime() {
        return ReceivedTime;
    }


    public int getCpuTime() {
        return CpuTime;
    }


    public int getGrantedCredit() {
        return GrantedCredit;
    }


    public int getElapsedTime() {
        return ElapsedTime;
    }

    public String getSentTime() {
        return SentTime;
    }

    public int getServerState() {
        return ServerState;
    }

    public int getValidateState() {
        return ValidateState;
    }
    //endregion
}
