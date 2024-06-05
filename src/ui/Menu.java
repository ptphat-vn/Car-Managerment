package ui;

import java.util.ArrayList;
import utils.Inputter;

/*
Menu: là 1 cái khuôn chuyên tạo ra các anh quản lý menu
có: danh sách các lựa chọn 'optionList'
có: tên của menu 'tilte'
có: những hàm thao tác với optionList
//thêm 1 option vào optionList
//hiển thị danh sách optionList
//hàm thu thập lựa chọn của người dùng
*/
public class Menu<T> {
    //mảng lưu các lựa chọn
    public ArrayList<String> optionList = new ArrayList<>();
    public String title;//tên menu
    //constructor
    public Menu(String title){
        this.title = title;
    }
    //hàm addNewOption: thêm 1 option cho danh sách options
    public void addNewOption(String newOption){
        optionList.add(newOption);
    }
    //hàm print: hiển thị danh sachs options kèm số phía trước
    public void print(){
        int count = 1;
        System.out.println("______"+title+"______");
        for (String op : optionList) {
            System.out.println(count + "." + op);
            count++;
        }
    }
    
    
    public int getChoice(){
        int choice = Inputter.getAnInteger("Input your choice",
                        "Your choice must between 1 and "+ optionList.size(),
                                            1, optionList.size());
        return choice;
    }
    
    public T ref_getChoice(ArrayList<T> options){
        
        int choice = Inputter.getAnInteger("Input SEQ of brand: ", 
                                            "SEQ must be between 1 and " + options.size(),
                                            1 , options.size());
        return options.get(choice - 1);
    }
}
