package contactmanagementsoftware;

import java.io.Serializable;
import java.util.Scanner;

public class CasualAcquaintances extends Acquaintances implements Serializable{
    private String WhenWhere;
    private String Circumstances;
    private String OtherInfo;
    public static int numberCA = 0;
    
    CasualAcquaintances(){
        numberCA++;
    }

    @Override
    public void setFirstInfo(String whenWhere) {
        Scanner reader = new Scanner(System.in);
        if(!whenWhere.isEmpty())
            this.WhenWhere = whenWhere;
        else{
            System.out.println("Enter atleast one character");
            setFirstInfo(reader.nextLine());
        }
    }
    
    @Override
    public String getFirstInfo() {
        return WhenWhere;
    }

    @Override
    public void setSecondInfo(String circumstances) {
        Scanner reader = new Scanner(System.in);
        if(!circumstances.isEmpty())
            this.Circumstances = circumstances;
        else{
            System.out.println("Enter atleast one character");
            setSecondInfo(reader.nextLine());
        }
    }
    
    @Override
    public String getSecondInfo() {
        return Circumstances;
    }

    @Override
    public void setThirdInfo(String otherInfo) {
        Scanner reader = new Scanner(System.in);
        if(!otherInfo.isEmpty())
            this.OtherInfo = otherInfo;
        else{
            System.out.println("Enter atleast one character");
             setThirdInfo(reader.nextLine());
        }
    }
    
    @Override
    public String getThirdInfo() {
        return OtherInfo;
    }

    @Override
    public int checkInformation() {
        return 3;
    }
}
