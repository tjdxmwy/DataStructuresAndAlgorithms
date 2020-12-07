package com.horse.ch01.sparsearray;

/**
 * @Description 二维数组与稀疏数组
 * @Author Mr.Horse
 * @Version v1.0.0
 * @Since 1.0
 * @Date 2020/12/7
 */

/**
 * 二维数组转稀疏数组的思路：
 *      1.遍历原始的二维数组，得到有效数据的个数sum
 *      2.根据sum创建稀疏数组 sparseArray[sum+1][3]
 *      3.将二维数组的有效数据存入到稀疏数组中
 *  稀疏数组转二维数组的思路：
 *      1.先读取稀疏数组的第一行，根据第一行的前两列，创建原始的二维数组
 *      2.读取稀疏数组的后几行数据，并将值赋给原始二维数组
 */
public class SparseArray {
    public static void main(String[] args) {
        //1.创建一个原始的二维数组 11*11
        int[][] chessArr = new int[11][11];
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;
        chessArr[4][5] = 2;
        System.out.println("输出原始的二维数组");
        for(int[] row: chessArr) {
            for(int data: row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        System.out.println("===========华丽的分割线=============");

        //2.将二维数组转成稀疏数组
        //2.1.先统计原始二维数组中非0的个数
        int sum = 0;
        for (int[] row : chessArr) {
            for (int data : row) {
                if(data != 0) {
                    sum += 1;
                }
            }
        }
        //2.2.创建稀疏数组
        int[][] spraseArr = new int[sum+1][3];
        spraseArr[0][0] = 11;
        spraseArr[0][1] = 11;
        spraseArr[0][2] = sum;
        //2.3.遍历所有非0的数，并将其存入spraseArr中
        int count = 0; //用于记录是第几个非0数
        for(int i=0; i<11; i++) {
            for(int j=0; j<11; j++) {
                if(chessArr[i][j] != 0) {
                    count ++;
                    spraseArr[count][0] = i;
                    spraseArr[count][1] = j;
                    spraseArr[count][2] = chessArr[i][j];
                }
            }
        }
		
        //2.4输出稀疏数组
        System.out.println("输出稀疏数组");
        for(int i=0; i<spraseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\n", spraseArr[i][0], spraseArr[i][1], spraseArr[i][2]);
        }
        System.out.println("===========华丽的分割线=============");

        //3.将稀疏数组转成二维数组
        int[][] chessArr2 = new int[spraseArr[0][0]][spraseArr[0][1]];
        for(int i=1; i<spraseArr.length; i++) {
            chessArr2[spraseArr[i][0]][spraseArr[i][1]] = spraseArr[i][2];
        }
        System.out.println("输出恢复后二维数组");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
