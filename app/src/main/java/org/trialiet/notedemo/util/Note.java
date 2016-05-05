package org.trialiet.notedemo.util;

import java.io.Serializable;

/**
 * Created by Trialiet on 2016/4/27.
 */
public class Note implements Serializable {
    private long id;
    private String title;
    private String content;

    public Note(long id, String title, String content){
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Note(String title, String content){
        this.title = title;
        this.content = content;
    }

    public Note(){
        this.id = 0;
        this.title = "";
        this.content = "";
    }

    public long getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString(){
        return this.title;
    }
}
