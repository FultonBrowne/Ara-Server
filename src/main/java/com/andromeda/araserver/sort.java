package com.andromeda.araserver;

import com.rometools.rome.feed.synd.SyndEntry;

import java.util.ArrayList;
import java.util.Comparator;

public class sort {
    ArrayList<SyndEntry> sortDateSyndEntry(ArrayList<SyndEntry> tosort){
        tosort.sort(Comparator.comparing(SyndEntry::getPublishedDate));
        return tosort;

    }
}
