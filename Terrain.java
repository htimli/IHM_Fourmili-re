package Projet;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Iterator;
import javax.swing.JPanel;


public class Terrain extends JPanel{
    private int line;
    private int column;
    private Fourmiliere fourmilere;


    public Terrain(Fourmiliere fourmilere) {
        super();
        this.line = fourmilere.getHauteur();
        this.column = fourmilere.getLargeur();
        this.fourmilere=fourmilere;
        this.setPreferredSize(new Dimension(600,600));
       
    }
    public void drawFourmis(Graphics g , int pasL , int pasH){        
        Iterator<Fourmi> ItFourmi = this.fourmilere.getLesFourmis().iterator();
        while (ItFourmi.hasNext()) {
            Fourmi f = ItFourmi.next();
            int x = f.getX();
            int y = f.getY();
            if(this.fourmilere.contientFourmi(x, y)){ 
                if(f.porte())
                    g.setColor(Color.blue);
                else
                    g.setColor(Color.green);     
                
                g.fillOval((pasL*(y-1)) , ((x-1)*pasH), 5,5); 
            }

        }
    }
    public void drawCellules(Graphics g , int pasL , int pasH , int ligne , int colone)
    {   
        
        if(fourmilere.getMur(colone, ligne)){
            g.setColor(Color.black);
            g.fillRect((colone-1)*pasL,(ligne-1)*pasH, pasL,pasH );
        }else{
            if(fourmilere.getQteGraines(colone, ligne)!=0){
               int nbGraines=fourmilere.getQteGraines(colone, ligne);
               g.setColor(new Color(255,240-(60*nbGraines),240-(60*nbGraines)));
               
            }else{
                g.setColor(Color.WHITE);
            }
            
            g.fillRect((colone-1)*pasL,(ligne-1)*pasH, pasL,pasH );
        }
        
        g.setColor(Color.yellow);
        g.drawRect((colone-1)*pasL,(ligne-1)*pasH, pasL,pasH );
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        System.out.println("panel L: "+ this.getWidth() +" H"+ this.getHeight());
        System.out.println("Loactaion x"+this.getLocation().x +"Y:"+this.getLocation().y);
        
        
        this.setBackground(Color.LIGHT_GRAY);
        
        
        int pasL = this.getWidth()/this.fourmilere.getLargeur();
        int pasH = this.getHeight()/this.fourmilere.getHauteur();
        
        
       
        
        for(int i=1 ;i<= this.fourmilere.getHauteur(); i++){
            for(int j=1 ;j<= this.fourmilere.getLargeur() ;j++){   
                
                this.drawCellules(g, pasL, pasH,j,i);   
                
                if(fourmilere.contientFourmi(i,j)){  
                    
                    this.drawFourmis(g, pasL, pasH);
                }
                
            }
        }
                    
    }

    public Fourmiliere getFourmilere() {
        return fourmilere;
    }

    public void setFourmilere(Fourmiliere fourmilere) {
        this.fourmilere = fourmilere;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }
    
    
    
    
    
    
}
