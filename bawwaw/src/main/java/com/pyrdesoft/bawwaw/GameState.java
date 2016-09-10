/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pyrdesoft.bawwaw;

import java.util.ArrayList;
import java.util.Stack;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 *
 * @author drasa
 */
public class GameState {
    
    Jumper mainChar; 
    ArrayList<Jumper> enemies;
    ArrayList<Sprite> collectables;
    ArrayList<BBox> walls;
    
    Stack<Bullet> deadBullets;
    ArrayList<Bullet> liveBullets;
    Stack<Bullet> limboBullets;
    
    ArrayList<Sprite> renderSprites;
    ArrayList<Jumper> liveJumpers;
    Stack<HitBox> limboHitBoxes;
    ArrayList<HitBox> liveHitBoxes;
    
    Image enemyImg;
    Image mainCharImg;
    Image platImg;
    Image bulletImg;
    Image slackyImg;
    
    BBox world;
    
    Controller playerCtrl;
    
    GameState() throws SlickException {
        
        enemies = new ArrayList<Jumper>();
        collectables = new ArrayList<Sprite>();
        walls = new ArrayList<BBox>();
        deadBullets = new Stack<Bullet>();
        liveBullets = new ArrayList<Bullet>();
        limboBullets = new Stack<Bullet>();
        
        liveJumpers = new ArrayList<Jumper>();
        liveHitBoxes = new ArrayList<HitBox>();
        limboHitBoxes = new Stack<HitBox>();
        renderSprites = new ArrayList<Sprite>();
        
        enemyImg = new Image("assets/bau.png");
        mainCharImg = new Image("assets/boyby.png");
        platImg = new Image("assets/plat.png");
        slackyImg = new Image("assets/slacky.png");
        bulletImg = new Image("assets/bullet.png");
        world = new BBox(0, 0, 640, 480);
    }

    
    void buildGameWorld() {
        
        Sprite mainCharSprite = new Sprite(100, 100, mainCharImg);
        mainChar = new Jumper(mainCharSprite);
        playerCtrl = new Controller();
        mainChar.ctrl = playerCtrl;
        
        
        for (int i = 0; i < 30; i++) {
            Sprite baddieSpr = new Sprite(Math.random() * 500, Math.random()*500, enemyImg);
            Jumper baddie = new Jumper(baddieSpr);
            HitBox baddieHitBox = new HitBox(2, baddie.bbox, baddie);
            liveHitBoxes.add(baddieHitBox);
        }
        
        for (int i = 0; i < 30; i++) {
            Sprite slackySpr = new Sprite(Math.random() * 500, Math.random()*500, slackyImg);
            Jumper slacky = new Jumper(slackySpr);
        }
        
        for (int i = 0; i < 10; i++) {
            Sprite wallSpr = new Sprite(Math.random() * 500, Math.random() * 500, platImg);
            BBox wall = new BBox(wallSpr.x, wallSpr.y, wallSpr.getWidth(), wallSpr.getHeight());
            walls.add(wall);
        }




        
        for (int i = 0; i < 1000; i++) { // TODO there is only limited amount of bullets
            Bullet bullet = new Bullet(bulletImg);
            deadBullets.push(bullet);
        }
    }

    void updatePlayerController(Input input) {
        playerCtrl.jump = false;
        playerCtrl.left = false;
        playerCtrl.right = false;
        playerCtrl.shoot = false;
        if (input.isKeyDown(Input.KEY_LEFT ))
            playerCtrl.left = true;
        if (input.isKeyDown(Input.KEY_RIGHT))
            playerCtrl.right = true;
        if (input.isKeyDown(Input.KEY_Z))
            playerCtrl.jump = true;
        if (input.isKeyDown(Input.KEY_X))
            playerCtrl.shoot = true;
    }

}
