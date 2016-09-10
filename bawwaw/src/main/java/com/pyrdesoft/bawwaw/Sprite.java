/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pyrdesoft.bawwaw;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Sprite {
    Image img;
    double x, y;
    
    Sprite(double x, double y, Image i) {
        img = i;
        this.x = x;
        this.y = y;
        Main.getCurrentGameState().allSprites.add(this);
    }
    
    void draw(Graphics g) {
        g.drawImage(this.img, (float) this.x, (float) this.y);
    }
    
    int getWidth() {
        return this.img.getWidth();
    }
    
    int getHeight() {
        return this.img.getHeight();
    }
    
    void updateFrom(BBox box) {
        x = box.x;
        y = box.y;
    } 
}