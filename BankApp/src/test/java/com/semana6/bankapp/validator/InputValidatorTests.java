package com.semana6.bankapp.validator;

import com.semana6.bankapp.dto.AccountDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InputValidatorTests {
    InputValidator validator = new InputValidator();
    AccountDto account = new AccountDto();


    //Name tests
    @Test
    void returnErrorIfNameIsBlank() {
        String error = validator.isNameValid(" ");
        assertNotNull(error);
    }

    @Test
    void returnErrorIfNameHasNumbers() {
        String error = validator.isNameValid("Gr4zi3ll4");
        assertNotNull(error);
    }

    @Test
    void returnErrorIfNameLengthIsBiggerThanThirty() {
        String error = validator.isNameValid("Graziella Souza Martins Silva Marinho");
        assertNotNull(error);
    }

    @Test
    void returnNullIfIsValid(){
        String error = validator.isNameValid("Graziella");
        assertNull(error);
    }

    //Email tests
    @Test
    void returnErrorIfEmailIsBlank() {
        String error = validator.isEmailValid(" ");
        assertNotNull(error);
    }

    @Test
    void returnErrorIfEmailLengthIsBiggerThanFifty() {
        String error = validator.isEmailValid("graziellasouzamartinsmarinhosantosoliveiramendes@gmail.com");
        assertNotNull(error);
    }

    @Test
    void returnErrorIfEmailDoesntHaveAtAndDotCom(){
        String error = validator.isEmailValid("graziellagmail.co");
        assertNotNull(error);
    }

    @Test
    void returnNullIfEmailIsValid(){
        String error = validator.isEmailValid("graziella@gmail.com");
        assertNull(error);
    }

    //Phone tests
    @Test
    void returnErrorIfPhoneIsBlank() {
        String error = validator.isPhoneNumberValid(" ");
        assertNotNull(error);
    }

    @Test
    void returnErrorIfPhoneLengthIsntEleven() {
        String error = validator.isPhoneNumberValid("1999999999");
        assertNotNull(error);
    }

    @Test
    void returnErrorIfPhoneHasALetter() {
        String error = validator.isPhoneNumberValid("199999999as");
        assertNotNull(error);
    }

    @Test
    void returnNullIfPhoneIsValid(){
        String error = validator.isPhoneNumberValid("19999999999");
        assertNull(error);
    }

    //CpfCnpj tests
    @Test
    void returnErrorIfCpfCnpjIsBlank() {
        String error = validator.isCpfCnpjValid(" ");
        assertNotNull(error);
    }

    @Test
    void returnErrorIfCpfCnpjLengthIsntEleven() {
        String error = validator.isCpfCnpjValid("999999999");
        assertNotNull(error);
    }

    @Test
    void returnErrorIfCpfCnpjHasALetter() {
        String error = validator.isCpfCnpjValid("999999999ab");
        assertNotNull(error);
    }

    @Test
    void returnNullIfCpfCnpjIsValid(){
        String error = validator.isCpfCnpjValid("99999999999");
        assertNull(error);
    }

    //Password tests
    @Test
    void returnErrorIfPasswordIsBlank() {
        String error = validator.isPasswordValid(" ");
        assertNotNull(error);
    }

    @Test
    void returnErrorIfPasswordLengthIsSmallerThanSix() {
        String error = validator.isPasswordValid("Senha");
        assertNotNull(error);
    }

    @Test
    void returnErrorIfPasswordLengthIsBiggerThanTen() {
        String error = validator.isPasswordValid("SenhaGraziella");
        assertNotNull(error);
    }

    @Test
    void returnNullIfPasswordIsValid(){
        String error = validator.isPasswordValid("SenhaGra");
        assertNull(error);
    }

    //MenuInput tests
    @Test
    void returnErrorIfMenuInputIsBlank() {
        String error = validator.isMenuInputValid(" ");
        assertNotNull(error);
    }

    @Test
    void returnErrorIfMenuInputIsNotANumber() {
        String error = validator.isMenuInputValid("abc");
        assertNotNull(error);
    }

    @Test
    void returnErrorIfMenuInputLengthIsSmallerThanOne() {
        String error = validator.isMenuInputValid("-10");
        assertNotNull(error);
    }

    @Test
    void returnErrorIfMenuInputLengthIsBiggerThanSix() {
        String error = validator.isMenuInputValid("12");
        assertNotNull(error);
    }

    @Test
    void returnNullIfMenuInputIsValid(){
        String error = validator.isMenuInputValid("4");
        assertNull(error);
    }

    //Deposit tests
    @Test
    void returnErrorIfDepositIsBlank() {
        String error = validator.isDepositValid(" ");
        assertNotNull(error);
    }

    @Test
    void returnErrorIfDepositIsntANumber() {
        String error = validator.isDepositValid("10ab");
        assertNotNull(error);
    }

    @Test
    void returnErrorIfDepositValueIsSmallerThanOne() {
        String error = validator.isDepositValid("-10");
        assertNotNull(error);
    }

    @Test
    void returnNullIfDepositIsValid(){
        String error = validator.isDepositValid("100");
        assertNull(error);
    }

    //Withdrawl tests
    @Test
    void returnErrorIfWithdrawIsBlank() {
        String error = validator.isWithdrawlValid(" ", account);
        assertNotNull(error);
    }

    @Test
    void returnErrorIfWithdrawlIsntANumber() {
        String error = validator.isWithdrawlValid("10ab", account);
        assertNotNull(error);
    }

    @Test
    void returnErrorIfWithdrawlIsBiggerThanBalance() {
        account.setBalance(50);
        String error = validator.isWithdrawlValid("100", account);
        assertNotNull(error);
    }

    @Test
    void returnNullIfWithdrawlIsValid(){
        account.setBalance(100);
        String error = validator.isWithdrawlValid("50", account);
        assertNull(error);
    }
}
