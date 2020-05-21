package com.niloy.salah;

public class ListSalah {
    String id;
    String name;
    String totalRakat;

    public ListSalah(String id, String name, String totalRakat) {
        this.id = id;
        this.name = name;
        this.totalRakat = totalRakat;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTotalRakat() {
        return totalRakat;
    }
}
