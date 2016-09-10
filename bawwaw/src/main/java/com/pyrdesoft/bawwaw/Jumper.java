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
    double shootState;
    boolean shoot;
    int faceDir;
            
    Jumper(Sprite s) {
        this.sprite = s;
        this.bbox = new BBox(s.x, s.y, s.getWidth(), s.getHeight());
        Main.getCurrentGameState().liveJumpers.add(this);
    }
    
    void obeyControls(GameState state) {
        if (ctrl == null)
            return;
        if (ctrl.left && !ctrl.right) {
            vel_x = -160;
            faceDir = -1;
        }
        if (ctrl.right && !ctrl.left)
        {
            vel_x = 160;
            faceDir = 1;
        }
        if (!ctrl.right && !ctrl.left) {
            vel_x = 0;
        }
        if (ctrl.jump && standing) {
            vel_y = -350;
        }
        if (ctrl.shoot && shootState <= 0) {
            Bullet.shoot(state.deadBullets, state.liveBullets, bbox.x, bbox.y, 500*faceDir, 0);
            shootState = 0.2;
        }
    }
    
    void update(GameState state, double delta) { 
        obeyControls(state);
        if (shootState > 0) {
            shootState -= delta;
        }
        vel_y += 500*delta;
        bbox.y += vel_y*delta;
        bbox.x += vel_x*delta;
        standing = false;
        sprite.updateFrom(bbox);
        if (bbox.bb_collides_any(state.walls)) {
            bbox.y -= vel_y*delta;
            vel_y *= -0.2; // TODO
            standing = true;
        }
    }
}
