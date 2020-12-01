package common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import user.model.service.UserService;
import user.model.vo.Users;


@WebFilter("/user/mypage")
public class UserAuthFilter implements Filter {

    /**
     * Default constructor. 
     */
    public UserAuthFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
			
		HttpServletRequest httpReq = (HttpServletRequest)request; 
		HttpSession session = httpReq.getSession();
		Users UserLoggedIn = ((Users)session.getAttribute("userLoggedIn"));
		
		//현재로그인한 사용자와 요청사용자 비교
		String userId = ((HttpServletRequest)request).getParameter("userId");
		
//		System.out.println("memberId@MemberAuthFilter="+memberId);
		if((UserLoggedIn==null || userId==null )
		||!(
			userId.equals(UserLoggedIn.getUserId())
		|| UserService.USER_ROLE_ADMIN.equals(UserLoggedIn.getUserRole())
				)){
			request.setAttribute("msg", "잘못된 경로로 접근하셨습니다.");
			request.setAttribute("loc", "/user/mypage?userId="+UserLoggedIn.getUserId());
			request.getRequestDispatcher("/WEB-INF/views/common/popup.jsp")
				.forward(request, response);
			return;
		}
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
