/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antcolonyoptimization;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author boholmni
 */
//Vi skapar fönstret var vi ritar ut hur myrorna traverserar i, en del av delmoment C1.
public class Grafik {
        final BufferedImage image = new BufferedImage(550, 440,
        BufferedImage.TYPE_INT_RGB);
        JPanel canvas = new JPanel() {
 
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
        }
    };
    
    public void drawGrafik() {
    JFrame frame = new JFrame();
    frame.setLayout(new BorderLayout());   // <== make panel fill frame
    frame.add(canvas, BorderLayout.CENTER);
    frame.setSize(600, 500);
    frame.setVisible(true);
    }
            
            
    
}
