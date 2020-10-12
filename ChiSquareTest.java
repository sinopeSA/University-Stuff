/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chisquaretest;
import java.util.Scanner;
/**
 *
 * @author SHUBHAM AGARWAL
 */
public class ChiSquareTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int obs_data[][]={{200,150,50},{250,300,50}};
        int rows, cols;
         Scanner input = new Scanner(System.in);
           //Calculates number of rows and columns present in given matrix    
         rows = obs_data.length;    
        cols = obs_data[0].length;    
        int sumRow[]=new int[rows];
        int sumCol[]=new int[cols];  
        int sum=0;
        //Calculates sum of each row of given matrix    
        for(int i = 0; i < rows; i++){    
            sumRow[i] = 0;     
            for(int j = 0; j < cols; j++)    
                 sumRow[i] += obs_data[i][j];   
              
        
            sum+=sumRow[i];
        }
             //Calculates sum of each column of given matrix    
        for(int i = 0; i < cols; i++)
        {    
            sumCol[i] = 0;    
            for(int j = 0; j < rows; j++)    
              sumCol[i]  += obs_data[j][i];    
    }
    
        double exp_data[][]=new double[rows][cols];
         for(int i = 0; i < rows; i++){     
            for(int j = 0; j < cols; j++){
                exp_data[i][j]=((sumRow[i]*sumCol[j])/(double)sum);
            }   
}
         double chisquare=0;
         for(int i = 0; i < rows; i++){     
            for(int j = 0; j < cols; j++){
                double od= (double)obs_data[i][j];
                double ed=(double)exp_data[i][j];
                chisquare +=((od-ed)*(od-ed))/ed;
            }
    }
         System.out.println("chi square value is:"+chisquare);
         int df= (rows-1)*(cols-1);
         System.out.println("Enter the critical value:");
         double cv= input.nextDouble();
        if(chisquare>cv)
            System.out.println("H0 is rejected");
        else if (chisquare<cv)
            System.out.println("Fails to reject");
}
}

