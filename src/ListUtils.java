import java.util.List;

public class ListUtils {

    public ListUtils() {

    }
    public static String listToString(List list){

        StringBuffer content = new StringBuffer();
        for(int i = 0; i < list.size(); i++){
            content.append(list.get(i));
            if(i < list.size() - 1){
                content.append(",");
            }
        }
        return content.toString();
    }
    public static boolean find(List<String> list, String str){
        boolean flag = false;
        for(String lis : list){
            if(lis.equals(str)){
                flag = true;
            }
        }
        return flag;
    }
    public static int findIndex(List<String> list, String str) {
        int index = 0;
        for(String lis : list) {
            if(lis.equals(str)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public static boolean clockJudge(String[] clock, int index) {
        if(clock[index].equals("0")) {
            return true;
        }
        return false;
    }

    public static int findZero(int index, String[] clock, int range) {

        while(true) {

            if(clock[index].equals("0")) {
                break;
            }else {
                clock[index] = "0";
                index++;
                if(index > range-1) {
                    index = Math.abs(range - index);
                }
            }
        }
        return index;
    }


    public static boolean strJudge(Object[] obj, String str) {
        boolean flag = false;
        if(obj == null) {
            return flag;
        }
        for(int i = 0; i < obj.length; i++) {
            if(str.equals(obj[i])) {
                flag = true;
                break;
            }
        }
        return flag;
    }



    public static int findNull(Object[][] str, int length, int memory) {

        int index = 0;
        if(str == null) {
            return -1;
        }
        for(int i = 0; i < memory; i++) {
            if(str[i][length] != null) {
                index = i;
            }
        }
        return index;
    }
}
