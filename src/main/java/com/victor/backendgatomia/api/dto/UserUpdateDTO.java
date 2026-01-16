package com.victor.backendgatomia.api.dto;

import jakarta.validation.constraints.NotBlank;

public class UserUpdateDTO {
    @NotBlank(message = "O nome é obrigatório")
    private String name;

    private String phone;

    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    public String getPhone(){return phone;}
    public void setPhone(String phone){this.phone = phone;}
}
