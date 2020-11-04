package Projet;

import java.awt.*;
import javax.swing.*;
/**
 *
 * @author timli
 */
public class TerrainZoom extends JPanel{
    private final int  line  ;
    private final int column;
    private Fourmiliere fourmilere ;
    private int posX;
    private int posY;
    private Terrain terrain_associé;
        
    public TerrainZoom(Fourmiliere fourmilere , Terrain t) {
        super();
        this.line = 11;
        this.column=11;
        this.fourmilere = fourmilere;
        
        this.terrain_associé=t;
        this.posX=this.terrain_associé.getWidth()/2; 
        this.posY=this.terrain_associé.getHeight()/2;
    }
    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
    
    public void drawFourmis(Graphics g , int pasH , int pasL , int colone , int ligne)
    {
        g.setColor(Color.green);     
        g.fillOval((pasL*(colone)) +((pasL)/2), ((ligne)*pasH) + ((pasH)/2), 9, 9);
    }
    
    public void drawCellules(Graphics g , int pasH , int pasL , int ligne , int colone , int i , int j)
    {
        g.drawRect((colone)*pasL,(ligne)*pasH, pasL,pasH );
            if(fourmilere.getMur(i, j)){
                g.setColor(Color.black);
                g.fillRect((colone)*pasL,(ligne)*pasH, pasL,pasH );
            }
            if(fourmilere.getQteGraines(i, j)!=0){
               int nbGraines=fourmilere.getQteGraines(i, j);
               g.setColor(new Color(255,240-(60*nbGraines),240-(60*nbGraines)));
               g.fillRect((colone)*pasL,(ligne)*pasH, pasL,pasH );
            }
          
    }
     
    @Override
    protected void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        
        this.setBackground(Color.LIGHT_GRAY);
        
        int largeur = this.getWidth();
        int hauteur = this.getHeight();
        
        int pasL=largeur/11;
        int pasH=hauteur/11;
        
        
        
        int x = (this.posX*this.terrain_associé.getColumn())/this.terrain_associé.getWidth();
        int y = (this.posY*this.terrain_associé.getLine())/this.terrain_associé.getHeight();
        System.out.println("cliked : "+x+"/"+y);
        
        g.setColor(Color.yellow);
        int fromx = x-5>=0 ? x-5 : 0;
        
        
        int fromy = y-5>=0 ? y-5 : 0;
        
        int cptW=0, cptH=0;
        
        for(int i=fromx ;i<=fromx+10; i++){
            for(int j=fromy ;j<=fromy+10 ;j++){               
                this.drawCellules(g, pasH, pasL,cptH,cptW, i, j);
                
                if(fourmilere.contientFourmi(i,j)){                                       
                    this.drawFourmis(g, pasH, pasL,cptW,cptH);
                }
                g.setColor(Color.yellow);
                System.out.println(i+" tour "+j);
                cptH++;
            }
            cptW++;
            cptH=0;
        }
        
        
        
        
    }
    
    
    
    
    
    
    
}
