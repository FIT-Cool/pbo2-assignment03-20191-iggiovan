package com.gio.Entity;

import javafx.beans.property.ObjectProperty;

import java.time.LocalDate;
import java.util.*;

public class Item {
    private int idItem ;
    private String namaItem;
    private Category category;
    private LocalDate date;


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getNamaItem() {
        return namaItem;
    }

    public void setNamaItem(String namaItem) {
        this.namaItem = namaItem;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return namaItem;
    }

}
