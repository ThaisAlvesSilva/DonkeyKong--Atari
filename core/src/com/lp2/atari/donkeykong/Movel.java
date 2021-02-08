package com.lp2.atari.donkeykong;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Movel extends Objeto{

    private float velX, velY;

    public Movel(String imgPath, float posX, float posY, float sizeX, float sizeY, float velX, float velY) {
        super(imgPath, posX, posY, sizeX, sizeY);
        this.velX = velX;
        this.velY = velY;
    }
    public int pularDiagonalEsquerda(Stage stage, int contador){
        for (Actor actor : stage.getActors()) {
            //Se o actor for o Mario
            if (actor.getX() == this.getPosX() && actor.getY() == this.getPosY()) {
                actor.remove();
            }
        }

        if(contador < 31){

            this.setPosY(this.getPosY() + 1);
            this.setPosX(this.getPosX() -2);
            this.setImg(new Image(new Texture(this.getImgPath())));
            this.getImg().setPosition(this.getPosX(), this.getPosY());
            this.getImg().setSize(this.getSizeX(), this.getSizeY());
            stage.addActor(this.getImg());
        }
        else if(contador < 61){

            this.setPosY(this.getPosY() - 1);
            this.setPosX(this.getPosX() -2);
            this.setImg(new Image(new Texture(this.getImgPath())));
            this.getImg().setPosition(this.getPosX(), this.getPosY());
            this.getImg().setSize(this.getSizeX(), this.getSizeY());
            stage.addActor(this.getImg());
        }
        else if(contador == 61){

            contador = -1;
            this.setImg(new Image(new Texture(this.getImgPath())));
            this.getImg().setPosition(this.getPosX(), this.getPosY());
            this.getImg().setSize(this.getSizeX(), this.getSizeY());
            stage.addActor(this.getImg());
        }

        return contador;
    }
    public int pularDiagonalDireita(Stage stage, int contador){
        for (Actor actor : stage.getActors()) {
            //Se o actor for o Mario
            if (actor.getX() == this.getPosX() && actor.getY() == this.getPosY()) {
                actor.remove();
            }
        }

        if(contador < 24){

            this.setPosY(this.getPosY()+1);
            this.setPosX(this.getPosX() + 2);
            this.setImg(new Image(new Texture(this.getImgPath())));
            this.getImg().setPosition(this.getPosX(), this.getPosY());
            this.getImg().setSize(this.getSizeX(), this.getSizeY());
            stage.addActor(this.getImg());
        }
        else if(contador >=24 && contador < 50){

            this.setPosY(this.getPosY() - 1);
            this.setPosX(this.getPosX() + 2);
            this.setImg(new Image(new Texture(this.getImgPath())));
            this.getImg().setPosition(this.getPosX(), this.getPosY());
            this.getImg().setSize(this.getSizeX(), this.getSizeY());
            stage.addActor(this.getImg());
        }
        else if(contador == 50){

            contador = -1;
            this.setImg(new Image(new Texture(this.getImgPath())));
            this.getImg().setPosition(this.getPosX(), this.getPosY());
            this.getImg().setSize(this.getSizeX(), this.getSizeY());
            stage.addActor(this.getImg());
        }

        return contador;
    }
    public int pularY(Stage stage, int contador){
        for (Actor actor : stage.getActors()) {
            //Se o actor for o Mario
            if (actor.getX() == this.getPosX() && actor.getY() == this.getPosY()) {
                actor.remove();
            }
        }

        if(contador < 31){

            this.setPosY(this.getPosY() + 1);
            this.setImg(new Image(new Texture(this.getImgPath())));
            this.getImg().setPosition(this.getPosX(), this.getPosY());
            this.getImg().setSize(this.getSizeX(), this.getSizeY());
            stage.addActor(this.getImg());
        }
        else if(contador < 61){

            this.setPosY(this.getPosY() - 1);
            this.setImg(new Image(new Texture(this.getImgPath())));
            this.getImg().setPosition(this.getPosX(), this.getPosY());
            this.getImg().setSize(this.getSizeX(), this.getSizeY());
            stage.addActor(this.getImg());
        }
        else if(contador == 61){

            contador = -1;
            this.setImg(new Image(new Texture(this.getImgPath())));
            this.getImg().setPosition(this.getPosX(), this.getPosY());
            this.getImg().setSize(this.getSizeX(), this.getSizeY());
            stage.addActor(this.getImg());
        }

        return contador;
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
