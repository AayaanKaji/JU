#ifndef GLOBAL_H
#define GLOBAL_H

#include <stdio.h>
#include <string.h>
#include <unistd.h>

#define FRAME_SIZE 4
#define MSG_SIZE 1024

typedef struct {
    int seq; // sequence number
    char data[FRAME_SIZE];
} Frame;

void waitForEvent();
void getData(char *buffer);
Frame makeFrame(int seq_number, char *data);
Frame sendFrame(Frame *frame);
void receiveFrame(Frame *frame);
void extractData(Frame *frame, char *data);
void deliverData(char *data);

void waitForEvent() {
    sleep(2);
}

#endif
