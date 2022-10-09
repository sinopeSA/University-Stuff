#include <stdio.h>
#include <stdlib.h>
int n, array[100],received[100],count,choice;
int inputs(int array[100],int n)
{
    count=0;
     for(int i=0 ; i<n;i++)
    { scanf("%d",&array[i]);}
    for(int i=0;i<n;i++)
    {   if (array[i]==1)
           count++;
    }
}
int parity(int array[100], int choice){
     switch (choice) {
        case 1: 
            array[n]= count%2;
            break;
        case 2: 
            if(count%2 == 0)
                array[n]=1;
            else array[n]=0;
            break;
        default : printf("invalid choice");
    }
}
int main(int argc, char** argv) {
    printf("Enter the Number of bits:");
    scanf("%d",&n);
    printf("Enter bit: ");
    inputs(array,n);
    printf("1. Even Parity\n2. Odd parity\n");
    printf("select the choice: ");
    scanf("%d",&choice);
    parity(array,choice);
    printf("\nEnter the received data: ");
    inputs(received,n);
    parity(received,choice);
    if(array[n]==received[n])  
        printf("\nSystem has No Error");
    else printf("\nSystem has an Error");
    return (EXIT_SUCCESS);
}
