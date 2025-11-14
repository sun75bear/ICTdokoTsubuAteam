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
    private String mutterId;
    private String username;
    private String text;
    private int good;
    private int bad;
    
    public Mutter(){}
    //name,text,mutterIdでpostされたつぶやきを作成してAddListする操作に使われている
        public Mutter(String username, String text, String mutterId) {
        this.username = username;
        this.text = text;
        this.mutterId = mutterId;
        this.good = 0;
        this.bad = 0;
    }
    
    //初期値作成用、mutterIDに対応
    public Mutter(String mutterId,String username,String text,int good){
        this.mutterId = mutterId;
        this.username = username;
        this.text = text;
        this.good = good;
        this.bad = 0;
    }

    public String getUsername() {
        return username;
    }

    public String getText() {
        return text;
    }
    //Mutterインスタンスのtext編集用にセッターメソッドを追加
    public void setText(String text) {
        this.text =text;
    }

    
    public int getGood() {
        return good;
    }

    public int getBad() {
        return bad;
    }
    
    public String getMutterId() {
        return mutterId;
    }
}
