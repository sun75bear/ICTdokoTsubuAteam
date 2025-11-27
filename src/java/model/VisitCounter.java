
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import jakarta.servlet.ServletContext;
import java.io.*;
public class VisitCounter {
  private static VisitCounter instance = new VisitCounter();
  private int totalVisits = 775;
  private File file;
  private VisitCounter() {
    // 初期化時はファイルをまだ設定できないので空
  }
  
    // initメソッド（サーバー起動時に一度だけ呼ばれる）
    public void init(ServletContext context) {
        if (file == null) {
            //あらかじめ準備しておいた、サーバ起動時の初期値読み取りのみに使用するテキストファイルを読ませる
            String resourcePath = "/WEB-INF/defaultVisitCount.txt";

            // ★★★ 読み込み: リソースストリームを使用 ★★★
            try (InputStream is = context.getResourceAsStream(resourcePath);
                 BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

                String line;
                // ファイルの内容を行ごとに読み込むループ
                while ((line = br.readLine()) != null) {

                    // 【コメント行のスキップ】: 先頭の空白をトリミングしてからチェック
                    String trimmedLine = line.trim();
                    if (trimmedLine.startsWith("//") || trimmedLine.isEmpty()) {
                        continue; // コメント行または空行の場合は次の行へスキップ
                    }

                    // コメントではない行が見つかったら、それを値として設定し、ループを抜ける
                    totalVisits = Integer.parseInt(trimmedLine);
                    System.out.println("VisitCounter initialized from resource: " + totalVisits);
                    break; // 最初の有効な行を読み込んだら終了
                }
            } catch (Exception e) {
                // 初期ファイル存在しない場合は、そのまま totalVisits = 770を初期値として開始
                System.out.println("Initial visitCount file not found or read error. Starting from scratch.");
                totalVisits = 770; // ファイルがない場合は775から開始(777で起動するギミックがVisitCounter.javaに仕込まれているから)
            }
            
            // ★★★ 書き込みパスの確立: サーバーの一時ディレクトリを使用する ★★★
            // WAR内のファイルは書き換えられないため、書き込み先は安全な外部パスにする。
            // このパスはOS/実行環境に依存せず、Java VMが管理するテンポラリディレクトリとなる。
            // 実際のファイルパスは、System.getProperty("java.io.tmpdir") の値によって動的に決定される。
            // 
            // 【実行環境別のファイルパスの例】
            //   Windows: %TEMP%\visitCount_write.txt 
            //   Linux/macOS: /tmp/visitCount_write.txt   
            
            String writePath = System.getProperty("java.io.tmpdir") + File.separator + "visitCount_write.txt";
            file = new File(writePath);

            // 初回書き込み（読み込み値の反映）
            saveToFile();
            System.out.println("VisitCounter write file path: " + writePath);
        }
    }
  
  public static VisitCounter getInstance() {
    return instance;
  }
  public synchronized void increment() {
      //インクリメント操作のみを担当、データの読み出しをスコープに含めないようにした
    totalVisits++;
    saveToFile();
  }
  public synchronized int getTotal() { 
    return totalVisits;
  }
  
// 【新規】デスクトップ上ファイルから最新の値を読み込み、サーバー上のVisitCounterインスタンスの内容を更新するメソッド
    public synchronized void syncVisitCounter() {
//        if (file == null) {
//            // パス設定は init() で行うのが理想的だが、ここではハードコーディングを維持
//            String path = "C:/Users/abi04/Desktop/visitCount.txt";
//            file = new File(path);
//        }
        
        // ファイルが存在していれば、メモリ上の値をファイルの内容で上書き
        if (file != null && file.exists()) { 
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