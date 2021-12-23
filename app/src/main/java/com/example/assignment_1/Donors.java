package com.example.assignment_1;


import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

public class Donors implements Serializable {
    private String name;
    private String city;
    private String donation_status;
    private String availability_status;
    private String blood_group;
    private int blood_group_image;
    private String id;
    private String contact;

    public Donors(String id,String contact,String name,String city,String donation_status,String availability_status,String blood_group,int blood_group_image )
    {
        this.name = name;
        this.city=city;
        this.donation_status=donation_status;
        this.availability_status=availability_status;
        this.blood_group=blood_group;
        this.blood_group_image=blood_group_image;
        this.id=id;
        this.contact=contact;
    }

    public void setContact(String contact){this.contact=contact;}
    public void  setId(String id){this.id=id;}
    public void setName(String name){this.name=name;}
    public void setCity(String city){this.city=city;}
    public void setDonation_status(String donation_status){this.donation_status=donation_status;}
    public void setAvailability_status(String availability_status){this.availability_status=availability_status;}
    public void setBlood_group(String blood_group){this.blood_group=blood_group;}
    public void setBlood_group_image(int blood_group_image){this.blood_group_image=blood_group_image;}

    public String getContact(){return contact;}
    public String getId(){return id;}
    public String getName(){return name;}
    public String getCity(){return city;}
    public String getDonation_status(){return donation_status;}
    public String getAvailability_status(){return availability_status;}
    public String getBlood_group(){return blood_group;}
    public int getBlood_group_image(){return blood_group_image;}

    public static ArrayList<Donors> load(IDonorDAO dao)
    {
        ArrayList<Donors> donors=new ArrayList<>();
        ArrayList<Hashtable<String, String>> list = dao.load();

        for (Hashtable<String,String> obj : list)
        {
            Donors donor=new Donors(obj.get("id"),obj.get("contact"),obj.get("name"),obj.get("city"),
                    obj.get("donation_status"),obj.get("availability_status"),
                    obj.get("blood_group"),Integer.parseInt(obj.get("blood_group_image")));
            donors.add(donor);
        }
        return donors;
    }

    public static void save(ArrayList<Donors> d,IDonorDAO dao)
    {
        ArrayList<Hashtable<String,String>> listOfH=new ArrayList<>();

        for (Donors donors : d)
        {
            Hashtable<String,String> ht=new Hashtable<>();
            ht.put("id",donors.id);
            ht.put("contact",donors.contact);
            ht.put("name",donors.name);
            ht.put("city",donors.city);
            ht.put("donation_status",donors.donation_status);
            ht.put("availability_status",donors.availability_status);
            ht.put("blood_group",donors.blood_group);
            ht.put("blood_group_image",Integer.toString(donors.blood_group_image));
            listOfH.add(ht);
        }

        dao.saveArr(listOfH);

    }

    public static void deleteAll(IDonorDAO dao){
        dao.deleteAll();
    }
}
