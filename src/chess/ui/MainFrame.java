/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.ui;

import javax.swing.JFrame;

/**
 *
 * @author fawad
 */
public class MainFrame extends JFrame {
    private final int GAME_HEIGHT = 600;
    private final int GAME_WIDTH = 600;
    
    public MainFrame() {
        this.setTitle("Chess");
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.add(new BoardPanel());
    }
}
