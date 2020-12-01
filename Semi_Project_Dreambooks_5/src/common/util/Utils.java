package common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Utils {
	/**
	 * MessageDigest : 단방향 암호화를 지원
	 * - 암호화된 문자열에서 원문을 추출할 수 없는 알고리즘
	 * 
	 * md5(사용불가 알고리즘 깨짐)
	 * sha1(사용불가 알고리즘 깨짐)
	 * sha256 (바이트 수 커질수록 암호깨질 위험도 낮음)
	 * sha512
	 * 
	 * 
	 * 문자열 -> byte배열 -> messageDigest 암호화 -> Base64 인코딩처리(이것이 암호화된 문자열)
	 * 
	 * @param password
	 * @return
	 */
	public static String getEncryptedPassword(String password) {
		String encryptedPassword = null;
		
		//1. byte[]로 변환
		byte[] bytes = null;
		try {
			bytes = password.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}		
		
		//2. MessageDigest 객체 생성
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("sha-512");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		//3. MessageDigest객체를 통한 암호화 
		md.update(bytes);
		byte[] encrytedBytes =  md.digest(); //실제 암호화
		
		//4. Base64인코더를 통한 인코딩 처리
		Base64.Encoder encoder = Base64.getEncoder();
		encryptedPassword = encoder.encodeToString(encrytedBytes);
		
		System.out.println("암호화/인코딩처리전 : "+ new String(encrytedBytes));
		System.out.println("암호화/인코딩처리후 : "+ encryptedPassword);
		
		return encryptedPassword;
	}

	public static String getPageBarHtml(int cPage, int numPerPage, int totalContents, String url) {
		String pageBar = "";
		int pageBarSize = 5; //페이지바에 표시될 페이지 번호 수
		//(공식2)
		int totalPage = (int)Math.ceil((double)totalContents/numPerPage);
		//(공식3) pageStaty 시작페이지 번호 구하기
		//1 2 3 4 5 => pageStart = 1
		//6 7 8 9 10 => pageStart = 6
		//11 12 13 14 15 => pageStart = 11
		//......
		int pageStart = ((cPage-1)/pageBarSize) * pageBarSize + 1 ;//cPage,pageBarSize
		int pageEnd = pageStart + pageBarSize - 1;
		
		//증감변수
		int pageNo = pageStart;
		
		//[이전]영역
		if(pageNo == 1) {
			//이전영역 없을 때
		}
		else {
			pageBar += "<a class=\"page-link\" href='"+url+"cPage="+(pageNo-1)+"'>[Previous]</a>"; //6페이지면 5페이지로 가게 //11페이지면 10페이지로 가게
		}
		//페이지번호 영역
		while(pageNo <= pageEnd && pageNo <= totalPage ) {
			//현재페이지인 경우
			if(pageNo == cPage) {
				pageBar += "<span class='cPage'>"+pageNo+"</span>";
			}else {
				pageBar += "<a class='page-link' href='"+url+"cPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		//[다음]영역
		if(pageNo > totalPage) {
			
		}
		else {
			//totalpAGE보다 큰경우
			pageBar += "<a class='page-link' href='"+url+"cPage="+pageNo+"'>[Next]</a>";
		}
		
//		System.out.println(pageBar);
		return pageBar;
	}
}
