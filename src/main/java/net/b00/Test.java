package net.b00;


public class Test {

	public static void main(String[] args) {
		/*int[] arr = getArr(10);
		//selectSort(arr);//选择法排序
		//selectBubbling(arr);//冒泡法排序
		selectInsert(arr);//插入法排序
		//打印结果
		System.out.println("");
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i] + ",");
		}*/
	}
	
	public static int[] getArr(int num){
		//生成10个数
		int[] arr = new int[num];
		for(int i=0;i<num;i++){
			arr[i] = (int)(1+Math.random()*(num-1+1));
			System.out.print(arr[i] + ",");
		}
		return arr;
	}
	
	//插入法排序 当前值与已排序值对比 小的插入在前面整体后移
	public static void selectInsert(int[] arr){
		int length = arr.length;
		for(int i=1;i<length;i++){
			int key = arr[i];
			int a = i - 1;
			System.out.println("比较" + arr[a] + ">" + key);
			while(a >= 0 && arr[a] > key){//如果第一个元素大于第二个元素
				System.out.println("移动" + arr[a + 1] + "=" + arr[a]);
				arr[a + 1] = arr[a];//全部往后移1次
				a--;
			}
			arr[a + 1] = key;
		}
	}
	
	//冒泡法排序 从前到后相邻比较
	public static void selectBubbling(int[] arr){
		int length = arr.length;
		for(int i=0;i<length;i++){
			for(int a=0;a<length - 1;a++){
				System.out.println("比较" + arr[a + 1] + "<" +arr[a]);
				if(arr[a + 1] < arr[a]){//比较下一个值与当前值的大小
					swap(arr,a,a + 1);
				}
			}
		}
	}

	//选择法排序 选择最小的放到最前面
	public static void selectSort(int[] arr){
		int length = arr.length;//数组长度
		for(int i=0;i<length;i++){
			int minNum = i;//默认把当前最小值定义为i的索引
			for(int a=i +1;a<length;a++){//a=i+1是为了避免重复比较
				System.out.println("比较" + arr[a] + "<" +arr[minNum]);
				if(arr[a] < arr[minNum]){//找出当前为比较区内最小的值
					minNum = a;
				}
			}
			if(minNum != i){//如果最小的值不在当前位置
				swap(arr,i,minNum);//交换位置
			}
		}
	}
	
	//交换两个索引的值位置
	public static void swap(int[] arr,int i,int a){
		int temp = arr[i];
		arr[i] = arr[a];
		arr[a] = temp;
	}

	
	
}
