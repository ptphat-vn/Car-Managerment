
package data;
/*
Car: khuôn đúc các chiếc xe
*/
public class Car implements Comparable<Car>{
    //props
    private String carID;
    private Brand brand;
    private String color;
    private String frameID;
    private String engineID;
    //constructor

    public Car() {
    }

    public Car(String carID, Brand brand, String color, 
                                    String frameID, String engineID) {
        this.carID = carID;
        this.brand = brand;
        this.color = color;
        this.frameID = frameID;
        this.engineID = engineID;
    }
    //getter và setter

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFrameID() {
        return frameID;
    }

    public void setFrameID(String frameID) {
        this.frameID = frameID;
    }

    public String getEngineID() {
        return engineID;
    }

    public void setEngineID(String engineID) {
        this.engineID = engineID;
    }
    
    //toString:

    @Override
    public String toString() {
        String str = String.format("%s,%s,%s,%s,%s",
                            carID, brand.getBrandID(), color, frameID, engineID);
        return str;
    }
    public String screenString() {
        String str = String.format("%s\n%s,%s,%s,%s",
                            brand , carID, color, frameID, engineID);
        return str;
    }

    @Override
    public int compareTo(Car that) {
        String thisBrandName = this.brand.getBrandName();
        String thatBrandName = that.brand.getBrandName();
        if(thisBrandName.compareTo(thatBrandName)> 0){
            return 1;
        }else if(thisBrandName.compareTo(thatBrandName) == 0){
            //so qua carID
            return this.getCarID().compareTo(that.getCarID());
        }
        
        return -1;
    }
    
    
    
}
