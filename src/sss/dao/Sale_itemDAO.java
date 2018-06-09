package sss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import sss.idao.ISale_item;
import sss.model.Employee;
import sss.model.Sale_item;
import sss.ConnectionManager;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Sale_itemDAO implements ISale_item {
    @Override
    public int insert(Sale_item si){
        return 0;
    }

    @Override
    public ArrayList<Sale_item> findSaleAll(){
        ArrayList<Sale_item> list = new ArrayList<Sale_item>();
        Sale_item info = null;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            // 获取所有用户数据
            pstmt = con.prepareStatement("select * from sale_item");
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                info = new Sale_item();

                info.setSale_ID(rs.getInt("sale_item_id"));
                info.setTicket_id(rs.getInt("ticket_id"));
                info.setSale_ID(rs.getInt("sale_ID"));
                info.setSale_item_price(rs.getFloat("sale_item_price"));
                // 加入列表
                list.add(info);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionManager.close(rs, pstmt, con);
            return list;
        }
    }

    @Override
    public Sale_item findSaleByID(int id){
        return null;
    }
}
