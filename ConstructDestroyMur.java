
package Projet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 *
 * @author timli
 */

public class ConstructDestroyMur extends MouseAdapter{
    
    private Terrain terrain ;

    public ConstructDestroyMur(Terrain terrain) {
        this.terrain = terrain;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        super.mouseDragged(me);
        if(me.getSource()instanceof Terrain && me.getButton()==3){   
            
            int pixX = me.getX();
            int pixY=me.getY();
            int x = (pixX*this.terrain.getColumn())/this.terrain.getWidth();
            int y =(pixY*this.terrain.getLine())/this.terrain.getHeight();
                         
            if(!this.terrain.getFourmilere().getMur(x+1, y+1)){               
                this.terrain.getFourmilere().setMur(x+1, y+1, true);      
                //faire un test si elle contient une fourmie
            }     
            else{
                this.terrain.getFourmilere().setMur(x+1, y+1,false);               
            }
            this.terrain.repaint();
            
        }
    }
    
    
    
}
