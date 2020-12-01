package user.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import user.model.vo.Cart;
import user.model.vo.CartExtends;
import user.model.vo.OrderBook;
import user.model.vo.OrdersExtends;
import user.model.vo.Users;

public class UserDAO {
	private Properties prop = new Properties();
	
	public UserDAO() {
		String fileName 
			= UserDAO.class.getResource("/sql/user/user-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public int insertMember(Connection conn, Users newUser) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertUser");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newUser.getUserId());
			pstmt.setString(2, newUser.getPassword());
			pstmt.setString(3, newUser.getUserName());
			pstmt.setString(4, newUser.getUserRole());
			pstmt.setString(5, newUser.getEmail());
			pstmt.setString(6, newUser.getPhone());

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
//		System.out.println("result@dao="+result);
		return result;
	}
	public Users selectOne(Connection conn, String userId) {
		Users user =null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectOne");
//		System.out.println(query);
		
		try{
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				user = new Users();
				user.setUserId(rset.getString("user_id"));
				user.setPassword(rset.getString("password"));
				user.setUserName(rset.getString("user_name"));
				user.setUserRole(rset.getString("user_role"));
				user.setEmail(rset.getString("email"));
				user.setPhone(rset.getString("phone"));
				user.setQuitYN(rset.getString("quit_yn"));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
//		System.out.println("user@dao="+user);
		return user;
	}
	public Users searchId(Connection conn, String userName, String email) {
		Users u = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("searchId");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			pstmt.setString(2, email);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				u = new Users();
				u.setUserId(rset.getString("user_id"));
				u.setPassword(rset.getString("password"));
				u.setUserName(rset.getString("user_name"));
				u.setUserRole(rset.getString("user_role"));
				u.setEmail(rset.getString("email"));
				u.setPhone(rset.getString("phone"));
				u.setQuitYN(rset.getString("quit_yn"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
//		System.out.println("users@dao = " + u);
		return u;
	}
	public int updatePassword(Connection conn, String userId, String newPwd) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updatePwd");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPwd);
			pstmt.setString(2, userId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
//		System.out.println("result@dao = " + result);
		return result;
	}
	
	public int updateUser(Connection conn, Users updateUser) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateUser");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, updateUser.getEmail());
			pstmt.setString(2, updateUser.getPhone());
			pstmt.setString(3, updateUser.getUserId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
//		System.out.println("result@dao = " + result);
		return result;
	}
	
	public int deleteUser(Connection conn, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteUser");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
//		System.out.println("rsult@Dao = "+ result);
		return result;
	}
	public List<Users> selectAllUserList(Connection conn) {
		List<Users> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAllUser");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			list = new ArrayList<>();
			
			while(rset.next()) {
				Users u = new Users();
				
				u.setUserId(rset.getString("user_id"));
				u.setPassword(rset.getString("password"));
				u.setUserName(rset.getString("user_name"));
				u.setUserRole(rset.getString("user_role"));
				u.setEmail(rset.getString("email"));
				u.setPhone(rset.getString("phone"));
				u.setQuitYN(rset.getString("quit_yn"));
				
				list.add(u);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		System.out.println("UserList@dao = "+list);
		return list;
	}
	public int adminUpdateUser(Connection conn, Users user) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("adminUpdateUser");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getEmail());
			pstmt.setString(2, user.getPhone());
			pstmt.setString(3, user.getUserRole());
			pstmt.setString(4, user.getUserId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
//		System.out.println("updateResult@dao="+result);
		
		return result;
	
	}
	public int insertCart(Connection conn, String book_no,String user_id, String price) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertCart");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,book_no);
			pstmt.setString(2,user_id);
			pstmt.setString(3,price);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
//		System.out.println("rsult@Dao = "+ result);
		return result;
	}
	public List<CartExtends> selectCartList(Connection conn, String userId) {
		List<CartExtends> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectCartList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<CartExtends>();
			while(rset.next()) {
				CartExtends c = new CartExtends();
				c.setCartCode(rset.getString("cart_code"));
				c.setBookNo(rset.getString("book_no"));
				c.setUserId(rset.getString("user_id"));
				c.setBookTitle(rset.getString("book_title"));
				c.setCartTotalPrice(rset.getString("price"));
				c.setBookImgOriginalFileName(rset.getString("book_img_original_filename"));
				c.setBookImgRenameFileName(rset.getString("book_img_rename_filename"));
				
				list.add(c);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	public int deleteCart(Connection conn, String book_no, String user_id) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteCart");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book_no);
			pstmt.setString(2, user_id);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
//		System.out.println("rsult@Dao = "+ result);
		return result;
	}
	
	public List<CartExtends> buyCartList(Connection conn, String[] check) {
		List<CartExtends> list = new ArrayList<CartExtends>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("buyCartList");
		
		try {
			for(int i=0;i<check.length;i++) {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,check[i]);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					CartExtends c = new CartExtends();
					c.setBookNo(rset.getString("book_no"));
					c.setUserId(rset.getString("user_id"));
					c.setBookTitle(rset.getString("book_title"));
					c.setCartTotalPrice(rset.getString("price"));
					c.setBookImgOriginalFileName(rset.getString("book_img_original_filename"));
					c.setBookImgRenameFileName(rset.getString("book_img_rename_filename"));
					
					list.add(c);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	public Cart checkCart(Connection conn, String book_no, String user_id) {
		Cart c = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("checkCart");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book_no);
			pstmt.setString(2, user_id);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				c = new Cart();
				c.setCartCode(rset.getString("cart_code"));
				c.setUserId(rset.getString("user_id"));
				c.setBookNo(rset.getString("book_no"));
				c.setCartTotalPrice(rset.getString("cart_total_price"));								
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return c;
	}
	public int insertOrder(Connection conn, String userId, String orderNum) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "insert into orders values (?,?,default,default)";
		
//		System.out.println("DAO : "+userId);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,orderNum);
			pstmt.setString(2,userId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
//		System.out.println("insertOrder@dao="+result);
		return result;
	}
	public int insertOrderBook(Connection conn, String orderNum, int listSize, String[] bookNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "insert into order_book values(seq_orderbook.nextval, ?, ?)";
//		System.out.println(sql);
		try {
			
			for(int i=0; i<listSize; i++) {

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, orderNum);
				pstmt.setString(2, bookNo[i]);
				
				result = pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
//		System.out.println("insertOrderBook@dao="+result);
		return result;
	}
	public OrdersExtends selectOrder(Connection conn, String orderNum) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		OrdersExtends o = null;
		String sql = "select order_no, user_id, order_date, cancel_yn, to_char(sum(price), '999,999') price from (select O.order_no, O.user_id, O.order_date, O.cancel_yn, B.price from orders O join order_book OB on O.order_no = OB.order_no join book B on OB.book_no = B.book_no) O where O.order_no = ? group by order_no, user_id, order_date, cancel_yn";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, orderNum);
			rset = pstmt.executeQuery();
					
			if(rset.next()) {
				o = new OrdersExtends();
				o.setOrderNo(rset.getString("order_no"));
				o.setUserId(rset.getString("user_id"));
				o.setOrderDate(rset.getDate("order_date"));
				o.setCancelYN(rset.getString("cancel_yn"));
				o.setTotal(rset.getString("price"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return o;
	}
	public int deleteOrder(Connection conn, String orderNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "delete orders where order_no = ?";
		
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, orderNo);
				
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	public List<OrdersExtends> selectOrderList(Connection conn, String userId) {
		List<OrdersExtends> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select order_no, user_id, order_date, cancel_yn, to_char(sum(price), '999,999') totalprice, count(book_no) countBook from (select O.order_no, O.user_id, O.order_date, O.cancel_yn, B.price, OB.book_no from orders O join order_book OB on O.order_no = OB.order_no join book B on OB.book_no = B.book_no) O where user_id = ? group by order_no, user_id, order_date, cancel_yn order by order_date desc";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			
			list= new ArrayList<OrdersExtends>();
			
			while(rset.next()) {
				OrdersExtends o = new OrdersExtends();
				o.setOrderNo(rset.getString("order_no"));
//				o.setBookTitle(rset.getString("book_title"));
				o.setUserId(rset.getString("user_id"));
				o.setOrderDate(rset.getDate("order_date"));
				o.setCancelYN(rset.getString("cancel_yn"));
				o.setTotal(rset.getString("totalprice"));
				o.setCountBookNo(rset.getString("countBook"));
				
//				System.out.println(o.getOrderNo());
				

				// 첫 번째 책 제목 가져오는 pstmt와 rset
				PreparedStatement titlePstmt = null;
				ResultSet titleRset = null;
				String titleSql = "select book_title from (select rownum, O.order_no, B.book_title from orders O join order_book OB on O.order_no = OB.order_no join book B on OB.book_no = B.book_no where O.order_no = ?) O where rownum = 1";

				titlePstmt = conn.prepareStatement(titleSql);
				titlePstmt.setString(1, o.getOrderNo());

				titleRset = titlePstmt.executeQuery();
					
				while(titleRset.next()) {
					o.setBookTitle(titleRset.getString("book_title"));
						
//					System.out.println(o.getBookTitle());
				}

				list.add(o);
			}
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	
	public List<Users> selectAll(Connection conn, int cPage, int numPerPage) {
		List<Users> list = new ArrayList<Users>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAllPaging");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (numPerPage*(cPage-1))+1);
			pstmt.setInt(2, cPage * numPerPage);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Users u = new Users();
				u.setUserId(rset.getString("user_id"));
				u.setPassword(rset.getString("password"));
				u.setUserName(rset.getString("user_name"));
				u.setUserRole(rset.getString("user_role"));
				u.setEmail(rset.getString("email"));
				u.setPhone(rset.getString("phone"));
				u.setQuitYN(rset.getString("quit_yn"));
				list.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public int selectTotalContents(Connection conn) {
		int totalContents = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectTotalContents");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			if(rset.next())
				totalContents = rset.getInt("TOTAL_CONTENTS");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
//		System.out.println("totalContents@dao = " + totalContents);
		return totalContents;
	}
	
	public List<Users> searchUser(Connection conn, String searchType, String searchKeyword, int cPage, int numPerPage) {
		List<Users> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("searchUserPaging");
//		String sql = prop.getProperty("searchUser");
		//select * from user where # like ?
		//select * from user where user_name like ?
		
		String columnName = "";
		switch(searchType) {
		case "userId": columnName = "user_id"; break;
		case "userName": columnName = "user_name"; break;
		case "userRole" : columnName = "user_role"; break;
		}
		sql = sql.replace("#", columnName);
//		System.out.println("sql@dao = " + sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  "%" + searchKeyword + "%");
			pstmt.setInt(2, (cPage - 1) * numPerPage + 1);
			pstmt.setInt(3, cPage * numPerPage);
			
			rset = pstmt.executeQuery();
			list = new ArrayList<Users>();
			
			while(rset.next()) {
				Users u = new Users();
				u.setUserId(rset.getString("user_id"));
				u.setPassword(rset.getString("password"));
				u.setUserName(rset.getString("user_name"));
				u.setUserRole(rset.getString("user_role"));
				u.setEmail(rset.getString("email"));
				u.setPhone(rset.getString("phone"));
				u.setQuitYN(rset.getString("quit_yn"));
				list.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
//		System.out.println("list@dao = " + list);
		return list;
	}
	
	public int selectTotalContents(Connection conn, String searchType, String searchKeyword) {
		int totalContents = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "";
		//select * from user where # like ?
		//select * from user where user_name like ?
		
		sql = prop.getProperty("selectFinderTotalContents");						
		String columnName = "";
		switch(searchType) {
		case "userId": columnName = "user_id"; break;
		case "userName": columnName = "user_name"; break;
		case "userRole" : columnName = "user_role"; break;
		}
		sql = sql.replace("#", columnName);
//		System.out.println("sql@dao = " + sql);

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchKeyword+"%");
			
			rset = pstmt.executeQuery();
			if(rset.next())
				totalContents = rset.getInt("TOTAL_CONTENTS");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
//		System.out.println("list@dao = " + totalContents);
		return totalContents;
	}
	
	public int deleteCart(Connection conn, int listSize, String[] bookNo, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "delete from cart where  user_id = ? and book_no = ?";
//		System.out.println(sql);
		try {
			
			for(int i=0; i<listSize; i++) {

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, userId);
				pstmt.setString(2, bookNo[i]);
				
				result = pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
//		System.out.println("insertOrderBook@dao="+result);
		return result;
	}
	
	
	public List<OrdersExtends> selectOrderList(Connection conn) {
		List<OrdersExtends> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select order_no, user_id, order_date, cancel_yn, to_char(sum(price), '999,999') totalprice, count(book_no) countBook from (select O.order_no, O.user_id, O.order_date, O.cancel_yn, B.price, OB.book_no from orders O join order_book OB on O.order_no = OB.order_no join book B on OB.book_no = B.book_no) O group by order_no, user_id, order_date, cancel_yn order by order_date desc";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			list= new ArrayList<OrdersExtends>();
			
			while(rset.next()) {
				OrdersExtends o = new OrdersExtends();
				o.setOrderNo(rset.getString("order_no"));
//				o.setBookTitle(rset.getString("book_title"));
				o.setUserId(rset.getString("user_id"));
				o.setOrderDate(rset.getDate("order_date"));
				o.setCancelYN(rset.getString("cancel_yn"));
				o.setTotal(rset.getString("totalprice"));
				o.setCountBookNo(rset.getString("countBook"));
				
//				System.out.println(o.getOrderNo());
				

				// 첫 번째 책 제목 가져오는 pstmt와 rset
				PreparedStatement titlePstmt = null;
				ResultSet titleRset = null;
				String titleSql = "select book_title from (select rownum, O.order_no, B.book_title from orders O join order_book OB on O.order_no = OB.order_no join book B on OB.book_no = B.book_no where O.order_no = ?) O where rownum = 1";

				titlePstmt = conn.prepareStatement(titleSql);
				titlePstmt.setString(1, o.getOrderNo());

				titleRset = titlePstmt.executeQuery();
					
				while(titleRset.next()) {
					o.setBookTitle(titleRset.getString("book_title"));
						
//					System.out.println(o.getBookTitle());
				}

				list.add(o);
			}
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	
	public List<OrderBook> selectOrderInfo(Connection conn, String orderNo) {
		List<OrderBook> list = null;
		OrderBook ob = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "select OB.order_book_no, OB.order_no, B.book_title, b.book_file_original_name, b.book_file_renamed_name, to_char(B.price, '999,999') price, O.order_date, O.user_id from order_book OB join book B on OB.book_no = B.book_no join orders O on OB.order_no = O.order_no where OB.order_no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, orderNo);
			rset = pstmt.executeQuery();
			
			list = new ArrayList<OrderBook>();
			while(rset.next()) {
				ob = new OrderBook();
				ob.setOrderBookNo(rset.getString("order_book_no"));
				ob.setOrderNo(rset.getString("order_no"));
				ob.setBookTitle(rset.getString("book_title"));
				ob.setPrice(rset.getString("price"));
				ob.setUserId(rset.getString("user_id"));
				ob.setOrderDate(rset.getDate("order_date"));
				ob.setOriginalFileName(rset.getString("book_file_original_name"));
				ob.setRenamedFileName(rset.getString("book_file_renamed_name"));
				
				list.add(ob);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
		public int deleteOkDday(Connection conn, Date orderDate) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select sysdate - to_date(?) dday from dual";
		int day = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, orderDate);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				day = rset.getInt("dday");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
//		System.out.println("result"+result);
//		System.out.println(day);
		return day;
	}

			public int cancelOrder(Connection conn, String orderNo) {
			int result = 0;
			PreparedStatement pstmt = null;
			String sql = "update orders set cancel_yn = 'Y' where order_no = ?";
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, orderNo);
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return result;
		}
		public String cancelYN(Connection conn, String orderNo) {
			String cancelYn = "";
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String sql = "select cancel_yn from orders where order_no = ?";
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, orderNo);
				rset = pstmt.executeQuery();
				if(rset.next()) {
					cancelYn = rset.getString("cancel_yn");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return cancelYn;
		}

}