package com.semana6.bankapp.validator;

public class InputValidator {

    public void isNameValid(String input){
        if (input.isBlank()){
            System.out.println("O campo não pode estar vazio");
            return;
        }

        if(input.length() > 30){
            System.out.println("O primeiro nome deve ter, no máximo, 30 letras");
            return;
        }
    }

    public void isEmailValid(String input) {
        if (input.isBlank()){
            System.out.println("O campo não pode estar vazio");
            return;
        }

        if(input.length() > 50){
            System.out.println("O campo deve ter, no máximo, 50 caracteres");
            return;
        }
    }

    public void isPhoneNumberValid(String input) {
        if (input.isBlank()){
            System.out.println("O campo não pode estar vazio");
            return;
        }

        if(input.length() > 12){
            System.out.println("O campo deve ter, no máximo, 12 caracteres");
            return;
        }
    }

    public void isCpfCnpjValid(String input) {
        if (input.isBlank()){
            System.out.println("O campo não pode estar vazio");
            return;
        }

        if(input.length() > 11){
            System.out.println("O campo deve ter, no máximo, 11 caracteres");
            return;
        }
    }
}
