package com.example.appdocsach;

import java.util.ArrayList;

public class Phan {
    private int idphan;
    private String tenPhan;
    private String text;
    private int idsach;

    public Phan(String tenPhan) {
        this.tenPhan = tenPhan;
    }

    public Phan(int idphan, String tenPhan, String text, int idsach) {
        this.idphan = idphan;
        this.tenPhan = tenPhan;
        this.text = text;
        this.idsach = idsach;
    }

    public int getIdphan() {
        return idphan;
    }

    public void setIdphan(int idphan) {
        this.idphan = idphan;
    }

    public String getTenPhan() {
        return tenPhan;
    }

    public void setTenPhan(String tenPhan) {
        this.tenPhan = tenPhan;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIdsach() {
        return idsach;
    }

    public void setIdsach(int idsach) {
        this.idsach = idsach;
    }
}
