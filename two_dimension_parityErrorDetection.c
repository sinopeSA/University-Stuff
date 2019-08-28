#include <stdio.h>
#include <stdlib.h>
 int sent[100][100],count,choice,i,j,n,received[100][100];
 int inputs(int sent[100][100],int n)
 {
      for(i=0;i<n;i++)
    {
        for(j=0;j<n;j++)
            scanf("%d",&sent[i][j]);
    }
 }
  int parity_detection( int sent[100][100], int choice)
  {
       switch(choice)
    {
        case 1:
            for(i=0;i<n;i++)
            {
                count=0;
                for(j=0;j<n;j++)
                {
                    if(sent[i][j]==1)
                        count++;
                }
                    sent[i][n]=count%2;
            }
            for(j=0;j<n;j++)
            {
                count=0;
                for(i=0;i<n;i++);
                {
                    if(sent[i][j]==1)
                        count++;
                }
                    sent[n][j]=count%2;
            } break;
        case 2:
         for(i=0;i<n;i++)
            {
                count=0;
                for(j=0;j<n;j++)
                {
                    if(sent[i][j]==1)
                        count++;
                }
                if(count%2==0) 
                    sent[i][n]=1;
                else 
                    sent[i][n]=0;               
            }
            for(j=0;j<n;j++)
            {
                count=0;
                for(i=0;i<n;i++);
                {
                    if(sent[i][j]==1)
                        count++;
                }
                if(count%2==0) 
                    sent[n][j]=1;
                else sent[n][j]=0; 
            } break; 
        default: printf("wrong choice");       
    }
 }
int main(int argc, char** argv) {
    printf("Enter the square matrix size:");
    scanf("%d",&n);
    printf("Enter the 2D binary numbers(bits): \n");
    inputs(sent,n);
    printf("\n1. for even parity.\n2. for odd parity \nEnter your choice:");
    scanf("%d",&choice);
    parity_detection(sent,choice);
    printf("Enter the 2D binary received numbers(bits): \n");
    inputs(received,n);
    parity_detection(received,choice);      
    int error=0;
    for(i=0;i<=n;i++)
    {
        if( sent[n][i]!=received[n][i]  || sent[i][n]!=received[i][n])
            error++;
    }
    if (error > 0) printf("\nSystem has an Error");
    else printf("\nSystem has No Error");
    return (EXIT_SUCCESS);
}



