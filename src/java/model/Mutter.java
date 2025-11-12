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
    private String mutterId;
    private String username;
    private String text;
    private int good;
    private int bad;
    
    public Mutter(){}
    public Mutter(String username,String text){
        //後々、IDをmutterIDに変えるべき
        this.id = 0;
        this.username = username;
        this.text = text;
        this.good = 0;
        this.bad = 0;
    }
    //abi04、mutterIdで保存、統合が終わったら以後それを使う
        public Mutter(String username, String text, String mutterId) {
        this.username = username;
        this.text = text;
        this.mutterId = mutterId;
    }
    
    //テスト用、コンストラクタのidいいね引数は廃止して、JAVABEANS準拠にする事
    public Mutter(String username,String text,int good){
        this.id = 0;
        this.username = username;
        this.text = text;
        this.good = good;
        this.bad = 0;
    }
    public Mutter(int id,String username,String text,int good){
        this.id = id;
        this.username = username;
        this.text = text;
        this.good = good;
        this.bad = 0;
    }
    //テスト用、mutterIDに対応
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

    public int getId() {
        return id;
    }
    public String getMutterId() {
        return mutterId;
    }
}
