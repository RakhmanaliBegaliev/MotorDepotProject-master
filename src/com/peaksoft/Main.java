package com.peaksoft;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


public class Main {
    private static GsonBuilder GSON_BUILDER = new GsonBuilder();
    private static Gson GSON = GSON_BUILDER.setPrettyPrinting().create();
    private static Path URI = Paths.get("./truck.json");
    public static void main(String[] args) {
        /**
        * Welcome guis it is first project of second stage
         * good luck*/
        Truck [] trucks = {
                Truck.creatTruck(1,"Renault Magnum", new Driver(), Status.BASE),
                Truck.creatTruck(2,"Volvo", new Driver(), Status.BASE),
                Truck.creatTruck(3,"DAF XT", new Driver(), Status.BASE),
        };
        String json = GSON.toJson(trucks);

//        System.out.println(json);
        write(json);
//        System.out.println(read());
        Truck [] trucks1 = GSON.fromJson(read(),Truck[].class);
        Driver [] drivers = {
          Driver.creatDriver(1,"Petr", ""),
          Driver.creatDriver(2,"Askar", ""),
          Driver.creatDriver(3,"Uson", ""),
        };


        System.out.println("                Info about Trucks");
        System.out.println(String.format(
                "%2s | %15s | %15s | %15s |",
                "#","Truck","Driver","State"));
        System.out.println("---+-----------------+-----------------+------------------");
        for (Truck truck : trucks1) {
            System.out.println(truck.getDriver()==null ? String.format(
                    "%2d | %15s | %15s | %15s |",
                    truck.getId(),truck.getName(),"",truck.getStatus()):String.format(
                    "%2d | %15s | %15s | %15s |",
                    truck.getId(),truck.getName()," ",truck.getStatus()));
        }
        System.out.println();
        System.out.println("                Info about Drivers");
        System.out.println(String.format(
                "%2s | %15s | %15s |",
                "#","Driver","State"));
        System.out.println("---+-----------------+-----------------+");
        for (Driver driver: drivers){
            System.out.println(driver.getTruck()==null ? String.format(
                    "%2s | %15s | %15s |",
                    driver.getId(),driver.getName(),driver.getStatus()): String.format(
                    "%2s | %15s | %15s |",
                    driver.getId(), driver.getName(), ""));
        }
        }
    public static void write (String json){
        try{
            Files.writeString(URI, json, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static String read(){
        String json = " ";
        int a;
        try{
            FileReader reader = new FileReader(String.valueOf(URI));
            while((a = reader.read())!=-1){
                json+=(char)a;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return json;
    }
}