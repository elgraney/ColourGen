package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.LookupOp;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
	// write your code ##
        gassy();
    }
    public static void gassy(){
        BufferedImage in = null;
        try {
            in = ImageIO.read(new File("gassy_boi.png"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        BufferedImage skin = in.getSubimage(0, 0 , in.getWidth(), in.getHeight());

        BufferedImage output = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = output.createGraphics();

        g2.setComposite(AlphaComposite.Src);

        g2.setColor(Color.WHITE);
        //height, width, x, y
        g2.fill(new Rectangle2D.Double(0, 0 ,in.getWidth(), in.getHeight()));
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(skin, 0,  0, null);

        g2.dispose();

        int i = 0;
        while (i < 100) {
            Color from = Color.decode("#FFFFFF"); //white
            Color to = new Color((int)(Math.random() * 0x1000000));
            BufferedImageOp lookup = new LookupOp(new ColourMapper(from, to), null);
            BufferedImage convertedImage = lookup.filter(skin, null);

            from = Color.decode("#000000"); //black
            to = new Color((int)(Math.random() * 0x1000000));
            lookup = new LookupOp(new ColourMapper(from, to), null);
            convertedImage = lookup.filter(convertedImage, null);

            from = Color.decode("#7F7F7F"); //grey
            to = new Color((int)(Math.random() * 0x1000000));
            lookup = new LookupOp(new ColourMapper(from, to), null);
            convertedImage = lookup.filter(convertedImage, null);

            from = Color.decode("#FF0000"); //red
            to = new Color((int)(Math.random() * 0x1000000));
            lookup = new LookupOp(new ColourMapper(from, to), null);
            convertedImage = lookup.filter(convertedImage, null);

            from = Color.decode("#00FF00"); //green
            to = new Color((int)(Math.random() * 0x1000000));
            lookup = new LookupOp(new ColourMapper(from, to), null);
            convertedImage = lookup.filter(convertedImage, null);

            from = Color.decode("#0000FF"); //blue
            to = new Color((int)(Math.random() * 0x1000000));
            lookup = new LookupOp(new ColourMapper(from, to), null);
            convertedImage = lookup.filter(convertedImage, null);


            try {
                ImageIO.write(convertedImage, "png", new File("gassy_"+i+".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            i++;
        }

    }
}
