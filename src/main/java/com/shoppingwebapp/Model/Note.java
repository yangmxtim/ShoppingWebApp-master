package com.shoppingwebapp.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="note_id")
    private int id;

    @Column(name="title")
    private String title;

    @Column(name="content")
    private String content;

    @Column(name="user_id")
    private int userId;

    public Note() {
    }




    public Note(String title, String content, int userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    public int getId() {
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

    public int getUserId() {return userId;}

    public void setUserId(int userId) {this.userId = userId;}

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", userId=" + userId +
                '}';
    }
}
