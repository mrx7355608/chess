/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.ui;

import controllers.BoardController;
import javax.swing.JFrame;
import models.board.BoardModel;
import views.BoardView;

/**
 *
 * @author fawad
 */
public class MainFrame extends JFrame {

    private final int GAME_HEIGHT = 600;
    private final int GAME_WIDTH = 600;

    public MainFrame() {
        BoardModel model = new BoardModel();
        BoardView view = new BoardView(model);
        BoardController controller = new BoardController(model, view);
        
        this.setTitle("Chess");
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(view);
    }

    
}
