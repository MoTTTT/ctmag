import java.lang.*;
import java.awt.PopupMenu;
import java.awt.event.*;
class OptionsMenuHandler extends java.lang.Object implements java.awt.event.ActionListener  
{
    private Ctmag ctmag;
    OptionsDialog optionsdialog;
    
    public OptionsMenuHandler(Ctmag f,OptionsDialog o)
    {        
        ctmag = f;
        optionsdialog = o;
    }
    public void actionPerformed(ActionEvent e)
    {
        ctmag.statustextfield.setText("Edit option settings, select \"OK\" to implement changes.");
        optionsdialog.instantiateFields();
        optionsdialog.show();         
    }
}