package HW5.Problem3;
import java.io.*;
import java.util.*;

//@sanchitmonga22
public class DoubleTrouble {
    private static class Coords{
        int x1;
        int y1;
        int x2;
        int y2;
        int distance;
        public Coords(int x1, int y1, int x2, int y2, int distance){
            this.x1=x1;
            this.x2=x2;
            this.y1=y1;
            this.y2=y2;
            this.distance=distance;
        }
        @Override
        public String toString(){
            return "x1:"+x1+" y1:"+y1+", x2:"+x2+" y2:"+y2;
        }
    }

    private static void print2dArray(char[][] input){
        for(int i=0;i<input.length;i++){
            for(int j=0;j<input[0].length;j++){
                System.out.print(input[i][j]+" ");
            }
            System.out.println();
        }
    }

    private static void addBorder(char[][] input){
        for(int i=0;i<input.length;i++){
            for(int j=0;j<input[0].length;j++){
                if(input[i][j]=='\0'){
                    input[i][j]='#';
                }
            }
        }
    }

    private static boolean eitherReachedEnd(int x1, int y1, int x2, int y2, char[][] input){
        if(input[x1][y1]=='#' && input[x2][y2]!='#'){
            return true;
        }else if(input[x1][y1]!='#' && input[x2][y2]=='#'){
            return true;
        }
        return false;
    }

    private static boolean checkSame(int x1, int y1, int x2, int y2,char[][] input){
        return x1==x2 && y1==y2;
    }

    private static int canExitMaze(char[][] input, Coords iniCoords){
        int rows= input.length;
        int cols= input[0].length;
        // (x1,y1),(x2,y2) to check whether or not it is seen
        boolean[][][][] seen = new boolean[rows][cols][rows][cols];
        Coords[] queue=new Coords[rows*cols*rows*cols];
        int beg=1;
        int end=2;
        queue[1]=iniCoords;
        seen[iniCoords.x1][iniCoords.y1][iniCoords.x2][iniCoords.y2]=true;
        while(beg<end){
            Coords headCoord= queue[beg];
            int x1=headCoord.x1, y1=headCoord.y1, x2=headCoord.x2, y2=headCoord.y2;
            char thing1=input[x1][y1],thing2=input[x2][y2];
            beg++;
            if(thing1=='#' && thing2=='#' && !checkSame(x1, y1, x2, y2, input)){
                return headCoord.distance;
            }
            if(eitherReachedEnd(x1, y1, x2, y2, input) || checkSame(x1, y1, x2, y2, input)){
                continue;
            }
            // moving down
            if(!seen[x1+1][y1][x2+1][y2] && input[x1+1][y1]!='x' && input[x2+1][y2]!='x'){
                queue[end]=new Coords(x1+1,y1,x2+1,y2, headCoord.distance+1);
                seen[x1+1][y1][x2+1][y2]=true;
                end++;
            }else if(!seen[x1+1][y1][x2][y2] && input[x1+1][y1]!='x'){
                queue[end]= new Coords(x1+1,y1,x2,y2, headCoord.distance+1);
                seen[x1+1][y1][x2][y2]=true;
                end++;
            }else if(!seen[x1][y1][x2+1][y2] && input[x2+1][y2]!='x'){
                queue[end]=new Coords(x1,y1,x2+1,y2, headCoord.distance+1);
                seen[x1][y1][x2+1][y2]=true;
                end++;
            }
            // moving up
            if(!seen[x1-1][y1][x2-1][y2] && input[x1-1][y1]!='x' && input[x2-1][y2]!='x'){
                queue[end]=new Coords(x1-1,y1,x2-1,y2, headCoord.distance+1);
                seen[x1-1][y1][x2-1][y2]=true;
                end++;
            }else if(!seen[x1-1][y1][x2][y2] && input[x1-1][y1]!='x'){
                queue[end]=new Coords(x1-1,y1,x2,y2, headCoord.distance+1);
                seen[x1-1][y1][x2][y2]=true;
                end++;
            }else if(!seen[x1][y1][x2-1][y2] && input[x2-1][y2]!='x' ){
                queue[end]=new Coords(x1,y1,x2-1,y2, headCoord.distance+1);
                seen[x1][y1][x2-1][y2]=true;
                end++;
            }
            // moving right
            if(!seen[x1][y1+1][x2][y2+1] && input[x1][y1+1]!='x' && input[x2][y2+1]!='x' ){
                queue[end]= new Coords(x1,y1+1,x2,y2+1, headCoord.distance+1);
                seen[x1][y1+1][x2][y2+1]=true;
                end++;
            }else if(!seen[x1][y1+1][x2][y2] && input[x1][y1+1]!='x' ){
                queue[end]= new Coords(x1,y1+1,x2,y2, headCoord.distance+1);
                seen[x1][y1+1][x2][y2]=true;
                end++;
            }else if(!seen[x1][y1][x2][y2+1] && input[x2][y2+1]!='x'){
                queue[end]=new Coords(x1,y1,x2,y2+1, headCoord.distance+1);
                seen[x1][y1][x2][y2+1]=true;
                end++;
            }
            // moving left
            if(!seen[x1][y1-1][x2][y2-1] && input[x1][y1-1]!='x' && input[x2][y2-1]!='x'){
                queue[end]=new Coords(x1,y1-1,x2,y2-1, headCoord.distance+1);
                seen[x1][y1-1][x2][y2-1]=true;
                end++;
            }else if(!seen[x1][y1-1][x2][y2] && input[x1][y1-1]!='x'){
                queue[end]=new Coords(x1,y1-1,x2,y2, headCoord.distance+1);
                seen[x1][y1-1][x2][y2]=true;
                end++;
            }else if(!seen[x1][y1][x2][y2-1] && input[x2][y2-1]!='x'){
                queue[end]=new Coords(x1,y1,x2,y2-1, headCoord.distance+1);
                seen[x1][y1][x2][y2-1]=true;
                end++;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        File file = new File("D:\\Fall 2020\\CSCI 261\\HW5\\Problem3\\input-3.7");
        //6,STUCK,STUCK,7,23,22,30
        Scanner sc;
		try {
            sc = new Scanner(file);
            String[] line1=sc.nextLine().split(" ");
            int rows= Integer.parseInt(line1[0])+2;
            int cols= Integer.parseInt(line1[1])+2;
            char[][] input= new char[rows][cols];
            int x1=-1;
            int y1=-1;
            int x2=-1;
            int y2=-1;
            for(int i=1;i<rows-1;i++){
                String inp =sc.nextLine();
                for(int j=1;j<cols-1;j++){
                    input[i][j]=inp.charAt(j-1);
                    if(input[i][j]=='1'){
                        x1=i;
                        y1=j;
                    }else if(input[i][j]=='2'){
                        x2=i;
                        y2=j;
                    }
                }
            }
            Coords initalConfig= new Coords(x1, y1, x2, y2,0);
            // adding the border
            addBorder(input);
            //print2dArray(input);
            // checking whether or not it can exit the maze
            int ans=canExitMaze(input,initalConfig);
            if(ans==-1){
                System.out.println("STUCK");
            }else{
                System.out.println(ans);
            }
            sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
}