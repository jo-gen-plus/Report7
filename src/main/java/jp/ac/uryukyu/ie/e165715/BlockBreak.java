package jp.ac.uryukyu.ie.e165715;

/**
 *
 * Created by e165715 on 2017/02/03.
 */
import java.awt.*;
import javax.swing.*;

public class BlockBreak extends JFrame {
    private MainPanel mainpanel;
    public BlockBreak() {
        // タイトルを設定
        setTitle("ブロック崩し");
        setResizable(false);

        // メインパネルを作成してフレームに追加
        MainPanel panel = new MainPanel();
        Container contentPane = getContentPane();
        contentPane.add(panel);

        // パネルサイズに合わせてフレームサイズを自動設定
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new BlockBreak();

    }
}