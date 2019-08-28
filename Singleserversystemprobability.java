package singleserversystemprobability;
import java.util.Random;
import java.util.Scanner;

public class Singleserversystemprobability {

    public static void main(String[] args) {

      Scanner in = new Scanner (System.in); 
      Random rand = new Random();
      System.out.printf("Enter the no. of Requests:");

      int request = in.nextInt(),total=0; 
      double cummtotal=0;
      double[] probability={0.0,0.10, 0.20, 0.30, 0.25, 0.10, 0.05};
      int[] iat=new int[10000], St ={1,2,3,4,5,6},at= new int[10000],ss= new int[10000],st=new int[10000];
      int[] se = new int[10000], wait =new int[10000], idle = new int[10000],rdd=new int[10000];
      double[] rd=new double[10000];
      double[] cummprob=new double[100];
      double sumwait=0.00, sumidle=0.00,sumservice=0.00,sumiat=0.00;
      se[0]=0;rd[0]=0;
      
      for(int i=1;i<=6;i++)
      {
          cummtotal += probability[i];
          cummprob[i]=cummtotal;
           rd[i]=100*cummprob[i];
      }

      System.out.printf("Reqno \tRDD \tIAT \tST \tAT \tSS \tSE \twait \tidle\n");
     
	for(int i=1;i<=request;i++){
          iat[i] = rand.nextInt(8)+1; //random number generation
          rdd[i] = rand.nextInt(100)+1; //random number generation
          total += iat[i]; // cummilative sum
          at[i]=total; //initilization 
          for(int j=1;j<=request;j++)
          {
              if(rdd[i]>rd[j-1] && rdd[i]<=rd[j])
                  st[i]=St[j-1];
          }
          if((at[i]-se[i-1])>=0) ss[i]=at[i]; //check and then initilize start time
          else ss[i]=se[i-1]; 
          se[i]=ss[i]+st[i];
          wait[i]=ss[i]-at[i]; 
          idle[i]=ss[i]-se[i-1];
          
          sumwait+=wait[i];
          sumidle+=idle[i];
          sumservice+=st[i];
          sumiat+=iat[i];
          System.out.printf("%d \t%d \t%d \t%d \t%d \t%d \t%d \t%d \t%d\n",i,rdd[i],iat[i],st[i],at[i],ss[i],se[i],wait[i],idle[i]);
         
      }
       System.out.printf("\nAverage waiting time of a customer: %f\n",(sumwait/request));
       System.out.printf("Idle time of the ATM machine: %f\n",sumidle);
       System.out.printf("Average service time: %f\n",(sumservice/request));
       System.out.printf("Average time between arrivals: %f\n", sumiat/request);
   }
}

