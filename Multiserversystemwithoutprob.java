/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiserversystemwithoutprob;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author SHUBHAM AGARWAL
 */
public class Multiserversystemwithoutprob {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      Scanner in = new Scanner (System.in); 
      Random rand = new Random();
      System.out.printf("Enter the no. of Requests:");
      int request = in.nextInt(),total=0;
      int[] iat=new int[10000], st =new int[10000],at= new int[10000],ssA= new int[10000],ssB= new int[10000];
      int[] seA = new int[10000],seB = new int[10000], wait =new int[10000], tis = new int[10000];
      double sumwait=0.00,sumtis=0.00;
      seA[0]=0;seB[0]=0;
      int idxablefree=0, idxbakerfree=0;
      System.out.printf("Reqno \tIAT \tST \tAT \tSSA \tSEA \tSSB \tSEB \tWait \tTIS\n");
      for(int i=1;i<=request;i++){
          iat[i] = rand.nextInt(8)+1; //random number generation
          st[i] = rand.nextInt(6)+1; //random number generation
          at[i]=iat[i]+at[i-1]; //initilization 
          boolean ableIsFree = seA[idxablefree]<=at[i];
          boolean bakerIsFree = seB[idxbakerfree]<=at[i];
          if(ableIsFree)
                  {
                      ssA[i]=at[i];
                      seA[i]=ssA[i]+st[i];
                      idxablefree=i;
                      wait[i]=ssA[i]-at[i];
                  } //check and then initilize start time
          else if(bakerIsFree) 
          {
            ssB[i]=at[i];
            seB[i]=ssB[i]+st[i];
            idxbakerfree =i;
            wait[i]=ssB[i]-at[i];
          } 
          else
          {
              if(seA[idxablefree]<=seB[idxbakerfree])
              {
                  ssA[i]=seA[idxablefree];
                  seA[i]=seA[idxablefree]+st[i];
                  idxablefree=i;
                  wait[i]=ssA[i]-at[i];
              }
              else 
              {
                  ssB[i]=seB[idxbakerfree];
                  seB[i]=seB[idxbakerfree]+st[i];
                  idxbakerfree=i;
                  wait[i]=ssB[i]-at[i];
              }
          }
          tis[i]=wait[i]+st[i];
          sumwait+=wait[i];
          sumtis+=tis[i];
          System.out.printf("%d \t%d \t%d \t%d \t%d \t%d \t%d \t%d \t%d \t%d\n",i,iat[i],st[i],at[i],ssA[i],seA[i],ssB[i],seB[i],wait[i],tis[i]);
    }
       System.out.printf("\nAverage waiting time of a customer: %f\n",(sumwait/request));
       System.out.printf("Total time in the system  %f\n",sumtis);
   }
    }
