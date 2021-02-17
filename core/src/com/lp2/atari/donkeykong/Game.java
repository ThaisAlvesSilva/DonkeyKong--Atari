package com.lp2.atari.donkeykong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;
import org.graalvm.compiler.lir.amd64.AMD64ControlFlow;

import java.util.ArrayList;
import java.util.List;

public class Game extends ApplicationAdapter {
    SpriteBatch batch;
    Stage stage;
    Stage stageBarril;
    Objeto plataforma;
    Objeto escada;
    Objeto macaco;
    Objeto princesa;
    int spriteDonkeyKong, spritePuloMarioDireita, spritePuloMarioY, spritePuloMarioEsquerda, contSom, contSomMorte;
    int queda;
    Movel mario;
    Timer timer;
    int pulou,i;
    boolean cont1,cont2,cont3;
    float teste=0;
    boolean entrou1,entrou2, entrou3, entrouEscada, entrouBuraco, perdeu, testeSom;
    boolean caiu;
    List<Movel> arrayBarril;
    float tamanho1;
    Sound puloSom;
    Sound andar;
    Sound morreuSom;
    public Texture GameOver;


    @Override
    public void create() {

        puloSom = Gdx.audio.newSound(Gdx.files.internal("Pulo.wav"));
        andar = Gdx.audio.newSound(Gdx.files.internal("Andar.mp3"));
        morreuSom = Gdx.audio.newSound(Gdx.files.internal("morreu.mp3"));
        GameOver = new Texture("gameOver.png");
        arrayBarril = new ArrayList<>();
        queda= 0;
        i=0;
        timer = new Timer();
        caiu = false;
        cont1=false;
        cont2=false;
        cont3=false;
        testeSom = false;
        tamanho1 = 0;
        spriteDonkeyKong = 1;
        spritePuloMarioDireita = 0;
        spritePuloMarioEsquerda = 0;
        spritePuloMarioY = 0;
        pulou = 0;
        entrou1 = false;
        entrou2 = false;
        entrou3 = false;
        entrouEscada = false;
        entrouBuraco = false;
        batch = new SpriteBatch();
        stage = new Stage();
        stageBarril = new Stage();
        perdeu =false;
        contSom=0;

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

        //Princesa
        princesa = new Objeto("princesa_1.png",150,420, 30,26);
        stage.addActor(princesa.getImg());

       arrayBarril.add(new Movel("barril_1.png", 180, 420, 20,20, 0,0));
       stageBarril.addActor(arrayBarril.get(0).getImg());

    }

    @Override
    public void render() {


        //Reinicializa a variavel com falso
        entrouBuraco=false;

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        i = moveBarril();
        if(i==1){
            dispose();
            Gdx.gl.glClearColor(0, 0, 0.2f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            //colocar a imagem de gameover aqui
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    Gdx.app.exit();
                }
            }, 12);
        }

        cair();

        //Caso ele esteja nas plataformas, atualiza a variavel que ele não está na escada e poderá pular
        if(mario.getPosY()==40 || mario.getPosY()==136 || mario.getPosY()==232 || mario.getPosY()==325 || mario.getPosY()==418) {
            entrouEscada = false;
        }
        //se o mario estiver em cima do buraco, ele cai
        if(caiu && queda > -1){
            queda++;
            queda = mario.cai(teste, stage, queda);
            //se entrou no buraco, atualiza a variavel com verdadeiro
            entrouBuraco = true;
        }else if(queda == -1){
            queda = 1;
            caiu = false;
        }


        //o Mário anda pra direita
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && mario.canMove("direita")) {
            removeMovel(mario,stage);
			andarMario(true);
			stage.addActor(mario.getImg());
            contSom++;
			contSom = mario.andaSom(contSom, andar);
        }
        //O Mario anda pra esquerda
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && mario.canMove("esquerda")) {
            removeMovel(mario,stage);
            andarMario(false);
			stage.addActor(mario.getImg());
            contSom++;
            contSom = mario.andaSom(contSom, andar);
        }
        //o mario sobe as escadas
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            removeMovel(mario,stage);
            entrouEscada=true;
            subiuEscada();
			stage.addActor(mario.getImg());
            contSom++;
            contSom = mario.andaSom(contSom, andar);

        }

        //o mario desce as escadas
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            removeMovel(mario,stage);
            entrouEscada=true;
            desceuEscada();
			stage.addActor(mario.getImg());
            contSom++;
            contSom = mario.andaSom(contSom, andar);
        }

        //Pula no eixo Y
        //Só pode pular caso não esteja no buraco e na escada
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && !entrou1 && !entrouEscada && !entrouBuraco){
           spritePuloMarioY = 0;
           entrou1 = true;
           puloSom.play();
        }

        if(entrou1 && spritePuloMarioY > -1){
            removeMovel(mario,stage);
            spritePuloMarioY++;
            spritePuloMarioY = mario.pularY(stage, spritePuloMarioY);

        }else if(spritePuloMarioY == -1){
            spritePuloMarioY = 0;
            entrou1 = false;
        }

        //Pula em parabola para a direita
        //Só pode pular caso não esteja no buraco e na escada
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !entrou2 && !entrouEscada && !entrouBuraco){
            spritePuloMarioDireita = 1;
            entrou2 = true;
        }

        if(entrou2 && spritePuloMarioDireita > -1 ){
            removeMovel(mario,stage);
            if(spritePuloMarioDireita <=50){
                    spritePuloMarioDireita++;
                    spritePuloMarioDireita = mario.pularDiagonalDireita(stage, spritePuloMarioDireita);
            }

        } else if(spritePuloMarioDireita == -1){
            spritePuloMarioDireita = 1;
            entrou2 = false;
        }

        //Pula em parabola para a esquerda
        //Só pode pular caso não esteja no buraco e na escada
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && Gdx.input.isKeyPressed(Input.Keys.LEFT) && !entrou3 && !entrouEscada && !entrouBuraco){
            spritePuloMarioEsquerda = 1;
            entrou3 = true;
        }

        if(entrou3 && spritePuloMarioEsquerda> -1){
            removeMovel(mario,stage);
            if(spritePuloMarioDireita <=50) {
                spritePuloMarioEsquerda++;
                spritePuloMarioEsquerda = mario.pularDiagonalEsquerda(stage, spritePuloMarioEsquerda);
            }
        }else if(spritePuloMarioEsquerda == -1){
            spritePuloMarioEsquerda =1;
            entrou3 = false;
        }

        //Tira a imagem do Donkey Kong
        for (Actor actor : stage.getActors()) {
            //Se o actor é o Donkey Kong
            if (actor.getX() == macaco.getPosX() && actor.getY() == macaco.getPosY()) {
                actor.remove();
            }
        }
        //Tira a imagem da princesa
        for (Actor actor : stage.getActors()) {
            //Se o actor é o Donkey Kong
            if (actor.getX() == princesa.getPosX() && actor.getY() == princesa.getPosY()) {
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

        //Troca a imagem do Donkey e da princesa
        if (spriteDonkeyKong <= 60) {
            //Donkey Kong
            macaco = new Objeto("donkey_kong_" + 1 + ".png", 60, 420, 50, 50);
            stage.addActor(macaco.getImg());
            princesa = new Objeto("princesa_" + 1 + ".png", 150,420, 30,26);
            stage.addActor(princesa.getImg());
            spriteDonkeyKong++;
        } else if (spriteDonkeyKong <= 120) {
            //Donkey Kong
            macaco = new Objeto("donkey_kong_" + 2 + ".png", 60, 420, 50, 50);
            stage.addActor(macaco.getImg());
            princesa = new Objeto("princesa_" + 2 + ".png", 150,420, 30,26);
            stage.addActor(princesa.getImg());
            spriteDonkeyKong++;
        }
        if (spriteDonkeyKong == 120) {
            spriteDonkeyKong = 1;
        }

        stage.act(Gdx.graphics.getDeltaTime());
        stageBarril.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        stageBarril.draw();
        batch.end();
    }

    public int moveBarril() {

        for (int i = 0; i < arrayBarril.size(); i++) {
            tamanho1 = arrayBarril.get(i).sizeX / 2;
            if (mario.getPosY() == 418 && arrayBarril.get(i).getPosY() == 420) {
                if (mario.getPosX() <= (arrayBarril.get(i).getPosX() + tamanho1) && mario.getPosX() >= (arrayBarril.get(i).getPosX() - tamanho1) && !cont1) {
                    cont1 = true;
                    if (cont1) {
                        morreuSom.play();
                        return 1;
                    }
                }
            }
            if (mario.getPosY() == 325 && arrayBarril.get(i).getPosY() == 324) {
                if (mario.getPosX() <= (arrayBarril.get(i).getPosX() + tamanho1) && mario.getPosX() >= (arrayBarril.get(i).getPosX() - tamanho1) && !cont2) {
                    cont2 = true;
                    System.out.println("contador:" + cont2);
                    if (cont2) {
                        morreuSom.play();
                        return 1;
                    }
                }
            }
            if (mario.getPosY() == arrayBarril.get(i).getPosY()) {
                if (mario.getPosX() <= (arrayBarril.get(i).getPosX() + tamanho1) && mario.getPosX() >= (arrayBarril.get(i).getPosX() - tamanho1) && !cont3) {
                    cont3 = true;
                    System.out.println("contador:" + cont3);
                    if (cont3) {
                        morreuSom.play();
                        return 1;
                    }
                }
            }
            spawnBarril(i);

        }
        return 0;
    }


    public void removeMovel(Movel movel, Stage stage){
        for (Actor actor : stage.getActors()) {
            //Se é o Movel
            if (actor.getX() == movel.getPosX() && actor.getY() == movel.getPosY()) {
                actor.remove();
            }
        }
    }

    public void spawnBarril(int i){

            if (arrayBarril.get(i).getPosY()<= 420 && arrayBarril.get(i).getPosY() > 325) {

                if (arrayBarril.get(i).getPosX()  <= 258) {
                    removeMovel(arrayBarril.get(i), stageBarril);
                    arrayBarril.get(i).setPosX(arrayBarril.get(i).getPosX()+3);
                    arrayBarril.get(i).setPosY(arrayBarril.get(i).getPosY());
                    //arrayBarril.get(i).setImg(new Image(new Texture("barril_1.png")));
                    arrayBarril.get(i).getImg().setPosition(arrayBarril.get(i).getPosX(),arrayBarril.get(i).getPosY());
                    stageBarril.addActor(arrayBarril.get(i).getImg());

                } else if (arrayBarril.get(i).getPosX()> 258) {
                    removeMovel(arrayBarril.get(i), stageBarril);
                    arrayBarril.get(i).setPosX(arrayBarril.get(i).getPosX());
                    arrayBarril.get(i).setPosY(arrayBarril.get(i).getPosY()-2);
                    //arrayBarril.get(i).setImg(new Image(new Texture("barril_1.png")));
                    arrayBarril.get(i).getImg().setPosition(arrayBarril.get(i).getPosX(),arrayBarril.get(i).getPosY());
                    stageBarril.addActor(arrayBarril.get(i).getImg());
                }
            }
            if (arrayBarril.get(i).getPosY()<= 325 && arrayBarril.get(i).getPosY()  > 232) {

                if (arrayBarril.get(i).getPosX() <= 534) {
                    removeMovel(arrayBarril.get(i), stageBarril);
                    arrayBarril.get(i).setPosX(arrayBarril.get(i).getPosX()+3);
                    arrayBarril.get(i).setPosY(arrayBarril.get(i).getPosY());
                    //arrayBarril.get(i).setImg(new Image(new Texture("barril_1.png")));
                    arrayBarril.get(i).getImg().setPosition(arrayBarril.get(i).getPosX(),arrayBarril.get(i).getPosY());
                    stageBarril.addActor(arrayBarril.get(i).getImg());

                } else if (arrayBarril.get(i).getPosX() > 534) {
                    removeMovel(arrayBarril.get(i), stageBarril);
                    arrayBarril.get(i).setPosX(arrayBarril.get(i).getPosX());
                    arrayBarril.get(i).setPosY(arrayBarril.get(i).getPosY()-2);
                   // arrayBarril.get(i).setImg(new Image(new Texture("barril_1.png")));
                    arrayBarril.get(i).getImg().setPosition(arrayBarril.get(i).getPosX(),arrayBarril.get(i).getPosY());
                    stageBarril.addActor(arrayBarril.get(i).getImg());
                }
            }
            if (arrayBarril.get(i).getPosY() <= 232 && arrayBarril.get(i).getPosY() > 136) {

                if (arrayBarril.get(i).getPosX() >= 495) {
                    removeMovel(arrayBarril.get(i), stageBarril);
                    arrayBarril.get(i).setPosX(arrayBarril.get(i).getPosX()-3);
                    arrayBarril.get(i).setPosY(arrayBarril.get(i).getPosY());
                    //arrayBarril.get(i).setImg(new Image(new Texture("barril_1.png")));
                    arrayBarril.get(i).getImg().setPosition(arrayBarril.get(i).getPosX(),arrayBarril.get(i).getPosY());
                    stageBarril.addActor(arrayBarril.get(i).getImg());

                } else if (arrayBarril.get(i).getPosX() < 495) {
                    removeMovel(arrayBarril.get(i), stageBarril);
                    arrayBarril.get(i).setPosX(arrayBarril.get(i).getPosX());
                    arrayBarril.get(i).setPosY(arrayBarril.get(i).getPosY()-2);
                    //arrayBarril.get(i).setImg(new Image(new Texture("barril_1.png")));
                    arrayBarril.get(i).getImg().setPosition(arrayBarril.get(i).getPosX(),arrayBarril.get(i).getPosY());
                    stageBarril.addActor(arrayBarril.get(i).getImg());
                }
            }
            if (arrayBarril.get(i).getPosY() <= 136 && arrayBarril.get(i).getPosY() > 40) {

                if (arrayBarril.get(i).getPosX() >= 324) {

                    if(arrayBarril.get(i).getPosX()==483){
                        arrayBarril.add(new Movel("barril_1.png", 180, 420, 20,20, 0,0));
                        stageBarril.addActor(arrayBarril.get(i+1).getImg());
                    }
                    removeMovel(arrayBarril.get(i), stageBarril);

                    arrayBarril.get(i).setPosX(arrayBarril.get(i).getPosX()-3);
                    arrayBarril.get(i).setPosY(arrayBarril.get(i).getPosY());
                   // arrayBarril.get(i).setImg(new Image(new Texture("barril_1.png")));
                    arrayBarril.get(i).getImg().setPosition(arrayBarril.get(i).getPosX(),arrayBarril.get(i).getPosY());
                    stageBarril.addActor(arrayBarril.get(i).getImg());


                } else if (arrayBarril.get(i).getPosX() < 324) {
                    removeMovel(arrayBarril.get(i), stageBarril);
                    arrayBarril.get(i).setPosX(arrayBarril.get(i).getPosX());
                    arrayBarril.get(i).setPosY(arrayBarril.get(i).getPosY()-2);
                   // arrayBarril.get(i).setImg(new Image(new Texture("barril_1.png")));
                    arrayBarril.get(i).getImg().setPosition(arrayBarril.get(i).getPosX(),arrayBarril.get(i).getPosY());
                    stageBarril.addActor(arrayBarril.get(i).getImg());
                }
            }
            if (arrayBarril.get(i).getPosY() == 40) {

                if (arrayBarril.get(i).getPosX() > 39) {
                    removeMovel(arrayBarril.get(i), stageBarril);
                    arrayBarril.get(i).setPosX(arrayBarril.get(i).getPosX()-3);
                    //arrayBarril.get(i).setImg(new Image(new Texture("barril_1.png")));
                    arrayBarril.get(i).getImg().setPosition(arrayBarril.get(i).getPosX(),arrayBarril.get(i).getPosY());
                    stageBarril.addActor(arrayBarril.get(i).getImg());
                }
                if (arrayBarril.get(i).getPosX() == 39) {
                    removeMovel(arrayBarril.get(i), stageBarril);
                    arrayBarril.remove(i);
                }
            }
    }


    public void cair(){
        //Caso o mario passe por cima do primeiro buraco
        if (mario.getPosY() == 136 && mario.getPosX() >= 273 && mario.getPosX() <= 324) {
            queda = 1;
            caiu = true;
            teste = (mario.getPosY()-96);
        }
        //Caso o mario passe por cima do segundo buraco
        if (mario.getPosY() == 232 && mario.getPosX() >=  87  && mario.getPosX() <= 144) {
            queda = 1;
            caiu = true;
            teste = (mario.getPosY()-96);
        }
        //Caso o mario passe por cima do terceiro buraco
        if (mario.getPosY() == 232 && mario.getPosX() >= 441 && mario.getPosX() <= 495) {
            queda = 1;
            caiu = true;
            teste = (mario.getPosY()-96);
        }
        //Caso o mario passe por cima do ultimo buraco
        if (mario.getPosY() == 418 && mario.getPosX() >= 258) {
            queda = 1;
            caiu = true;
            teste = (mario.getPosY()-92);
        }

    }
    public void desceuEscada() {

        if (mario.getPosY() > 40 && mario.getPosY() <= 136) {
            if (mario.getPosX() >= 495 && mario.getPosX() <= 522) {
                mario.setPosY(mario.getPosY() - 1);
                mario = new Movel("mario_3_3.png", mario.getPosX(), (mario.getPosY() - 2), 32, 33, 0, 0);
                contSom++;
                contSom = mario.andaSom(contSom, andar);
            }

        } else if (mario.getPosY() >= 136 && mario.getPosY() <= 232) {
            if (mario.getPosX() >= 195 && mario.getPosX() <= 225) {
                mario.setPosY(mario.getPosY() - 1);
                mario = new Movel("mario_3_3.png", mario.getPosX(), (mario.getPosY() - 2), 32, 33, 0, 0);
                contSom++;
                contSom = mario.andaSom(contSom, andar);
            }
        } else if (mario.getPosY() >= 232 && mario.getPosY() <= 325) {
            if (mario.getPosX() >= 537 && mario.getPosX() <= 561) {
                mario.setPosY(mario.getPosY() - 1);
                mario = new Movel("mario_3_3.png", mario.getPosX(), (mario.getPosY() - 2), 32, 33, 0, 0);
                contSom++;
                contSom = mario.andaSom(contSom, andar);
            }
        } else if (mario.getPosY() >= 325 && mario.getPosY() <= 415) {
            if (mario.getPosX() >= 220 && mario.getPosX() <= 260) {
                mario.setPosY(mario.getPosY() - 1);
                mario = new Movel("mario_3_3.png", mario.getPosX(), (mario.getPosY() - 2), 32, 33, 0, 0);
                contSom++;
                contSom = mario.andaSom(contSom, andar);
            }
        } else if (mario.getPosY() >= 418) {
            if (mario.getPosX() >= 213 && mario.getPosX() <= 237) {
				mario.setPosY(mario.getPosY() - 1);
				mario = new Movel("mario_3_3.png", mario.getPosX(), (mario.getPosY() - 2), 32, 33, 0, 0);
                contSom++;
                contSom = mario.andaSom(contSom, andar);
            }
        }
    }

    public void subiuEscada() {
        if (mario.getPosY() < 135) {
            if (mario.getPosX() >= 495 && mario.getPosX() <= 522) {
                mario.setPosY(mario.getPosY() + 1);
                mario = new Movel("mario_3_3.png", mario.getPosX(), (mario.getPosY() + 2), 32, 33, 0, 0);
            }
        } else if (mario.getPosY() >= 135 && mario.getPosY() < 230) {
            if (mario.getPosX() >= 195 && mario.getPosX() <= 225) {
                mario.setPosY(mario.getPosY() + 1);
                mario = new Movel("mario_3_3.png", mario.getPosX(), (mario.getPosY() + 2), 32, 33, 0, 0);
            }
        } else if (mario.getPosY() >= 230 && mario.getPosY() < 325) {
            if (mario.getPosX() >= 537 && mario.getPosX() <= 561) {
                mario.setPosY(mario.getPosY() + 1);
                mario = new Movel("mario_3_3.png", mario.getPosX(), (mario.getPosY() + 2), 32, 33, 0, 0);
            }
        } else if (mario.getPosY() >= 325 && mario.getPosY() <= 415) {
            if (mario.getPosX() >= 213 && mario.getPosX() <= 237) {
                mario.setPosY(mario.getPosY() + 1);
                mario = new Movel("mario_3_3.png", mario.getPosX(), (mario.getPosY() + 2), 32, 33, 0, 0);
            }
        }
    }
	public void andarMario(boolean direcao) {

        if(direcao) {
            //anda pra direita
            if (mario.getPosY() == 40) {
                mario.setPosX(mario.getPosX() + 1);
                mario = new Movel("mario_2_1.png", (mario.getPosX() + 2), mario.getPosY(), 30, 26, 0, 0);

            } else if (mario.getPosY() == 136) {
                mario.setPosX(mario.getPosX() + 1);
                mario = new Movel("mario_2_1.png", (mario.getPosX() + 2), mario.getPosY(), 30, 26, 0, 0);

            } else if (mario.getPosY() == 232) {

                mario.setPosX(mario.getPosX() + 1);
                mario = new Movel("mario_2_1.png", (mario.getPosX() + 2), mario.getPosY(), 30, 26, 0, 0);

            } else if (mario.getPosY() == 325) {

                mario.setPosX(mario.getPosX() + 1);
                mario = new Movel("mario_2_1.png", (mario.getPosX() + 2), mario.getPosY(), 30, 26, 0, 0);

            } else if (mario.getPosY() == 418) {
                mario.setPosX(mario.getPosX() + 1);
                mario = new Movel("mario_2_1.png", (mario.getPosX() + 2), mario.getPosY(), 30, 26, 0, 0);
            }
        }else{
            //anda pra esquerda
            if (mario.getPosY() == 40) {
                mario.setPosX(mario.getPosX() - 3);
                mario = new Movel("mario_1_2.png", mario.getPosX(), mario.getPosY(), 30, 26, 0, 0);

            } else if (mario.getPosY() == 136) {
                mario.setPosX(mario.getPosX() - 3);
                mario = new Movel("mario_1_2.png", mario.getPosX(), mario.getPosY(), 30, 26, 0, 0);

            } else if (mario.getPosY() == 232) {
                mario.setPosX(mario.getPosX() - 3);
                mario = new Movel("mario_1_2.png", mario.getPosX(), mario.getPosY(), 30, 26, 0, 0);

            } else if (mario.getPosY() == 325) {
                mario.setPosX(mario.getPosX() - 3);
                mario = new Movel("mario_1_2.png", mario.getPosX(), mario.getPosY(), 30, 26, 0, 0);

            } else if (mario.getPosY() == 418) {
                mario.setPosX(mario.getPosX() - 3);
                mario = new Movel("mario_1_2.png", mario.getPosX(), mario.getPosY(), 30, 26, 0, 0);
            }
        }
	}


    @Override
    public void dispose() {
        batch.dispose();
       // morreuSom.dispose();
        puloSom.dispose();
        andar.dispose();
        stage.dispose();
        princesa.dispose();
        mario.dispose();
        macaco.dispose();
        stageBarril.dispose();
    }
}
