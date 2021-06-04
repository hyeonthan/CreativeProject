package Network;

public class Protocol {
    // 프로토콜 타입
    public static final String PT_EXIT = "0"; // 프로그램 종료
    public static final String PT_REQ_LOGIN_INFO = "1"; // 로그인 정보 요청
    public static final String PT_REQ_LOGIN = "2"; // 로그인 요청
    public static final String PT_RES_LOGIN = "3"; // 로그인 요청 응답
    public static final String PT_REQ_VIEW = "6"; // 조회 요청
    public static final String PT_RES_VIEW = "7"; // 조회 요청 으답
    public static final String PT_REQ_RENEWAL = "8"; // 갱신 요청
    public static final String PT_RES_RENEWAL = "9"; // 갱신 요청 응답

    // 프로토콜 코드
    // 로그인 요청에 의한 응답
    public static final String RES_LOGIN_Y ="3-0";  //로그인 성공
    public static final String RES_LOGIN_N = "3-1"; //로그인 실패

    //조회 요청
    public static final String REQ_DESTINATION_REGION = "6-0";      //여행지 조회 요청
    public static final String REQ_TOURIST_DETAIL = "6-1";          //관광지 상세정보 조회 요청
    public static final String REQ_FOREST_DETAIL = "6-2";           //휴양림 상세정보 조회 요청
    public static final String REQ_BEACH_DETAIL = "6-3";            //해수욕장 상세정보 조회 요청
    public static final String REQ_REVIEW_DETAIL = "6-4";           //리뷰 상세정보 조회 요청
    public static final String REQ_TOILET = "6-5";                  //화장실 조회 요청
    public static final String REQ_PARKING = "6-6";                 //주차장 조회 요청
    public static final String REQ_DESTINATION_LOCATION = "6-7";    //주변 여행지 조회 요청
    public static final String REQ_STATISTICS = "6-8";              //통계정보 조회 요청
    public static final String REQ_MYPAGE = "6-9";                  //마이페이지 조회 요청
    public static final String REQ_ID_DUPLICATION = "6-A";          //아이디 중복 확인 요청
    public static final String REQ_STATISTICS_DETAIL = "6-B";       //상세정보 조회 시 통계 요청 
    public static final String REQ_FAVORITES = "6-C";               //즐겨찾기 조회 요청
    public static final String REQ_REVIEWS = "6-D";                 //리뷰 조회 요청
    public static final String REQ_DESTINATION = "6-E";             //여행지 하나 조회 요청

    //조회 요청에 대한 응답
    public static final String RES_DESTINATION_REGION_Y = "7-0";      //여행지 조회 요청 승인
    public static final String RES_DESTINATION_REGION_N = "7-1";      //여행지 조회 요청 거절
    public static final String RES_TOURIST_DETAIL_Y = "7-2";          //관광지 상세정보 조회 요청 승인
    public static final String RES_TOURIST_DETAIL_N = "7-3";          //관광지 상세정보 조회 요청 거절
    public static final String RES_FOREST_DETAIL_Y = "7-4";           //휴양림 상세정보 조회 요청 승인
    public static final String RES_FOREST_DETAIL_N = "7-5";           //휴양림 상세정보 조회 요청 거절
    public static final String RES_BEACH_DETAIL_Y = "7-6";            //해수욕장 상세정보 조회 요청 승인
    public static final String RES_BEACH_DETAIL_N = "7-7";            //해수욕장 상세정보 조회 요청 거절
    public static final String RES_REVIEW_DETAIL_Y = "7-8";           //리뷰 상세정보 조회 요청 승인
    public static final String RES_REVIEW_DETAIL_N = "7-9";           //리뷰 상세정보 조회 요청 거절
    public static final String RES_TOILET_Y = "7-A";                  //화장실 조회 요청 승인
    public static final String RES_TOILET_N = "7-B";                  //화장실 조회 요청 거절
    public static final String RES_PARKING_Y = "7-C";                 //주차장 조회 요청 승인
    public static final String RES_PARKING_N = "7-D";                 //주차장 조회 요청 거절
    public static final String RES_DESTINATION_LOCATION_Y = "7-E";    //주변 여행지 조회 요청 승인
    public static final String RES_DESTINATION_LOCATION_N = "7-F";    //주변 여행지 조회 요청 거절
    public static final String RES_STATISTICS_Y = "7-10";             //통계정보 조회 요청 승인
    public static final String RES_STATISTICS_N = "7-11";             //통계정보 조회 요청 거절
    public static final String RES_MYPAGE_Y = "7-12";                 //마이페이지 조회 요청 승인
    public static final String RES_MYPAGE_N = "7-13";                 //마이페이지 조회 요청 거절
    public static final String RES_ID_DUPLICATION_Y = "7-14";          //아이디 중복 확인 요청 승인
    public static final String RES_ID_DUPLICATION_N = "7-15";          //아이디 중복 확인 요청 거절
    public static final String RES_STATISTICS_DETAIL_Y = "7-16";       //상세정보 조회 시 통계 요청 승인
    public static final String RES_STATISTICS_DETAIL_N = "7-17";       //상세정보 조회 시 통계 요청 거절
    public static final String RES_FAVORITES_Y = "7-18";               //즐겨찾기 조회 요청 승인
    public static final String RES_FAVORITES_N = "7-19";               //즐겨찾기 조회 요청 승인
    public static final String RES_REVIEWS_Y = "7-20";                 //리뷰 조회 요청 승인
    public static final String RES_REVIEWS_N = "7-21";                 //리뷰 조회 요청 거절
    public static final String RES_DESTINATION_Y = "7-22";             //여행지 하나 조회 요청 승인
    public static final String RES_DESTINATION_N = "7-23";             //여행지 하나 조회 요청 거절


    // 갱신 요청
    public static final String REQ_SIGNUP = "8-0";                  //회원가입 요청
    public static final String REQ_CREATE_REVIEW = "8-1";           //리뷰 등록 요청
    public static final String REQ_UPDATE_REVIEW = "8-2";           //리뷰 수정 요청
    public static final String REQ_DELETE_REVIEW = "8-3";           //리뷰 삭제 요청
    public static final String REQ_UPDATE_USER = "8-4";             //회원정보 수정 요청
    public static final String REQ_CREATE_FAVORITES = "8-5";         //즐겨찾기 등록 요청
    public static final String REQ_DELETE_FAVORITES ="8-6";          //즐겨찾기 삭제 요청
    public static final String REQ_UPDATE_VIEWSCOUNT = "8-7";        //조회수 업데이트 요청        

    //갱신 요청에 대한 응답답
    public static final String RES_SIGNUP_Y = "9-0";                  //회원가입 요청 성공  
    public static final String RES_SIGNUP_N = "9-1";                  //회원가입 요청 실패
    public static final String RES_CREATE_REVIEW_Y = "9-2";           //리뷰 등록 요청 성공
    public static final String RES_CREATE_REVIEW_N = "9-3";           //리뷰 등록 요청 실패
    public static final String RES_UPDATE_REVIEW_Y = "9-4";           //리뷰 수정 요청 성공
    public static final String RES_UPDATE_REVIEW_N = "9-5";           //리뷰 수정 요청 실패
    public static final String RES_DELETE_REVIEW_Y = "9-6";           //리뷰 삭제 요청 성공
    public static final String RES_DELETE_REVIEW_N = "9-7";           //리뷰 삭제 요청 실채
    public static final String RES_UPDATE_USER_Y = "9-8";             //회원정보 수정 요청 성공
    public static final String RES_UPDATE_USER_N = "9-9";             //회원정보 수정 요청 실패
    public static final String RES_CREATE_FAVORITES_Y = "9-A";         //즐겨찾기 등록 요청 성공
    public static final String RES_CREATE_FAVORITES_N = "9-B";         //즐겨찾기 등록 요청 실패
    public static final String RES_DELETE_FAVORITES_Y ="9-C";          //즐겨찾기 삭제 요청 성공
    public static final String RES_DELETE_FAVORITES_N ="9-D";          //즐겨찾기 삭제 요청 실패
    public static final String RES_UPDATE_VIEWSCOUNT_Y = "9-E";        //조회수 업데이트 요청 성공  
    public static final String RES_UPDATE_VIEWSCOUNT_N = "9-F";        //조회수 업데이트 요청 실패

}
