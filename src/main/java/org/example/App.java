package org.example;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.Format;
import java.util.*;


public class App
{
    static List<Book> bookList = new ArrayList<>();
    public static void main(String[] args) {
        parseBible();
        writeFile(createJsonBible().toString());
    }

    public static void writeFile(String content) {
        File file = new File("/Users/slavebb/IdeaProjects/bible/res/files/bible.json");
        try {
            Files.writeString(Paths.get(file.toURI()), content, StandardOpenOption.CREATE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void parseBible() {
        JSONObject jsonObject = new JSONObject(getConnector("https://v2.api.bible/bibles/723f623685375bf8-01/books"));
        System.out.println(jsonObject);
        JSONArray books = jsonObject.getJSONArray("data");
        for (int i = 0; i < books.length(); i++) {
            JSONObject bookObject = books.getJSONObject(i);
            Book book = new Book(bookObject);
            System.out.println(i + ": " + book.getId());
            bookList.add(book);
        }

        for (Book book : bookList) {
            JSONObject chapterObject = new JSONObject(getConnector("https://v2.api.bible/bibles/723f623685375bf8-01/books/" + book.getId() + "/chapters"));
            JSONArray chapters = chapterObject.getJSONArray("data");
            for (int i = 0; i < chapters.length(); i++) {
                book.getChapters().add(new Chapter(chapters.getJSONObject(i)));
            }

            for (Chapter chapter : book.getChapters()) {
                JSONObject verseObject = new JSONObject(getConnector("https://v2.api.bible/bibles/723f623685375bf8-01/chapters/" + chapter.getId() + "/verses"));
                JSONArray verses = verseObject.getJSONArray("data");
                for (int i = 0; i < verses.length(); i++) {
                    Verse verse = new Verse(verses.getJSONObject(i));
                    JSONObject contentObject = new JSONObject(getConnector("https://v2.api.bible/bibles/723f623685375bf8-01/verses/" + verse.getId()));
//                    System.out.println("=> " + contentObject);
                    String content = contentObject.getJSONObject("data").getString("content");
                    System.out.println(content);
                    verse.setContent(content);
                    chapter.getVerses().add(verse);
                }
            }
        }
    }

    public static JSONArray createJsonBible() {
        JSONArray data = new JSONArray();

        for (Book book : bookList) {
            JSONArray chapters = new JSONArray();
            JSONObject bookObject = new JSONObject();
            bookObject.put("id", book.getId());
//            bookObject.put("bibleId", book.getBibleId());
            bookObject.put("nameLong", book.getNameLong());
            bookObject.put("name", book.getName());
            bookObject.put("abbreviation", book.getAbbreviation());
            for (Chapter chapter : book.getChapters()) {
                if (chapter.getNumber().equals("intro")) continue;
                JSONArray verses = new JSONArray();
                JSONObject chapterObject = new JSONObject();
                chapterObject.put("reference", chapter.getReference());
                chapterObject.put("bookId", chapter.getBookId());
                chapterObject.put("id", chapter.getId());
//                chapterObject.put("bibleId", chapter.getBibleId());
                chapterObject.put("number", chapter.getNumber());
                for (Verse verse : chapter.getVerses()) {
                    JSONObject verseObject = new JSONObject();
                    verseObject.put("reference", verse.getReference());
                    verseObject.put("bookId", verse.getBookId());
                    verseObject.put("chapterId", verse.getChapterId());
                    verseObject.put("id", verse.getId());
                    verseObject.put("orgId", verse.getOrgId());
//                    verseObject.put("bibleId", verse.getBibleId());
                    verseObject.put("content", verse.getContent());
                    verses.put(verseObject);
                }
                chapterObject.put("verses", verses);
                chapters.put(chapterObject);
            }
            bookObject.put("chapters", chapters);
            data.put(bookObject);
        }

        return data;
    }
    public static String getConnector(String link) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .header("content-type", "json")
                .header("include-verse-spans", "true")
                .header("include-notes", "true")
                .header("api-key", "GB1Q1dMAKtlkzggBkGecmZE")
                .uri(URI.create(link))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return response.body();
    }
}
