package com.lp2.atari.donkeykong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;

public class Game extends ApplicationAdapter {
    SpriteBatch batch;
    Stage stage;
    Objeto plataforma;
    Objeto escada;
    Objeto escada4;
    Objeto macaco;
    int spriteDonkeyKong, spritePuloMarioDireita, spritePuloMarioY, spritePuloMarioEsquerda;
    int quedaMarioDireita, quedaMarioEsquerda, quedaMarioDiagonalEsquerda, quedaMarioDiagonalDireita;
    Movel mario;
    Timer timer;
    int pulou;
    boolean entrou1,entrou2, entrou3;
    boolean caiuDireita, caiuEsquerda, caiuDiagonalEsquerda, caiuDiagonalDireita;

    @Override
    public void create() {
        quedaMarioEsquerda = 0;
        timer = new Timer();
        caiuDireita = false;
        quedaMarioDireita = 0;
        caiuEsquerda = false;
        spriteDonkeyKong = 1;
        spritePuloMarioDireita = 0;
        spritePuloMarioEsquerda = 0;
        spritePuloMarioY = 0;
        pulou = 0;
        entrou1 = false;
        entrou2 = false;
        entrou3 = false;

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

        System.out.println("X:"+mario.getPosX());
        System.out.println("Y:"+mario.getPosY());

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        //o Mário anda pra direita
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && mario.canMove("direita")) {

            if (mario.getPosY() == 136 && mario.getPosX() >= 273 && mario.getPosX() <= 324) {
                quedaMarioDireita = 1;
                caiuDireita = true;
            }

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
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && mario.canMove("esquerda")) {

            if (mario.getPosY() == 136 && mario.getPosX() >= 273 && mario.getPosX() <= 324) {
                quedaMarioEsquerda = 1;
                caiuEsquerda = true;
            }

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
            for (Actor actor : stage.getActors()) {
                //Se é o Mario
                if (actor.getX() == mario.getPosX() && actor.getY() == mario.getPosY()) {
                    actor.remove();
                }
            }
            desceuEscada();
			stage.addActor(mario.getImg());
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && !entrou1){
           spritePuloMarioY = 0;
           entrou1 = true;
        }

        if(entrou1 && spritePuloMarioY > -1){
            for (Actor actor : stage.getActors()) {
                //Se é o Mario
                if (actor.getX() == mario.getPosX() && actor.getY() == mario.getPosY()) {
                    actor.remove();
                }
            }
            spritePuloMarioY++;
            spritePuloMarioY = mario.pularY(stage, spritePuloMarioY);
        }else if(spritePuloMarioY == -1){
            spritePuloMarioY = 0;
            entrou1 = false;
        }

        //DiagonalDireita
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !entrou2){
            spritePuloMarioDireita = 1;
            entrou2 = true;
        }

        if(entrou2 && spritePuloMarioDireita > -1){
            System.out.println("_____________1:"+ spritePuloMarioDireita +"_____________");
            for (Actor actor : stage.getActors()) {
                //Se é o Mario
                if (actor.getX() == mario.getPosX() && actor.getY() == mario.getPosY()) {
                    actor.remove();
                }
            }
            if(spritePuloMarioDireita <=50){
                spritePuloMarioDireita++;
                spritePuloMarioDireita = mario.pularDiagonalDireita(stage, spritePuloMarioDireita);
            }

        }
        else if(spritePuloMarioDireita == -1){

            if (mario.getPosX() >= 273 && mario.getPosX() <= 324) {
                quedaMarioDiagonalEsquerda = 1;
                caiuDiagonalEsquerda = true;
            }

            spritePuloMarioDireita = 1;
            entrou2 = false;
        }

        //DiagonalEsquerda
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && Gdx.input.isKeyPressed(Input.Keys.LEFT) && !entrou3){

            spritePuloMarioEsquerda = 1;
            entrou3 = true;
        }

        if(entrou3 && spritePuloMarioEsquerda> -1){
            for (Actor actor : stage.getActors()) {
                //Se é o Mario
                if (actor.getX() == mario.getPosX() && actor.getY() == mario.getPosY()) {
                    actor.remove();
                }
            }
            spritePuloMarioEsquerda++;
            spritePuloMarioEsquerda = mario.pularDiagonalEsquerda(stage, spritePuloMarioEsquerda);
        }else if(spritePuloMarioEsquerda == -1){
            if (mario.getPosX() >= 273 && mario.getPosX() <= 324) {
                quedaMarioDiagonalEsquerda = 1;
                caiuDiagonalEsquerda = true;
            }

            spritePuloMarioEsquerda =1;
            entrou3 = false;
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

        if(caiuDireita && quedaMarioDireita > -1){
            quedaMarioDireita++;
            quedaMarioDireita = mario.cai(40, stage, quedaMarioDireita);
        }else if(quedaMarioDireita == -1){
            quedaMarioDireita = 1;
            caiuDireita = false;
        }

        if(caiuEsquerda && quedaMarioEsquerda > -1){
            quedaMarioEsquerda++;
            quedaMarioEsquerda = mario.cai(40, stage, quedaMarioEsquerda);
        }else if(quedaMarioEsquerda == -1){
            quedaMarioEsquerda = 1;
            caiuEsquerda = false;
        }

        if(caiuDiagonalEsquerda && quedaMarioDiagonalEsquerda > -1){
            quedaMarioDiagonalEsquerda++;
            quedaMarioDiagonalEsquerda = mario.cai(40, stage, quedaMarioDiagonalEsquerda);
        }else if(quedaMarioDiagonalEsquerda == -1){
            quedaMarioDiagonalEsquerda = 1;
            caiuDiagonalEsquerda = false;
        }

        if(caiuDiagonalDireita && quedaMarioDiagonalDireita > -1){
            quedaMarioDiagonalDireita++;
            quedaMarioDiagonalDireita = mario.cai(40, stage, quedaMarioDiagonalDireita);
        }else if(quedaMarioDiagonalDireita == -1){
            quedaMarioDiagonalDireita = 1;
            caiuDiagonalDireita = false;
            System.out.println("godgod");
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
