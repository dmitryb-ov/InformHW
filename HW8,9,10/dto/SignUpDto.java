package com.example.demo.dto;

import com.example.demo.util.FieldMatch;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@FieldMatch.List({
        @FieldMatch(first = "password", second = "repeatPassword", message = "The password fields must match")
})
@Data
public class SignUpDto {
    @NotBlank(message = "Поле не может быть пустым")
    private String name;
    @NotBlank(message = "Поле не может быть пустым")
    private String secondName;
    @NotBlank(message = "Поле не может быть пустым")
    @Pattern(regexp = "[A-Za-z0-9]+", message = "Плохой пароль")
    private String password;
    @NotBlank(message = "Поле не может быть пустым")
    private String repeatPassword;
    @NotBlank(message = "Поле не может быть пустым")
    private String bDate;
}
