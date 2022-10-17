package org.example;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ConfigParser config;
        if(args.length==0){
            System.out.println("up and running");
            config = new ConfigParser();
        } else{
            System.out.println("up and running: "+ args[0]);
            config = new ConfigParser(args[0]);
        }

        String fileName = config.getFileName();
        config.parse(fileName);
        String dbName = config.get("dbname");
        System.out.println(dbName);
        String stagingDBName = config.get("application.name");
        System.out.println(stagingDBName);


        }
    }
