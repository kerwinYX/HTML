import java.io.BufferedInputStream;
import java.util.*;


public class first {
    private int n;//�ڴ�ҳ��
    private int m;//���ʴ���
    private int F;//û��ֱ���ҵ��Ĵ���,(F/m)Ϊȱҳ��
    private List<Integer> list=null;//���ʵ�ַ����
    private Map<Integer,Integer> map=null;
    public first(){
        F=0;
        map=new HashMap<Integer,Integer>();//�洢ÿһ���ڴ�ҳ�����������
        Scanner cin=new Scanner(new BufferedInputStream(System.in));
        System.out.println("�������û�����ҳ��ַ����");
        list=new ArrayList<Integer>();
        String s=cin.nextLine();
        String []s1=s.split(" ");
        m=s1.length;
        for (int i=0;i<m;i++)
            list.add(Integer.valueOf(s1[i]));
        System.out.println("�������ڴ�Ҷ������");
        n=cin.nextInt();
        menu();
        switch(cin.nextInt()){
            case 1:OPT();break;
            case 2:FIFO();break;
            case 3:LRU();break;
        }
        cin.close();
    }
    public void menu(){
        System.out.println("**** ѡ������û��㷨�밴1 **********");
        System.out.println("**** ѡ���Ƚ��ȳ��û��㷨�밴2 *******");
        System.out.println("**** ѡ�������Զδʹ���û��㷨�밴3 ***");
    }
    public void OPT(){//����û��㷨
        int j;
        for (int i=0;i<m;i++)
        {
            int k=list.get(i);//������Ԫ��
            //System.out.print(map);
            if (!map.containsValue(k)){
                F++;//����ֱ���ҵ�������1
                if (map.size()<n){//���û��װ��
                    int temp=map.size();
                    map.put(temp, k);
                }
                else{//���װ����
                    int index=0;//���ĸ�λ�õ���̭��ȥ
                    int min=0;//��ʼ�����
                    for (int t=0;t<n;t++)
                    {
                        for (j=i+1;j<m;j++){//��������һ�����ֵ�����
                            if (list.get(j)==map.get(t)){//��һ���ҵ�
                                if (j-i>min){
                                    index=t;//����ֵ
                                    min=j-i;
                                }
                                break;
                            }
                        }
                        if (j==m){//��������
                            index=t;
                            min=j-i;
                        }
                    }
                    map.remove(index);
                    map.put(index,k);//�޸ı���Ԫ��
                }
            }
        }
        System.out.println("������Ϊ:"+F*1.0/m);
    }
    public void FIFO(){//�Ƚ��ȳ��û��㷨
        Queue<Integer>q=new LinkedList<Integer>();
        for (int i=0;i<m;i++)
        {
            int k=list.get(i);//������Ԫ��
            if (!map.containsValue(k)){
                F++;//����ֱ���ҵ�������1
                if (map.size()<n){//���û��װ��
                    int temp=map.size();
                    map.put(temp, k);
                    q.offer(temp);
                }
                else
                {
                    int temp=q.poll();//�ų���Ԫ��λ��
                    map.remove(temp);
                    map.put(temp,k);
                    q.offer(temp);

                }
            }
        }
        System.out.println("������Ϊ:"+F*1.0/m);
    }
    public void LRU(){//�����Զδʹ���û��㷨
        List<Integer>linkedlist=new LinkedList<Integer>();
        int start=0;
        for (int i=0;i<m;i++)
        {
            int k=list.get(i);//������Ԫ��
            if (!map.containsKey(k)){
                F++;//����ֱ���ҵ�������1
                if (map.size()<n){//���û��װ��
                    int temp=map.size();
                    map.put(k,temp);
                    linkedlist.add(k);//���λ��
                }
                else
                {
                    int temp=linkedlist.get(0);
                    int c=map.get(temp);//λ��
                    map.remove(temp);
                    map.put(k,c);
                    linkedlist.remove(0);
                    linkedlist.add(k);
                }
            }
            else//����������ֵ�������ֵ���߲��ں���ѹ��
            {
                int d=linkedlist.indexOf(k);//���Ҵ���λ��
                linkedlist.remove(d);
                linkedlist.add(k);
            }
        }
        System.out.println("������Ϊ:"+F*1.0/m);
    }
    public static  void main(String args[]){
        first p=new first();
    }

}
