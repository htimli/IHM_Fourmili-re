package Projet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import jbutton.ButtonIcon;

public class MyFrame extends JFrame {

    private static final ButtonIcon icone_start = new ButtonIcon(ButtonIcon.IconShape.Start, 20, 20);
    private static final ButtonIcon icone_pause = new ButtonIcon(ButtonIcon.IconShape.Pause, 20, 20);
    private JButton quit;
    private JButton start_pause;
    private JButton init;
    private JSpinner spinner;
    protected Fourmiliere fourmiliere;
    private JButton loupe;
    
    private Terrain terrain;
    private TerrainZoom new_terrain;    
    
    public MyFrame() {
        super("Projet");

        //south border
        this.start_pause = new JButton("start", icone_start);
        this.start_pause.addActionListener(new ActionListener() {            
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (ae.getSource() == start_pause) {
                    if (start_pause.getIcon() == icone_start) {
                        start_pause.setIcon(icone_pause);
                    } else {
                        start_pause.setIcon(icone_start);
                    }
                }
            }
        });
        this.quit = new JButton("QUIT");
        this.quit.addActionListener(e -> System.exit(0));
        Box box_south = new Box(BoxLayout.X_AXIS);
        box_south.add(this.start_pause);
        box_south.add(Box.createGlue());
        box_south.add(this.quit);
        this.add(box_south, BorderLayout.SOUTH);

        //east border 
        this.loupe = new JButton("loupe");
        Box box_east = new Box(BoxLayout.Y_AXIS);
        box_east.add(Box.createGlue());
        box_east.add(this.loupe);
        MyLabel nb_graines = new MyLabel("nbGraines:");
        nb_graines.setText("0");
        MyLabel nb_fourmis = new MyLabel("nbFourmis:");
        nb_fourmis.setText("0");
        box_east.add(Box.createRigidArea(new Dimension(0, 50)));
        box_east.add(nb_graines);
        box_east.add(Box.createRigidArea(new Dimension(0, 50)));
        box_east.add(nb_fourmis);
        box_east.add(Box.createGlue());
        this.add(box_east, BorderLayout.EAST);
        
        ButtonListenerZoom list = new ButtonListenerZoom(this);
        this.loupe.addActionListener(list);

        //west border
        Box box_west = new Box(BoxLayout.Y_AXIS);
        this.init = new JButton("Init");
        this.init.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                fourmiliere = new Fourmiliere(fourmiliere.getHauteur(),fourmiliere.getLargeur());
                fourmiliere.initTest();
                terrain.setFourmilere(fourmiliere);
                repaint();
            }
        });
        
        //box_west.add(Box.createGlue());
        box_west.add(this.init);
        
        
        box_west.add(Box.createRigidArea(new Dimension(0, 50)));
        SpinnerModel value
                = new SpinnerNumberModel(100, //initial value  
                        5, //minimum value  
                        100, //maximum value  
                        5); //step  
        
        this.spinner = new JSpinner(value);        
        this.spinner.setMaximumSize(new Dimension(50, 20));
        
        this.spinner.addChangeListener(new ChangeListener() {
        
            @Override
            public void stateChanged(ChangeEvent ce) {
               
                fourmiliere.setHauteur((int)spinner.getValue()); 
                fourmiliere.setLargeur((int)spinner.getValue());
                System.out.println(fourmiliere.getHauteur()); // et la largeur ??oui 
                terrain.setFourmilere(fourmiliere);
                repaint();
            }
        });
        
        Box box_spiner = new Box(BoxLayout.X_AXIS);
        box_spiner.add(new JLabel("size :"));
        box_spiner.add(this.spinner);
        box_west.add(box_spiner);
        box_west.add(Box.createRigidArea(new Dimension(0, 50)));
        
        this.add(box_west, BorderLayout.WEST);

        //CENTER
        //kant hna
        fourmiliere = new Fourmiliere(100, 100);
        fourmiliere.ajouteFourmi(1, 1);        
        fourmiliere.ajouteFourmi(2, 2);        
        fourmiliere.ajouteFourmi(3, 3);
        fourmiliere.ajouteFourmi(4, 4);        
        fourmiliere.ajouteFourmi(5, 5);        
        
        fourmiliere.setMur(1, 2, true);
        fourmiliere.setMur(2, 1, true);
        
        fourmiliere.setQteGraines(3, 1, 1);
        fourmiliere.setQteGraines(3, 2, 2);
        fourmiliere.setQteGraines(3, 3, 3);
        fourmiliere.setQteGraines(3, 4, 4);
        
        terrain = new Terrain(fourmiliere);        
        this.spinner.setValue((int)fourmiliere.getHauteur());
        this.add(terrain, BorderLayout.CENTER);

        //drag 
        ConstructDestroyMur cdm = new ConstructDestroyMur(terrain);
        terrain.addMouseListener(cdm);
        
    }
    
    public Terrain getTerrain() {
        return terrain;
    }
    
    public TerrainZoom getNew_terrain() {
        return new_terrain;
    }
    
    public void setNew_terrain(TerrainZoom new_terrain) {
        this.new_terrain = new_terrain;
    }
    
    public static void main(String argV[]) {
        MyFrame ma_fenetre = new MyFrame();
        ma_fenetre.setDefaultCloseOperation(EXIT_ON_CLOSE);
        ma_fenetre.setPreferredSize(new Dimension(800, 600));
        ma_fenetre.pack();
        ma_fenetre.setVisible(true);
        
    }
    
}
