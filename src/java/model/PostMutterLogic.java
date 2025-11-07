/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author abi06
 */
public class PostMutterLogic {
    public void execute(Mutter mutter, List<Mutter> mutterList){
        //つぶやきを新着順（リストの先頭についかする）
        mutterList.add(0,mutter);
    }
}
