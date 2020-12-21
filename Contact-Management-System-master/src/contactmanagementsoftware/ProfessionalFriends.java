package contactmanagementsoftware;

import java.io.Serializable;
import java.util.Scanner;

public class ProfessionalFriends extends Acquaintances implements Serializable{
    
    private String CommonInterests;
    public static int numberProF = 0;
    
    ProfessionalFriends(){
        numberProF++;
    }

    // Override the setFirstInfo hook method from Acquaintances.java to set the CommonInterests information
    @Override
    public void setFirstInfo(String CommonInterests) {
        Scanner reader = new Scanner(System.in);
        if(!CommonInterests.isEmpty())
            this.CommonInterests = CommonInterests;
        else{
            System.out.println("Enter at least one character");
            setFirstInfo(reader.nextLine());
        }
    }
    
    // Override the getFirstInfo hook method from Acquaintances.java to get the CommonInterests information
    @Override
    public String getFirstInfo() {
        return CommonInterests;
    }

    // Override the abstract method, checkInformation() in the Acquaintances.java to return the number of extra information required which is 1
    @Override
    public int checkInformation() {
        return 1;
    }
}
