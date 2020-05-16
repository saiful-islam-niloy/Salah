package com.niloy.salah;

public class ListRakat {
    String id, rakat, niyat_arabic, niyat_bangla_pornounciation, niyat_bangla;

    public ListRakat(String id, String rakat, String niyat_arabic, String niyat_bangla_pornounciation, String niyat_bangla) {
        this.id = id;
        this.rakat = rakat;
        this.niyat_arabic = niyat_arabic;
        this.niyat_bangla_pornounciation = niyat_bangla_pornounciation;
        this.niyat_bangla = niyat_bangla;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRakat() {
        return rakat;
    }

    public void setRakat(String rakat) {
        this.rakat = rakat;
    }

    public String getNiyat_arabic() {
        return niyat_arabic;
    }

    public void setNiyat_arabic(String niyat_arabic) {
        this.niyat_arabic = niyat_arabic;
    }

    public String getNiyat_bangla_pornounciation() {
        return niyat_bangla_pornounciation;
    }

    public void setNiyat_bangla_pornounciation(String niyat_bangla_pornounciation) {
        this.niyat_bangla_pornounciation = niyat_bangla_pornounciation;
    }

    public String getNiyat_bangla() {
        return niyat_bangla;
    }

    public void setNiyat_bangla(String niyat_bangla) {
        this.niyat_bangla = niyat_bangla;
    }
}
