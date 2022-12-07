package com.sdv.FoodService.Validator;

import com.sdv.FoodService.Services.IUserService;
import com.sdv.FoodService.Models.Request.RegistrationRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    private IUserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return RegistrationRequestModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RegistrationRequestModel user = (RegistrationRequestModel) o;

        if(userService.FindByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "", "This username is already in use");
        }

        if(userService.FindByEmail(user.getEmail()) != null){
            errors.rejectValue("email", "", "This Email is already in use");
        }

        if(userService.FindByPhone(user.getPhone()) != null){
            errors.rejectValue("phone", "", "This phone is already in use");
        }
    }
}
