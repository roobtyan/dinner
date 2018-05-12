package entity;

import java.util.Date;

/**
 * 订餐实体类
 * @author 言立慧
 */
public class Orders {
    private int id;
    private int table_id;
    private Date order_date;
    private double total_price;
    private int order_status;

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", table_id=" + table_id +
                ", order_date=" + order_date +
                ", total_price=" + total_price +
                ", order_status=" + order_status +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTable_id() {
        return table_id;
    }

    public void setTable_id(int table_id) {
        this.table_id = table_id;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }
}
