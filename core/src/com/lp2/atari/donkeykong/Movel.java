package com.lp2.atari.donkeykong;

public class Movel extends Objeto{

    private float velX, velY;

    public Movel(String imgPath, float posX, float posY, float sizeX, float sizeY, float velX, float velY) {
        super(imgPath, posX, posY, sizeX, sizeY);
        this.velX = velX;
        this.velY = velY;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    @Override
    public String toString() {
        return "Movel{" +
                "posX=" + posX +
                '}';
    }
}
