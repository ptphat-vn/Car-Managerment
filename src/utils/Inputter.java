
package utils;

import java.util.Scanner;

/*
Là 1 cái khuôn nhưng anh không dùng để đúc ra Object 
mà anh dùng Class này để lưu những hàm hỗ trợ cho việc nhập
*/
public class Inputter {
    //props
    public static Scanner sc = new Scanner(System.in);
    //Những method hỗ trợ cho việc nhập
    //1. Method ép người dùng nhập số nguyên chuẩn
    //có 1 cái hàm mời nhập số nguyên, nếu đúng thì trả ra số nguyên
    //sai thì chửi
    public static int getAnInteger(String inpMsg, String errMsg){
        System.out.println(inpMsg);//hiển thị câu mời nhập
        while(true){
            try {
                int number = Integer.parseInt(sc.nextLine());
                return number;
            } catch (Exception e) {
                System.out.println(errMsg);//hiển thị câu chửi
            }
        }
    }
    //2.Method ép nhập số nguyên chuẩn nhưng ở trong khoảng yêu cầu
    public static int getAnInteger(String inpMsg, String errMsg,
                                                int lowerBound, int upperBound){
        if(lowerBound > upperBound){//đổi chổ nếu người dùng khờ
            int tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        System.out.println(inpMsg);//hiển thị câu mời nhập
        while(true){
            try {
                int number = Integer.parseInt(sc.nextLine());
                if(number < lowerBound || number > upperBound){
                    throw new Exception();//tạo ra 1 cái ngoại lệ
                }
                return number;
            } catch (Exception e) {
                System.out.println(errMsg);//hiển thị câu chửi
            }
        }
    }
    //1. Method ép người dùng nhập số thực chuẩn
    //có 1 cái hàm mời nhập số thực, nếu đúng thì trả ra số thực
    //sai thì chửi
    public static double getADouble(String inpMsg, String errMsg){
        System.out.println(inpMsg);//hiển thị câu mời nhập
        while(true){
            try {
                double number = Double.parseDouble(sc.nextLine());
                return number;
            } catch (Exception e) {
                System.out.println(errMsg);//hiển thị câu chửi
            }
        }
    }
    //2.Method ép nhập số thực chuẩn nhưng ở trong khoảng yêu cầu
    public static double getADouble(String inpMsg, String errMsg,
                                        double lowerBound, double upperBound){
        if(lowerBound > upperBound){//đổi chổ nếu người dùng khờ
            double tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        System.out.println(inpMsg);//hiển thị câu mời nhập
        while(true){
            try {
                double number = Double.parseDouble(sc.nextLine());
                if(number < lowerBound || number > upperBound){
                    throw new Exception();//tạo ra 1 cái ngoại lệ
                }
                return number;
            } catch (Exception e) {
                System.out.println(errMsg);//hiển thị câu chửi
            }
        }
    }
    //1.nhập chuỗi không được để trống
    public static String getString(String inpMsg, String errMsg){
        System.out.println(inpMsg);
        while(true){
            try {
                String str = sc.nextLine();
                if(str.isEmpty()){//ktra cái chuỗi có bị rỗng không
                    throw new Exception();//rỗng thì báo lỗi
                }
                return str;
            } catch (Exception e) {
                System.out.println(errMsg);
            }
        }
    }
    //2.nhập chuỗi không được để trống và phải đúng regex
    //kiểm tra xem người dùng nhập đúng hay hongg
    public static String getString(String inpMsg, String errMsg, String regex){
        System.out.println(inpMsg);
        while(true){
            try {
                String str = sc.nextLine();
                if(str.isEmpty() || !str.matches(regex)){
                //ktra cái chuỗi có bị rỗng không và có đúng format không
                    throw new Exception();//rỗng thì báo lỗi
                }
                return str;
            } catch (Exception e) {
                System.out.println(errMsg);
            }
        }
    }
    //3. Hàm nhập ngày tháng năm theo format
    //f(câu mời, câu chưỡi, 'dd - mm - yyyy')
    
}
