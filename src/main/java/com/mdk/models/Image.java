package com.mdk.models;

public class Image extends AbstractModel<Image>{
    private String url;
    private String name;
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
