/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.pieces;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author fawad
 */
public class Piece {
    public int x;
    public int y;
    public int row;
    public int col;
    public String color;
    public String name;
    Image image;

    public Piece(int row, int col, String color, String name) {
        this.x = col * 60;
        this.y = row * 60;
        this.row = row;
        this.col = col;
        this.color = color.toLowerCase();
        this.name = name.toLowerCase();
        this.loadImage();
    }
    
    private void loadImage() {
        try {
            String path = "src/chess/assets/" + this.color + "_" + this.name + ".png";
            this.image = ImageIO.read(new File(path)).getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        } catch (IOException ex) {
            Logger.getLogger(Piece.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Move> findMoves(Piece[][] board) {
        return new ArrayList();
    };
    
    public void updatePiece(int newRow, int newCol) {
        this.row = newRow;
        this.col = newCol;
    };
    
    public void draw(Graphics g) {
        g.drawImage(this.image, x, y, null);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
}
