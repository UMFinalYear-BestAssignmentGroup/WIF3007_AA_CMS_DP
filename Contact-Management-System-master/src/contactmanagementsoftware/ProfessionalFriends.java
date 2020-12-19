package contactmanagementsoftware;

import java.io.Serializable;
import java.util.Scanner;

public class ProfessionalFriends extends Acquaintances implements Serializable{
    
    private String CommonInterests;
    public static int numberProF = 0;
    
    ProfessionalFriends(){
        numberProF++;
    }

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
    
    @Override
    public String getFirstInfo() {
        return CommonInterests;
    }

    @Override
    public int checkInformation() {
        return 1;
    }
}
