package com.wk.util;

import org.omg.CORBA.PRIVATE_MEMBER;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * @author: wk
 * @Date: 2020/10/29 10:05
 * @Description
 */
public class CodeUtil {
    // 放到session中的key
    private static final String RANDOMCODEKEY = "RANDOMVALIDATECODEKEY";
    // 随机产生只有数字的字符串
    private String randString = "0123456789";
    //private String randString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    //private String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // 随机生成的验证码
    private String verCode = "";
    // 图片宽
    private int width = 95;
    // 图片高
    private int height = 25;
    // 干扰线数量
    private int lineNum = 40;
    // 随机产生的字符数量
    private int strNum = 4;
    private Random random = new Random();
    // 获得字体
    private Font getFont(){
        return new Font("Fixedsys", Font.CENTER_BASELINE, 18);
    }

    // 获得颜色
    private Color getRandColor(int fc, int bc){
        if (fc > 255){
            fc = 255;
        }
        if (bc > 255){
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r,g,b);
    }

    // 生成随机图片
    public void getRandCode(HttpServletRequest request, HttpServletResponse response){
        //final HttpSession session = request.getSession();
        // BufferedImage类时具有缓冲区的Image类,Image类时用于描述图像信息的类
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 产生Image对象的Graphics对象.改对象可以在图像上进行各种绘制操作
        final Graphics gs = image.getGraphics();
        // 图片大小
        gs.fillRect(0,0,width,height);
        // 字体大小
        gs.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE,18));
        // 字体颜色
        gs.setColor(getRandColor(10,113));

        // 绘制干扰线
        for (int i=0; i<=lineNum; i++){
            drowLine(gs);
        }

        // 绘制随机字符
        for (int i=0;i<=strNum; i++){
            verCode = drowString(gs, verCode,i);
        }
        System.out.println("random value: " + verCode);
        // 将随机字符串保存到session中
        // 正常情况下 这里应该保存到redis或者其他的介质中,放到session中,不能正常防止机器人
        //session.removeAttribute(RANDOMCODEKEY);
        //session.setAttribute(RANDOMCODEKEY, randomString);
        gs.dispose();
        try {
            ImageIO.write(image,"JPEG", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 生成随机图片
    public void getRandCode(OutputStream outputStream){
        //final HttpSession session = request.getSession();
        // BufferedImage类时具有缓冲区的Image类,Image类时用于描述图像信息的类
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 产生Image对象的Graphics对象.改对象可以在图像上进行各种绘制操作
        final Graphics gs = image.getGraphics();
        // 图片大小
        gs.fillRect(0,0,width,height);
        // 字体大小
        gs.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE,18));
        // 字体颜色
        gs.setColor(getRandColor(20,113));

        // 绘制干扰线
        for (int i=0; i<=lineNum; i++){
            drowLine(gs);
        }

        // 绘制随机字符
        for (int i=0;i<=strNum; i++){
            verCode = drowString(gs, verCode,i);
        }
        System.out.println("random value: " + verCode);
        // 将随机字符串保存到session中
        // 正常情况下 这里应该保存到redis或者其他的介质中,放到session中,不能正常防止机器人
        //session.removeAttribute(RANDOMCODEKEY);
        //session.setAttribute(RANDOMCODEKEY, randomString);
        gs.dispose();
        try {
            ImageIO.write(image,"JPEG", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 绘制字符串
    private String drowString(Graphics gs, String randomString, int i){
        gs.setFont(getFont());
        gs.setColor(new Color(random.nextInt(101),random.nextInt(111),
                random.nextInt(121)));
        final String randS = String.valueOf(getRandomString(random.nextInt(this.randString.length())));
        randomString += randS;
        gs.translate(random.nextInt(3), random.nextInt(3));
        gs.drawString(randS, 13*i,16);
        return randomString;
    }

    // 绘制干扰线
    private void drowLine(Graphics gs){
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int x1 = random.nextInt(13);
        int y1 = random.nextInt(15);
        gs.drawLine(x,y,x+x1,y+y1);
    }
    // 获取随机字符
    public String getRandomString(int num){
        return String.valueOf(randString.charAt(num));
    }
}
