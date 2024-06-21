//VerifyCode.java
package com.shoppingwebapp.Model;

import java.util.Random;

import javax.imageio.ImageIO;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import com.shoppingwebapp.Service.RedisService;

public class VerifyCode {
    // height weight
    private int w = 250;
    private int h = 40;

    private Random random = new Random();
    //字體
    private String[] fonts = {"Helvetica", "微軟雅黑" };
    //隨機字符
    private String codes = "23456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    //背景色
    private Color bgcolor = new Color(0, 255, 255);
    //gettext 獲取驗證碼
    private String text;

    //redis
    private  RedisService redisService;
    
    public VerifyCode(RedisService redisService) {
        this.redisService = redisService;
    }
    
    //random color
    private Color randomColor() {
        int red = random.nextInt(255);
        int green = random.nextInt(255);
        int blue = random.nextInt(255);
        return new Color(red, green, blue);
    }

    //random font
    private Font randomFont() {
        int index = random.nextInt(fonts.length);
        String fontName = fonts[index];
        int style = random.nextInt(4);
        int size = random.nextInt(5) + 30;

        return new Font(fontName, style, size);
    }

    //干擾
    private void drawLine(BufferedImage image) {
        int num = 3;
        Graphics2D g2 = (Graphics2D) image.getGraphics();

        for(int i = 0; i < num ;i++){
            int x1 = random.nextInt(w);
            int y1 = random.nextInt(h);
            int x2 = random.nextInt(w);
            int y2 = random.nextInt(h);
            g2.setStroke(new BasicStroke(1.5F));//??
            g2.setColor(randomColor());
            g2.drawLine(x1, y1, x2, y2);
        }
    }

    //get random number 
    private char randomChar(){
        int index = random.nextInt(codes.length());
        return codes.charAt(index);
    }

    //create pic
    public BufferedImage createImage(){
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        StringBuilder sb = new StringBuilder();
        //draw
        for(int i = 0; i < 4; i++) {
            String s = randomChar() + "";
            sb.append(s);
            float x = i * 1.0F * w / 4; //橫向位置
            g2.setFont(randomFont());
            g2.setColor(randomColor());
            g2.drawString(s, x , h - 10);
        }
        this.text = sb.toString();
        //redis
        redisService.setStr("VCode", this.text);

        drawLine(image);

        return image;
    }

    

    //get text
    public String getText() {
        //redis
        return redisService.getStr("VCode");
        // return this.text;
    }

    //set output
    public static void output(BufferedImage bi, OutputStream fos) throws FileNotFoundException, IOException{
        ImageIO.write(bi, "JPEG", fos);
    }
}

