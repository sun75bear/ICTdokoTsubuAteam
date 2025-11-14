package listener;

import java.util.ArrayList;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import model.Mutter; // Mutterクラスをインポート

@WebListener
public class AppInitializer implements ServletContextListener {

    // Webアプリケーション起動時に一度だけ実行される
    @Override
    public void contextInitialized(ServletContextEvent event) {
        // アプリケーションスコープを取得
        ServletContext application = event.getServletContext();
        
        // リストがまだ存在しないことを確認し、初期化
        if (application.getAttribute("mutterList") == null) {
            
            // 初期の空のリストを作成
            ArrayList<Mutter> mutterList = new ArrayList<>();
            // ★★★ アプリケーション起動時にテストデータを１２こ作成し、mutterListに追加 ★★★
            // String　mutterIdに対応
            Mutter mutter1 = new Mutter("f8d9b1a0-1c3b-4e5a-8f7d-2a6c9e4b3d1f","奥本", "データ１、つぶやきアプリへようこそ！",0);
            Mutter mutter2 = new Mutter("c7e6a9b1-2d4e-5f6b-9c8a-3b7d5e9f1a2c","奥本", "データ2、サコダ車両。",5);
            Mutter mutter3 = new Mutter("a1b2c3d4-e5f6-7a8b-9c0d-1e2f3a4b5c6d","奥本", "データ3、離職者によるサコダ車両の不正内部告発",7);
            Mutter mutter4 = new Mutter("8e7d6c5b-4a3f-2e1d-0c9b-8a7f6e5d4c3b","奥本", "データ４、さこだ車両オイル交換サービス！",2);
            Mutter mutter5 = new Mutter("3c4d5e6f-7a8b-9c0d-1e2f-3a4b5c6d7e8f","神田", "データ５、革ジャン",1);
            Mutter mutter6 = new Mutter("f9e8d7c6-b5a4-9d8c-7b6a-5f4e3d2c1b0a","城山", "データ６、サコダ、どこつぶアプリへ！",5);
            Mutter mutter7 = new Mutter("1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d","長谷川", "データ７、たまたまだよ",8);
            Mutter mutter8 = new Mutter("6f5e4d3c-2b1a-0f9e-8d7c-6b5a4f3e2d1c","八反地", "データ８、たまにね",10);
            Mutter mutter9 = new Mutter("b1c2d3e4-f5a6-8b9c-d0e1-f2a3b4c5d6e7","渡辺", "データ９、今はまだたまねぎ",4);
            Mutter mutter10 = new Mutter("a8b7c6d5-e4f3-2a1b-0c9d-8e7f6a5b4c3d","荒木", "データ１０、明日は明日の風が吹く",2);
            Mutter mutter11 = new Mutter("d4e5f6a7-b8c9-0d1e-2f3a-4b5c6d7e8f90","安藤", "データ１１、幸せ風味",3);
            Mutter mutter12 = new Mutter("2c3b4a5d-6e7f-8c9a-b0d1-e2f3a4b5c6d7","江原", "データ１２、お書上げ",0);
            Mutter mutter13 = new Mutter("2c3a0c0d-6a5e-8c9a-b0d1-e2f3a4b5c6d7","海","今日からサコダ車両で開発を頑張ります！",0);

            mutterList.add(mutter13);    
            mutterList.add(mutter12);            
            mutterList.add(mutter11);
            mutterList.add(mutter10);
            mutterList.add(mutter9);            
            mutterList.add(mutter8);
            mutterList.add(mutter7);            
            mutterList.add(mutter6);            
            mutterList.add(mutter5);
            mutterList.add(mutter4);
            mutterList.add(mutter3);            
            mutterList.add(mutter2);
            mutterList.add(mutter1); 
            // ★★★ テストデータ追加完了 ★★★
            
            // データをアプリケーションスコープに設定
            application.setAttribute("mutterList", mutterList);
            System.out.println("【初期化成功】mutterList をアプリケーションスコープに設定しました。");
        }
    }

    // Webアプリケーション終了時に実行される
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        // ...
    }
}