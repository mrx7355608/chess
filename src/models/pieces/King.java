/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.pieces;

import java.io.IOException;

/**
 *
 * @author fawad
 */
public class King extends Piece {
    public King(int x, int y, String color) throws IOException {
        super(x, y, color, "king");        
    }
}
