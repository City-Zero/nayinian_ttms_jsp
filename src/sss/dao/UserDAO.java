package sss.dao;

import sss.ConnectionManager;
import sss.idao.IUser;
import sss.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by zxw on 17-11-19.
 */

public class UserDAO implements IUser{

    @Override
    public boolean insert(User user) {
        boolean result = false;
        if(user == null)
            return result;

        // 获取Connection
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try
        {
            String sql = "insert into user(emp_no, emp_pass, type, head_path) values(?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user.getEmp_no());
            pstmt.setString(2, user.getEmp_pass());
            pstmt.setInt(3, user.getType());
            pstmt.setString(4, user.getHead_path());

            pstmt.executeUpdate();
            result = true;
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
    public boolean delete(String emp_no) {
        boolean result = false;
        if(emp_no == "")
            return result;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try
        {
            // 删除子某个用户
            String sql = "delete from user where emp_no=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, emp_no);
            pstmt.executeUpdate();
            ConnectionManager.close(null, pstmt, con);

            result = true;
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
    public boolean update(User user) {
        boolean result = false;
        if(user == null)
            return result;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try
        {
            String sql = "update user set emp_pass=?,type=?,head_path=? where emp_no=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user.getEmp_pass());
            pstmt.setInt(2, user.getType());
            pstmt.setString(3,user.getHead_path());
            pstmt.setString(4,user.getEmp_no());
            System.out.println(pstmt);
            pstmt.executeUpdate();
            result = true;
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
    public ArrayList<User> findUserAll(int offset, int nums) {
        ArrayList<User> list = new ArrayList<User>();
        User info = null;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            // 获取所有用户数据
            pstmt = con.prepareStatement("select * from user limit ? , ?");
            pstmt.setInt(1,offset);
            pstmt.setInt(2,nums);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                info = new User();
                info.setEmp_no(rs.getString("emp_no"));
                info.setEmp_pass(rs.getString("emp_pass"));
                info.setType(rs.getInt("type"));
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
    public User findUserById(String emp_no) {
        User info = null;
        if(emp_no == null || emp_no.equals(""))
            return info;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            // 获取所有用户数据
            pstmt = con.prepareStatement("select * from user where emp_no=?");
            pstmt.setString(1, emp_no);
            rs = pstmt.executeQuery();
            if(rs.next())
            {
                // 如果有值的话再实例化
                info = new User();
                info.setEmp_no(rs.getString("emp_no"));
                info.setEmp_pass(rs.getString("emp_pass"));
                info.setType(rs.getInt("type"));
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

    @Override
    public User findUserByNo(String emp_no) {
        User info = null;
        if(emp_no == null || emp_no.equals(""))
            return info;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            // 获取所有用户数据
            pstmt = con.prepareStatement("select * from user where emp_no=?");
            pstmt.setString(1, emp_no);
            rs = pstmt.executeQuery();
            if(rs.next())
            {
                // 如果有值的话再实例化
                info = new User();
                info.setEmp_no(emp_no);
                info.setEmp_pass(rs.getString("emp_pass"));
                info.setType(rs.getInt("type"));
                info.setHead_path(rs.getString("head_path"));
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