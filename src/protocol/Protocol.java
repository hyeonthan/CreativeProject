package protocol;

public class Protocol {
    // 프로토콜 타입
    public static final String PT_EXIT = "0"; // 프로그램 종료
    public static final String PT_REQ_LOGIN_INFO = "1"; // 로그인 정보 요청
    public static final String PT_REQ_LOGIN = "2"; // 로그인 요청
    public static final String PT_RES_LOGIN = "3"; // 로그인 요청 응답
    public static final String PT_REQ_FILE = "4";  //파일 전송 요청
    public static final String PT_RES_FILE = "5";  //파일 전송 요청에 대한 응답
    public static final String PT_REQ_VIEW = "6"; // 조회 요청
    public static final String PT_RES_VIEW = "7"; // 조회 요청 으답
    public static final String PT_REQ_RENEWAL = "8"; // 갱신 요청
    public static final String PT_RES_RENEWAL = "9"; // 갱신 요청 응답

    // 프로토콜 코드
    // 로그인 요청에 의한 응답
    public static final String RES_LOGIN_Y ="3-0";  //로그인 성공
    public static final String RES_LOGIN_N = "3-1"; //로그인 실패

    // 파일 전송에 대한 응답
    public static final String REQ_FILE = "4-0";    //리뷰 사진 파일 요청

    //파일 전송 요청에 대한 응답답
    public static final String RES_FILE_Y = "5-0";  //리뷰 사진 파일 요청에 대한 승인
    public static final String RES_FILE_N = "5-1";  //리뷰 사진 파일 요청에 대한 거절

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

    //조회 요청에 대한 응답
    public static final String RES_DESTINATION_REGION_Y = "7-0";      //여행지 조회 요청 승인
    public static final String RES_DESTINATION_REGION_N = "7-1";      //여행지 조회 요청 거절
    public static final String REQ_TOURIST_DETAIL_Y = "7-2";          //관광지 상세정보 조회 요청 승인
    public static final String REQ_TOURIST_DETAIL_N = "7-3";          //관광지 상세정보 조회 요청 거절
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

    // 갱신 요청
    public static final String REQ_SINGUP = "8-0";                  //회원가입 요청
    public static final String REQ_CREATE_REVIEW = "8-1";           //리뷰 등록 요청
    public static final String REQ_UPDATE_REVIEW = "8-2";           //리뷰 수정 요청
    public static final String REQ_DELETE_REVIEW = "8-3";           //리뷰 삭제 요청
    public static final String REQ_UPDATE_USER = "8-4";             //회원정보 수정 요청
    public static final String REQ_CREATE_BOOKMARK = "8-5";         //즐겨찾기 등록 요청
    public static final String REQ_DELETE_BOOKMARK ="8-6";          //즐겨찾기 삭제 요청

    //갱신 요청에 대한 응답답
    public static final String RES_SINGUP_Y = "8-0";                  //회원가입 요청 성공  
    public static final String RES_SINGUP_N = "8-0";                  //회원가입 요청 실패
    public static final String RES_CREATE_REVIEW_Y = "8-1";           //리뷰 등록 요청 성공
    public static final String RES_CREATE_REVIEW_N = "8-1";           //리뷰 등록 요청 실패
    public static final String RES_UPDATE_REVIEW_Y = "8-2";           //리뷰 수정 요청 성공
    public static final String RES_UPDATE_REVIEW_N = "8-2";           //리뷰 수정 요청 실패
    public static final String RES_DELETE_REVIEW_Y = "8-3";           //리뷰 삭제 요청 성공
    public static final String RES_DELETE_REVIEW_N = "8-3";           //리뷰 삭제 요청 실채
    public static final String RES_UPDATE_USER_Y = "8-4";             //회원정보 수정 요청 성공
    public static final String RES_UPDATE_USER_N = "8-4";             //회원정보 수정 요청 실패
    public static final String RES_CREATE_BOOKMARK_Y = "8-5";         //즐겨찾기 등록 요청 성공
    public static final String RES_CREATE_BOOKMARK_N = "8-5";         //즐겨찾기 등록 요청 실패
    public static final String RES_DELETE_BOOKMARK_Y ="8-6";          //즐겨찾기 삭제 요청 성공
    public static final String RES_DELETE_BOOKMARK_N ="8-6";          //즐겨찾기 삭제 요청 실패

}
