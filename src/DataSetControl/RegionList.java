package DataSetControl;

import java.util.HashMap;

public class RegionList {
    public static final String[] Do = {"충청북도", "충청남도", "제주특별자치도", "전라북도", "전라남도", "인천광역시", "울산광역시", "경상북도","경상남도", "경기도", "강원도", "서울특별시", "부산광역시", "대전광역시", "대구광역시"};
    public static final String[] chungbuk = { "청주시","충주시","제천시","보은군","옥천군","영동군" ,"증평군", "진천군", "괴산군", "음성군", "단양군"};
    public static final String[] chungnam = {"천안시", "공주시", "보령시", "아산시", "서산시","논산시", "계룡시", "당진시", "금산군", "부여군", "서천군","청양군", "홍성군", "예산군", "태안군"};
    public static final String[] jeonbuk = {"전주시", "군산시", "익산시", "정읍시", "남원시", "김제시", "완주군", "진안군", "무주군", "장수군", "임실군", "순창군", "고창군", "부안군"};
    public static final String[] jeonnam = {"강진군", "고흥군", "곡성군", "광양시", "구례군","나주시", "담양군", "목포시", "무안군", "보성군", "순천시", "신안군", "여수시", "영광군", "영암군", "완도군", "장성군","장흥군", "진도군", "함평군", "해남군", "화순군"};
    public static final String[] gyeongbuk = {"포항시", "경주시", "김천시", "안동시", "구미시", "영주시", "영천시", "상주시", "문경시", "경산시", "군위군", "의성군", "청송군", "영양군", "영덕군", "청도군", "고령군", "성주군", "칠곡군", "예천군", "봉화군", "울진군", "울릉군"};
    public static final String[] gyeongnam = {"창원시", "진주시", "통영시", "사천시", "김해시", "밀양시", "거제시", "양산시", "의령군", "함안군", "창녕군", "고성군", "남해군", "하동군", "산청군", "함양군", "거창군", "합천군"};
    public static final String[] gyeonggi = {"가평시", "고양시", "과전시", "광명시", "광주시", "구리시", "군포시", "김포시", "남양주시", "동두천시", "부천시", "성남시", "수원시", "시흥시", "안산시", "안성시", "안양시", "양주시", "양평군", "여주시", "연천군", "오산시", "용인시", "의왕시", "의정부시", "이천시", "파주시", "평택시", "포천시", "하남시", "화성시"};
    public static final String[] gangwon = {"춘천시", "원주시", "강릉시", "동해시", "태백시", "속초시", "삼척시", "홍천군", "횡성군", "영월군", "평창군", "정선군", "철원군", "화천군", "양구군", "인제군", "고성군", "양양군"};
    public static final HashMap<String,String[]> hsMap = new HashMap<String,String[]>(){{
        put(Do[0],chungbuk);
        put(Do[1],chungnam);
        put(Do[3],jeonbuk);
        put(Do[4],jeonnam);
        put(Do[7],gyeongbuk);
        put(Do[8],gyeongnam);
        put(Do[9],gyeonggi);
        put(Do[10],gangwon);
    }};

}