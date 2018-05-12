package entity;

/**
 * 订餐详细实体类
 * @author 言立慧
 */
public class OrderDetail {
    int id;
    int order_id;
    int food_id;
    int food_count;
    int order_status;

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", order_id=" + order_id +
                ", food_id=" + food_id +
                ", food_count=" + food_count +
                ", order_status=" + order_status +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public int getFood_count() {
        return food_count;
    }

    public void setFood_count(int food_count) {
        this.food_count = food_count;
    }

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }
}
