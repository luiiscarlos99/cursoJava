package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement(
						"INSERT INTO seller " +
						" (Name, Email, BirthDate, BaseSalary, DepartmentId) "	+
						"VALUES " + 
						"(?, ?, ?, ?, ?),(?, ?, ?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS
					);
			
			st.setString(1, "Luis");
			st.setString(2, "luis@gmail.com");
			st.setDate(3, new java.sql.Date(sdf.parse("12/02/1999").getTime()));
			st.setDouble(4, 3000.0);
			st.setInt(5, 4);		
			st.setString(6, "Luis");
			st.setString(7, "luis@gmail.com");
			st.setDate(8, new java.sql.Date(sdf.parse("12/02/1999").getTime()));
			st.setDouble(9, 3000.0);
			st.setInt(10, 4);	
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				while(rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Done! Id: " + id);
				}
			}
			else {
				System.out.println("fail");
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		catch(ParseException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}
