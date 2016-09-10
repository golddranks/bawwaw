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
    static BBox cursorBox;
    static Sprite cursorSprite;
    static Image cursorImgNormal;
    static Image cursorImgHilite;
    
    double lastDelta;

    @Override
    public void init(GameContainer gc) throws SlickException {
        state = new GameState();
        state.buildGameWorld();
        cursorImgNormal = new Image("assets/cursor.png");
        cursorImgHilite = new Image("assets/cursor2.png");
        cursorSprite = new Sprite(0, 0, cursorImgNormal);
        cursorBox = new BBox(cursorSprite);
    }
    
    void updateCursor(Input input) {
        cursorBox.x = input.getMouseX() - cursorSprite.getWidth()/2;
        cursorBox.y = input.getMouseY() - cursorSprite.getHeight()/2;
        cursorSprite.updateFrom(cursorBox);
        if (cursorBox.bb_collides_any(this.state.walls)) {
            cursorSprite.img = this.cursorImgHilite;
        } else {
            cursorSprite.img = this.cursorImgNormal;
        }
    }

    @Override
    public void update(GameContainer gc, int delta_ms) throws SlickException {
        Input input = gc.getInput();
        
        double delta = (double) delta_ms / 1000;
        lastDelta = delta;
        
        updateCursor(input);
        state.updatePlayerController(input);
        for (Jumper j : state.liveJumpers) {
            j.update(state, delta);
        }
        Bullet.updateAll(state, delta);
        HitBox.updateAll(state);
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        for( Sprite s : state.renderSprites ) {
            s.draw(g);
        }
        for( Bullet b : state.liveBullets) {
            b.draw(g);
        }
        g.drawString("Mouse:"+String.valueOf(cursorBox.x) + String.valueOf(cursorBox.y), 40, 40);
        g.drawString("LEFT RIGHT, Z=JUMP X=SHOOT", 40, 100);
        g.drawString(String.valueOf(lastDelta), 40, 120);
        
        
        
        
        
    }
  
    public static void main(String[] args) {
        try {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new Main("BAWWAW"));
            appgc.setDisplayMode(640, 480, false);
            appgc.setVSync(true);
            appgc.setTargetFrameRate(60);
            appgc.start();
        } catch (SlickException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static GameState getCurrentGameState() {
        return state;
    }
}
