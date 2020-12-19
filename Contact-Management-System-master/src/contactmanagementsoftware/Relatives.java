package contactmanagementsoftware;

import java.io.Serializable;
import java.util.Scanner;

public class Relatives extends Acquaintances implements Serializable{
    private String BDate;
    private String LDate;
    public static int numberRel = 0;
    
    Relatives(){
        numberRel++;
    }

    @Override
    public void setFirstInfo(String bDate) {
        this.BDate = bDate;
    }
    
    @Override
    public String getFirstInfo() {
        return BDate;
    }

    @Override
    public void setSecondInfo(String lDate) {
        this.LDate = lDate;
    }
    
    @Override
    public String getSecondInfo() {
        return LDate;
    }

    @Override
    public int checkInformation() {
        return 2;
    }
}
