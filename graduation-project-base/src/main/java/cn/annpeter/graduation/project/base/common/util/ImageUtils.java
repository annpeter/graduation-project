package cn.annpeter.graduation.project.base.common.util;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

/**
 * Created on 2017/03/30
 *
 * @author annpeter.it@gmail.com
 */
public class ImageUtils {


    /**
     * 先按最小宽高为size等比例绽放, 然后图像居中抠出半径为radius的圆形图像
     *
     * @param size   要缩放到的尺寸
     * @param radius 圆角半径
     * @param type   0:高度与宽度的最大值为maxSize进行等比缩放 1:高度与宽度的最小值为maxSize进行等比缩放
     */
    public static BufferedImage getRoundedImage(BufferedImage img, int size, int radius, int type) {

        BufferedImage result = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = result.createGraphics();

        // 先按最小宽高为size等比例绽放, 然后图像居中抠出直径为size的圆形图像
        Image fixedImg = getScaledImage(img, size, type);

        // 在适当的位置画出
        graphics.drawImage(fixedImg, (size - fixedImg.getWidth(null)) / 2, (size - fixedImg.getHeight(null)) / 2, null);

        // 圆角
        if (radius > 0) {
            RoundRectangle2D round = new RoundRectangle2D.Double(0, 0, size, size, radius * 2, radius * 2);
            Area clear = new Area(new Rectangle(0, 0, size, size));
            clear.subtract(new Area(round));
            graphics.setComposite(AlphaComposite.Clear);

            // 抗锯齿
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.fill(clear);
            graphics.dispose();
        }
        return result;
    }


    /**
     * 将Img进行缩放, 最大为maxSize大小
     *
     * @param maxSize 最大宽度
     * @param type    0:高度与宽度的最大值为maxSize进行等比缩放 1:高度与宽度的最小值为maxSize进行等比缩放
     */
    public static Image getScaledImage(BufferedImage img, int maxSize, int type) {
        int inWidth = img.getWidth();
        int inHeight = img.getHeight();
        int outWidth = inWidth;
        int outHeight = inHeight;
        if (type == 0) {
            outWidth = inWidth > inHeight ? maxSize : (maxSize * inWidth / inHeight);
            outHeight = inWidth > inHeight ? (maxSize * inHeight / inWidth) : maxSize;
        } else if (type == 1) {
            outWidth = inWidth > inHeight ? (maxSize * inWidth / inHeight) : maxSize;
            outHeight = inWidth > inHeight ? maxSize : (maxSize * inHeight / inWidth);
        }
        Image image = img.getScaledInstance(outWidth, outHeight, Image.SCALE_SMOOTH);
        BufferedImage result = new BufferedImage(outWidth, outHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = result.createGraphics();
        g.drawImage(image, 0, 0, null); // 在适当的位置画出
        return result;
    }

}
