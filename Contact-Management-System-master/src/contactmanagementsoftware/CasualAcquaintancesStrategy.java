/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contactmanagementsoftware;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class CasualAcquaintancesStrategy implements Strategy, Serializable{

    @Override
    public void setText(Acquaintances a) {
        MUI mui = MUI.getInstance();
        mui.setOne(a.getFirstInfo());
        mui.setTwo(a.getSecondInfo());
        mui.setThree(a.getThirdInfo());
    }
    
}
