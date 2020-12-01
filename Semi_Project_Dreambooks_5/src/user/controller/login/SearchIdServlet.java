package user.controller.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.model.service.UserService;
import user.model.vo.Users;

/**
 * Servlet implementation class SearchIdServlet
 */
@WebServlet("/user/searchId")
public class SearchIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchIdServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				//1. 사용자 입력값
				String userName = request.getParameter("findId-name");
				String email = request.getParameter("findId-email");
				
//				System.out.println("userName@servlet = "+userName);
//				System.out.println("email@servlet = "+email);
				
				//2. 업무로직
				Users u = new UserService().searchId(userName, email);
				
				//3. 뷰단 처리
				String msg = "";
				String loc = "/";
				
				if(u != null) {
					String encryptedId = encryptedId(u.getUserId());
//					System.out.println("회원님의 아이디는 " + encryptedId + "입니다.");
					msg = "회원님의 아이디는"+encryptedId+"입니다.";
					loc = "/";
				}
				else {
					msg = "존재하지 않는 회원입니다";
					loc = "/";
				}				
				request.setAttribute("loc", loc);
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("/WEB-INF/views/common/popupId.jsp").forward(request, response);
	}
	
	//아이디 암호화 처리
	public String encryptedId(String userId) {
		String encryptedId = "";
		int length = userId.length();
		char[] arr = new char[length];
		
		for(int i=0; i<length; i++) {
			arr[i] = userId.charAt(i);
//			System.out.print(arr[i]);
		}
		
		for(int i=0; i<Math.ceil(length/2); i++) {
			int rnd = (int) (Math.random()*length-1);
			if(arr[rnd] == '*') {
				//이미 그 자리가 '*' 이라면 반복문 한번 더 돌리기
				i--;
			}else {
				arr[rnd] = '*';				
			}
		}
		
		for(int i=0; i<length; i++) {
			encryptedId += arr[i];
		}
		
//		System.out.println(encryptedId);
		return encryptedId;
	}
	
}
