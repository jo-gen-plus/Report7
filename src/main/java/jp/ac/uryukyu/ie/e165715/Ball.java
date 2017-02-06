package jp.ac.uryukyu.ie.e165715;

import java.awt.*;
import java.util.Random;
/**
 *
 * Created by e165715 on 2017/02/06.
 */
public class Ball {

    // サイズ
    private static final int SIZE = 8;
    // ボールの当たり位置


    // 位置（ボールを囲む矩形の左上隅）
    private int x, y;
    // 速度
    private int vx, vy;

    // 乱数生成器
    private Random rand;

    public Ball() {
        rand = new Random(System.currentTimeMillis());

        // 位置を初期化
        x = rand.nextInt(MainPanel.getWIDTH() - SIZE);
        y = 0;

        // 速度を初期化（とりあえず固定）
        vx = 5;
        vy = 5;
    }

    /**
     * ボールを描画
     *
     * @param g
     */
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);

        g.fillOval(x, y, SIZE, SIZE);
    }

    /**
     * ボールの移動
     *
     */
    public void move() {
        x += vx;
        y += vy;

        // 左右の壁にぶつかった場合にバウンド
        if (x < 0 || x > MainPanel.getWIDTH() - SIZE) {
            vx = -vx;
        }

        // 上下の壁にぶつかった場合にバウンド
        if (y < 0 || y > MainPanel.getHEIGHT() - SIZE) {
            vy = -vy;
        }
    }
    /**
     * X方向のバウンド
     */
    public void boundX() {
        vx = -vx;
    }

    /**
     * Y方向のバウンド
     */
    public void boundY() {
        vy = -vy;
    }

    /**
     * ななめにバウンド
     */
    public void boundXY() {
        vx = -vx;
        vy = -vy;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
    public int getVX(){
        return vx;
    }
    public int getVY(){
        return vy;
    }
    public int getSize(){
        return SIZE;
    }



}