/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcampusapi.model;

/**
 *
 * @author ranet
 */
public class ErrorMessage {
    private String message; 
    private int code; 
    
    public ErrorMessage(){
        
    }
    
    public ErrorMessage(String message, int code){
        this.message= message; 
        this.code= code; 
        
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
    
    
    
}
