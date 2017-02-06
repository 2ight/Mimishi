package com.mimishi.mimishi.model;

import java.util.List;

/**
 * Created by chen on 17-2-4.
 */

public class ResourcesMain {

    public int index;
    public String name;
    public List<ItemList> list;

    public static class ItemList{
        public int item_index;
        public String item_name;
        public String item_url;

    }


}
