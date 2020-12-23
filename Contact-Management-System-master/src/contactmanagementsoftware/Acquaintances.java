package contactmanagementsoftware;

import java.io.Serializable;
import java.util.Scanner;

public abstract class Acquaintances implements Serializable {

    private String Name;
    private String MobileNo;
    private String Email;
    private String Info1;
    private String Info2;
    private String Info3;
    public static int number = 0;
    private Strategy strategy;

    Acquaintances() {
        number++;
    }

    // Template Method to set the information for each category of acquaintances
    public void setDetails(String name, String mobileNo, String email, String info1, String info2, String info3) {
        setName(name);
        setMobileNo(mobileNo);
        setEmail(email);
        // infoNum is the number of extra informations required by each category of acquaintances
        int infoNum = checkInformation();

        if (infoNum > 0) {
            // For acquaintances that required only one extra information
            setFirstInfo(info1);
        }
        if (infoNum > 1) {
            //For acquaintances that required two extra information
            setSecondInfo(info2);
        }
        if (infoNum > 2) {
            //For acquaintances that required three extra information
            setThirdInfo(info3);
        }
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Scanner reader = new Scanner(System.in);
        if (!name.isEmpty()) {
            this.Name = name;
        } else {
            System.out.println("Enter atleast one character");
            setName(reader.nextLine());
        }
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public String getEmail() {
        return Email;
    }

    public boolean MobileNoChecker(String str) {
        int x;
        for (int j = 0; j < str.length(); j++) {
            x = (int) str.charAt(j);
            if (x < 48 || x > 57) {
                return false;
            }
        }
        return true;
    }

    public void setMobileNo(String MobileNo) {
        Scanner reader = new Scanner(System.in);
        if (MobileNoChecker(MobileNo) && MobileNo.length() >= 6 && MobileNo.length() <= 15) {
            this.MobileNo = MobileNo;
        } else {
            System.out.print("Enter a valid Mobile No of length between 6 and 15 (inclusive): ");
            setMobileNo(reader.next());
        }
    }

    public void setEmail(String Email) {
        Scanner reader = new Scanner(System.in);
        if (Email.contains("@")) {
            this.Email = Email;
        } else {
            System.out.print("Enter a valid Email: ");
            this.setEmail(reader.next());
        }
    }

    // Hook method to set the first information for each category
    // Will be overwrite in subclasses when needed
    public void setFirstInfo(String info) {
        this.Info1 = info;
    }

    //Hook method to get the first information of each category
    // Will be overwrite in subclasses when needed
    public String getFirstInfo() {
        return Info1;
    }

    // Hook method to set the second information for each category
    // Will be overwrite in subclasses when needed
    public void setSecondInfo(String info) {
        this.Info2 = info;
    }

    // Hook method to get the second information of each category
    // Will be overwrite in subclasses when needed
    public String getSecondInfo() {
        return Info2;
    }

    // Hook method to set the third information for each category
    // Will be overwrite in subclasses when needed
    public void setThirdInfo(String info) {
        this.Info3 = info;
    }

    // Hook method to get the third information of each category
    // Will be overwrite in subclasses when needed
    public String getThirdInfo() {
        return Info3;
    }

    // Abstract method that return the number of extra informations required by each category
    // Used in setDetails() template method to determine the number of extra information.
    public abstract int checkInformation();
    
    public void setStrategy(Strategy s){
        this.strategy = s;
    }
    
    public void executeStrategy(){
        System.out.println("Strategy: "+this.getClass());
        strategy.setText(this);
    }
}
