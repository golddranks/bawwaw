/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pyrdesoft.bawwaw;

import static com.pyrdesoft.bawwaw.Main.state;
import java.util.ArrayList;
import java.util.Stack;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;


public class Bullet {
    Image img;
    BBox box;
    double x_vel, y_vel;
    
    Bullet(Image img) {
        this.img = img;
        box = new BBox(0, 0, img.getWidth(), img.getHeight());
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
    }
    
    void update(GameState gs) {
        box.x += x_vel;
        box.y += y_vel;
        if (!box.bb_collides(gs.world)) {
            gs.limboBullets.push(this);
        }
    }
    
    static void updateAllBullets(GameState state) {
        for( Bullet b : state.liveBullets) {
            b.update(state);
        }
        while( !state.limboBullets.empty() ) {
            Bullet b = state.limboBullets.pop();
            state.liveBullets.remove(b);
            state.deadBullets.push(b);
        }
    }
    
    
    
}
