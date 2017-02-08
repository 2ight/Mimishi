package com.mimishi.mimishi.verify;

import android.content.Context;

import com.mimishi.mimishi.model.VerifyingUsers;

import java.util.List;

/**
 * Created by chen on 17-2-7.
 */

public class VerifyUsers  {

    public static boolean verifyUsers(Context context, String serialNumber, List<VerifyingUsers.UsersList> userList){


        boolean isValid = false;
        for (int i = 0; i > userList.size(); i++){
            if(userList.get(i).serial_num.equals(serialNumber)){
                isValid = true;
                break;
            }
        }
        return isValid;
    }

}
