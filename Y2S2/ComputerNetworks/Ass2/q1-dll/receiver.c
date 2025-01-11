#include "global.h"

void receiveFrame(Frame *frame) {
    printf("Frame received, sequence number: %d\n", frame->seq);
    
    char data[FRAME_SIZE + 1] = {0};
    extractData(frame, data);
    deliverData(data);
}

void extractData(Frame *frame, char *data) {
    strncpy(data, frame->data, FRAME_SIZE);
    data[FRAME_SIZE] = '\0';
}

void deliverData(char *data) {
    printf("Received data: %s\n", data);
}
