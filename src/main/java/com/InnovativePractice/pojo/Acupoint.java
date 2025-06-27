package com.InnovativePractice.pojo;


import lombok.Data;

@Data
public class Acupoint {


    private String id;

    private String alternativeNames;

    private String functionDesc;

    private byte[] image;

    private String locationDescription;

    private String name;

    private String technique;

    private Meridian meridian;

}