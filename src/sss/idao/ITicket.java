package sss.idao;

import sss.model.Ticket;

import java.util.ArrayList;

public interface ITicket {
    public boolean insert(Ticket ticket);

    public boolean delete(int ticket_id);

    public boolean updata(Ticket ticket);

    public ArrayList<Ticket> findTicketAll(int offset, int nums);

    public Ticket findTicketById(int id);

}
