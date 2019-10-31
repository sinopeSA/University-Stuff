/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   main.c
 * Author: SHUBHAM AGARWAL
 *
 * Created on September 6, 2019, 12:14 PM
 */

#include <stdio.h>
#include <stdlib.h>

struct node {
    unsigned distance[20];
    unsigned from[20];
} routingTable[10];

int main(int argc, char** argv) {
    int distanceMat[20][20];
    int node, i, j, k, count = 0;
    printf("\nEnter the number of nodes : ");
    scanf("%d", &node);
    printf("\nEnter the cost matrix :\n");
    for (i = 0; i < node; i++)
        for (j = 0; j < node; j++) {
            scanf("%d", &distanceMat[i][j]);
            distanceMat[i][i] = 0;
            routingTable[i].distance[j] = distanceMat[i][j];
            routingTable[i].from[j] = j;
        }
    do {
        count = 0;
        for (i = 0; i < node; i++)
            for (j = 0; j < node; j++)
                for (k = 0; k < node; k++)
                    if (routingTable[i].distance[j] > distanceMat[i][k] + routingTable[k].distance[j]) {
                        routingTable[i].distance[j] = routingTable[i].distance[k] + routingTable[k].distance[j];
                        routingTable[i].from[j] = k;
                        count++;
                    }
    } while (count != 0);
    for (i = 0; i < node; i++) {
        printf("\n\nState value for router %d is \n", i + 1);
         printf("\t\nDestination node:\tNEXT HOP:\t Cost:\n");
        for (j = 0; j < node; j++) {
            printf("\n%d\t\t\t%d\t%d", j + 1, routingTable[i].from[j] + 1, routingTable[i].distance[j]);
        }
    }
    printf("\n\n");
    return (EXIT_SUCCESS);
}

