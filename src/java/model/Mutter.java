/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author abi06
 */
public class Mutter implements Serializable{
    private int id;
    private String userName;
    private String text;
    private int good;
    private int bad;
    
    public Mutter(){}
    public Mutter(String userName,String text){
        this.id = 0;
        this.userName = userName;
        this.text = text;
        this.good = 0;
        this.bad = 0;
    }

    public String getUserName() {
        return userName;
    }

    public String getText() {
        return text;
    }

    public int getGood() {
        return good;
    }

    public int getBad() {
        return bad;
    }
    
}
