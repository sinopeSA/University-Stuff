/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   main.c
 * Author: SHUBHAM AGARWAL
 *
 * Created on August 30, 2019, 11:51 AM
 */

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
int main(int argc, char** argv) {
    int i,j;
    float x[100],y[100],distancetable[100][100],nodedistance,n=10,noderange;
       printf("co-ordinates are:\nX-co-ordinate:");
    for(i=1;i<=n;i++)
    {
        x[i]=1+ rand()%10;
        y[i]=1+ rand()%50;
        printf("|%.2f ",x[i]);
    }
       printf("\ny-co-ordinate:");
        for(i=1;i<=n;i++)
        {
            printf("|%.2f ",y[i]);
        }
        printf("\nThe Neighbor Table is:\n");
        printf("\t 1 \t 2\t 3\t 4\t 5\t 6\t 7\t 8\t 9\t 10\t\n\n");
    for(i=1;i<=n;i++)
    { printf("%d \t",i);
        for(j=1;j<=n;j++)
        {
            distancetable[i][j]=sqrt(((x[i]-x[j])*(x[i]-x[j]))+((y[i]-y[j])*(y[i]-y[j])));
            printf("|%.2f\t",distancetable[i][j]);
        } printf("\n");
    }
    printf("Enter the range of the node:");
    scanf("%f",&noderange);
     for(i=1;i<=n;i++)
    {
          printf("\nThe Neighbor of %d  is: ",i);
        for(j=1;j<=n;j++)
        {
            if(distancetable[i][j]<=noderange &&i!=j)
                printf("|%d| ",j);
        }
    }
    return (EXIT_SUCCESS);
}