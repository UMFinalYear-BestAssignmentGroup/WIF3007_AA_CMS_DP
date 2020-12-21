package contactmanagementsoftware;

import java.io.Serializable;
import java.util.Scanner;

public class PersonalFriends extends Acquaintances implements Serializable{
    private String AContext;
    private String ADate;
    private String Events;
    public static int numberPerF = 0;
    
    PersonalFriends(){
        numberPerF++;
    }

    // Override the setFirstInfo hook method from Acquaintances.java to set the AContext information
    @Override
    public void setFirstInfo(String aContext) {
        Scanner reader = new Scanner(System.in);
        if(!aContext.isEmpty())
            this.AContext = aContext;
        else{
            System.out.println("Enter at least one character");
            setFirstInfo(reader.nextLine());
        }
    }
    
    // Override the getFirstInfo hook method from Acquaintances.java to get the AContext information
    @Override
    public String getFirstInfo() {
        return AContext;
    }

    // Override the setSecondInfo hook method from Acquaintances.java to set the ADate information
    @Override
    public void setSecondInfo(String aDate) {
        this.ADate = aDate;
    }

    // Override the getSecondInfo hook method from Acquaintances.java to get the ADate information
    @Override
    public String getSecondInfo() {
        return ADate;
    }

    // Override the setThirdInfo hook method from Acquaintances.java to set the Events information
    @Override
    public void setThirdInfo(String events) {
        Scanner reader = new Scanner(System.in);
        if(!events.isEmpty())
            this.Events = events;
        else{
            System.out.println("Enter at least one character");
            setThirdInfo(reader.nextLine());
        }
    }
    
     // Override the getThirdInfo hook method from Acquaintances.java to get the Events information
    @Override
    public String getThirdInfo() {
        return Events;
    }

    // Override the abstract method, checkInformation() in the Acquaintances.java to return the number of extra information required which is 3
    @Override
    public int checkInformation() {
        return 3;
    }
}
