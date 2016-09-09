/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pyrdesoft.bawwaw;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Main extends BasicGame {

    public Main(String gamename) {
        super(gamename);
    }
    
    static GameState state;
    static Block cursor;
    static Image cursorImgNormal;
    static Image cursorImgHilite;

    @Override
    public void init(GameContainer gc) throws SlickException {
        state = new GameState();
        state.buildGameWorld();
        this.cursorImgNormal = new Image("assets/cursor.png");
        this.cursorImgHilite = new Image("assets/cursor2.png");
        Sprite cursorSprite = new Sprite(-100.0, -100.0, cursorImgNormal);
        cursor = new Block(cursorSprite);
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        Input input = gc.getInput();
        
        cursor.sprite.x = input.getMouseX();
        cursor.sprite.y = input.getMouseY();
        if (cursor.bb_collides_any(this.state.allBlocks)) {
            cursor.sprite.img = this.cursorImgHilite;
        } else {
            cursor.sprite.img = this.cursorImgNormal;
        }
        
        state.mainChar.update(state);
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        g.drawString("Mouse:"+String.valueOf(cursor.sprite.x) + String.valueOf(cursor.sprite.y), 40, 40);
        for( Sprite s : state.allSprites ) {
            s.draw(g);
        }
    }
  
    public static void main(String[] args) {
        try {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new Main("Simple Slick Game"));
            appgc.setDisplayMode(640, 480, false);
            appgc.start();
        } catch (SlickException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static GameState getCurrentGameState() {
        return state;
    }
}
