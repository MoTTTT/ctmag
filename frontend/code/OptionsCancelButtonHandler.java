import java.lang.*;
import java.awt.event.*;
class OptionsCancelButtonHandler extends java.lang.Object implements java.awt.event.ActionListener  
{
    private OptionsDialog optionsdialog;
    private Ctmag ctmag;
    
    public OptionsCancelButtonHandler(OptionsDialog o, Ctmag c)
    {
        optionsdialog = o;
        ctmag = c;
    }
    
    public void actionPerformed(ActionEvent e)
    {
        optionsdialog.setVisible(false);
        ctmag.statustextfield.setText(" ");        
    }
}