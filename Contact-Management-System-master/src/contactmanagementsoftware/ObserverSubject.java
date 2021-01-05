/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contactmanagementsoftware;

/**
 *
 * @author Melvin
 */
public interface ObserverSubject {
    public void addObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void notifyAllObservers();
    public void perform(String action);
    public void setButtonState(String action);
    public String getButtonState();
}
