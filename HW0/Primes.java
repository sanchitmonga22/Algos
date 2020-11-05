package HW0;

import java.util.Scanner;

//@author: Sanchit Monga

class Primes {
    /**
     * @param num The number that has to be checked whether it is a prime number or not
     * @return boolean, whether the number is a prime number or not
     */
    private static boolean checkPrime (int num){
        if(num==2){
            return true;
        }
        else if(num<2){
            return false;
        }
        boolean flag=false;
        for(int i=2;i<=num/2;i++){
            if(num%i==0){
                flag=true;
                break;
            }
        }
        return !flag;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int max= sc.nextInt();
        for(int i=0;i<=max;i++){
            if(checkPrime(i)){
                System.out.println(i);
            }
        }
        sc.close();
    }
}