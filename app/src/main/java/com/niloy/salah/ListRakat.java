package com.niloy.salah;

class ListRakat {
    private String id, priority, rakat;

    public ListRakat(String id, String priority, String rakat) {
        this.id = id;
        this.priority = priority;
        this.rakat = rakat;
    }

    public String getId() {
        return id;
    }

    public String getPriority() {
        return priority;
    }

    public String getRakat() {
        return rakat;
    }
}
