package sss.model;

import java.sql.Time;

public class Sale {
    private int sale_id;
    private int emp_id ;
    private Time sale_time;
    private float sale_payment;
    private float sale_change;
    private int sale_type;
    private int sale_status;

    public int getSale_id() {
        return sale_id;
    }

    public void setSale_id(int sale_id) {
        this.sale_id = sale_id;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public Time getSale_time() {
        return sale_time;
    }

    public void setSale_time(Time sale_time) {
        this.sale_time = sale_time;
    }

    public float getSale_payment() {
        return sale_payment;
    }

    public void setSale_payment(float sale_payment) {
        this.sale_payment = sale_payment;
    }

    public float getSale_change() {
        return sale_change;
    }

    public void setSale_change(float sale_change) {
        this.sale_change = sale_change;
    }

    public int getSale_type() {
        return sale_type;
    }

    public void setSale_type(int sale_type) {
        this.sale_type = sale_type;
    }

    public int getSale_status() {
        return sale_status;
    }

    public void setSale_status(int sale_status) {
        this.sale_status = sale_status;
    }
}
