package com.mimishi.mimishi.model;

import java.util.List;

/**
 * Created by chen on 17-2-8.
 */

public class SignedUsers {
    public String name;
    public List<ItemList> list;

    public static class ItemList{
        public String serial_num;
        public long sign_time;
    }
 }
