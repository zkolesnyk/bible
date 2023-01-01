package org.example;

import org.json.JSONObject;

public class Verse {
    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getBibleId() {
        return bibleId;
    }

    public void setBibleId(String bibleId) {
        this.bibleId = bibleId;
    }

    String reference;
    String bookId;
    String chapterId;
    String id;
    String orgId;
    String bibleId;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    int number;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    String content;

    Verse(JSONObject object) {
        setOrgId(object.getString("orgId"));
        setBibleId(object.getString("bibleId"));
        setId(object.getString("id"));
        setBookId(object.getString("bookId"));
        setChapterId(object.getString("chapterId"));
        setReference(object.getString("reference"));
    }
}
