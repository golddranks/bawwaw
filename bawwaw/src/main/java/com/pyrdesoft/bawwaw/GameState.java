/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pyrdesoft.bawwaw;

import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author drasa
 */
public class GameState {
    
    Jumper mainChar; 
    ArrayList<Sprite> enemies;
    ArrayList<Sprite> collectables;
    ArrayList<Block> walls;
    
    ArrayList<Sprite> allSprites;
    ArrayList<Block> allBlocks;
    
    Image enemyImg;
    Image mainCharImg;
    Image platImg;
    
    GameState() throws SlickException {
        
        enemies = new ArrayList<Sprite>();
        collectables = new ArrayList<Sprite>();
        walls = new ArrayList<Block>();
        
        allSprites = new ArrayList<Sprite>();
        allBlocks = new ArrayList<Block>();
        
        enemyImg = new Image("assets/bau.png");
        mainCharImg = new Image("assets/boyby.png");
        platImg = new Image("assets/plat.png");
    }

    
    void buildGameWorld() {
        
        Sprite mainCharSprite = new Sprite(100, 100, mainCharImg);
        mainChar = new Jumper(mainCharSprite);
        
        
        for (int i = 0; i < 100; i++) {
            Sprite baddie = new Sprite(Math.random() * 500, Math.random()*500, enemyImg);
            enemies.add(baddie);
        }
        
        for (int i = 0; i < 10; i++) {
            Sprite wallSpr = new Sprite(Math.random() * 500, Math.random() * 500, platImg);
            Block wall = new Block(wallSpr);
            walls.add(wall);
        }
    }

}
