/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contactmanagementsoftware;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public class AcquaintancesFactory {
    public AcquaintancesFactory(){
        
    }
    
    public Acquaintances Factory(int type) {
        switch (type){
            case 0: 
                return new PersonalFriends();
            case 1:
                return new Relatives();
            case 2:
                return new ProfessionalFriends();
            case 3:
                return new CasualAcquaintances();
        }
        return null;
    }
    
    public Acquaintances Factory2(int type, String Name, String Mobile, String Email, String One, String Two, String Three) {
        switch (type){
            case 0: 
                PersonalFriends perF = new PersonalFriends();
                perF.setDetails(Name, Mobile, Email, Two, Three, One);
                return perF;
            case 1:
                Relatives rel = new Relatives();
                rel.setDetails(Name, Mobile, Email, One, Two, null);
                return rel;
            case 2:
                ProfessionalFriends proF = new ProfessionalFriends();
                proF.setDetails(Name, Mobile, Email, One, null, null);
                return proF;
            case 3:
                CasualAcquaintances ca = new CasualAcquaintances();
                ca.setDetails(Name, Mobile, Email, One, Two, Three);
                return ca;
        }
        return null;
    }
    
    public Acquaintances existingAcquaintances(int type, ArrayList<ArrayList<Acquaintances>> a, int num){
        Acquaintances ac;
        switch (type){
            case 0: 
                ac = (PersonalFriends) a.get(type).get(num);
                return ac;
            case 1:
                ac = (Relatives) a.get(type).get(num);
                return ac;
            case 2:
                ac = (ProfessionalFriends) a.get(type).get(num);
                return ac;
            case 3:
                ac = (CasualAcquaintances) a.get(type).get(num);
                return ac;
        }
        return null;
    }
}
