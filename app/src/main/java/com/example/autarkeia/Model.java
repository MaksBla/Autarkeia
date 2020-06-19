package com.example.autarkeia;

public class Model {
    private int image;

    private String desc;

    public Model(int image,  String desc) {
        this.image = image;

        this.desc = desc;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}