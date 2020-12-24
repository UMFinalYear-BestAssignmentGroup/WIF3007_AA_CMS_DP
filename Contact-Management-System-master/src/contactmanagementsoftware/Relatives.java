package contactmanagementsoftware;

import java.io.Serializable;

public class Relatives extends Acquaintances implements Serializable{
    private String BDate;
    private String LDate;
    public static int numberRel = 0;
    
    Relatives(){
        strategy = new RelativesStrategy();
        numberRel++;
    }

    // Override the setFirstInfo hook method from Acquaintances.java to set the BDate information
    @Override
    public void setFirstInfo(String bDate) {
        this.BDate = bDate;
    }
    
    // Override the getFirstInfo hook method from Acquaintances.java to get the BDate information
    @Override
    public String getFirstInfo() {
        return BDate;
    }

    // Override the setSecondInfo hook method from Acquaintances.java to set the LDate information
    @Override
    public void setSecondInfo(String lDate) {
        this.LDate = lDate;
    }
    
    // Override the getSecondInfo hook method from Acquaintances.java to get the LDate information
    @Override
    public String getSecondInfo() {
        return LDate;
    }

    // Override the abstract method, checkInformation() in the Acquaintances.java to return the number of extra information required which is 2
    @Override
    public int checkInformation() {
        return 2;
    }
}
