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
public class HitBox {

    int hp;
    BBox hitbox;
    Destroyable thing;

    HitBox(int hp, BBox box, Destroyable thing) {
        this.hp = hp;
        this.hitbox = box;
        this.thing = thing;
    }

    void update(GameState state) {
        Bullet hitBullet = null;
        for (Bullet bullet : state.liveBullets) {
            if (hitbox.bb_collides(bullet.box)) {
                hitBullet = bullet;
                hitBullet.getHit(state);
                break;
            }
        }
        if (hitBullet != null) {
            hp -= hitBullet.damage;
        }
        
        if (hp <= 0) {
            die(state);
        }
    }
    
    void die(GameState state) {
        state.limboHitBoxes.push(this);
    }

    static void updateAll(GameState state) {
        for (HitBox box : state.liveHitBoxes) {
            box.update(state);
        }
        while (!state.limboHitBoxes.isEmpty()) {
            HitBox dyingThing = state.limboHitBoxes.pop();
            state.liveHitBoxes.remove(dyingThing);
            dyingThing.thing.destroy(state);
        }
    }
}
