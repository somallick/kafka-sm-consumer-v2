package com.kafkatech.dto;

import lombok.Data;

@Data
public class CustomerDTO {

    private int id;
    private String name;
    private String email;
    private String contactNo;

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", contactNo='" + contactNo + '\'' +
                '}';
    }
}