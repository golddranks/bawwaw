/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pyrdesoft.bawwaw;

/**
 *
 * @author drasa
 */
public class Jumper {
    Sprite sprite;
    Block bbox;
    double vel_x;
    double vel_y;
            
    Jumper(Sprite s) {
        this.sprite = s;
        this.bbox = new Block(s);
    }
    
    void update(GameState state) {
        this.sprite.y += vel_y;
        this.sprite.x += vel_x;
        vel_y += 0.1;
        if (this.bbox.bb_collides_any(state.allBlocks)) {
            vel_y *= -1; // TODO
        }
    }
}
