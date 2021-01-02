/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contactmanagementsoftware;

/**
 *
 * @author User
 */
public class PersonalFriendsStrategy implements Strategy{

    @Override
    public void setText(Acquaintances a) {
        MUI mui = MUI.getInstance();
        mui.setOne(a.getThirdInfo());
        mui.setTwo(a.getFirstInfo());
        mui.setThree(a.getSecondInfo());
    }
    
}
