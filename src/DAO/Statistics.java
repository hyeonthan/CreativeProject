package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;
import java.util.ArrayList;

import DBcontrol.DBconnection;
import DTO.DestinationDTO;

public class Statistics {
    private Connection conn;
    private PreparedStatement psmt;
    private ResultSet rs;
    private Savepoint sp;

    // 여행지 평점 가져오기
    public ArrayList<DestinationDTO> loadScope(){
        String sql = "SELECT sortation, name, scope FROM destination";
        try{
            conn = DBconnection.getConnection();

            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();
            while(rs.next()){
                String sortation = rs.getString("sortation");
                String name = rs.getString("name");
                double scope = rs.getDouble("scope");
            }
        }
    }
}
