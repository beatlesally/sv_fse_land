package wr;

import com.rometools.rome.feed.atom.Content;
import com.rometools.rome.feed.atom.Entry;
import com.rometools.rome.feed.atom.Feed;
import com.rometools.rome.feed.synd.*;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.net.URL;
import java.util.*;

public class AtomObserver implements Observer {
    private ArrayList<Entry> entries = new ArrayList<>();
    private ArrayList<Content> contents = new ArrayList<>();

    private Feed feed = new Feed("Log");

    @Override
    public void update(Observable o, Object arg) {
        String[] list = (String[]) arg;
        String[] cutWR = list[0].split("2");

        //Entry
        Entry entry = new Entry();
        entry.setCreated(new Date());
        entry.setTitle("Atom Feed WR");
        entry.setCreated(new Date());

        //Content
        Content content = new Content();
        content.setValue("Ausgangswährung: "+cutWR[0]+"\t Ausgangsbetrag: "+ list[1]+"\t Zielwährung: "+cutWR[1]+"\t Zielbetrag: "+list[2]+"\n");
        content.setType("LOG");
        contents.add(content);


        //Feed erstellen
        entry.setContents(contents);
        entries.add(entry);
        feed.setEntries(entries);

        //System.out.println(feed.toString()); Ausgeben des Feeds

    }
}
