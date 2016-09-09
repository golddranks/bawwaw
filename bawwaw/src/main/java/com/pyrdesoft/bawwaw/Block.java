/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pyrdesoft.bawwaw;

import java.util.ArrayList;

/**
 *
 * @author drasa
 */
public class Block {
    Sprite sprite;
    Block(Sprite sprite) {
        this.sprite = sprite;
        Main.getCurrentGameState().allBlocks.add(this);
    }
    
    boolean bb_collides(Block other) {
        double this_right_edge = this.sprite.x + this.sprite.getWidth();
        double this_left_edge = this.sprite.x;
        double other_left_edge = other.sprite.x;
        double other_right_edge = other.sprite.x + other.sprite.getWidth();
        
        if (this_right_edge < this_left_edge)
            return false;
        if (other_right_edge < this_left_edge)
            return false;
        
        double this_top_edge = this.sprite.y;
        double this_bottom_edge = this.sprite.y + this.sprite.getHeight();
        double other_top_edge = other.sprite.y;
        double other_bottom_edge = other.sprite.y + this.sprite.getHeight();
        
        if (this_bottom_edge < other_top_edge)
            return false;
        if (other_bottom_edge < this_top_edge)
            return false;
        return true;
    }
    
    boolean bb_collides_any(ArrayList<Block> blocks) {
        for (Block b : blocks) {
            if (b == this)
                continue;
            if (this.bb_collides(b)) {
                return true;
            }
        }
        return false;
    }
}
