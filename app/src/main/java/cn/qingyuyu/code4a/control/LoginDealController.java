package cn.qingyuyu.code4a.control;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import cn.dxkite.baidusign.SignActivity;

/**
 * Created by harrytit on 2017/11/2.
 */

public class LoginDealController {
    public boolean call(String call, Activity con,String mail)
    {
        Log.e("call",call);
        if(call.equals("login"))
            return login(con);
        else if(call.equals("bindmail"))
            return bindMail(mail);
        else if(call.equals("checkmail"))
            return checkMail();
        return false;
    }
    private boolean login(Activity con)
    {
        Intent i=new Intent(con, SignActivity.class);
        con.startActivity(i);

        return true;
    }
    private boolean bindMail(String mailAdd)
    {
        boolean r=false;
        //绑定邮箱
        return r;
    }
    private boolean checkMail()
    {
        boolean r=false;
        //检查是否已经绑定过邮箱
        return r;
    }

}