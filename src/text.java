import java.util.Arrays;

public class text {
  public static void main(String[] args) {

        text T  = new text();

        int[] scores={79,52,98,81};


        // ���÷���������ɼ����飬����ȡ�ɼ��ĸ���

        int count= T.sort(scores);



        System.out.println("����"+count+"���ɼ���Ϣ��");
    }

        /*
         * ���ܣ������Գɼ�������������سɼ��ĸ���
         * ����һ������������������ķ���������ɼ�����
         * ʹ�� Arrays ��Գɼ���������������
         * ����ִ�к󷵻�������Ԫ�صĸ���
         */
        public int sort(int[] scores){
            Arrays.sort(scores);
            System.out.println(Arrays.toString(scores));

            // ����������Ԫ�صĸ���
            return scores.length;
        }
    }

