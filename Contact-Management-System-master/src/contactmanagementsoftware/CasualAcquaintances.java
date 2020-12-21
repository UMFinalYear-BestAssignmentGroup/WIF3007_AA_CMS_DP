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

    // Override the setFirstInfo hook method from Acquaintances.java to set the whenWhere information
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
    
    // Override the getFirstInfo hook method from Acquaintances.java to get the whenWhere information
    @Override
    public String getFirstInfo() {
        return WhenWhere;
    }

    // Override the setSecondInfo hook method from Acquaintances.java to set the circumstances information
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
    
    // Override the getSecondInfo hook method from Acquaintances.java to get the circumstances information
    @Override
    public String getSecondInfo() {
        return Circumstances;
    }

    // Override the setThirdInfo hook method from Acquaintances.java to set the otherInfo information
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
    
    // Override the getThirdInfo hook method from Acquaintances.java to get the otherInfo information
    @Override
    public String getThirdInfo() {
        return OtherInfo;
    }

    // Override the abstract method, checkInformation() in the Acquaintances.java to return the number of extra information required which is 3 
    @Override
    public int checkInformation() {
        return 3;
    }
}
