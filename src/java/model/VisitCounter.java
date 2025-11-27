
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.io.*;
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
  public synchronized void increment() {
      //インクリメント操作のみを担当、データの読み出しをスコープに含めないようにしたから、下記テキスト読み出し操作をコメントアウト
//    if (file == null) {
////            String path = context.getRealPath("/log/visitCount.txt");
//    //デスクトップ上のパス指定は、実行環境に依存するからあんまりよくない
//      String path = "C:/Users/abi06/Desktop/visitCount.txt";
////      String path = context.getRealPath("/log/visitCount.txt");
//      file = new File(path);
//    // 【修正点】ファイルがあれば、毎回ファイルから最新の値を読み込むようにする
//        if (file != null) { 
//            // 読み込みロジックをgetTotal()からコピーまたは分離してここに配置
//            // ★★★ ここにファイル読み込みロジックを再配置した ★★★
//
//            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
//              String line = br.readLine();
//                if (line != null) {
//                  totalVisits = Integer.parseInt(line); // ファイルの最新値でメモリを上書き
//                }
//              } catch (IOException | NumberFormatException e) {
//              // エラー処理
//            }
//        }
//    }
    totalVisits++;
    saveToFile();
  }
  public synchronized int getTotal() { 
    return totalVisits;
  }
  
// 【新規】デスクトップ上ファイルから最新の値を読み込み、サーバー上のVisitCounterインスタンスの内容を更新するメソッド
    public synchronized void syncVisitCounter() {
        if (file == null) {
            // パス設定は init() で行うのが理想的だが、ここではハードコーディングを維持
            String path = "C:/Users/abi04/Desktop/visitCount.txt";
            file = new File(path);
        }
        
        // ファイルが存在していれば、メモリ上の値をファイルの内容で上書き
        if (file.exists()) { 
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line = br.readLine();
                if (line != null) {
                    // ★★★ ファイルに入力があった場合は、totalVisitsを入力値で上書き ★★★
                    totalVisits = Integer.parseInt(line); 
                }
            } catch (IOException | NumberFormatException e) {
                // エラー時の処理 (ファイル内容が不正な場合など)
                System.err.println("Error reading visit count file: " + e.getMessage());
            }
        }
        // else: ファイルが存在しなければ totalVisits は初期値 775のままで処理が通る。
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