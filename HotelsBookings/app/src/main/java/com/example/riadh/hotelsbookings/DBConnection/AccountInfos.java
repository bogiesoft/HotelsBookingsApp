package com.example.riadh.hotelsbookings.DBConnection;

import android.content.Context;

/**
 * Created by riadh on 8/19/2016.
 */
public class AccountInfos {
    private static String fullUsername;
    private static String Userid;


    private static String isadmin;
    private static Context mCtx;

    AccountInfos(Context ctxt){
        mCtx=ctxt;
    }

    public static String getUserid() {
        return Userid;
    }


    public static void setUserid(String userid) {
        AccountInfos.Userid = userid;
    }

    public static String getFullUsername() {
        return fullUsername;
    }

    public static void setFullUsername(String fullUsername) {
        AccountInfos.fullUsername = fullUsername;
    }

    public static void setIsadmin(String isadmin) {
        AccountInfos.isadmin = isadmin;
    }

    public static String getIsadmin() {
        return isadmin;
    }
}