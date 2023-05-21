package Controller.SingletonClasses.Handlers;

import Controller.Profile.Elements.Email.EmailI;

public interface HandlerI {
    String concern = null;
    HandlerI successor = null;
    void handle(String concern, EmailI email, String folderName)throws Exception;
}
