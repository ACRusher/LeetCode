/**
 * @author xiliang.zxl
 * @date 2015-11-21 下午3:15
 */
public class NumMatrix {

    int[][] matrix;
    int width;
    int height;

    public NumMatrix(int[][] matrix) {
        this.matrix=matrix;
        width=matrix[0].length;
        height=matrix.length;
        init();
    }
    public void init(){
        for(int i=0;i<height;++i)
            for(int j=0;j<width;++j){
                if(j-1>=0) matrix[i][j] += matrix[i][j-1];
            }
        for(int i=0;i<height;++i)
            for(int j=0;j<width;++j){
                if(i-1>=0) matrix[i][j] += matrix[i-1][j];
            }
    }
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return getRangeSum(row2,col2)-getRangeSum(row2,col1-1)-getRangeSum(row1-1,col2)
                +getRangeSum(row1-1,col1-1);
    }

    private int getRangeSum(int x,int y){
        if(x<0 || y<0 || x>=height || y>=width) return 0;
        return matrix[x][y];
    }

    public static void main(String[] args) {
        int[][] arr={{1,2,3},{1,2,3},{1,2,3}};
        NumMatrix numMatrix=new NumMatrix(arr);
        System.out.println(numMatrix.getRangeSum(1,1));
        System.out.println(numMatrix.getRangeSum(0,1));
        System.out.println(numMatrix.sumRegion(1,1,1,1));
    }
}
