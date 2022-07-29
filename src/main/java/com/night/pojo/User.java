package com.night.pojo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {
    private String username;
    private String password;
    private Integer age;
    private String phone;
    private String email;
}
