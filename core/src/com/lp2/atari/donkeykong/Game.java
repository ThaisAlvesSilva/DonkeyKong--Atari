package com.lp2.atari.donkeykong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	Stage stage;
	Objeto plataforma;
	@Override
	public void create () {
		batch = new SpriteBatch();

		stage = new Stage();

		//Dados da plataforma 1
		plataforma = new Objeto("plataforma_1_buraco.png",40,385,222.218f, 40);
		stage.addActor(plataforma.getImg());

		//Dados da plataforma 2
		plataforma = new Objeto("plataforma_inteira.png",40,290,550, 35);
		stage.addActor(plataforma.getImg());

		//Dados da plataforma 3
		plataforma = new Objeto("plataforma_2_buracos_1.png",40,195,55, 40);
		stage.addActor(plataforma.getImg());

		//Dados da plataforma 4
		plataforma = new Objeto("plataforma_2_buracos_2.png",165,195,285, 40);
		stage.addActor(plataforma.getImg());

		//Dados da plataforma 5
		plataforma = new Objeto("plataforma_2_buracos_3.png",515,195,75, 40);
		stage.addActor(plataforma.getImg());

		//Dados da plataforma 6
		plataforma = new Objeto("plataforma_1_buraco.png",40,100,238, 40);
		stage.addActor(plataforma.getImg());

		//Dados da plataforma 7
		plataforma = new Objeto("plataforma_1_buraco.png",345,100,238, 40);
		stage.addActor(plataforma.getImg());

		//Dados da plataforma 8
		plataforma = new Objeto("plataforma_inteira.png",40,5,550, 35);
		stage.addActor(plataforma.getImg());
	}



	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
