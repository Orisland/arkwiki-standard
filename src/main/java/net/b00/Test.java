package net.b00;


public class Test {

	public static void main(String[] args) {
		/*int[] arr = getArr(10);
		//selectSort(arr);//ѡ������
		//selectBubbling(arr);//ð�ݷ�����
		selectInsert(arr);//���뷨����
		//��ӡ���
		System.out.println("");
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i] + ",");
		}*/
	}
	
	public static int[] getArr(int num){
		//����10����
		int[] arr = new int[num];
		for(int i=0;i<num;i++){
			arr[i] = (int)(1+Math.random()*(num-1+1));
			System.out.print(arr[i] + ",");
		}
		return arr;
	}
	
	//���뷨���� ��ǰֵ��������ֵ�Ա� С�Ĳ�����ǰ���������
	public static void selectInsert(int[] arr){
		int length = arr.length;
		for(int i=1;i<length;i++){
			int key = arr[i];
			int a = i - 1;
			System.out.println("�Ƚ�" + arr[a] + ">" + key);
			while(a >= 0 && arr[a] > key){//�����һ��Ԫ�ش��ڵڶ���Ԫ��
				System.out.println("�ƶ�" + arr[a + 1] + "=" + arr[a]);
				arr[a + 1] = arr[a];//ȫ��������1��
				a--;
			}
			arr[a + 1] = key;
		}
	}
	
	//ð�ݷ����� ��ǰ�������ڱȽ�
	public static void selectBubbling(int[] arr){
		int length = arr.length;
		for(int i=0;i<length;i++){
			for(int a=0;a<length - 1;a++){
				System.out.println("�Ƚ�" + arr[a + 1] + "<" +arr[a]);
				if(arr[a + 1] < arr[a]){//�Ƚ���һ��ֵ�뵱ǰֵ�Ĵ�С
					swap(arr,a,a + 1);
				}
			}
		}
	}

	//ѡ������ ѡ����С�ķŵ���ǰ��
	public static void selectSort(int[] arr){
		int length = arr.length;//���鳤��
		for(int i=0;i<length;i++){
			int minNum = i;//Ĭ�ϰѵ�ǰ��Сֵ����Ϊi������
			for(int a=i +1;a<length;a++){//a=i+1��Ϊ�˱����ظ��Ƚ�
				System.out.println("�Ƚ�" + arr[a] + "<" +arr[minNum]);
				if(arr[a] < arr[minNum]){//�ҳ���ǰΪ�Ƚ�������С��ֵ
					minNum = a;
				}
			}
			if(minNum != i){//�����С��ֵ���ڵ�ǰλ��
				swap(arr,i,minNum);//����λ��
			}
		}
	}
	
	//��������������ֵλ��
	public static void swap(int[] arr,int i,int a){
		int temp = arr[i];
		arr[i] = arr[a];
		arr[a] = temp;
	}

	
	
}
