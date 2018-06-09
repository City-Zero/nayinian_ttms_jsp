package sss.dao;

import sss.ConnectionManager;
import sss.idao.ISchdule;
import sss.model.Schdule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.concurrent.ScheduledExecutorService;

public class SchduleDAO implements ISchdule{
    @Override
    public boolean insert(Schdule schdule) {
        boolean result =false;
        if(schdule==null){
            return result;
        }
        //获取Connection
        Connection con=ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt=null;
        try{
            String sql="insert into Schdule(sched_id,studio_id,play_id,sched_time,sched_ticket_price) values(?,?,?,?,?)";
            pstmt=con.prepareStatement(sql);
            pstmt.setInt(1,schdule.getSched_id());
            pstmt.setInt(2,schdule.getStudio_id());
            pstmt.setInt(3,schdule.getPlay_id());
            pstmt.setDate(4,schdule.getSched_time());
            pstmt.setDouble(5,schdule.getSched_ticket_price());
            pstmt.executeUpdate();
            result=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            //关闭连接
            ConnectionManager.close(null, pstmt, con);
            return result;
        }
    }

    @Override
    public boolean delete(int sched_id) {
       boolean result=false;
       if(sched_id==-1){
           return result;
       }
        //获取Connection
        Connection con=ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt=null;
        try{
            //删除某条演出计划
            String sql="delete from schdule where sched_id=?";
            pstmt =con.prepareStatement(sql);
            pstmt.setInt(1,sched_id);
            pstmt.executeUpdate();
            ConnectionManager.close(null,pstmt,con);
            result =true;
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            //关闭连接
            ConnectionManager.close(null,pstmt,con);
            return result;
        }
    }

    @Override
    public boolean update(Schdule schedule) {
        boolean result=false;
        if(schedule==null){
            return result;
        }
        //获取Connection
        Connection con=ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt=null;
        try{

            String sql="update schedule set studio_id=?,play_id=?,sched_time=?,sched_ticket_price=? where sched_id=?";
            pstmt =con.prepareStatement(sql);
            pstmt.setInt(1, schedule.getStudio_id());
            pstmt.setInt(2, schedule.getPlay_id());
            pstmt.setDate(3, schedule.getSched_time());
            pstmt.setDouble(4, schedule.getSched_ticket_price());
            pstmt.executeUpdate();
            result =true;
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            //关闭连接
            ConnectionManager.close(null,pstmt,con);
            return result;
        }
    }

    @Override
    public ArrayList<Schdule> findSchduleAll(int offset,int nums) {
        ArrayList<Schdule> list= new ArrayList<Schdule>();
        Schdule info=null;
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            //获取所有演出计划
            pstmt=con.prepareStatement("select * from Schdule limit ? , ?");
            pstmt.setInt(1,offset);
            pstmt.setInt(2,nums);
            rs=pstmt.executeQuery();
            while(rs.next()){
                info=new Schdule();
                info.setSched_id(rs.getInt("sched_id"));
                info.setStudio_id(rs.getInt("studio_id"));
                info.setPlay_id(rs.getInt("play_id"));
                info.setSched_time(rs.getDate("sched_time"));
                info.setSched_ticket_price(rs.getDouble("sched_ticket_price"));
                list.add(info);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            //关闭连接
            ConnectionManager.close(null,pstmt,con);
            return list;
        }
    }

    @Override
    public ArrayList<Schdule> findSchduleByPlayId(int play_id) {
        ArrayList<Schdule> list= new ArrayList<Schdule>();
        Schdule info=null;
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            //获取所有演出计划
            pstmt=con.prepareStatement("select * from schedule where play_id=?");
            pstmt.setInt(1,play_id);
            rs=pstmt.executeQuery();
            while(rs.next()){
                info=new Schdule();
                info.setSched_id(rs.getInt("sched_id"));
                info.setStudio_id(rs.getInt("studio_id"));
                info.setPlay_id(play_id);
                info.setSched_time(rs.getDate("sched_time"));
                info.setSched_ticket_price(rs.getDouble("sched_ticket_price"));
                list.add(info);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            //关闭连接
            ConnectionManager.close(null,pstmt,con);
            return list;
        }
    }

    @Override
    public Schdule findSchduleById(int Schdule_id){
        Schdule info=null;
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            //获取所有演出计划
            pstmt=con.prepareStatement("select * from schedule where sched_id=?");
            pstmt.setInt(1,Schdule_id);
            rs=pstmt.executeQuery();
            while(rs.next()){
                info=new Schdule();
                info.setSched_id(rs.getInt("sched_id"));
                info.setStudio_id(rs.getInt("studio_id"));
                info.setPlay_id(rs.getInt("play_id"));
                info.setSched_time(rs.getDate("sched_time"));
                info.setSched_ticket_price(rs.getDouble("sched_ticket_price"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            //关闭连接
            ConnectionManager.close(null,pstmt,con);
            return info;
        }
    }
}
