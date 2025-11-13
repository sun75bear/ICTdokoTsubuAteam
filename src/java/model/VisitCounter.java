
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.io.*;
import jakarta.servlet.ServletContext;
public class VisitCounter {
  private static VisitCounter instance = new VisitCounter();
  private int totalVisits = 775;
  private File file;
  private VisitCounter() {
    // 初期化時はファイルをまだ設定できないので空
  }
  public static VisitCounter getInstance() {
    return instance;
  }
  // ファイルパスをサーブレットコンテキストから設定
//  public void init(ServletContext context) {
//  public void init() {
//    System.out.println("init");
//    if (file == null) {
////            String path = context.getRealPath("/WEB-INF/visitCount.txt");
//      String path = "C:/Users/abi02/Desktop/visitCount.txt";
////      String path = context.getRealPath("/dokoTsubu_GroupA/log/visitCount.txt");
//      System.out.println("インスタンス化");
//      file = new File(path);
//
//      // ファイルがあれば読み込み
//      if (file.exists()) {
//        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
//          String line = br.readLine();
//          if (line != null) {
//            totalVisits = Integer.parseInt(line);
//          }
//        } catch (IOException | NumberFormatException e) {
//          totalVisits = 0;
//        }
//      } else {
//        totalVisits = 0;
//      }
//    }
//  }
  public synchronized void increment() {
    if (file == null) {
//            String path = context.getRealPath("/log/visitCount.txt");
      String path = "C:/Users/abi05/Desktop/visitCount.txt";
//      String path = context.getRealPath("/log/visitCount.txt");
      file = new File(path);
      // ファイルがあれば読み込み
      if (file.exists()) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
          String line = br.readLine();
          if (line != null) {
            totalVisits = Integer.parseInt(line);
          }
        } catch (IOException | NumberFormatException e) {
          totalVisits = 776;
        }
      } else {
        totalVisits = 776;
      }
    }
    
    totalVisits++;
    saveToFile();
  }
  public synchronized int getTotal() {
    return totalVisits;
  }
  private void saveToFile() {
    System.out.println("saveFile");
    if (file != null) {
      try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
        pw.println(totalVisits);
        System.out.println("Success");
      } catch (IOException e) {
        e.printStackTrace();
        System.out.println("testFileSave");
      }
    }
  }
}