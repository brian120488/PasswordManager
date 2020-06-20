package com.example.passwordmanager;

public class Password {
    private String password;
    private String site;

    public Password(String password, String site) {
        this.password = password;
        this.site = site;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
