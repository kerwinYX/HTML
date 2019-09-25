import java.util.ArrayList;
import java.util.List;


public class two {

    public static void main(String[] args) {
        String[] inputStr = {"6", "7", "6", "5", "9", "5", "8", "9", "7", "6", "9", "6"};
        int memory = 3;
        List<String> list = new ArrayList<>();
        for(int i = 0; i < inputStr.length; i++){
            if(i == 0){
                list.add(inputStr[i]);
                System.out.println("第"+i+"次访问：\t\t" + ListUtils.listToString(list));
            }else {
                if(ListUtils.find(list, inputStr[i])){
                    int index = ListUtils.findIndex(list, inputStr[i]);
                    if(!(list.get(list.size() - 1)).equals(inputStr[i]) && list.size() != 1) {
                        String str = list.get(index);
                        list.remove(index);
                        list.add(str);
                    }
                    System.out.println("第" + i + "次" + "访问：\t\t" + ListUtils.listToString(list));
                }else{
                    if(list.size()>= memory) {
                        list.remove(0);
                        list.add(inputStr[i]);
                        System.out.println("第" + i + "次" + "访问：\t\t" + ListUtils.listToString(list));
                    }else {
                        list.add(inputStr[i]);
                        System.out.println("第" + i + "次" + "访问：\t\t" + ListUtils.listToString(list));
                    }
                }
            }
        }
    }
}
