/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author abi04
 */
public class User implements Serializable{
    //フィールド
    private String name;
    private String pass;
    public int userId;
    
    
    //コンストタ
    public User(){}
    
    public User(String name, String pass,int userId) {
        this.name = name;
        this.pass = pass;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }
    
    public int getUserId() {
        return userId;
    }
    
}
