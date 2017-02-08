package com.mimishi.mimishi.verify;

import android.content.Context;

import com.mimishi.mimishi.model.VerifyingUsers;
import com.mimishi.mimishi.utils.LogUtils;

import java.util.List;

/**
 * Created by chen on 17-2-7.
 */

public class VerifyUsers  {

    public static boolean verifyUsers(Context context, String serialNumber, List<VerifyingUsers.UsersList> userList){

        boolean isValid = false;
        LogUtils.i("list.size", String.valueOf(userList.size()));
        LogUtils.i("num", userList.get(0).serial_num);
        for (int i = 0; i < userList.size(); i++){
            if(userList.get(i).serial_num.equals(serialNumber)){
                isValid = true;
                break;
            }
            LogUtils.i(String.valueOf(isValid));
        }
        return isValid;
    }

}
