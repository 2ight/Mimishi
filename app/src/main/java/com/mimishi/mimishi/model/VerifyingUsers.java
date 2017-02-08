package com.mimishi.mimishi.model;

import java.util.List;

/**
 * Created by chen on 17-2-7.
 */

public class VerifyingUsers  {
    public String name;
    public String time;
    public List<UsersList> list;

    public static class UsersList{
        public String serial_num;
        public long sign_time;
    }

}

