package org.example;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Chapter {

    Chapter(JSONObject object) {
        setBibleId(object.getString("bibleId"));
        setBookId(object.getString("bookId"));
        setNumber(object.getString("number"));
        setId(object.getString("id"));
        setReference(object.getString("reference"));
    }
    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBibleId() {
        return bibleId;
    }

    public void setBibleId(String bibleId) {
        this.bibleId = bibleId;
    }

    String reference;
    String number;
    String bookId;
    String id;
    String bibleId;

    public List<Verse> getVerses() {
        return verses;
    }

    List<Verse> verses = new ArrayList<>();
}
