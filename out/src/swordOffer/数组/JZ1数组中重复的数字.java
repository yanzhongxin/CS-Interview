package swordOffer.数组;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author www.yanzhongxin.com
 * @date 2020/9/8 22:44
 * 数组中重复的数字
 */
public class JZ1数组中重复的数字 {

    //时间0(n方)数组遍历o(n),下一层比较函数o(n),空间复杂度o(n方)  空间o(n)
    public boolean duplicate(int numbers[],int length,int [] duplication) {
        if ((length==0)||numbers==null)
            return  false; //length=0或者number数组为空肯定不存在重复的数字，返回false
        ArrayList arrays=new ArrayList<Integer>();
        //用list集合存放需要查找的重复数字，这里空间复杂度o(n)
        for (int i=0;i<length;i++){
            if (!arrays.contains(numbers[i])){//不包含的话，加入到list
                arrays.add(numbers[i]);
            }else {
                //发现第一个重复数字
                duplication[0]=numbers[i];// 用duplication数组第一个位置存放重复数字
                return true;//说明包含重复数字
            }
        }
        return  false;//list存放了所有的number都没有找到重复数字，返回false
    }
    //第二种算法，时间复杂度O(n) 空间复杂度o(1)

    public static boolean duplicate1(int numbers[],int length,int [] duplication) {
        if (numbers==null || length==0)
            return false;//数组空或者长度0返回false不存在重复元素
        for (int i=0;i<length;i++){

            while ( numbers[i]!=i){// 数组元素不等于下标，说明元素不属于该位置
                if (!swap(numbers,i,numbers[i])){//互换失败说明，第二次出现,3 1 2 1 4
                    duplication[0]=numbers[i];
                    return  true;
                }
            }

        }
        return false;
    }
    public static boolean swap(int temp[],int now,int desk){//互换数组元素
        //temp 3 1 2 1 4 ,now=0 desk=
        if (temp[now]==temp[desk]){
            //需要互换的目的index数组中包含的数值，等于当前下表中数组数值，找到了重复数字
            return false;
        }
        int test=temp[now];
        temp[now]=temp[desk];
        temp[desk]=test;
        return  true;
    }
    public static void main(String[] args) {
        int[] array={2,1,3,1,4};
        int [] dump=new int[5];
        duplicate1(array,5,dump);
        System.out.println(dump[0]);
    }
    /*
    * 利用set不包含重复元素，数组元素添加到set集合中，添加失败说明是已经包含了
    * 时间复杂度
    * */
    public static boolean duplicate2(int numbers[],int length,int [] duplication) {
        if (numbers == null || length == 0)
            return false;//数组空或者长度0返回false不存在重复元素
        HashSet<Integer> set=new HashSet<>();
        for (int i=0;i<length;i++){
            if (!set.add(numbers[i])){
                //添加失败，说明已经包含了该元素
                duplication[0]=numbers[i];
                return true;
            }
        }
        return  false;

    }
}
/*
[2,1,3,1,4]
3 1 2 1 4
1 1 2 3 4
*/
