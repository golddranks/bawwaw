/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pyrdesoft.bawwaw;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Sprite {
    Image img;
    
    Sprite(String filename) throws SlickException {
        img = new Image("assets/"+filename);
    }
}