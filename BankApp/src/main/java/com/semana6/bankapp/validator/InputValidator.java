package com.semana6.bankapp.validator;

import com.semana6.bankapp.dto.AccountDto;


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

        if(!input.contains("@") && !input.contains(".com")){
            return "\nEmail inválido, insira novamente\n";
        }

        return null;
    }

    public String isPhoneNumberValid(String input) {
        if (input.isBlank()){
            return "\nO campo não pode estar vazio\n";
        }

        if(input.trim().length() != 11){
            return "\nO campo deve ter 11 caracteres\n";
        }

        if (input.matches(".*[a-zA-Z]+.*")) {
            return "\nEsse campo não pode conter letras\n";
        }

        return null;
    }

    public String isCpfCnpjValid(String input) {
        if (input.isBlank()){
            return "\nO campo não pode estar vazio\n";
        }

        if(input.length() != 11){
            return "\nO cpf deve ter 11 números\n";
        }

        if (input.matches(".*[a-zA-Z]+.*")) {
            return "\nEsse campo não pode conter letras\n";
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

    public String isDepositValid (String input) {
        try {
            float depositoInt = Float.parseFloat(input);
            if (input.isBlank()) {
                return "\nO campo não pode ser vazio\n";
            }

            if (depositoInt < 0) {
                return "\nInsira um número acima de 0\n";
            }

        } catch (NumberFormatException e) {
            return "\nO valor deve ser um número válido\n";
        }

        return null;
    }

    public String isWithdrawalValid (String input, AccountDto accountDto){
        if (input.isBlank()) {
            return "\nO campo não pode ser vazio\n";
        }

        try {
            float withdrawalFloat = Float.parseFloat(input);

            if (withdrawalFloat >  accountDto.getBalance()) {
                return "\nVocê não tem esse valor na sua conta\n";
            }
        } catch (NumberFormatException e){
            return "\nA opção deve ser um número\n";
        }

        return null;
    }
}
