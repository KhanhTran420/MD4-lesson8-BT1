package com.codegym.model;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class User implements Validator {

    private String firstName;
    private String lastName;
    private String number;
    private int age;
    private String email;

    public User() {
    }

    public User(String firstName, String lastName, String number, int age, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.age = age;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
       User user = (User) target;
       String firstName = user.getFirstName();
       String lastName = user.getLastName();
       String number = user.getNumber();
       String email = user.getEmail();
       int age = user.getAge();
        ValidationUtils.rejectIfEmpty(errors, "number", "number.empty");


        if (firstName.length()>45||firstName.length()<1){
            errors.rejectValue("firstName","firstName.length");
        }
        if (lastName.length()>45||lastName.length()<1){
            errors.rejectValue("lastName","lastName.length");
        }
        if (number.length()>11 || number.length()<10){
            errors.rejectValue("number","number.length");
        }
        if (!number.startsWith("0")){
            errors.rejectValue("number", "number.startsWith");
        }
        if (!number.matches("(^$|[0-9]*$)")){
            errors.rejectValue("number", "number.matches");
        }
        if (!email.matches("(^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$)")){
            errors.rejectValue("email","email.matches");
        }
        if (age<18){
            errors.rejectValue("age","age.number");
        }
    }
}
