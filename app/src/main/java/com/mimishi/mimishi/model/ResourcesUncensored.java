package com.mimishi.mimishi.model;

import java.util.List;

/**
 * Created by chen on 17-2-4.
 */

public class ResourcesUncensored {

    public int index;
    public String name;
    public List<ItemList> list;

    public static class ItemList{
        public int item_index;
        public String item_title;
        public String item_thumbnail;
        public String item_video;

    }


}
