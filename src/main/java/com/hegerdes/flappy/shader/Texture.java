package com.hegerdes.flappy.shader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;

public class Texture {

    private int width, height;
    private int texture;

    public Texture(String path){
        texture = load(path);
    }

    private int load(String path){
        int[] pixels = null;

        try {
            BufferedImage img = ImageIO.read(new FileInputStream(path));
            width = img.getWidth();
            height = img.getHeight();

            pixels = new int[width * height];
            img.getRGB(0,0, width, height, pixels,0,width);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[] data = new int[width * height];
        for(int i = 0;i < width * height; i++){
            int a = (pixels[i]) & 0xff000000 >> 24;
            int r = (pixels[i]) & 0xff0000 >> 16;
            int g = (pixels[i]) & 0xff00 >> 8;
            int b = (pixels[i]) & 0xff;

            data[i] = a << 24 | b << 16 | g << 8 | r;
        }

        int res = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, res);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glBindTexture(GL_TEXTURE_2D, 0);


        return 1;
    }
}
