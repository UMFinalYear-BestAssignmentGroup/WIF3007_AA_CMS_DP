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
    
    @Override
    public String getFirstInfo() {
        return AContext;
    }


    @Override
    public void setSecondInfo(String aDate) {
        this.ADate = aDate;
    }

    @Override
    public String getSecondInfo() {
        return ADate;
    }

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
    
    @Override
    public String getThirdInfo() {
        return Events;
    }

    @Override
    public int checkInformation() {
        return 3;
    }
}
