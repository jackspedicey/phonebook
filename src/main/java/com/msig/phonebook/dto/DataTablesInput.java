package com.msig.phonebook.dto;

import lombok.Data;

@Data
public class DataTablesInput {
    private int draw;
    private int start;
    private int length;
    private String searchValue;
    private String sortColumn;
    private String sortDirection;
}
