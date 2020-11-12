package com.study.photo;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;


public class ImageTransparent {

    public static void main(String[] args) {
        img2BackGroundTransparent("/Users/panzz/Desktop/ic_download.png");
    }

    public static void img2BackGroundTransparent(String path) {
        // TODO Auto-generated constructor stub
        try {
            //判断是否为透明图片
            if(checkImgTransparent(path)){
                return;
            }
            BufferedImage image = ImageIO.read(new File(path));
            ImageIcon imageIcon = new ImageIcon(image);
            BufferedImage bufferedImage = new BufferedImage(
                    imageIcon.getIconWidth(), imageIcon.getIconHeight(),
                    BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D g2D = (Graphics2D) bufferedImage.getGraphics();
            g2D.drawImage(imageIcon.getImage(), 0, 0,
                    imageIcon.getImageObserver());
            int alpha = 0;
            for (int j1 = bufferedImage.getMinY(); j1 < bufferedImage
                    .getHeight(); j1++) {
                for (int j2 = bufferedImage.getMinX(); j2 < bufferedImage
                        .getWidth(); j2++) {
                    int rgb = bufferedImage.getRGB(j2, j1);
                    alpha = 0;
                    rgb = (alpha << 24) | (rgb & 0x00ffffff);
                    bufferedImage.setRGB(j2, j1, rgb);
                }
            }
            g2D.drawImage(bufferedImage, 0, 0, imageIcon.getImageObserver());
            // 生成图片为PNG
            //如果jpg格式，则透明处理后变为黑。因jpg为不支持透明。所以此处格式强制为png
            ImageIO.write(bufferedImage, "png", new File(path));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*private static boolean colorInRange(int color) {
        int red = (color & 0xff0000) >> 16;
        int green = (color & 0x00ff00) >> 8;
        int blue = (color & 0x0000ff);
        if (red >= color_range && green >= color_range && blue >= color_range){
            return true;
        }
        return false;
    }
    private static int color_range = 210;
    private static Pattern pattern = Pattern.compile("[0-9]*");
    private static boolean isNo(String str) {
        return pattern.matcher(str).matches();
    }*/

    private static boolean checkImgTransparent(String path) throws IOException{
        File file = new File(path);
        BufferedImage bi = (BufferedImage) ImageIO.read(file);
        // 获取图像的宽度和高度
        int width = bi.getWidth();
        int height = bi.getHeight();
        boolean isTransparent = false;
        // 扫描图片
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {// 行扫描
                int dip = bi.getRGB(j, i);
                if (dip >>24 == 0){
                    isTransparent = true;
                    break;

                }
            }
        }
        return isTransparent;
    }


}
