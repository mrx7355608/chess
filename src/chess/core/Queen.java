/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.core;

import java.io.IOException;

/**
 *
 * @author fawad
 */
public class Queen extends Piece {
    public Queen(int x, int y, String color) throws IOException {
        super(x, y, color, "queen");        
    }
}
