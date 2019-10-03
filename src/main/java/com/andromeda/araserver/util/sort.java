package com.andromeda.araserver.util;

import com.rometools.rome.feed.synd.SyndEntry;

import java.util.ArrayList;
import java.util.Comparator;

public class sort {
    //sort Rss data
   public ArrayList<SyndEntry> sortDateSyndEntry(ArrayList<SyndEntry> tosort) {
        //sort by date
        tosort.sort(Comparator.comparing(SyndEntry::getPublishedDate));
        // return sorted value
        return tosort;
    }
}
