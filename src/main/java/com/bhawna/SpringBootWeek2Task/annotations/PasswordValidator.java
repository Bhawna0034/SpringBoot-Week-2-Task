package com.bhawna.SpringBootWeek2Task.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordValidation,String> {
    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {

            if(password==null){
                return false;
            }
            else{
                boolean check= isValidPassword(password);
                return check;
            }
        }

        private boolean isValidPassword(String password) {
            boolean hasUppercase=false;
            boolean hasLowercase=false;
            boolean hasSpecialChar=false;
            String specialChars = "!@#$%^&*()-+";

            //check for minimum length
            if(password.length()<10)
                return false;

            for(char c:password.toCharArray()){
                if(Character.isUpperCase(c)){
                    hasUppercase=true;
                }
                else if(Character.isLowerCase(c)){
                    hasLowercase=true;
                }
                else if(specialChars.indexOf(c)>=0){
                    hasSpecialChar=true;
                }
                if(hasUppercase && hasLowercase && hasSpecialChar){
                    return true;
                }
            }
            return hasUppercase && hasLowercase && hasSpecialChar;
       }
}
