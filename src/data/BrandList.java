
package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import ui.Menu;
import utils.Inputter;

/*
Tư duy 1: BrandList là anh quản lí danh sách brand
Tư duy 2: BrandList là danh sách các brand

*/
public class BrandList {
    //danh sách các dòng xe cần quản lí
    ArrayList<Brand> brandList = new ArrayList<>();
    //những method hỗ trợ xử lí các danh sách
    
    //hàm đọc file
    public boolean loadFromFile(String url){
        File f = new File(url);
        try {
            //xử lí file
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line = reader.readLine();
            while(line != null){
                //xử lí dòng
                StringTokenizer st = new StringTokenizer(line, ",");//coi chừng BUG
                String id = st.nextToken().trim();
                String name = st.nextToken().trim();
                String str = st.nextToken().trim();
                //hash stage 2
                st = new StringTokenizer(str, ":");//coi chừng BUG
                String sound = st.nextToken().trim();
                double price = Double.parseDouble(st.nextToken().trim());//coi chừng BUG
                //thu thập thông tin thì tạo
                Brand nbrand = new Brand(id, name, sound, price);//coi chừng BUG
                brandList.add(nbrand);
                line = reader.readLine();//đọc dòng tiếp theo
                
            }
            //thành công thì 
            return true;
        } catch (Exception e) {
            System.out.println("Brand file error: "+ e);
            return false;
        }
    }
    //hàm in
    public void listBrands(){
        if(brandList.isEmpty()){
            System.out.println("Brand List has nothing to print");
            return;//ngừng luônn
        }
        System.out.println("____BrandList____");
        for (Brand brand : brandList) {
            System.out.println(brand);//.toString();
        }
    }
    //hàm tìm id
    public int searchID (String keyID){
        for (int i = 0; i <= brandList.size() - 1; i++) {
            if(brandList.get(i).getBrandID().equals(keyID)){
                return i;
            }
        }
        return -1;
    }
    
    //hàm thêm mới
    public void addBrand(){
        //thu thập rồi mới đúc
        //id cấm trùng
        boolean isDup;
        String id;
        do{
            isDup = false;//reset tin là không trùng
            id = Inputter.getString("Input brand id: ",
                                    "That field is required!!");
            //kiểm tra xem có brand nào xài mã này chưa
            //nếu có thì nghĩa là bị trùng
            int pos = searchID(id);//nếu pos != -1 là tìm được => bị trùng
            if(pos != -1){
                System.out.println("Brand ID is has been used");
                isDup = true;
            }
        }while(isDup);//còn trùng thì nhập lại
        //name, sound, price
        String name = Inputter.getString("Input brand name: ",
                                        "That field is required!");
        String sound = Inputter.getString("Input brand sound: ",
                                        "That field is required!");
        double price = Inputter.getADouble("Input price: ",
                                        "Price  must be positive real number!"
                                        , 1, Math.pow(2, 53) - 1);
        //thu thập đủ thông tin thì tạo
        Brand nBrand = new Brand(id, name, sound, price);
        brandList.add(nBrand);
        System.out.println("Brand adding is successful");
        
    }
    //hàm updateBrand()
    public void updateBrand(){
        //xin id cần update
        String keyID = Inputter.getString("Input brand id youu wanna update: ", 
                                          "That field is required!");
        //dùng id tìm brand
        int pos = searchID(keyID);
        Brand brand = pos == -1 ? null : brandList.get(pos);
        //nếu có thì update
        if(brand == null){
            System.out.println("Not found");
        }else{
            System.out.println("The brand information before updating");
            System.out.println(brand);//.toString
            System.out.println("Updating.......");
            //thu thập xong thì set lại
            String name = Inputter.getString("Input new brand name",
                                            "That field is required!");
            String sound = Inputter.getString("Input new sound brand",
                                            "That field is required!");
            Double price = Inputter.getADouble("Input new price",
                                            "Price mustr be positive real number", 1, Math.pow(2, 53)- 1);
            brand.setBrandName(name);
            brand.setSoundBrand(sound);
            brand.setPrice(price);
            System.out.println("Brand updating is successful");
                    
        }
    }
    //hàm search();
    public void searchBrand(){
        //xin id cần update
        String keyID = Inputter.getString("Input brand id youu wanna find: ", 
                                          "That field is required!");
        //dùng id tìm brand
        int pos = searchID(keyID);
        Brand brand = pos == -1 ? null : brandList.get(pos);
        //nếu có thì update
        if(brand == null){
            System.out.println("Not found");
        }else{
            System.out.println("The brand information");
            System.out.println(brand);//.toString
            
        }
    }
    //hàm getUserChoice()
    //hiển thị danh sách brandList có đánh số
    //gọi ref_getChoice để xin người dùng chọn và trả ra Brand đã chọn
    public Brand getUserChoice(){
        //hiển thị brandList như menu có số
        int count = 1;
        System.out.println("_____BrandList_____");
        for (Brand brand : brandList) {
            System.out.println(count +". "+ brand);
            count++;
        }
        //tạo menu để sài ref_getChoice
        Menu <Brand> brandMenu = new Menu("brandListMenu");
        //thu thập lựa chọn và trả ra brand tương ứng
        return brandMenu.ref_getChoice(brandList);
    }
    
    //hàm save to file
    public boolean saveToFile(String url){
        File f = new File(url);
        try {
            //xử lí
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(f));
            for (Brand brand : brandList) {
                writer.write(brand.toString());
                writer.write("\n");
                //phải xuống hàng để có thể băm được
            }
            writer.flush();
            //xong thì
            return true;
        } catch (Exception e) {
            System.out.println("Save Brand file error: "+ e);
            return false;
        }
    }
    
    
}
