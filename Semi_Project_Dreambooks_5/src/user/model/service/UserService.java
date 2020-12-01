package user.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import book.model.dao.BookDAO;
import user.model.dao.UserDAO;
import user.model.vo.Cart;
import user.model.vo.CartExtends;
import user.model.vo.OrderBook;
import user.model.vo.OrdersExtends;
import user.model.vo.Users;

public class UserService {

	public static final String USER_ROLE_USER = "U";
	public static final String USER_ROLE_ADMIN = "A";
	public static final String USER_ROLE_PUBLISHER = "P";
	
	private UserDAO userDAO = new UserDAO();

	public int insertUser(Users newUser) {
		Connection conn = getConnection();
		
		int result = userDAO.insertMember(conn, newUser);
		
		if(result > 0) 
			commit(conn);
		else 
			rollback(conn);
		
		close(conn);
		
		return result;
	}

	public Users selectOne(String userId) {
		Connection conn = getConnection();
		Users user = userDAO.selectOne(conn, userId);
		close(conn);
//		System.out.println("user@service="+user);
		return user;
	}

	public Users searchId(String userName, String email) {
		Connection conn = getConnection();
		Users u = userDAO.searchId(conn,userName,email);
		close(conn);
		
		return u;
	}

	public int updatePassword(String userId, String newPwd) {
			Connection conn = getConnection();
			int result = userDAO.updatePassword(conn, userId, newPwd);
			
			if(result > 0)
				commit(conn);
			else
				rollback(conn);
			close(conn);
		return result;
	}

	public int updateUser(Users updateUser) {
		Connection conn = getConnection();
		int result = userDAO.updateUser(conn,updateUser);
		if(result>0) commit(conn);
		else rollback(conn);
		return result;
	}

	public int deleteUser(String userId) {
		Connection conn = getConnection();
		int result = userDAO.deleteUser(conn,userId);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public List<Users> selectAllUserList() {
		Connection conn = getConnection();
		List<Users> list = userDAO.selectAllUserList(conn);
//		System.out.println("UserList@service = "+list);
		close(conn);
		return list;
	}

	public int adminUpdateUser(Users user) {
		Connection conn = getConnection();
		int result = userDAO.adminUpdateUser(conn, user);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	public int insertCart(String book_no, String user_id, String price) {
		Connection conn = getConnection();
		int result = userDAO.insertCart(conn,book_no,user_id,price);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public List<CartExtends> selectCartList(String userId) {
		Connection conn = getConnection();
		List<CartExtends> list = userDAO.selectCartList(conn,userId);
		close(conn);
		return list;
	}

	public int deleteCart(String book_no, String user_id) {
		Connection conn = getConnection();
		int result = userDAO.deleteCart(conn,book_no,user_id);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public List<CartExtends> buyCartList(String[] check) {
		Connection conn = getConnection();
		List<CartExtends> list = userDAO.buyCartList(conn,check);
//		System.out.println(list.size());
//		for(int i=0;i<list.size();i++)
//			System.out.println(list.get(i).getBookTitle());
		close(conn);
		return list;
	}

	public Cart checkCart(String book_no, String user_id) {
		Connection conn = getConnection();
		Cart c = userDAO.checkCart(conn, book_no,user_id);
		close(conn);
		return c;
	}

	public int InsertOrder(String orderNum, int listSize, String[] bookNo, String userId) {
		Connection conn = getConnection();
		
		//주문서
		int result = userDAO.insertOrder(conn, userId, orderNum);
		
		if(result > 0) {
			int result2 = userDAO.insertOrderBook(conn, orderNum, listSize, bookNo);
			if(result2>0) {
				int result3 =BookDAO.updateSaleCount(conn,listSize,bookNo);
				if(result3>0) {
					int result4 =userDAO.deleteCart(conn, listSize, bookNo,userId);
				}else {
					System.out.println("result3오류");
				}
			}else {
				System.out.println("result2오류");
			}
		}else {
			System.out.println("오류!!!!!!!");
		}
		//주문내역서
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		return result;
	}

	public OrdersExtends selectOrder(String orderNum) {
		Connection conn = getConnection();
		OrdersExtends o = userDAO.selectOrder(conn, orderNum);
		close(conn);
		return o;
		
	}

	public int deleteOrder(String orderNo) {
		Connection conn = getConnection();
		
		int result = userDAO.deleteOrder(conn, orderNo);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		return result;
	}

	public List<OrdersExtends> selectOrderList(String userId) {
		Connection conn = getConnection();
		List<OrdersExtends> list = userDAO.selectOrderList(conn,userId);
		close(conn);
		return list;
	}
	
	public List<Users> selectAllUserList(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Users> list = userDAO.selectAll(conn,cPage,numPerPage);
	    close(conn);				
		return list;
	}

	public int selectTotalContents() {
		Connection conn = getConnection();
		int totalContents = userDAO.selectTotalContents(conn);
		close(conn);
		return totalContents;
	}

	public List<Users> searchUser(String searchType, String searchKeyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Users> list = userDAO.searchUser(conn,searchType, searchKeyword, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int selectTotalContents(String searchType, String searchKeyword) {
		Connection conn = getConnection();
		int totalContents = 0;
		if(searchKeyword.equals("T")) {
			totalContents = userDAO.selectTotalContents(conn);
		}
		else {
			totalContents = userDAO.selectTotalContents(conn,searchType, searchKeyword);			
		}
		close(conn);
		return totalContents;
	}

	public List<OrdersExtends> allOrderList() {
		Connection conn = getConnection();
		List<OrdersExtends> list = userDAO.selectOrderList(conn);
		close(conn);
		return list;
	}

	public List<OrderBook> selectOrderInfo(String orderNo) {
		Connection conn = getConnection();
		List<OrderBook> ob = userDAO.selectOrderInfo(conn, orderNo);
		close(conn);
		return ob;
	}
	
	public int deleteOkDday(Date orderDate) {
		Connection conn = getConnection();
		int result = userDAO.deleteOkDday(conn, orderDate);
		close(conn);
		return result;
	}

		public int cancelOrder(String orderNo) {
		Connection conn = getConnection();
		int result = userDAO.cancelOrder(conn, orderNo);
		close(conn);
		return result;
	}

	public String cancelYN(String orderNo) {
		Connection conn = getConnection();
		String cancelYn = userDAO.cancelYN(conn, orderNo);
		close(conn);
		return cancelYn;
	}

}