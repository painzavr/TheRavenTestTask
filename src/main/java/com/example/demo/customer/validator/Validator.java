package com.example.demo.customer.validator;

import com.example.demo.customer.customer.Customer;
import com.example.demo.customer.dto.CustomerDTO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public static boolean  validate(CustomerDTO customerDTO){
        return phoneCheck(customerDTO.getPhone()) && emailCheck(customerDTO.getEmail()) && fullNameCheck(customerDTO.getFullName());
    }
    private static boolean phoneCheck(String phone){
        if (phone != null && !phone.isEmpty()) {
            String regex = "\\+\\d{6,14}";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(phone);
            return matcher.matches();
        }else{
            return true;
        }

    }
    private static boolean emailCheck(String email){
        String regex = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    private static boolean fullNameCheck(String fullName){
        String regex = "^\\s*[A-Za-z\\s]{2,50}\\s*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fullName);
        return matcher.matches();
    }
}
