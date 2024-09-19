/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import models.board.BoardModel;
import views.BoardView;

/**
 *
 * @author fawad
 */
public class BoardController {
    private final BoardModel boardModel;
    private final BoardView boardView;
    
    public BoardController(BoardModel model, BoardView view) {
        this.boardModel = model;
        this.boardView = view;
    }
    
    
    
}
