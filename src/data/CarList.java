
package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import sun.util.locale.StringTokenIterator;
import utils.Inputter;

/*
CarList: là anh quản lí danh sách các chiếc xe carList
    thì ảnh có : danh sách các chiếc xe carList
                 có 1 anh quản lí các danh sách các "dòng xe" (BrandList)

 */
public class CarList {
    ArrayList<Car> carList = new ArrayList<>();
    BrandList brandList;//anh quản lí ds brandList tên là brandList
    
    //constructor
    public CarList(BrandList brandList) {
        this.brandList = brandList;
    }
    
    //hàm đọc file
    public boolean loadFromFile(String url){
        File f = new File(url);
        try {
            //xử lí file
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line = reader.readLine();
            while(line != null){
                //xử lí dòng
                StringTokenizer st = new StringTokenizer(line, ",");
                String cid = st.nextToken().trim();
                String bid = st.nextToken().trim();
                String color = st.nextToken().trim();
                String fid = st.nextToken().trim();
                String eid = st.nextToken().trim();
                //dùng bid tìm brand tương ứng
                int pos = brandList.searchID(bid);
                Brand brand = brandList.brandList.get(pos);//anh quản lí có brandList
                //tạo
                Car nCar = new Car(cid, brand, color, fid, eid);//coi chừng bị BUG
                carList.add(nCar);
                line = reader.readLine();//đọc dòng tiếp theo
            }
            //nếu oke thì
            return true;
        } catch (Exception e) {
            System.out.println("Car file Error: "+ e);
            return false;
        }
    }
    
    //hàm in
    public void listCars(){
        if(carList.isEmpty()){
            System.out.println("CarList has nothing to print");
            
            return;
        }
        Collections.sort(carList);//sắp xếp trước khi in
        
        for (Car car : carList) {
            System.out.println(car.screenString());
        }
    }
    //hàm tìm vị trí theo carID
    public int searchID (String carID){
        for (int i = 0; i <=carList.size() - 1; i++) {
            if(carList.get(i).getCarID().equals(carID)){
                return i;
            }
        }
        return -1;
    }
    
    //hàm tìm vị trí theo frameID
    public int searchFrame (String frameID){
        for (int i = 0; i <=carList.size() - 1; i++) {
            if(carList.get(i).getFrameID().equals(frameID)){
                return i;
            }
        }
        return -1;
    }
    
    //hàm tìm vị trí theo engineID
    public int searchEngine (String engineID){
        for (int i = 0; i <=carList.size() - 1; i++) {
            if(carList.get(i).getEngineID().equals(engineID)){
                return i;
            }
        }
        return -1;
    }
    
    //hàm thêm 1 chiếc xe
    public void addCar(){
        //thu thập 
        //id cấm trùng
        boolean isDup;
        String carID;
        do{
            isDup = false;//reset, tin là không bị trùng
            carID = Inputter.getString("Input carID: ",
                                        "That field is required!");
            //dùng carID tìm car
            int pos = searchID(carID);//nếu pos khác -1 nghĩa là tìm được
            if(pos != -1){//tìm được nghĩa là bị trùng
                System.out.println("CarID has been used!!");
                isDup = true;//bị trùng
            }
            
            
        }while(isDup);//còn trùng thì còn nhập lại
        //nhập brand bằng menu
        Brand brand = brandList.getUserChoice();
        //nhập color
        String color = Inputter.getString("Input color: ", 
                                            "That field is required!");
        //frameID theo format và cấm trùng
        String frameID;
        do{
            isDup = false;//reset, tin là không bị trùng
            frameID = Inputter.getString("Input frameID: ",
                                    "frameID must be match F00000", "F\\d{5}");
            //dùng carID tìm car
            int pos = searchFrame(frameID);//nếu pos khác -1 nghĩa là tìm được
            if(pos != -1){//tìm được nghĩa là bị trùng
                System.out.println("FrameID has been used!!");
                isDup = true;//bị trùng
            }
            
            
        }while(isDup);//còn trùng thì còn nhập lại
        //engineID theo format và cấm trùng
        String engineID;
        do{
            isDup = false;//reset, tin là không bị trùng
            engineID = Inputter.getString("Input frameID: ",
                                    "engineID must be match E00000", "E\\d{5}");
            //dùng carID tìm car
            int pos = searchEngine(engineID);//nếu pos khác -1 nghĩa là tìm được
            if(pos != -1){//tìm được nghĩa là bị trùng
                System.out.println("EngineID has been used!!");
                isDup = true;//bị trùng
            }
            
            
        }while(isDup);//còn trùng thì còn nhập lại
        
        //tạo
        Car nCar = new Car(carID, brand, color, frameID, engineID);
        carList.add(nCar);
        System.out.println("Car adding is successful");
    }
    //hàm in ra dựa vào BrandName(tìm dựa vào)
    public void printBasedBrandName(){
        //nhập 1 phần brandName cần tìm
        String key = Inputter.getString("Input a part of BrandName", 
                                        "That field is required!");
        //duyệt qua các chiếc xe, xe nào mà brandName có chứa key
        //thì in ra
        int count = 0;
        for (Car car : carList) {
            if(car.getBrand().getBrandName().contains(key)){
                //có chiếc xe nào mà dòng xe của nó có key không
                System.out.println(car.screenString());
                count++;
            }   
        }
        if(count == 0){
            System.out.println("No car is detected");
        }
    }
    
    //hàm remove
    public boolean removeCar(){
        String keyId = Inputter.getString("Input carID you wanna remove: ", 
                                        "That field is required!");
        //từ keyId tìm Car cần xóa
        int pos = searchID(keyId);
        Car car = pos == -1 ? null : carList.get(pos);
        if(car == null){
            System.out.println("Not found");
            return false;
        }else{
            System.out.println("Car Information: ");
            System.out.println(car.screenString());
            carList.remove(pos);
            System.out.println("Car remove successful!");
            return true;
        }
    }
    //hàm update
    public boolean updateCar(){
        String keyId = Inputter.getString("Input carID you wanna update: ", 
                                        "That field is required!");
        //từ keyId tìm Car cần xóa
        int pos = searchID(keyId);
        Car car = pos == -1 ? null : carList.get(pos);
        if(car == null){
            System.out.println("Not found");
            return false;
        }else{
            System.out.println("Car Information Before Updating: ");
            System.out.println(car.screenString());
            //thu thập thông tin mới
            //nhập brand bằng menu
            Brand brand = brandList.getUserChoice();
            //nhập color
            String color = Inputter.getString("Input color: ", 
                                                "That field is required!");
            //frameID theo format và cấm trùng
            boolean isDup;
            String frameID;
            do{
                isDup = false;//reset, tin là không bị trùng
                frameID = Inputter.getString("Input frameID: ",
                                        "frameID must be match F00000", "F\\d{5}");
                //dùng carID tìm car
                pos = searchFrame(frameID);//nếu pos khác -1 nghĩa là tìm được
                if(pos != -1){//tìm được nghĩa là bị trùng
                    System.out.println("FrameID has been used!!");
                    isDup = true;//bị trùng
                }


            }while(isDup);//còn trùng thì còn nhập lại
            //engineID theo format và cấm trùng
            String engineID;
            do{
                isDup = false;//reset, tin là không bị trùng
                engineID = Inputter.getString("Input frameID: ",
                                        "engineID must be match E00000", "E\\d{5}");
                //dùng carID tìm car
                pos = searchEngine(engineID);//nếu pos khác -1 nghĩa là tìm được
                if(pos != -1){//tìm được nghĩa là bị trùng
                    System.out.println("EngineID has been used!!");
                    isDup = true;//bị trùng
                }


            }while(isDup);//còn trùng thì còn nhập lại


            //set
            car.setBrand(brand);
            car.setColor(color);
            car.setFrameID(frameID);
            car.setEngineID(engineID);
            System.out.println("Car updating is successful");
            return true;
        }
    }
    //hàm save to file
    public boolean saveToFile(String url){
        File f = new File(url);
        try {
            //xử lí
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(f));
            for (Car car : carList) {
                writer.write(car.toString());
                writer.write("\n");
                //phải xuống hàng để có thể băm được
            }
            writer.flush();
            //xong thì
            return true;
        } catch (Exception e) {
            System.out.println("Save Car file error: "+ e);
            return false;
        }
    }
}
