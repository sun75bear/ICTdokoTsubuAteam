package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author abi04
 */

public class LoginLogic {
    int userId = 0;
    public int execute(User user){
      if(user.getName().equals("奥本") && user.getPass().equals("01")){userId=1;return userId;}      
 else if(user.getName().equals("神田") && user.getPass().equals("02")){userId=2;return userId;}
 else if(user.getName().equals("城山") && user.getPass().equals("03")){userId=3;return userId;}
 else if(user.getName().equals("長谷川") && user.getPass().equals("04")){userId=4;return userId;} 
 else if(user.getName().equals("八反地") && user.getPass().equals("05")){userId=5;return userId;} 
 else if(user.getName().equals("渡辺") && user.getPass().equals("06")){userId=6;return userId;}      
 else if(user.getName().equals("荒木") && user.getPass().equals("07")){userId=7;return userId;}      
 else if(user.getName().equals("安藤") && user.getPass().equals("08")){userId=8;return userId;}      
 else if(user.getName().equals("岩本") && user.getPass().equals("09")){userId=9;return userId;}
 else if(user.getName().equals("江原") && user.getPass().equals("010")){userId=10;return userId;}
 
 ///↓テスト用ログイン
 else if(user.getName().equals("1") && user.getPass().equals("1")){userId=1;return userId;}   
        return userId;
    }
}

