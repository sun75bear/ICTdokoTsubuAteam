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
    private String userName;
    private String text;
    
    public Mutter(){}
    public Mutter(String userName,String text){
        this.userName = userName;
        this.text = text;    
    }

    public String getUserName() {
        return userName;
    }

    public String getText() {
        return text;
    }
    
}
