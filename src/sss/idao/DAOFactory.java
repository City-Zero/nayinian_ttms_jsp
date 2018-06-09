package sss.idao;

import sss.dao.EmployeeDAO;
import sss.dao.SaleDAO;
import sss.dao.StudioDAO;
import sss.dao.UserDAO;
import sss.dao.PlayDAO;
import sss.dao.SchduleDAO;
import sss.dao.Sale_itemDAO;
import sss.dao.xiaoshouDAO;
/**
 * Created by zxw on 17-11-19.
 */

public class DAOFactory
{
    public static IEmployee creatEmployeeDAO()
    {
        return new EmployeeDAO();
    }

    public static IUser createUserDAO() { return new UserDAO(); }

    public static IStudio createStudioDAO() {
        return new StudioDAO();
    }

    public static ISale createSaleDAO(){ return new SaleDAO();}

    public static IPlay createPlayDAO(){ return new PlayDAO();}

    public static ISchdule createSchduleDAO(){ return new SchduleDAO();}

    public static ISale_item createSale_itemDAO(){ return new Sale_itemDAO();}

    public static Ixiaoshou createxiaoshouDAO(){return new xiaoshouDAO();}
}