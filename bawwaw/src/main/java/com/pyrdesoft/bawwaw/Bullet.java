/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pyrdesoft.bawwaw;

import java.util.ArrayList;
import java.util.Stack;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;


public class Bullet {
    Image img;
    BBox box;
    double x_vel, y_vel;
    int damage;
    boolean gotHit;
    Bullet(Image img) {
        this.img = img;
        box = new BBox(0, 0, img.getWidth(), img.getHeight());
        gotHit = false;
    }
    
    
    void draw(Graphics g) {
        g.drawImage(this.img, (float) box.x, (float) box.y);
    }
    
    
    static void shoot(Stack<Bullet> deadBullets, ArrayList<Bullet> liveBullets, double x, double y, double x_vel, double y_vel) {
        Bullet bullet = deadBullets.pop();
        liveBullets.add(bullet);
        bullet.x_vel = x_vel;
        bullet.y_vel = y_vel;
        bullet.box.x = x;
        bullet.box.y = y;
        bullet.damage = 1;
        bullet.gotHit = false;
    }
    
    void update(GameState gs, double delta) {
        box.x += x_vel*delta;
        box.y += y_vel*delta;
        if (!box.bb_collides(gs.world)) {
            getHit(gs);
        }
        if (box.bb_collides_any(gs.walls)) {
            getHit(gs);
        }
    }
    
    void getHit(GameState gs) {
        if (!gotHit) { // Ensures that limboBullets contains only one ref to bullet
                        // even if getHit() is called multiple times for the same bullet
            gs.limboBullets.push(this);
        }
        gotHit = true;
    }
    
    static void updateAll(GameState state, double delta) {
        for( Bullet b : state.liveBullets) {
            b.update(state, delta);
        }
        while( !state.limboBullets.empty() ) {
            Bullet b = state.limboBullets.pop();
            state.liveBullets.remove(b);
            state.deadBullets.push(b);
        }
    }
    
    
    
}
