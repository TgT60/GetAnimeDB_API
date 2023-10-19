package com.example.kostyaproject;

import android.util.Log;
import android.util.Xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;

public class AnimeXmlParser extends DefaultHandler {
    Anime anime;
    final String TAG = "demo";

    StringBuilder buffer = new StringBuilder();



    public Anime parse(InputStream inputStream) throws IOException, SAXException {
        Xml.parse(inputStream, Xml.Encoding.UTF_8,this);
        return anime;
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if(localName.equals("anime")){
            anime = new Anime();
            anime.setId(attributes.getValue("id"));
        } else if (localName.equals("titles")) {
            anime.setTitles(new Titles());
        }
        buffer.setLength(0);

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        Log.d(TAG, "endElement: " + localName);
        Log.d(TAG, "debug buffer: " +buffer.toString());
        Log.d(TAG, "debug localName: " +localName);

        if(localName.equals("type") && buffer.toString() != null){
            anime.setType(buffer.toString());
            Log.d(TAG, "debug anime type: " +anime.type);
        } else if(localName.equals("episodecount")){
            anime.setEpisodecount(buffer.toString());
        } else if(localName.equals("startdate")){
            anime.setStartdate(buffer.toString());
        } else if(localName.equals("enddate")){
            anime.setEnddate(buffer.toString());
        }else if(anime.getTitles() != null){
            if(localName.equals("title")){
                anime.getTitles().setTitle(buffer.toString());
            }
        }
        anime.setType("dota");
        Log.d(TAG, "debug anime type after if statement: " +anime.getStartdate());

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        buffer.append(ch,start,length);
        Log.d(TAG, "characters: " + buffer.toString());
    }
}
