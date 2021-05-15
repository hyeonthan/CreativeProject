package DataSetControl;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

import com.opencsv.CSVReader;

import DTO.BeachDTO;
import DTO.DestinationDTO;
import DTO.ForestLodgeDTO;
import DTO.ParkingLotsDTO;
import DTO.ToiletDTO;
import DTO.TouristSpotDTO;

public class LoadFromExcel {
    public static ArrayList<BeachDTO> runBeach(String path, String encoding) {
        String[] line;
        
        int cnt=0;
        String bc = "bc";

        String code;
        String name;
        String introduction;
        String Do;
        String city;
        String address;
        double latitude;
        double longitude;
        String phone_num;
        String home_page;
        String opening_period;
        String available_time;
        String amenities;
        ArrayList<BeachDTO> list = new ArrayList<BeachDTO>();
        
        try {
                CSVReader br = new CSVReader(new InputStreamReader(new FileInputStream(path), encoding));
                line = br.readNext();
                String[] tempLine = line;
                while ((line = br.readNext()) != null) {

                    if(cnt < 10) {
                        bc = "bc00";
                    }
                    else if (cnt >= 10 && cnt < 100){
                        bc = "bc0";
                    }
                    else {
                        bc = "bc";
                    }
                    bc = bc + Integer.toString(cnt++); 
                    code = bc;
                    name = line[1];
                    introduction = line[0];

                    String[] addressSplit = line[2].split(" ");
                    Do = addressSplit[0];

                    int index = 0;
                    String addressSum = "";
                    if(Do.equals("부산광역시") || Do.equals("울산광역시") || Do.equals("인천광역시") || Do.equals("제주특별자치도")){
                        city = "";
                        index = 1;
                    }
                    else {
                        city = addressSplit[1];
                        index = 2;
                    }
                    for(int i = index; i < addressSplit.length; i++){
                        addressSum += addressSplit[i];
                        if((i + 1) != addressSplit.length){
                            addressSum += " ";
                        }
                    }
                    address = addressSum;
                    latitude = Double.parseDouble(line[3]);
                    longitude = Double.parseDouble(line[4]);
                    phone_num = line[5];
                    home_page = line[6];
                    opening_period = line[7];
                    available_time = line[8];
                    
                    String amenitiesSum = "";
                    for(int i = 9; i < line.length; i++){
                        if(line[i].equals("N")){
                            continue;
                        }
                        amenitiesSum += tempLine[i];
                        if((i+1) != line.length){
                            amenitiesSum += ",";
                        }
                    }
                 
                        amenities = amenitiesSum;
                    
                    // System.out.println(code + " " + name + " " + introduction + " " + Do + " " + city + " " + address + " " + latitude + " " + longitude + " " + phone_num + " " + home_page
                    // + " " + opening_period + " " + available_time + " " + amenities);
                    
                    BeachDTO beachDTO = new BeachDTO(code, name, introduction, Do, city, address, latitude, longitude, phone_num, home_page, opening_period, available_time, amenities);
                    list.add(beachDTO);
                }



            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } 
        return list;
    }
    public static ArrayList<ForestLodgeDTO> runForestLodge(String path, String encoding) {
        String[] line;
        
        int cnt=0;
        String fl = "fl";
        String code;
        String name;
        String Do;
        String city;
        String address;
        String phone_num;
        String home_page;
        double latitude;
        double longitude;
        String amenities;
        String capacity_people;
        String enter_fee;
        String accommodation;
        ArrayList<ForestLodgeDTO> list = new ArrayList<ForestLodgeDTO>();
        
        try {
                CSVReader br = new CSVReader(new InputStreamReader(new FileInputStream(path), encoding));
                line = br.readNext();
                while ((line = br.readNext()) != null) {
                    if(cnt < 10) {
                        fl = "fl00";
                    }
                    else if (cnt >= 10 && cnt < 100){
                        fl = "fl0";
                    }
                    else {
                        fl = "fl";
                    }
                    fl = fl + Integer.toString(cnt++); 
                    code = fl;
                    name = line[0];

                    String[] addressSplit = line[5].split(" ");
                    Do = addressSplit[0];
                    int index = 0;
                    String addressSum = "";
                    if(Do.equals("부산광역시") || Do.equals("울산광역시") || Do.equals("인천광역시") || Do.equals("제주특별자치도") || Do.equals("대구광역시") || Do.equals("세종특별자치시")){
                        city = "";
                        index = 1;
                    }
                    else {
                        city = addressSplit[1];
                        index = 2;
                    }
                    for(int i = index; i < addressSplit.length; i++){
                        addressSum += addressSplit[i];
                        if((i + 1) != addressSplit.length){
                            addressSum += " ";
                        }
                    }
                    address = addressSum;
                    latitude = Double.parseDouble(line[8]);
                    longitude = Double.parseDouble(line[9]);
                    phone_num = line[6];
                    home_page = line[7];
                    enter_fee = line[2];
                    amenities = line[4];
                    capacity_people = line[1];
                    accommodation = line[3];
                    // System.out.println(code + " " + name + " " + Do + " " + city + " " + address + " " + latitude + " " + longitude + " " + phone_num + " " + home_page
                    // + " " + enter_fee + " " + amenities);
                    
                    // 휴양림 DTO 생성 후 List 반환
                    ForestLodgeDTO forestLodgeDTO = new ForestLodgeDTO(code ,name, Do, city, address, phone_num, latitude, longitude, amenities, capacity_people, enter_fee, accommodation, home_page);
                    list.add(forestLodgeDTO);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } 
        return list;
    }
    public static ArrayList<TouristSpotDTO> runTouristSpot(String path, String encoding) {

        String[] line;
        
        int cnt=0;
        String ts = "ts";
        String code;
        String name;
        String introduction;
        String phone_num;
        String Do;
        String city;
        String address;
        double latitude;
        double longitude;
        String amenities;
        int possibleParking;
        String managementAgency;
        ArrayList<TouristSpotDTO> list = new ArrayList<TouristSpotDTO>();
        
        try {
                CSVReader br = new CSVReader(new InputStreamReader(new FileInputStream(path), encoding));
                line = br.readNext();
                while ((line = br.readNext()) != null) {
                    if(cnt < 10) {
                        ts = "ts00";
                    }
                    else if (cnt >= 10 && cnt < 100){
                        ts = "ts0";
                    }
                    else {
                        ts = "ts";
                    }
                    ts = ts + Integer.toString(cnt++); 
                    code = ts;
                    if(code.equals("ts613")){
                        break; 
                    }
                    name = line[0];
                    String[] addressSplit;
                 
                    if(line[2].equals("")){
                        addressSplit = line[1].split(" ");
                    }
                    else{
                        addressSplit = line[2].split(" ");

                    }
                    Do = addressSplit[0];
                   
                    int index = 0;
                    String addressSum = "";
                    if(Do.equals("부산광역시") || Do.equals("울산광역시") || Do.equals("인천광역시") || Do.equals("제주특별자치도") || Do.equals("대구광역시") || Do.equals("세종특별자치시")){
                        city = "";
                        index = 1;
                    }
                    else {
                        city = addressSplit[1];
                        index = 2;
                    }
                    for(int i = index; i < addressSplit.length; i++){
                        addressSum += addressSplit[i];
                        if((i + 1) != addressSplit.length){
                            addressSum += " ";
                        }
                    }
                    address = addressSum;
                    latitude = Double.parseDouble(line[3]);
                    longitude = Double.parseDouble(line[4]);
                    amenities = line[5];
                    possibleParking = Integer.parseInt(line[6]);
                    introduction = line[7];
                    phone_num = line[8];
                    managementAgency = line[9];
                    //System.out.println(code + " " + name + " " + introduction + " " + phone_num + " " + Do + " " + city + " " + address + " " +latitude + " " + longitude + " " + amenities + " " + possibleParking + " " + managementAgency);
                     
                    // 관광지 DTO 생성 후 List 반환
                    TouristSpotDTO touristSpotDTO = new TouristSpotDTO(code, name, introduction, phone_num, Do, city, address, latitude, longitude, amenities, possibleParking, managementAgency);
                    list.add(touristSpotDTO);
                }
           


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } 
        return list;
    }
    public static ArrayList<ParkingLotsDTO> runParkingLots(String path, String encoding) {
        String[] line;
        
        int cnt=0;
        String pl = "pl";
        String code;
        String name;
        String Do;
        String city;
        String address;
        int size;
        String opening_days;
        String weekdays_opening_time;
        String sat_opening_time;
        String holiday_opening_time;
        String fee;
        String phone_num;
        double latitude;
        double longitude;
        ArrayList<ParkingLotsDTO> list = new ArrayList<ParkingLotsDTO>();
        
        try {
                CSVReader br = new CSVReader(new InputStreamReader(new FileInputStream(path), encoding));
                line = br.readNext();
                line = br.readNext();
                while ((line = br.readNext()) != null) {
                    if(line[13].equals("") || line[14].equals("") || line[13].equals("null") || line[14].equals("null")){ // 위경도 공백 or null일 시 continue
                        continue;
                    }
                    if(cnt < 10) {
                        pl = "pl0000";
                    }
                    else if (cnt >= 10 && cnt < 100){
                        pl = "pl000";
                    }
                    else if (cnt >= 100 && cnt < 1000){
                        pl = "pl00";
                    }
                    else if (cnt >= 1000 && cnt < 10000){
                        pl = "pl0";
                    }
                    else {
                        pl = "pl";
                    }
                    pl = pl + Integer.toString(cnt++); 
                    code = pl;
                    name = line[0];
                    String[] addressSplit;
                    if(line[2].equals("") || line[2].equals("null")){
                        addressSplit = line[1].split(" ");
                    }
                    else{
                        addressSplit = line[2].split(" ");

                    }
                    Do = addressSplit[0];
                    int index = 0;
                    String addressSum = "";
                    if(Do.equals("부산광역시") || Do.equals("울산광역시") || Do.equals("인천광역시") || Do.equals("제주특별자치도") || Do.equals("대구광역시") || Do.equals("세종특별자치시")){
                        city = "";
                        index = 1;
                    }
                    else {
                        city = addressSplit[1];
                        index = 2;
                    }
                    for(int i = index; i < addressSplit.length; i++){
                        addressSum += addressSplit[i];
                        if((i + 1) != addressSplit.length){
                            addressSum += " ";
                        }
                    }
                    address = addressSum;
                    latitude = Double.parseDouble(line[13]);
                    longitude = Double.parseDouble(line[14]);
                    size = Integer.parseInt(line[3]);
                    phone_num = line[12];
                    opening_days = line[4];
                    weekdays_opening_time = line[5] + "~"  + line[6];
                    sat_opening_time = line[7] + "~"  + line[8];
                    holiday_opening_time = line[9] + "~"  + line[10];
                    fee = line[11];
                    
                    // System.out.println(code + " " + name + " " + Do + " " + city + " " + address + " " + latitude + " " + longitude + " " + phone_num + " " + size
                    // + " " + fee + " " + opening_days + " " + weekdays_opening_time + " " + sat_opening_time + " " + holiday_opening_time);
                    
                    // 주차장 DTO 생성 후 List 반환
                    ParkingLotsDTO parkingLotsDTO = new ParkingLotsDTO(code, name, Do, city, address, size, opening_days, weekdays_opening_time, sat_opening_time, holiday_opening_time, fee, phone_num, latitude, longitude);
                    list.add(parkingLotsDTO);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } 
        return list;
    }
    public static ArrayList<ToiletDTO> runToilet(String path, String encoding) {
        String[] line;
        
        int cnt=0;
        String tl = "tl";
        String code;
        String name;
        String Do;
        String city;
        String address;
        String Public;
        String opening_time;
        double latitude;
        double longitude;
        String managementAgency;
        ArrayList<ToiletDTO> list = new ArrayList<ToiletDTO>();
        
        try {
                CSVReader br = new CSVReader(new InputStreamReader(new FileInputStream(path), encoding));
                line = br.readNext();
                line = br.readNext();
                while ((line = br.readNext()) != null) {
                    if(line[6].equals("") || line[7].equals("") || line[6].equals("null") || line[7].equals("null")){ // 위경도 공백 or null일 시 continue
                        continue;
                    }
                    if(cnt < 10) {
                        tl = "tl0000";
                    }
                    else if (cnt >= 10 && cnt < 100){
                        tl = "tl000";
                    }
                    else if (cnt >= 100 && cnt < 1000){
                        tl = "tl00";
                    }
                    else if (cnt >= 1000 && cnt < 10000){
                        tl = "tl0";
                    }
                    else {
                        tl = "tl";
                    }
                    tl = tl + Integer.toString(cnt++); 
                    code = tl;
                    name = line[0];
                    String[] addressSplit;
                    if(line[2].equals("") || line[2].equals("null")){
                        addressSplit = line[1].split(" ");
                    }
                    else{
                        addressSplit = line[2].split(" ");

                    }
                    Do = addressSplit[0];
                    int index = 0;
                    String addressSum = "";
                    if(Do.equals("부산광역시") || Do.equals("울산광역시") || Do.equals("인천광역시") || Do.equals("제주특별자치도") || Do.equals("대구광역시") || Do.equals("세종특별자치시")){
                        city = "";
                        index = 1;
                    }
                    else {
                        city = addressSplit[1];
                        index = 2;
                    }
                    for(int i = index; i < addressSplit.length; i++){
                        addressSum += addressSplit[i];
                        if((i + 1) != addressSplit.length){
                            addressSum += " ";
                        }
                    }
                    address = addressSum;
                    latitude = Double.parseDouble(line[6]);
                    longitude = Double.parseDouble(line[7]);
                    Public = line[3];
                    opening_time = line[5];
                    managementAgency = line[4];
                    // System.out.println(code + " " + name + " " + Do + " " + city + " " + address + " " + latitude + " " + longitude + " " + Public + " " + opening_time
                    //  + " " + managementAgency);
                    // 화장실 DTO 생성 후 List 반환
                    ToiletDTO toiletDTO = new ToiletDTO(code, name, Do, city, address, Public, opening_time, latitude, longitude, managementAgency);
                    list.add(toiletDTO);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } 
        return list;
    }
    public static ArrayList<DestinationDTO> runDestination(ArrayList<BeachDTO> bList, ArrayList<ForestLodgeDTO> fList, ArrayList<TouristSpotDTO> tList){
        ArrayList<DestinationDTO> list = new ArrayList<DestinationDTO>();
        String code;
        String sortation;
        String forestLodge_code;
        String beach_code;
        String touristSpot_code;
        String name;
        String Do;
        String city;
        String address;
        double scope;
        int views;

        int cnt = 0;
        String dt = "dt";
        Iterator<BeachDTO> bIt = bList.iterator();
        BeachDTO beachDTO = null;
        Iterator<ForestLodgeDTO> fIt = fList.iterator();
        ForestLodgeDTO forestLodgeDTO = null;
        Iterator<TouristSpotDTO> tIt = tList.iterator();
        TouristSpotDTO touristSpotDTO = null;
        DestinationDTO destinationDTO;

        while(bIt.hasNext()){
            beachDTO = bIt.next();
            if(cnt < 10) {
                dt = "dt000";
            }
            else if (cnt >= 10 && cnt < 100){
                dt = "dt00";
            }
            else if (cnt >= 100 && cnt < 1000){
                dt = "dt0";
            }
            else {
                dt = "dt";
            }
            dt = dt + Integer.toString(cnt++); 
            code = dt;
            sortation = "해수욕장";
            beach_code = beachDTO.getCode();
            forestLodge_code = null;
            touristSpot_code = null;
            name = beachDTO.getName();
            Do = beachDTO.getDo();
            city = beachDTO.getCity();
            address = beachDTO.getAddress();
            scope = 0;
            views = 0;
            destinationDTO = new DestinationDTO(code, sortation, forestLodge_code, beach_code, touristSpot_code, name, Do, city, address, scope, views);
            list.add(destinationDTO);
            System.out.println(code + " " + sortation + " " + forestLodge_code + " " + beach_code + " " + touristSpot_code + " " + name + " " + Do + " " + city + " " + address + " " + scope);
            
        }
        while(fIt.hasNext()){
            forestLodgeDTO = fIt.next();
            if(cnt < 10) {
                dt = "dt000";
            }
            else if (cnt >= 10 && cnt < 100){
                dt = "dt00";
            }
            else if (cnt >= 100 && cnt < 1000){
                dt = "dt0";
            }
            else {
                dt = "dt";
            }
            dt = dt + Integer.toString(cnt++); 
            code = dt;
            sortation = "휴양림";
            beach_code = null;
            forestLodge_code = forestLodgeDTO.getCode();
            touristSpot_code = null;
            name = forestLodgeDTO.getName();
            Do = forestLodgeDTO.getDo();
            city = forestLodgeDTO.getCity();
            address = forestLodgeDTO.getAddress();
            scope = 0;
            views = 0;
            destinationDTO = new DestinationDTO(code, sortation, forestLodge_code, beach_code, touristSpot_code, name, Do, city, address, scope, views);
            list.add(destinationDTO);
            System.out.println(code + " " + sortation + " " + forestLodge_code + " " + beach_code + " " + touristSpot_code + " " + name + " " + Do + " " + city + " " + address + " " + scope);
            
        }
        while(tIt.hasNext()){
            touristSpotDTO = tIt.next();
            if(cnt < 10) {
                dt = "dt000";
            }
            else if (cnt >= 10 && cnt < 100){
                dt = "dt00";
            }
            else if (cnt >= 100 && cnt < 1000){
                dt = "dt0";
            }
            else {
                dt = "dt";
            }
            dt = dt + Integer.toString(cnt++); 
            code = dt;
            sortation = "관광지";
            beach_code = null;
            forestLodge_code = null;
            touristSpot_code = touristSpotDTO.getCode();
            name = touristSpotDTO.getName();
            Do = touristSpotDTO.getDo();
            city = touristSpotDTO.getCity();
            address = touristSpotDTO.getAddress();
            scope = 0;
            views = 0;
            destinationDTO = new DestinationDTO(code, sortation, forestLodge_code, beach_code, touristSpot_code, name, Do, city, address, scope, views);
            list.add(destinationDTO);
            System.out.println(code + " " + sortation + " " + forestLodge_code + " " + beach_code + " " + touristSpot_code + " " + name + " " + Do + " " + city + " " + address + " " + scope);
        }
        return list;
    }
}

