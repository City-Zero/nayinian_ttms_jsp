package sss.idao;

import java.util.ArrayList;
import sss.model.Sale;

public interface ISale {
    public int insert(Sale sale);

    public ArrayList<Sale> findSaleAll();

    public Sale findSaleByID(int id);
}
