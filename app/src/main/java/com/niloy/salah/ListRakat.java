package com.niloy.salah;

public class ListRakat {
    private String id, priority, rakat, niyat_arabic, niyat_bangla_pornounciation, niyat_bangla;

    public ListRakat(String id, String priority, String rakat, String niyat_arabic, String niyat_bangla_pornounciation, String niyat_bangla) {
        this.id = id;
        this.priority = priority;
        this.rakat = rakat;
        this.niyat_arabic = niyat_arabic;
        this.niyat_bangla_pornounciation = niyat_bangla_pornounciation;
        this.niyat_bangla = niyat_bangla;
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

    public String getNiyat_arabic() {
        return niyat_arabic;
    }

    public String getNiyat_bangla_pornounciation() {
        return niyat_bangla_pornounciation;
    }

    public String getNiyat_bangla() {
        return niyat_bangla;
    }
}
