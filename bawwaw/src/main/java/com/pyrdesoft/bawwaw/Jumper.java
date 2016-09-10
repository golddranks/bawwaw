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
    BBox bbox;
    Controller ctrl;
    double vel_x;
    double vel_y;
    boolean standing;
            
    Jumper(Sprite s) {
        this.sprite = s;
        this.bbox = new BBox(s.x, s.y, s.getWidth(), s.getHeight());
        Main.getCurrentGameState().allJumpers.add(this);
    }
    
    void obeyControls() {
        if (ctrl == null)
            return;
        if (ctrl.left && !ctrl.right)
            vel_x = -3;
        if (ctrl.right && !ctrl.left)
            vel_x = 3;
        if (!ctrl.right && !ctrl.left)
            vel_x = 0;
        if (ctrl.jump && standing)
            vel_y = -5;
    }
    
    void update(GameState state) { 
        obeyControls();
        vel_y += 0.15;
        bbox.y += vel_y;
        bbox.x += vel_x;
        standing = false;
        sprite.updateFrom(bbox);
        if (bbox.bb_collides_any(state.walls)) {
            bbox.y -= vel_y;
            vel_y *= -0.5; // TODO
            standing = true;
        }
    }
}
