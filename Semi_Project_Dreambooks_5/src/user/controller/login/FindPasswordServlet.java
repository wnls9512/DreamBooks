package user.controller.login;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.util.Utils;
import user.model.service.UserService;
import user.model.vo.Users;

/**
 * Servlet implementation class FindPasswordServlet
 */
@WebServlet("/user/findPassword")
public class FindPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FindPasswordServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userId = request.getParameter("findPwdId");
		String userEmail = request.getParameter("findPwdEmail");
		String userPhone = request.getParameter("findPwdTel");
//			// 확인
//		System.out.println("userId@servlet=" + userId);
//		System.out.println("userEmail@servlet=" + userEmail);
//		System.out.println("userPhone@servlet=" + userPhone);
		
		// 3. 업무로직
		Users u = new UserService().selectOne(userId);
		//확인
//		System.out.println("Users@servlet = "+u);
	
		// 4. 응답처리
		String message = "";
		String loc = "";		
		if(u == null
			|| !userEmail.equals(u.getEmail())) {
			
			message = "아이디나 이메일 정보가 일치하지 않습니다.";
			loc="/";
			
			request.setAttribute("msg", message);
			request.setAttribute("loc", loc);
			request.getRequestDispatcher("/WEB-INF/views/common/popupPwd.jsp")
			.forward(request, response);
			return;
		}
		
			//mail server 설정
			String host = "smtp.gmail.com";
			String user = "dreambooks.a";
			String password = "dream12!!";
			
			//메일 받을 주소
			String to_email = u.getEmail();
			
			//SMTP 서버 정보를 설정한다.
            Properties props = new Properties();
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", 465);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.ssl.enable", "true");
            
            //인증 번호 생성기
            StringBuffer temp =new StringBuffer();
            Random rnd = new Random();
            for(int i=0;i<10;i++)
            {
                int rIndex = rnd.nextInt(3);
                switch (rIndex) {
                case 0:
                    // a-z
                    temp.append((char) ((int) (rnd.nextInt(26)) + 97));
                    break;
                case 1:
                    // A-Z
                    temp.append((char) ((int) (rnd.nextInt(26)) + 65));
                    break;
                case 2:
                    // 0-9
                    temp.append((rnd.nextInt(10)));
                    break;
                }
            }
		
            String AuthenticationKey = temp.toString();
//            System.out.println(AuthenticationKey);
            
            Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user,password);
                }
            });
            
            try {
                MimeMessage msg = new MimeMessage(session);
                msg.setFrom(new InternetAddress(user, "DreamBooks"));
                msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to_email));
                
                //메일 제목
                msg.setSubject("안녕하세요. DreamBooks 임시 비밀번호 발급 메일입니다.");
                //메일 내용
                msg.setHeader("content-type", "text/html; charset=utf-8");
                msg.setContent("<div align='center' style='border:1px solid black;'>"
                			+"<h3 style='color:blue;'><strong>"+u.getUserId()+"</strong>님의 임시 비밀번호 입니다.</h3>"
                			+"<p>임시 비밀번호 : "+temp+"</p><br> 비밀번호 변경 후 사용해주세요!</div>", "text/html;charset=euc-kr");
                
                
                Transport.send(msg);
//                System.out.println("이메일 전송");
                
                message="임시비밀번호가 발급되었습니다.";
                loc= "/";
                
    			request.setAttribute("msg", message);
    			request.setAttribute("loc", loc);
    			request.getRequestDispatcher("/WEB-INF/views/common/popupPwd.jsp")
    			.forward(request, response);
                
            }catch (Exception e) {
                e.printStackTrace();
            }
            
	}

}
