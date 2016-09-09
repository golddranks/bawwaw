/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pyrdesoft.bawwaw;

import java.util.ArrayList;
import org.newdawn.slick.SlickException;

/**
 *
 * @author drasa
 */
public class GameState {
    
    Sprite mainChar; 
    ArrayList<Sprite> enemies;
    ArrayList<Sprite> collectables;
    ArrayList<Sprite> walls;
    
    void buildGameWorld() throws SlickException {
        mainChar = new Sprite("bau.png");
        
        enem = new Sprite("bau.png");
    }

}
