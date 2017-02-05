package com.mimishi.mimishi.model;

import java.util.List;

/**
 * Created by chen on 17-2-5.
 */

public class ResourceMain{

    public int index;
    public String name;
    public List<ResourceList> list;

    public static class ResourceList{
        public int item_index;
        public String item_name;
        public String item_url;
    }

}
