package model;

import java.sql.*;
import java.util.ArrayList;

public class DAO extends DBConnection {
	private static DAO instance = new DAO();
	public static DAO getInstance() {
		return instance;
	}
	private DAO() {}
	
	public int insertMember(MemberVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int rs = 0;
		try {
			conn = getConnection();
			sql = "INSERT INTO MEMBER_TBL_02 VALUES(?, ?, ?, ?, TO_DATE(?, 'YYYYMMDD'), ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getCustno());
			pstmt.setString(2, vo.getCustname());
			pstmt.setString(3, vo.getPhone());
			pstmt.setString(4, vo.getAddress());
			pstmt.setString(5, vo.getJoindate());
			pstmt.setString(6, vo.getGrade());
			pstmt.setString(7, vo.getCity());
			rs = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeDBResource(pstmt, conn);
		}
		return rs;
	}
	public int getCustno() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int custno = 0;
		try {
			conn = getConnection();
			sql = "SELECT MAX(CUSTNO) FROM MEMBER_TBL_02";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next())
				custno = rs.getInt("MAX(CUSTNO)") + 1;
			else
				custno = 100001;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeDBResource(rs, pstmt, conn);
		}
		return custno;
	}
	public ArrayList<MemberVO> listCust() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		ArrayList<MemberVO> listvo = new ArrayList<MemberVO>();
		try {
			conn = getConnection();
			sql = "SELECT CUSTNO, CUSTNAME, PHONE, ADDRESS, "
					+ " TO_CHAR(JOINDATE, 'YYYY-MM-DD'), GRADE, CITY "
					+ " FROM MEMBER_TBL_02";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setCustno(rs.getInt(1));
				vo.setCustname(rs.getString(2));
				vo.setPhone(rs.getString(3));
				vo.setAddress(rs.getString(4));
				vo.setJoindate(rs.getString(5));
				if(rs.getString(6).equals("A"))
					vo.setGrade("VIP");					
				else if(rs.getString(6).equals("B"))
					vo.setGrade("일반");
				else
					vo.setGrade("직원");
				vo.setCity(rs.getString(7));
				listvo.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return listvo;
	}
	public MemberVO getCust(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		MemberVO vo = new MemberVO();
		try {
			conn = getConnection();
			sql = "SELECT CUSTNO, CUSTNAME, PHONE, ADDRESS, "
					+ " TO_CHAR(JOINDATE, 'YYYYMMDD'), GRADE, CITY "
					+ " FROM MEMBER_TBL_02 WHERE CUSTNO=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo.setCustno(Integer.parseInt(rs.getString(1)));
				vo.setCustname(rs.getString(2));
				vo.setPhone(rs.getString(3));
				vo.setAddress(rs.getString(4));
				vo.setJoindate(rs.getString(5));
				vo.setGrade(rs.getString(6));
				vo.setCity(rs.getString(7));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeDBResource(rs, pstmt, conn);
		}
		return vo;
	}
	public int updateCust(MemberVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int rs = 0;
		try {
			conn = getConnection();
			sql = "UPDATE MEMBER_TBL_02 SET "
					+ " CUSTNAME=?, PHONE=?, ADDRESS=?, JOINDATE=TO_DATE(?, 'YYYYMMDD'), GRADE=?, CITY=? "
					+ " WHERE CUSTNO=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getCustname());
			pstmt.setString(2, vo.getPhone());
			pstmt.setString(3, vo.getAddress());
			pstmt.setString(4, vo.getJoindate());
			pstmt.setString(5, vo.getGrade());
			pstmt.setString(6, vo.getCity());
			pstmt.setInt(7, vo.getCustno());
			rs = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeDBResource(pstmt, conn);
		}
		return rs;
	}
	public ArrayList<SaleVO> saleCust() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		ArrayList<SaleVO> listvo = new ArrayList<SaleVO>();
		try {
			conn = getConnection();
			sql = "SELECT ME.CUSTNO, ME.CUSTNAME, ME.GRADE, "
					+ " SUM(MO.PRICE) AS SALES "
					+ " FROM MEMBER_TBL_02 ME "
					+ " JOIN MONEY_TBL_02 MO ON (ME.CUSTNO=MO.CUSTNO) "
					+ " GROUP BY ME.CUSTNO, ME.CUSTNAME, ME.GRADE "
					+ " ORDER BY SALES DESC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				SaleVO vo = new SaleVO();
				vo.setCustno(rs.getInt(1));
				vo.setCustname(rs.getString(2));
				if(rs.getString(3).equals("A"))
					vo.setGrade("VIP");					
				else if(rs.getString(3).equals("B"))
					vo.setGrade("일반");
				else
					vo.setGrade("직원");
				vo.setSale(rs.getInt(4));
				listvo.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeDBResource(rs, pstmt, conn);
		}
		return listvo;
	}
}
