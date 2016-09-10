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
public class BBox {
    double x, y, width, height;
    
    BBox(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        Main.getCurrentGameState().allBlocks.add(this);
    }
    
    BBox(Sprite sprite){
        x = sprite.x;
        y = sprite.y;
        width = sprite.getWidth();
        height = sprite.getHeight();
    }
    
    boolean bb_collides(BBox other) {
        double this_left_edge = this.x;
        double this_right_edge = this.x + this.width;
        double other_left_edge = other.x;
        double other_right_edge = other.x + other.width;
        
        if (this_right_edge < other_left_edge)
            return false;
        if (other_right_edge < this_left_edge)
            return false;
        
        double this_top_edge = this.y;
        double this_bottom_edge = this.y + this.height;
        double other_top_edge = other.y;
        double other_bottom_edge = other.y + other.height;
        
        if (this_bottom_edge < other_top_edge)
            return false;
        if (other_bottom_edge < this_top_edge)
            return false;

        return true;
    }
    
    boolean bb_collides_any(ArrayList<BBox> blocks) {
        for (BBox b : blocks) {
            if (b == this)
                continue;
            if (this.bb_collides(b)) {
                return true;
            }
        }
        return false;
    }
}
