package com.lp2.atari.donkeykong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;

import java.util.concurrent.TimeUnit;

public class Game extends ApplicationAdapter {
    SpriteBatch batch;
    Stage stage;
    Objeto plataforma;
    Objeto escada;
    Objeto escada4;
    Objeto macaco;
    int spriteDonkeyKong;
    Movel mario;
    Timer timer;

    @Override
    public void create() {
        timer = new Timer();

        spriteDonkeyKong = 1;

        batch = new SpriteBatch();

        stage = new Stage();

        //Dados da plataforma 1
        plataforma = new Objeto("plataforma_1_buraco.png", 40, 385, 222.218f, 40);
        stage.addActor(plataforma.getImg());

        //Dados da plataforma 2
        plataforma = new Objeto("plataforma_inteira.png", 40, 290, 550, 35);
        stage.addActor(plataforma.getImg());

        //Dados da plataforma 3
        plataforma = new Objeto("plataforma_2_buracos_1.png", 40, 195, 55, 40);
        stage.addActor(plataforma.getImg());

        //Dados da plataforma 4
        plataforma = new Objeto("plataforma_2_buracos_2.png", 165, 195, 285, 40);
        stage.addActor(plataforma.getImg());

        //Dados da plataforma 5
        plataforma = new Objeto("plataforma_2_buracos_3.png", 515, 195, 75, 40);
        stage.addActor(plataforma.getImg());

        //Dados da plataforma 6
        plataforma = new Objeto("plataforma_1_buraco.png", 40, 100, 238, 40);
        stage.addActor(plataforma.getImg());

        //Dados da plataforma 7
        plataforma = new Objeto("plataforma_1_buraco.png", 345, 100, 238, 40);
        stage.addActor(plataforma.getImg());

        //Dados da plataforma 8
        plataforma = new Objeto("plataforma_inteira.png", 40, 5, 550, 35);
        stage.addActor(plataforma.getImg());

        //Escada 1
        escada = new Objeto("escada.png", 220, 325, 40, 63);
        stage.addActor(escada.getImg());

        //Escada 2
        escada = new Objeto("escada.png", 545, 230, 40, 63);
        stage.addActor(escada.getImg());

        //Escada 3
        escada = new Objeto("escada.png", 205, 135, 40, 63);
        stage.addActor(escada.getImg());

        //Escada 4
        escada = new Objeto("escada.png", 500, 40, 40, 63);
        stage.addActor(escada.getImg());

        //Donkey Kong
        macaco = new Objeto("donkey_kong_1.png", 60, 420, 50, 50);
        stage.addActor(macaco.getImg());

        //Mario
        mario = new Movel("mario_2_1.png", 60, 40, 30, 26, 0, 0);
        stage.addActor(mario.getImg());
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        //o Mário anda pra direita
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {

            for (Actor actor : stage.getActors()) {
                //Se é o Mario
                if (actor.getX() == mario.getPosX() && actor.getY() == mario.getPosY()) {
                    actor.remove();
                }
            }
			andarDireita();
			stage.addActor(mario.getImg());

        }
        //O Mario anda pra esquerda
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {

            for (Actor actor : stage.getActors()) {
                //Se é o Mario
                if (actor.getX() == mario.getPosX() && actor.getY() == mario.getPosY()) {
                    actor.remove();
                }
            }
            andarEsquerda();
			stage.addActor(mario.getImg());
        }
        //o mario sobe as escadas
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {


            for (Actor actor : stage.getActors()) {
                //Se é o Mario
                if (actor.getX() == mario.getPosX() && actor.getY() == mario.getPosY()) {
                    actor.remove();
                }
            }

            subiuEscada();
			stage.addActor(mario.getImg());
        }

        //o mario desce as escadas
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            System.out.println(mario.getPosY());
            for (Actor actor : stage.getActors()) {
                //Se é o Mario
                if (actor.getX() == mario.getPosX() && actor.getY() == mario.getPosY()) {
                    actor.remove();
                }
            }
            desceuEscada();
			stage.addActor(mario.getImg());
        }

        for (Actor actor : stage.getActors()) {
            //Se o actor é o Donkey Kong
            if (actor.getX() == macaco.getPosX() && actor.getY() == macaco.getPosY()) {
                actor.remove();
            }
        }

        /**if(spriteDonkeyKong > 2){
         spriteDonkeyKong = 1;
         }**/

        /**Delay em milisegundos
         try {
         Thread.sleep(666);
         }
         catch(InterruptedException ex) {
         Thread.currentThread().interrupt();
         }**/

        if (spriteDonkeyKong <= 60) {
            //Donkey Kong
            macaco = new Objeto("donkey_kong_" + 1 + ".png", 60, 420, 50, 50);
            stage.addActor(macaco.getImg());
            spriteDonkeyKong++;
        } else if (spriteDonkeyKong <= 120) {
            //Donkey Kong
            macaco = new Objeto("donkey_kong_" + 2 + ".png", 60, 420, 50, 50);
            stage.addActor(macaco.getImg());
            spriteDonkeyKong++;
        }

        if (spriteDonkeyKong == 120) {
            spriteDonkeyKong = 1;
        }

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        batch.end();
    }

    public void desceuEscada() {
        if (mario.getPosY() > 40 && mario.getPosY() <= 136) {
            if (mario.getPosX() >= 500 && mario.getPosX() <= 540) {
                mario.setPosY(mario.getPosY() - 1);
                mario = new Movel("mario_3_3.png", mario.getPosX(), (mario.getPosY() - 2), 32, 33, 0, 0);
            }
        } else if (mario.getPosY() >= 136 && mario.getPosY() <= 232) {
            if (mario.getPosX() >= 205 && mario.getPosX() <= 245) {
                mario.setPosY(mario.getPosY() - 1);
                mario = new Movel("mario_3_3.png", mario.getPosX(), (mario.getPosY() - 2), 32, 33, 0, 0);
            }
        } else if (mario.getPosY() >= 232 && mario.getPosY() <= 325) {
            if (mario.getPosX() >= 545 && mario.getPosX() <= 585) {
                mario.setPosY(mario.getPosY() - 1);
                mario = new Movel("mario_3_3.png", mario.getPosX(), (mario.getPosY() - 2), 32, 33, 0, 0);
            }
        } else if (mario.getPosY() >= 325 && mario.getPosY() <= 415) {
            if (mario.getPosX() >= 220 && mario.getPosX() <= 260) {
                mario.setPosY(mario.getPosY() - 1);
                mario = new Movel("mario_3_3.png", mario.getPosX(), (mario.getPosY() - 2), 32, 33, 0, 0);
            }
        } else if (mario.getPosY() >= 418) {
            if (mario.getPosX() >= 220 && mario.getPosX() <= 260) {
				mario.setPosY(mario.getPosY() - 1);
				mario = new Movel("mario_3_3.png", mario.getPosX(), (mario.getPosY() - 2), 32, 33, 0, 0);
			}
        }
    }

    public void subiuEscada() {
        if (mario.getPosY() < 135) {
            if (mario.getPosX() >= 500 && mario.getPosX() <= 540) {
                mario.setPosY(mario.getPosY() + 1);
                mario = new Movel("mario_3_3.png", mario.getPosX(), (mario.getPosY() + 2), 32, 33, 0, 0);
            }
        } else if (mario.getPosY() >= 135 && mario.getPosY() < 230) {
            if (mario.getPosX() >= 205 && mario.getPosX() <= 245) {
                mario.setPosY(mario.getPosY() + 1);
                mario = new Movel("mario_3_3.png", mario.getPosX(), (mario.getPosY() + 2), 32, 33, 0, 0);
            }
        } else if (mario.getPosY() >= 230 && mario.getPosY() < 325) {
            if (mario.getPosX() >= 545 && mario.getPosX() <= 585) {
                mario.setPosY(mario.getPosY() + 1);
                mario = new Movel("mario_3_3.png", mario.getPosX(), (mario.getPosY() + 2), 32, 33, 0, 0);
            }
        } else if (mario.getPosY() >= 325 && mario.getPosY() <= 415) {
            if (mario.getPosX() >= 220 && mario.getPosX() <= 260) {
                mario.setPosY(mario.getPosY() + 1);
                mario = new Movel("mario_3_3.png", mario.getPosX(), (mario.getPosY() + 2), 32, 33, 0, 0);
            }
        }
    }
	public void andarDireita() {
		if (mario.getPosY() == 40) {

			mario.setPosX(mario.getPosX() + 1);
			mario = new Movel("mario_2_1.png", (mario.getPosX() + 2), mario.getPosY(), 30, 26, 0, 0);

		} else if (mario.getPosY() ==136) {

			mario.setPosX(mario.getPosX() + 1);
			mario = new Movel("mario_2_1.png", (mario.getPosX() + 2), mario.getPosY(), 30, 26, 0, 0);

		} else if (mario.getPosY() ==232) {

			mario.setPosX(mario.getPosX() + 1);
			mario = new Movel("mario_2_1.png", (mario.getPosX() + 2), mario.getPosY(), 30, 26, 0, 0);

		} else if (mario.getPosY() ==325) {

			mario.setPosX(mario.getPosX() + 1);
			mario = new Movel("mario_2_1.png", (mario.getPosX() + 2), mario.getPosY(), 30, 26, 0, 0);

		} else if (mario.getPosY() == 418) {

			mario.setPosX(mario.getPosX() + 1);
			mario = new Movel("mario_2_1.png", (mario.getPosX() + 2), mario.getPosY(), 30, 26, 0, 0);
		}
	}
	public void andarEsquerda() {
		if (mario.getPosY() == 40) {

				mario.setPosX(mario.getPosX() - 1);
				mario = new Movel("mario_1_2.png", (mario.getPosX() - 2), mario.getPosY(), 30, 26, 0, 0);
		} else if (mario.getPosY() ==136) {

				mario.setPosX(mario.getPosX() - 1);
				mario = new Movel("mario_1_2.png", (mario.getPosX() - 2), mario.getPosY(), 30, 26, 0, 0);
		} else if (mario.getPosY() ==232) {

				mario.setPosX(mario.getPosX() - 1);
				mario = new Movel("mario_1_2.png", (mario.getPosX() - 2), mario.getPosY(), 30, 26, 0, 0);
		} else if (mario.getPosY() ==325) {

				mario.setPosX(mario.getPosX() - 1);
				mario = new Movel("mario_1_2.png", (mario.getPosX() - 2), mario.getPosY(), 30, 26, 0, 0);

		} else if (mario.getPosY() == 418) {

				mario.setPosX(mario.getPosX() - 1);
				mario = new Movel("mario_1_2.png", (mario.getPosX() - 2), mario.getPosY(), 30, 26, 0, 0);
		}
	}
    @Override
    public void dispose() {
        batch.dispose();
    }
}
