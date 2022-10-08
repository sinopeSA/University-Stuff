/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atmsimulation;
import java.util.ArrayList; 
import java.util.Random;

/**
 *
 * @author SHUBHAM AGARWAL
 */
public class AtmSimulation {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        ArrayList<Integer> interArrTime= new ArrayList<>();
        ArrayList<Integer> servicetime= new ArrayList<>();
        ArrayList<Integer> arrivalTime=new ArrayList<>();
        ArrayList<Integer> servicestartTime=new ArrayList<>();
        ArrayList<Integer> serviceendTime=new ArrayList<>();
        ArrayList<Integer> waitTime=new ArrayList<>();
        ArrayList<Integer> idleTime=new ArrayList<>();
        ArrayList<Integer> serviceCompleteTime=new ArrayList<>();
        Random rand = new Random();
    
        interArrTime.add(0);servicetime.add(0);arrivalTime.add(0);
        serviceendTime.add(0);servicestartTime.add(0);
        idleTime.add(0); waitTime.add(0);serviceCompleteTime.add(0);
        
        int sumwait=0,sumidle=0, i=1;
        System.out.println("NOTE: All the calculations are in minutes");
        System.out.printf("Reqno \tIAT \tST \tAT \tSS \tSE \twait \tidle \tSCT\n");
     
         while(true)
         {
             interArrTime.add(rand.nextInt(4)+1);
             servicetime.add(rand.nextInt(6)+1);
             arrivalTime.add(arrivalTime.get(i-1)+interArrTime.get(i));

            if((arrivalTime.get(i) - serviceendTime.get(i-1))>=0 )
                 servicestartTime.add(arrivalTime.get(i));
            else servicestartTime.add(serviceendTime.get(i-1));
         
            serviceendTime.add(servicestartTime.get(i)+servicetime.get(i));
            waitTime.add(servicestartTime.get(i)-arrivalTime.get(i));
            idleTime.add(servicestartTime.get(i)-serviceendTime.get(i-1));
            serviceCompleteTime.add(servicetime.get(i)+waitTime.get(i));
             
            sumwait+=waitTime.get(i);
           // sumservice+=servicetime.get(i);
            sumidle+=idleTime.get(i);
            if(serviceendTime.get(serviceendTime.size()-1)<=12*60) {
            System.out.print(i+" \t"+interArrTime.get(i)+" \t"+servicetime.get(i)+" \t");
            System.out.print(arrivalTime.get(i)+" \t"+servicestartTime.get(i)+" \t"+serviceendTime.get(i));
            System.out.println(" \t"+waitTime.get(i)+" \t"+idleTime.get(i)+" \t"+serviceCompleteTime.get(i));
            }
            else break;
               i++;
         }       
    }
}
