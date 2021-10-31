package com.example.assignment_1;


import java.io.Serializable;

public class Donors implements Serializable {
    private String name;
    private String city;
    private String donation_status;
    private String availability_status;
    private String blood_group;
    private int blood_group_image;

    public Donors(String name,String city,String donation_status,String availability_status,String blood_group,int blood_group_image )
    {
        this.name = name;
        this.city=city;
        this.donation_status=donation_status;
        this.availability_status=availability_status;
        this.blood_group=blood_group;
        this.blood_group_image=blood_group_image;
    }

    public void setName(String name){this.name=name;}
    public void setCity(String city){this.city=city;}
    public void setDonation_status(String donation_status){this.donation_status=donation_status;}
    public void setAvailability_status(String availability_status){this.availability_status=availability_status;}
    public void setBlood_group(String blood_group){this.blood_group=blood_group;}
    public void setBlood_group_image(int blood_group_image){this.blood_group_image=blood_group_image;}

    public String getName(){return name;}
    public String getCity(){return city;}
    public String getDonation_status(){return donation_status;}
    public String getAvailability_status(){return availability_status;}
    public String getBlood_group(){return blood_group;}
    public int getBlood_group_image(){return blood_group_image;}
}
