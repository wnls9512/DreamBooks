package common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter{

	private FilterConfig filterConfig;
	
	/**
	 * 필터객체 생성시 1회 실행되는 코드
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		
	}

	@Override
	public void doFilter(ServletRequest request, 
							ServletResponse response, 
							FilterChain chain)
			throws IOException, ServletException {
		//초기화 파라미터 사용하기 //web.xml에 초기화 해 둔 것 꺼내 쓰기
		String encodingType = filterConfig.getInitParameter("encodingType");
		
		//1. 전처리
		request.setCharacterEncoding(encodingType);
		//System.out.println("encodingType@EncodingFilter 처리");
		//web.xml에 등록해야 함
		
		//처리할 다음 필터가 있다면, 해당필터의 doFilter를 실행하고
		//없다면, servlet객체의 메소드를 호출한다.
		chain.doFilter(request, response);
		
		//2. 후처리
	}

	@Override
	public void destroy() {
		
	}



}
