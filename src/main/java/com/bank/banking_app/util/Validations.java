package com.bank.banking_app.util;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@NoArgsConstructor
@Data
public class Validations {


    public boolean isAadhaarValidation(String aadhaar){
        String regex= "^[2-9]{1}[0-9]{3}\\s[0-9]{4}\\s[0-9]{4}$";
        Pattern p= Pattern.compile(regex);
        if(aadhaar==null)
            return false;
        Matcher m=p.matcher(aadhaar);
        return m.matches();
    }
}
