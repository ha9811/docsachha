package com.example.appdocsach;

import java.io.Serializable;

public class Sach implements Serializable {
    private int id;
    private String bookName;
    private String imgHinh;
    private String author;
    private int idsach;

    public Sach(int id, String bookName, String imgHinh, String author, int idsach) {
        this.id = id;
        this.bookName = bookName;
        this.imgHinh = imgHinh;
        this.author = author;
        this.idsach = idsach;
    }

    public Sach(String bookName, String imgHinh, String author, int idsach) {
        this.bookName = bookName;
        this.imgHinh = imgHinh;
        this.author = author;
        this.idsach = idsach;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getImgHinh() {
        return imgHinh;
    }

    public void setImgHinh(String imgHinh) {
        this.imgHinh = imgHinh;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getIdsach() {
        return idsach;
    }

    public void setIdsach(int idsach) {
        this.idsach = idsach;
    }
}
