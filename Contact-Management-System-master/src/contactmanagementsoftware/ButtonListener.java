/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contactmanagementsoftware;

/**
 *
 * @author Melvin
 */
public class ButtonListener implements Observer{
    MUI mui;
    
    public ButtonListener(MUI mui) {
        this.mui = mui;
    }

    @Override
    public void update(String action) {
        mui.perform(action);
    }
}
