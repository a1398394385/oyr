package oyw.gp.oyr.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomUtils;

/**
 * 生成图形验证码
 * 
 * @author OuYangWei
 * @since 2020-5-1
 */
public class Captcha {
    /**
     * 单例模式实例
     * 
     * @see SerializeData
     */
    private static SerializeData captcha = new SerializeData();

    /**
     * 用于生成随机参数
     */
    private static Random random = new Random();

    /**
     * 随机生成字符串的取值范围
     */
    private static String randString = "0123456789abcdefghijkmnpqrtyABCDEFGHIJLMNQRTY";

    /**
     * 图片宽度
     */
    private static int width = 110;

    /**
     * 图片高度
     */
    private static int height = 40;

    /**
     * 字符的数量
     */
    private static int StringNum = 4;

    /**
     * 干扰线数量
     */
    private static int lineSize = 35;

    private Captcha() {
    }

    /**
     * 获取随机字符,并返回字符的String格式
     * 
     * @param index 指定位置
     * @return
     */
    private static String getRandomChar(int index) {
        // 获取指定位置index的字符，并转换成字符串表示形式
        return String.valueOf(randString.charAt(index));
    }

    /**
     * 获取随机指定区间的随机数
     * 
     * @param min 最小值
     * @param max 最大值
     * @return
     */
    private static int getRandomNum(int min, int max) {
        return RandomUtils.nextInt(min, max);
    }

    /**
     * 获得字体
     * 
     * @return
     */
    private static Font getFont() {
        return new Font("Fixedsys", Font.CENTER_BASELINE, 25); // 名称、样式、磅值
    }

    /**
     * 获得颜色
     * 
     * @param frontColor
     * @param backColor
     * @return Color
     */
    private static Color getRandColor(int frontColor, int backColor) {
        if (frontColor > 255)
            frontColor = 255;
        if (backColor > 255)
            backColor = 255;

        int red = frontColor + random.nextInt(backColor - frontColor - 16);
        int green = frontColor + random.nextInt(backColor - frontColor - 14);
        int blue = frontColor + random.nextInt(backColor - frontColor - 18);
        return new Color(red, green, blue);
    }

    /**
     * 绘制字符串,返回绘制的字符串
     * 
     * @param g
     * @param randomString
     * @param i
     * @return String
     */
    private static String drawString(Graphics g, String randomString, int i) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(getFont());
        g2d.setColor(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat()));
        String randChar = String.valueOf(getRandomChar(random.nextInt(randString.length())));
        randomString += randChar; // 组装
        int rot = getRandomNum(5, 10);
        g2d.translate(random.nextInt(3), random.nextInt(3));
        g2d.rotate(rot * Math.PI / 180);
        g2d.drawString(randChar, 13 * i, 20);
        g2d.rotate(-rot * Math.PI / 180);
        return randomString;
    }

    /**
     * 绘制干扰线
     * 
     * @param g
     */
    private static void drawLine(Graphics g) {
        // 起点(x,y) 偏移量x1、y1
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.setColor(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat()));
        g.drawLine(x, y, x + xl, y + yl);
    }

    /**
     * 生成图片验证码
     */
    private static void generateCaptcha() {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);

        // 填充矩形; 设置字体、颜色
        Graphics g = image.getGraphics();
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
        g.setColor(getRandColor(110, 133));
        // 绘制干扰线
        for (int i = 0; i <= lineSize; i++) {
            drawLine(g);
        }
        // 绘制字符
        String randomString = "";
        for (int i = 1; i <= StringNum; i++) {
            randomString = drawString(g, randomString, i);
            captcha.setValue(randomString);
        }
        // 释放绘图资源
        g.dispose();

        // 使用输出流将图片转换为Base64编码
        ByteArrayOutputStream bs = null;
        try {
            bs = new ByteArrayOutputStream();
            // 将绘制得图片输出到流
            ImageIO.write(image, "png", bs);
            String imgSrc = new String(Base64.encodeBase64(bs.toByteArray()));
            captcha.setBase64Str(imgSrc);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bs.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                bs = null;
            }
        }
    }

    /**
     * 获取验证码 Base64 值
     */
    public static String getBase64Str() {
        return captcha.getBase64Str();
    }

    /**
     * 获取验证码 Base64 值
     * 
     * @param refresh 是否刷新当前图形验证码
     */
    public static String getBase64Str(Boolean refresh) {
        if (refresh)
            generateCaptcha();
        return captcha.getBase64Str();
    }

    /**
     * 获取验证码内容值
     */
    public static String getValue() {
        return captcha.getValue();
    }

    /**
     * 获取验证码内容值
     * 
     * * @param refresh 是否刷新当前图形验证码
     */
    public static String getValue(Boolean refresh) {
        if (refresh)
            generateCaptcha();
        return captcha.getValue();
    }

    /**
     * 用于序列化验证码图片 包含Base64值以及验证码内容值
     */
    public static class SerializeData implements Serializable {
        private static final long serialVersionUID = 1L;

        /**
         * Base64 值
         */
        private String Base64Str;

        /**
         * 验证码内容值
         */
        private String value;

        public String getBase64Str() {
            return Base64Str;
        }

        public void setBase64Str(String base64Str) {
            Base64Str = base64Str;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
