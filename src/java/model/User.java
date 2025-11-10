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
public class User implements Serializable{
    private String name;
    private String pass;
    private int id;
    
    public User(){}
    public User(String name,String pass){
        //idは仮の値で初期化、ほんとはログイン成功時のIDを引き継いでuserを作ってsessionに保存したものを使う
        this.id = 114514;
        this.name = name;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

    public int getId() {
        return id;
    }
    
}
