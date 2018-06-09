package sss.api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sss.idao.DAOFactory;
import sss.model.Schdule;
import sss.model.xiaoshou;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Time;

import static sss.idao.DAOFactory.createxiaoshouDAO;

/**
 * Created by zxw on 17-12-19.
 */
@WebServlet(name = "user",urlPatterns = "/api/xiaoshou")
public class xiaoshouServer extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        需要实现3种条件的查询
        time：区间，在sale表
        剧目：在play表<--schedule<--ticket<--sale_item
        销售员：在sale表<--sale_item
         */
        response.setContentType("text/json;charset=utf-8");
        Writer out = response.getWriter();

        String movie = request.getParameter("movie");
        String people = request.getParameter("people");
        String start = request.getParameter("start");
        String end = request.getParameter("end");
        System.out.println(movie+people+start+end);
        int movie_id = -1;
        if(movie != null && !movie.equals("")){
            try{
                movie_id = Integer.parseInt(movie);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        int people_id = -1;
        if(people != null && !people.equals("")){
            try{
                people_id = Integer.parseInt(people);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        ArrayList<xiaoshou> list = DAOFactory.createxiaoshouDAO().findxiaoshouAll(movie_id,people_id,start,end);


        JSONObject json = new JSONObject();
        JSONArray jsonarr;
        JSONArray all = new JSONArray();
        if (list.size() == 0){
            json.put("status",false);
            out.write(json.toString());
            return;
        }
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH：mm：ss");
        for (xiaoshou s : list
                ){
            jsonarr = new JSONArray();
            jsonarr.add(String.valueOf(s.getSale_item_id()));
            jsonarr.add(String.valueOf(s.getSale_item_price()));
            jsonarr.add(String.valueOf(s.getEmp_id()));
            jsonarr.add(String.valueOf(sdf.format(s.getSale_time())));
            jsonarr.add(String.valueOf(s.getSale_type()));
            jsonarr.add(String.valueOf(s.getSale_status()));

            all.add(jsonarr);
        }
        json.put("status",true);
        json.put("object", all);

        out.write(json.toString());;
    }
}
