package com.lp2.atari.donkeykong;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Movel extends Objeto{

    private float velX, velY;

    public Movel(String imgPath, float posX, float posY, float sizeX, float sizeY) {
        super(imgPath, posX, posY, sizeX, sizeY);
    }

    public int pularDiagonalEsquerda(Stage stage, int contador){
        for (Actor actor : stage.getActors()) {
            //Se o actor for o Mario
            if (actor.getX() == this.getPosX() && actor.getY() == this.getPosY()) {
                actor.remove();
            }
        }
        if(contador < 24){

            this.setPosY(this.getPosY()+1);
            this.setPosX(this.getPosX() - 2);
            this.setImg(new Image(new Texture(this.getImgPath())));
            this.getImg().setPosition(this.getPosX(), this.getPosY());
            this.getImg().setSize(this.getSizeX(), this.getSizeY());
            stage.addActor(this.getImg());
        }
        else if(contador >=24 && contador < 50){

            this.setPosY(this.getPosY() - 1);
            this.setPosX(this.getPosX() - 2);
            this.setImg(new Image(new Texture(this.getImgPath())));
            this.getImg().setPosition(this.getPosX(), this.getPosY());
            this.getImg().setSize(this.getSizeX(), this.getSizeY());
            stage.addActor(this.getImg());
        }
        else if(contador == 50){
            contador = -1;
            this.setPosY(this.getPosY()+4);
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
                this.setPosY(this.getPosY() + 1);
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
            this.setPosY(this.getPosY()+4);
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
        else if(contador >= 31 && contador < 61){

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

    public int cai(float chao, Stage stage, int cont) {

        if (this.getPosY() > chao) {

            for (Actor actor : stage.getActors()) {
                //Se o actor for o Mario
                if (actor.getX() == this.getPosX() && actor.getY() == this.getPosY()) {
                    actor.remove();
                }
            }

            this.setPosY(this.getPosY() - 3);
            this.setImg(new Image(new Texture(this.getImgPath())));
            this.getImg().setPosition(this.getPosX(), this.getPosY());
            this.getImg().setSize(this.getSizeX(), this.getSizeY());
            stage.addActor(this.getImg());
            return cont;

        } else if (this.getPosY() == chao) {
            return -1;
        }
        return -1;
    }

    public boolean canMove(String orientacao){
        if(orientacao.equals("esquerda")){
            if (this.posX > 39) {
                return true;
            }
        }else if(orientacao.equals("direita")){
            if(this.posX < 561){
                return true;
            }
        }
        return false;
    }

    public int andaSom(int cont, Sound som){
        if (cont == 10) {
            som.play();
        }
        else if(cont >20){
            som.pause();
            cont = 0;
        }
        return cont;
    }
    @Override
    public String toString() {
        return "Movel{" +
                "posX=" + posX +
                '}';
    }



}
