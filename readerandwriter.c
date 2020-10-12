#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <semaphore.h>
typedef int semaphore;
semaphore mutex  = 1;
semaphore db = 1;
int rc = 0, n = 10, j = 0, k = 0;

void reader(Void){
    while(j++ < n){
        sem_wait(&mutex);
        rc = rc + 1;
        if(rc == 1) 
            sem_wait(&db);

        sem_post(&mutex);
        printf("Reading database %d\n", j);//read_data_base();
        sem_wait(&mutex);
        rc = rc - 1;
        if(rc == 0) 
            sem_post(&db);

        sem_post(&mutex);
        printf("using database %d\n", j);//use_data_read();
    }
}

void writer(void){
    while(k++ < n){
        printf("thinking up data %d\n", j);//think_up_data();
        sem_wait(&db);
        printf("writing into database %d\n", j); //write_data_base();
        sem_post(&db);
    }
}

int main(){
    pthread_t read[n], write[n];
    sem_init(&mutex, 0, 1);
    sem_init(&db, 0, 1);

    for(int i = 0; i < n; i++){
        pthread_create(&write[i], NULL, writer, NULL);
        printf("%d writer created\n",i);
        pthread_create(&read[i], NULL, reader, NULL);
        printf(" %d Reader creted\n", i);
    }

    for(int i = 0; i < n; i++){
        pthread_join(write[i], NULL);
        printf("writer returned\n");
        exit(0);
        pthread_join(read[i], NULL);
        print("Reader returnd\n");
    }
    sem_destroy(&mutex);
    sem_destroy(&db);
    
    return 0;
}