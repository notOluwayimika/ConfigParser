package org.example;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConfigParser {
    private String fileName;
    private Map<String,String> configMap;

    public ConfigParser() {
        this.fileName="config.txt";
    }

    public ConfigParser(String environment) {

        if (environment.equals("Production")) {
            this.fileName = "config" + environment + ".txt";
        } else if (environment.equals("Staging")) {
            this.fileName = "config" + environment + ".txt";
        } else if (environment.equals("Development")) {
            this.fileName = "config" + environment + ".txt";
        }
    }

    public Map<String,String> parse(String configFile) throws FileNotFoundException {
        System.out.println("ready to read");
        HashMap<String,String> map = new HashMap<>();
        String line;
        try(BufferedReader reader = new BufferedReader(new FileReader(configFile))) {
            while((line = reader.readLine())!=null){
                String[] keyValuePair = line.split("=",2);
                if(keyValuePair.length>1){
                    String key = keyValuePair[0];
                    String value = keyValuePair[1];
                    map.putIfAbsent(key,value);
                } else {
//                    System.out.println("Nothing to map  here: "+line);
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        } ;
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            System.out.println(entry.getKey() + "=" + entry.getValue().toString());
//        }
        this.configMap = map;
        return map;
    }

    public String getFileName() {
        return fileName;
    }

    public String get(String key) {
        String newKey;
        if(key.contains(".")){
            try {
                newKey = key.substring(key.indexOf(".")+1);
                for(Map.Entry<String,String> entry: this.configMap.entrySet()){
                    if(entry.getKey().equals(newKey)){
                        return entry.getValue();
                    }}
            }catch (StringIndexOutOfBoundsException e){
                System.out.println("String index out of bounds. String length: " + key.length());
            }

        }else{
            for(Map.Entry<String,String> entry: this.configMap.entrySet()){
                if(entry.getKey().equals(key)){
                    return entry.getValue();
                }
            }
        }

        return "Key doesn't exist";
    }
}
