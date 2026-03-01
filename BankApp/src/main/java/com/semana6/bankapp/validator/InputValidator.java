package com.semana6.bankapp.validator;

import com.semana6.bankapp.dto.CustomerDto;

public class InputValidator {

    public String isNameValid(String input){
        if (input.isBlank()){
            return "\nO campo não pode estar vazio\n";
        }

        if(input.length() > 30){
            return "\nO primeiro nome deve ter, no máximo, 30 letras\n";
        }

        return null;
    }

    public String isEmailValid(String input) {
        if (input.isBlank()){
            return "\nO campo não pode estar vazio\n";
        }

        if(input.length() > 50){
            return "\nO campo deve ter, no máximo, 50 caracteres\n";
        }


        return null;
    }

    public String isPhoneNumberValid(String input) {
        if (input.isBlank()){
            return "\nO campo não pode estar vazio\n";
        }

        if(input.length() > 12){
            return "\nO campo deve ter, no máximo, 12 caracteres\n";
        }
        return null;
    }

    public String isCpfCnpjValid(String input) {
        if (input.isBlank()){
            return "\nO campo não pode estar vazio\n";
        }

        if(input.length() > 11){
            return "\nO cpf deve ter, no máximo, 11 letras\n";
        }

        return null;
    }

    public String isPasswordValid(String input) {
        if (input.isBlank()){
            return "\nO campo não pode estar vazio\n";
        }

        if(input.length() < 6){
            return "\nA senha deve ter pelo menos 6 caracteres\n";
        }

        if (input.length() > 10){
            return "\nA senha deve ter, no máximo, 10 caracteres\n";
        }

        return null;
    }

    public String isMenuInputValid(String input) {
        if (input.isBlank()) {
            return "\nO campo não pode ser vazio\n";
        }

        try {
            int intInput = Integer.parseInt(input);

            if (intInput > 6 || intInput <= 0) {
                return "\nSelecione um número de opção válido\n";
            }

        } catch (NumberFormatException e){
            return "\nA opção deve ser um número\n";
        }

        return null;
    }

    public String isLoginValid(CustomerDto objCustomer, String loginEmail, String loginSenha) {
        if (!loginEmail.equals(objCustomer.getEmail())){
            return "\nEmail inválido\n";
        }

        if(!loginSenha.equals(objCustomer.getPassHash())) {
            return "\nSenha inválida\n";
        }

        return null;
    }
}
