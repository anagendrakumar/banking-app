package com.bank.banking_app.validations;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor
@Component
public class Validations {


    public static boolean isAadhaarValidation(String aadhaar) {
        String regex= "^[2-9]{1}[0-9]{3}\\s[0-9]{4}\\s[0-9]{4}$";
        Pattern p= Pattern.compile(regex);
        Matcher m=p.matcher(aadhaar);
        return m.matches();
    }

    public static boolean isMobileValidation(String phone){
        String regex="^\\d{10}$";
        Pattern p= Pattern.compile(regex);
        Matcher m=p.matcher(phone);
        return m.matches();
    }

    public static boolean isEmailValidation(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern p=Pattern.compile(emailRegex);
        return email !=null && p.matcher(email).matches();
    }

}
