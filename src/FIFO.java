import java.util.ArrayList;
import java.util.List;

public class FIFO {

    public static void main(String[] args) {
        String inputStr[] = {"6", "7", "6", "5", "9", "5", "8", "9", "7", "6", "9", "6"};
        // 内存块
        int memory = 3;
        List<String> list = new ArrayList<>();
        for(int i = 0; i < inputStr.length; i++){
            if(i == 0){
                list.add(inputStr[i]);
                System.out.println("第"+ i +"次访问：\t\t" + ListUtils.listToString(list));
            }else {
                if(ListUtils.find(list, inputStr[i])){
                    System.out.println("第" + i + "次" + "访问：\t\t" + ListUtils.listToString(list));
                }else{
                    if(list.size() < memory){
                        list.add(inputStr[i]);
                    }else{
                        list.remove(0);
                        list.add(inputStr[i]);

                    }
                    System.out.println("第" + i + "次" + "访问：\t\t" + ListUtils.listToString(list));
                }
            }
        }
    }

}
