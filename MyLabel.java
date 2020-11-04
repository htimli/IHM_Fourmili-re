
package Projet;

import javax.swing.JLabel;


public class MyLabel extends JLabel {
    private String prefix = "";
    
    public MyLabel(String prefix) {
        super();
        this.prefix=prefix;
        super.setText(prefix);
        
    }
    
    @Override
    public void setText(String s){
        super.setText(this.prefix+s);
    }
    
    
}
