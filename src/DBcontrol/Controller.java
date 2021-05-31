package DBcontrol;
import java.util.ArrayList;

import DTO.BeachDTO;
import DTO.DestinationDTO;
import DTO.ForestLodgeDTO;
import DTO.TouristSpotDTO;
import DataSetControl.ExcelToDB;
import DataSetControl.LoadFromExcel;
public class Controller {
    public static void main(String[] args) throws Exception {
        //해수욕장 데이터 가공 후 DB에 넣기
        //ArrayList<BeachDTO> bList = LoadFromExcel.runBeach("C:\\Users\\ehskf\\OneDrive\\바탕 화면\\3-1강의자료\\창프\\회의\\해양수산부_해수욕장정보목록_20141027.csv", "euc-kr");
        //ExcelToDB.insertBeach(bList);

        //휴양림 데이터 가공 후 DB에 넣기
        //ArrayList<ForestLodgeDTO> fList = LoadFromExcel.runForestLodge("C:\\Users\\ehskf\\OneDrive\\바탕 화면\\3-1강의자료\\창프\\회의\\전국휴양림표준데이터-20210318.csv", "UTF-8");
        // ExcelToDB.insertForestLodge(fList);

        //관광지 데이터 가공 후 DB에 넣기
        //ArrayList<TouristSpotDTO> tList = LoadFromExcel.runTouristSpot("C:\\Users\\ehskf\\OneDrive\\바탕 화면\\3-1강의자료\\창프\\회의\\전국관광지정보표준데이터-20210318.csv", "UTF-8");
        // ExcelToDB.insertTouristSpot(tList);
        
        //주차장 데이터 가공 후 DB에 넣기
        // ArrayList<ParkingLotsDTO> pList = LoadFromExcel.runParkingLots("C:\\Users\\ehskf\\OneDrive\\바탕 화면\\3-1강의자료\\창프\\회의\\전국주차장정보표준데이터-20210409.csv", "UTF-8");
        // ExcelToDB.insertParkingLots(pList);

        //화장실 데이터 가공 후 DB에 넣기
        // ArrayList<ToiletDTO> toiletList = LoadFromExcel.runToilet("C:\\Users\\ehskf\\OneDrive\\바탕 화면\\3-1강의자료\\창프\\회의\\전국공중화장실표준데이터-20210409.csv", "UTF-8");
        // ExcelToDB.insertToilet(toiletList);
        
        //여행지 DB에 넣기
        //ArrayList<DestinationDTO> dList = LoadFromExcel.runDestination(bList, fList, tList);
        //ExcelToDB.insertDestination(dList);
        //ExcelToDB.test();
    }
}
