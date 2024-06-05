
package data;

public class Brand {
    //props
    private String brandID;
    private String brandName;
    private String soundBrand;
    private double price;
    //constructor
    //rỗng
    public Brand() {
    }
    //đầy đủ
    public Brand(String brandID, String brandName, String soundBrand, double price) {
        this.brandID = brandID;
        this.brandName = brandName;
        this.soundBrand = soundBrand;
        this.price = price;
    }
    //getter và setter

    public String getBrandID() {
        return brandID;
    }

    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getSoundBrand() {
        return soundBrand;
    }

    public void setSoundBrand(String soundBrand) {
        this.soundBrand = soundBrand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    //hàm tạo ra chuỗi đẹp chứ k in
    @Override
    public String toString() {
        String str = String.format("%s, %s, %s: %.3f", 
                    brandID, brandName, soundBrand, price);
        return str;
    }
    
    
}
