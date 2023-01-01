package org.example;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Book {
    Book(JSONObject object) {
        setAbbreviation(object.getString("abbreviation"));
        setBibleId(object.getString("bibleId"));
        setId(object.getString("id"));
        setName(object.getString("name"));
        setNameLong(object.getString("nameLong"));
    }
    public String getNameLong() {
        return nameLong;
    }

    public void setNameLong(String nameLong) {
        this.nameLong = nameLong;
    }

    public String getBibleId() {
        return bibleId;
    }

    public void setBibleId(String bibleId) {
        this.bibleId = bibleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    String nameLong;
    String bibleId;
    String name;
    String id;
    String abbreviation;

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    List<Chapter> chapters = new ArrayList<>();
}
