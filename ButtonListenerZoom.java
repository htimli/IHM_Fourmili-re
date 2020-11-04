/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projet;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

/**
 *
 * @author timli
 */
public class ButtonListenerZoom implements ActionListener{
    private MyFrame fenetre;

        public ButtonListenerZoom(MyFrame f) {
            this.fenetre = f;
        }
    

        @Override
        public void actionPerformed(ActionEvent ae) {
            
            JFrame f = new JFrame("zoom");
            fenetre.setNew_terrain( new TerrainZoom(fenetre.fourmiliere ,fenetre.getTerrain())); 
            MouseListenerZoom ml = new MouseListenerZoom(fenetre.getNew_terrain());
            fenetre.getTerrain().addMouseListener(ml);
            f.add(fenetre.getNew_terrain());
            f.setVisible(true);
            f.setPreferredSize(new Dimension(800,800));
            
        }
        public class MouseListenerZoom extends MouseAdapter{
            private TerrainZoom terrainZoom;

            public MouseListenerZoom(TerrainZoom terrainZoom) {
                this.terrainZoom = terrainZoom;
            }

            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me); 
                this.terrainZoom.setPosX(me.getX());
                this.terrainZoom.setPosY(me.getY());
                terrainZoom.repaint();

                //System.out.println("cliked : "+me.getX()+"/"+me.getY());
            }
        
         
    }
}
