package com.example.cocktailplace.ui.home;

public class Boisson {
    private String boisson;

    public Boisson () {

    }

    public Boisson (String boisson){
        this.boisson = boisson;
    }

    public String getBoisson (){
        return  boisson;
    }

    public void setBoisson (String boisson) {
        this.boisson = boisson;
    }

}
