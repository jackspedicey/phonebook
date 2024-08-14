package com.msig.phonebook.dto;

import lombok.Data;

@Data
public class APIResponse {

    private boolean success;
    private String message;
    private String data;

}
