package sss.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import sss.idao.ISale;
import sss.ConnectionManager;
import sss.model.Sale;

import java.sql.Connection;
import java.util.ArrayList;


/*
插入一条销售记录，返回-1则失败，其他返回值为刚插入的条目的id
*/
public class SaleDAO implements ISale {
    @Override
    public int insert(Sale sale){
        int result = -1;
        if(sale == null)
            return result;

        // 获取Connection
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try
        {
            String sql = "insert into sale(emp_id, sale_time, sale_payment, sale_change,sale_type,sale_status) values(?,?,?,?,?,?)";
            pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, sale.getEmp_id());
            pstmt.setTime(2, sale.getSale_time());
            pstmt.setFloat(3, sale.getSale_payment());
            pstmt.setFloat(4, sale.getSale_change());
            pstmt.setInt(5,sale.getSale_type());
            pstmt.setInt(6,sale.getSale_status());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                result = id;
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            // 关闭连接
            ConnectionManager.close(null, pstmt, con);
            return result;
        }
    }

    @Override
    public ArrayList<Sale> findSaleAll(){
        ArrayList<Sale> list = new ArrayList<Sale>();
        return list;
    }

    @Override
    public Sale findSaleByID(int id){
        Sale info = null;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            // 获取所有用户数据
            pstmt = con.prepareStatement("select * from Sale where sale_ID=?");
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if(rs.next())
            {
                // 如果有值的话再实例化
                info = new Sale();
                info.setSale_id(rs.getInt("sale_id"));
                info.setEmp_id(rs.getInt("emp_id"));
                info.setSale_time(rs.getTime("sale_time"));
                info.setSale_payment(rs.getFloat("sale_payment"));
                info.setSale_change(rs.getFloat("sale_change"));
                info.setSale_type(rs.getInt("sale_type"));
                info.setSale_status(rs.getInt("sale_status"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionManager.close(rs, pstmt, con);
            return info;
        }
    }
}
