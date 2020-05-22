package com.niloy.salah;

class ListSalah {
    private String id;
    private String name;
    private String totalRakat;

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
