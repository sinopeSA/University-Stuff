#include <stdio.h>
#include <stdlib.h>
#include <semaphore.h>
#include <time.h>
#include <pthread.h>
#define DEBUG(P) {printf("%d ", P);};printf("\n");
#define N 10
sem_t mutex, full, empty;
int front = 0;
int rear = 0;
int CQueue[N];

int produce_item() {
    int p = 1 + rand() % 40;
    return p;
}

void insert_item(int item) {
    if (rear == N)
        rear = 0;
    CQueue[rear++] = item;
    printf("The Item Inserted is %d\n", item);
}

int remove_item() {
    if (front == N)
        front = 0;
    int e = CQueue[front];
    return e;
}

void consume_item(int item) {
    printf("The Item Consumed is %d\n", item);
}

void producer(void *a) {
    int item, i;
    for (i = 0; i < 20; i++) 
    {
        item = produce_item();
        sem_wait(&empty);
        sem_wait(&mutex);
        insert_item(item);
        sem_post(&mutex);
        sem_post(&full);
    }
    pthread_exit(a);
}

void consumer(void *a) {
    int item, i;
    for (i = 0; i < 20; i++) {
        sem_wait(&full);
        sem_wait(&mutex);
        item = remove_item();
        sem_post(&mutex);
        sem_post(&empty);
        consume_item(item);
    }
    pthread_exit(a);
}

int main(int argc, char** argv) {
    sem_init(&mutex, 0, 1);
    sem_init(&full, 0, 0);
    sem_init(&empty, 0, N);
    pthread_t th1, th2;
    int a;
    pthread_create(&th1, NULL, producer, 1);
    pthread_create(&th2, NULL, consumer, 1);
    pthread_join(th1, &a);
    pthread_join(th2, &a);
    return (EXIT_SUCCESS);
}

