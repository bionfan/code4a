package cn.atd3.code4a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Environment;
import android.util.Log;

import java.io.File;


/**
 * 系统定义的常量
 * Created by Administrator on 2017\8\8 0008.
 */

public final class Constant {

    public final static String serverAddress = "http://code4a.atd3.cn/";
    public final static String shareUrl = "http://blog.qingyuyu.cn/";
    public final static String shareImg = "http://blog.qingyuyu.cn/conf/profile.png";
    public final static String remoteAdImg = "http://code4a.atd3.cn/ad.png";
    public final static String remoteAdUrl = "http://code4a.atd3.cn/adurl.txt";

    public final static int C4DROID = 0;
    public final static int AIDE = 1;
    public final static int ANDROID = 2;
    public final static int SUCESS = 0;
    public final static int INFO = 1;
    public final static int NORMAL = 2;
    public final static int WARNING = 3;
    public final static int ERROR = 4;
    public final static String sdcardName = File.separator + "code4a";
    public final static String privateString = File.separator + ".private";
    public final static String zipDir = File.separator + "zip";
    public final static String zipFile = File.separator + "code.zip";
    private static boolean init = false;
    private static String privateFilePath;
    private static String publicFilePath;

    private static String adImg = null;
    private static String adUrl = File.separator + "adUrl.txt";
    private static String userData = File.separator + "user.data";


    private static boolean debug;


    private Constant(Context context) {
        Constant.debug = judgeDebug(context);
        Constant.privateFilePath = context.getFilesDir().getAbsolutePath();
        Constant.publicFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + sdcardName;
        if (debug) {
            Constant.privateFilePath = Constant.publicFilePath + privateString;
        }
        Log.e("privateFilePath", privateFilePath);
        adImg = privateFilePath + File.separator + "adImg.png";
        adUrl = privateFilePath + File.separator + "adUrl.txt";
        userData = privateFilePath + File.separator + "user.data";
        init = true;
    }

    private boolean judgeDebug(Context context) {
        if (context.getApplicationInfo() != null && (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0) {
            return true;
        }
        return false;
    }

    public static void init(Context context) {
        if (!init) {
            new Constant(context);
        }
    }

    public static String getPrivateFilePath() {
        return privateFilePath;
    }

    public static String getPublicFilePath() {
        return publicFilePath;
    }

    public static String getAdImg() {
        return adImg;
    }

    public static String getAdUrl() {
        return adUrl;
    }

    public static String getUserData() {
        return userData;
    }

    public static boolean isDebug() {
        return debug;
    }
}