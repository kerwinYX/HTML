import java.util.ArrayList;
import java.util.List;

public class FIFO {

    public static void main(String[] args) {
        String inputStr[] = {"6", "7", "6", "5", "9", "5", "8", "9", "7", "6", "9", "6"};
        // �ڴ��
        int memory = 3;
        List<String> list = new ArrayList<>();
        for(int i = 0; i < inputStr.length; i++){
            if(i == 0){
                list.add(inputStr[i]);
                System.out.println("��"+ i +"�η��ʣ�\t\t" + ListUtils.listToString(list));
            }else {
                if(ListUtils.find(list, inputStr[i])){
                    System.out.println("��" + i + "��" + "���ʣ�\t\t" + ListUtils.listToString(list));
                }else{
                    if(list.size() < memory){
                        list.add(inputStr[i]);
                    }else{
                        list.remove(0);
                        list.add(inputStr[i]);

                    }
                    System.out.println("��" + i + "��" + "���ʣ�\t\t" + ListUtils.listToString(list));
                }
            }
        }
    }

}
