package jp.ac.uryukyu.ie.e165715;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by e165715 on 2017/02/07.
 */
public class MainPanelTest {
    public Racket racket;// ラケット
    private Ball ball;//ボール
    @Test
    public void run() throws Exception {
        ball = new Ball();
        racket = new Racket();
        int collidePos = racket.collideWith(ball);
        collidePos = Racket.LEFT;    }

}