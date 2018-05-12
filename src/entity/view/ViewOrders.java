package entity.view;

import javax.naming.Name;

/**
 * 接受从前端的值，为临时使用
 * @author 言立慧
 */
public class ViewOrders {
    /**
     * 菜品id
     */
    private int food_id;
    /**
     * 菜品名称
     */
    private String foodName;
    /**
     * 菜品单价
     */
    private double price;
    /**
     * 菜品总数目
     */
    private int count = 1;
    /**
     * 菜品价格小计
     */
    private double smPrice;

    @Override
    public String toString() {
        return "ViewOrders{" +
                "food='" + foodName + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", smPrice=" + smPrice +
                '}';
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getSmPrice() {
        smPrice = count * price;
        return smPrice;
    }

    public void setSmPrice(double smPrice) {
        this.smPrice = smPrice;
    }
}
