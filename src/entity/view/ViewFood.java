package entity.view;

/**
 * 菜品视图类
 * @author roobtyan
 */
public class ViewFood {
    private int id;
    private String foodName;
    private int food_type_id;
    private double price;
    private double mprice;
    private String remark;
    private String img;
    private String typeName;

    @Override
    public String toString() {
        return "ViewFood{" +
                "id=" + id +
                ", foodName='" + foodName + '\'' +
                ", food_type_id=" + food_type_id +
                ", price=" + price +
                ", mprice=" + mprice +
                ", remark='" + remark + '\'' +
                ", img='" + img + '\'' +
                ", typeName='" + typeName + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getFood_type_id() {
        return food_type_id;
    }

    public void setFood_type_id(int food_type_id) {
        this.food_type_id = food_type_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getMprice() {
        return mprice;
    }

    public void setMprice(double mprice) {
        this.mprice = mprice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
