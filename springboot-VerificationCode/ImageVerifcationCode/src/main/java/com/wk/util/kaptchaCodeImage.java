package com.wk.util;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author: wk
 * @Date: 2020/10/29 16:49
 * @Description
 */
public class kaptchaCodeImage {

    public DefaultKaptcha producer(){
        DefaultKaptcha defaultKaptcha=new DefaultKaptcha();
        Properties properties=new Properties();
        properties.setProperty("kaptcha.border", "no");
        properties.setProperty("kaptcha.border.color", "white");
        properties.setProperty("kaptcha.textproducer.font.color", "255,192,55");
        properties.setProperty("kaptcha.image.width", "125");
        properties.setProperty("kaptcha.image.height", "45");
        properties.setProperty("kaptcha.session.key", "code");
        properties.setProperty("kaptcha.textproducer.font.size", "38");
        properties.setProperty("kaptcha.noise.color","21,113,171");
        properties.setProperty("kaptcha.background.clear.from","0,154,255");
        properties.setProperty("kaptcha.background.clear.to","0,202,255");
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        properties.setProperty("kaptcha.textproducer.font.names", "Arial");
        Config config=new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

    public void ImgCode() throws IOException {
        String path = "C:\\code-workspace\\source\\springbootDemo\\springboot-VerificationCode\\ImageVerifcationCode\\src\\main\\resources\\bit.jpeg";
        final BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(path));

        DefaultKaptcha kaptcha = producer();
        final String text = kaptcha.createText();
        System.out.println("gen code: " + text);
        final BufferedImage image = kaptcha.createImage(text);
        ImageIO.write(image,"jpg", outputStream);
        System.out.println("image complete.");
    }

    public static void main(String[] args) throws IOException {
        final kaptchaCodeImage codeImage = new kaptchaCodeImage();
        codeImage.ImgCode();
    }
}
