package jp.ac.uryukyu.ie.e165715;

/**
 *
 * Created by e165715 on 2017/02/03.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainPanel extends JPanel implements Runnable, MouseMotionListener {
    private Racket racket;// ラケット
    private Ball ball;//ボール
    private Block[] block; // ブロック

    // パネルサイズ
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    // ブロックの行数
    private static final int NUM_BLOCK_ROW = 10;
    // ブロックの列数
    private static final int NUM_BLOCK_COL = 7;
    // ブロック数
    private static final int NUM_BLOCK = NUM_BLOCK_ROW * NUM_BLOCK_COL;


    private Thread thread;

    public MainPanel() {

        // パネルの推奨サイズを設定、pack()するときに必要です。
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // 変数などの初期化をします。

        addMouseMotionListener(this);

        // ラケット作成
        racket = new Racket();
        //　ブロック作成
        block = new Block[NUM_BLOCK];
        //　ボール作成
        ball = new Ball();
        // ブロックを並べる
        for (int i = 0; i < NUM_BLOCK_ROW; i++) {
            for (int j = 0; j < NUM_BLOCK_COL; j++) {
                int x = j * Block.WIDTH + Block.WIDTH;
                int y = i * Block.HEIGHT + Block.HEIGHT;
                block[i * NUM_BLOCK_COL + j] = new Block(x, y);
            }
        }
        //threadをスタートするとrunが走ります。
        thread = new Thread(this);
        thread.start();

    }

    //ゲームのメインループ
    public void run() {

        while (true) {
            // ボールの移動
            ball.move();

            // ラケットとボールの衝突処理
            int collidePos = racket.collideWith(ball);
            // ラケットに当たっていたら
            if (collidePos != Racket.NO_COLLISION) {
                // ボールの当たった位置に応じてボールの速度を変える
                switch (collidePos) {
                    case Racket.LEFT:
                        // ラケットの左側に当たったときは左に反射するようにしたい
                        // もしボールが右に進んでいたら反転して左へ
                        // 左に進んでいたらそのまま
                        if (ball.getVX() > 0) ball.boundX();
                        ball.boundY();
                        break;
                    case Racket.RIGHT:
                        // ラケットの右側に当たったときは右に反射するようにしたい
                        // もしボールが左に進んでいたら反転して右へ
                        // 右に進んでいたらそのまま
                        if (ball.getVX() < 0) ball.boundX();
                        ball.boundY();
                        break;
                }
            }


            // ブロックとボールの衝突処理
            for (int i = 0; i < NUM_BLOCK; i++) {
                // すでに消えているブロックは無視
                if (block[i].getDeleted())
                    continue;
                // ブロックの当たった位置を計算
                collidePos = block[i].collideWith(ball);
                // ブロックに当たっていたら
                if (collidePos != Block.NO_COLLISION) {
                    block[i].delete();
                    // ボールの当たった位置からボールの反射方向を計算
                    switch (collidePos) {
                        case Block.DOWN :
                        case Block.UP :
                            ball.boundY();
                            break;
                        case Block.LEFT :
                        case Block.RIGHT :
                            ball.boundX();
                            break;
                        case Block.UP_LEFT :
                        case Block.UP_RIGHT :
                        case Block.DOWN_LEFT :
                        case Block.DOWN_RIGHT :
                            ball.boundXY();
                            break;
                    }
                    break; // 1回に壊せるブロックは1つ
                }
            }
            repaint();

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    // マウスを動かしたとき呼び出される
    public void mouseMoved(MouseEvent e) {
        int x = e.getX(); // マウスのX座標
        racket.move(x); // ラケットを移動
        repaint();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        racket.draw(g);
        ball.draw(g);
        //NUM_BLOCKの分だけブロックを追加する
        for (int i = 0; i < NUM_BLOCK; i++) {
            if (!block[i].getDeleted()) {
                block[i].draw(g);
            }
        }
        // 盤面を描いたり、フィールドを描いたりする
    }


    // マウスをドラッグしたとき呼び出される
    public void mouseDragged(MouseEvent e) {
    }

    public static int getWIDTH(){
        return WIDTH;
    }
    public static int getHEIGHT(){
        return HEIGHT;
    }
}

