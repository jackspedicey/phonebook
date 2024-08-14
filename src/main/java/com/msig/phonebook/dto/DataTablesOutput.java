package com.msig.phonebook.dto;

import lombok.Data;

import java.util.List;

@Data
public class DataTablesOutput<T> {
    private int draw;
    private long recordsTotal;
    private long recordsFiltered;
    private List<T> data;
}
