package com.lp2.atari.donkeykong;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class AnimatedObject extends Objeto{

    public AnimatedObject(String imgPath, float posX, float posY, float sizeX, float sizeY) {
        super(imgPath, posX, posY, sizeX, sizeY);
    }

    public int startAnimation(Stage stage, int times, String img1Path, String img2Path){

        for(Actor actor : stage.getActors()){
            if(actor.getX() == this.getPosX() && actor.getY() == this.getPosY()){
                actor.remove();
            }
        }

        if(times <= 60){
            this.setImg(new Image(new Texture(img1Path)));
            this.getImg().setPosition(this.getPosX(), this.getPosY());
            this.getImg().setSize(this.getSizeX(), this.getSizeY());
            stage.addActor(this.getImg());

            return times;
        }else if(times < 120){
            this.setImg(new Image(new Texture(img2Path)));
            this.getImg().setPosition(this.getPosX(), this.getPosY());
            this.getImg().setSize(this.getSizeX(), this.getSizeY());
            stage.addActor(this.getImg());

            return times;
        }else if(times == 120){
            this.setImg(new Image(new Texture(img2Path)));
            this.getImg().setPosition(this.getPosX(), this.getPosY());
            this.getImg().setSize(this.getSizeX(), this.getSizeY());
            stage.addActor(this.getImg());

            return 0;
        }

        return -1;
    }
}
