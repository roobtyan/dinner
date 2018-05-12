package utils;

/**
 * 封装所有的查询条件
 */
public class Condition {
    private int foodTypeId = -1;     //查询的id
    private String foodName;       //查询的菜品名称
    public int getFoodTypeId() {
        return foodTypeId;
    }

    public void setFoodTypeId(int foodTypeId) {
        this.foodTypeId = foodTypeId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
}
