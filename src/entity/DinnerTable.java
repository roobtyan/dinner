package entity;

import java.util.Date;

/**
 * 餐桌实体类
 */
public class DinnerTable {
    /**
     * 餐桌id
     */
    private int id;
    /**
     * 餐桌名称
     * */
    private String tableName;
    /**
     * 餐桌名称
     * */
    private int table_status;
    /**
     * 预订时间
     */
    private Date orderDate;

    /**
     * 重写toString
     * @return
     */
    @Override
    public String toString() {
        return "DinnerTable{" +
                "id=" + id +
                ", tableName='" + tableName + '\'' +
                ", table_status=" + table_status +
                ", orderDate=" + orderDate +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getTable_status() {
        return table_status;
    }

    public void setTable_status(int table_status) {
        this.table_status = table_status;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
