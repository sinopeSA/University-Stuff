/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weathersimulation;
import java.util.Random;
// @author SHUBHAM AGARWAL
public class WeatherSimulation {
    public static void main(String[] args) {
       Random rand = new Random(); 
       double[] rainData={0,0,1,2,3,4,5};
       double[] probRain={0,0.5,0.25,0.15,0.05,0.03,0.02};
       double[] noRainData={0,0,1,2,3};
       double[] probNoRain={0,0.75,0.15,0.06,0.04};
       double[] cummProbRain=new double[7];
       double[] cummProbNoRain=new double[5];
       double[] rdd=new double[11];
       double[] rdRain=new double[7];
       double[] rdnorain=new double[5];
       double[] raining = new double[11];
       double rainadder=0,norainadder=0,total=0;
       int i,j,count=0;
       boolean previousdayrain=true;
       
       for(i=0;i< probRain.length;i++)
       {
           rainadder+=probRain[i];
           cummProbRain[i]=rainadder;
           rdRain[i]=cummProbRain[i]*100;
       }
       
       for(i=0;i< probNoRain.length;i++)
       {
           norainadder+=probNoRain[i];
           cummProbNoRain[i]=norainadder;
           rdnorain[i]=cummProbNoRain[i]*100;
       }
       System.out.println("DAY \tRandom Distribution \tRained in cm");
      
       for(i=1;i<=10;i++)
       {
            rdd[i] = rand.nextInt(100)+1; //random number generation
            if(previousdayrain)
            {
                 for(j=1;j<5;j++)
                    {
                        if(rdd[i]>rdnorain[j-1] && rdd[i]<=rdnorain[j])
                        {
                            raining[i]=noRainData[j];
                            count++;
                            if(noRainData[j]!=0)
                               previousdayrain= false;
                        }
                    }
            }
            else
                {
                 for(j=1;j<7;j++)
                    {
                        if(rdd[i]>rdRain[j-1] && rdd[i]<=rdRain[j])
                        {
                            raining[i]=rainData[j];
                            if(rainData[j]==0)
                                previousdayrain=true;
                        }
                    }
                }
            total+=raining[i];
            System.out.println(i+"\t\t"+rdd[i]+"\t\t  "+raining[i]);
       }
        System.out.println("The total day without rains is:"+count+"days");
        System.out.println("The total rain during the period is:"+total+"cm");
    }
}
