package sss.model;
import java.io.Serializable;
import java.sql.Date;

public class Schdule  implements Serializable{
    private int sched_id;
    private int studio_id;
    private int play_id;
    private double sched_ticket_price;
    public void setSched_id(int sched_id) {
        this.sched_id = sched_id;
    }

    public void setStudio_id(int studio_id) {
        this.studio_id = studio_id;
    }

    public void setPlay_id(int play_id) {
        this.play_id = play_id;
    }

    public void setSched_time(Date sched_time) {
        this.sched_time = sched_time;
    }

    public void setSched_ticket_price(double sched_ticket_price) {
        this.sched_ticket_price = sched_ticket_price;
    }

    private Date sched_time;

    public int getSched_id() {
        return sched_id;
    }

    public int getStudio_id() {
        return studio_id;
    }

    public int getPlay_id() {
        return play_id;
    }

    public Date getSched_time() {
        return sched_time;
    }

    public double getSched_ticket_price() {
        return sched_ticket_price;
    }




}
