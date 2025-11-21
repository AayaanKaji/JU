#include "global.h"

void getData(char *buffer) {
    printf("Enter message: ");
    fgets(buffer, MSG_SIZE, stdin);
    buffer[strcspn(buffer, "\n")] = '\0';
}

Frame makeFrame(int seq_number, char *data) {
    Frame frame;
    frame.seq = seq_number;
    strncpy(frame.data, data, FRAME_SIZE);
    return frame;
}

Frame sendFrame(Frame *frame) {
    printf("\nSending frame...\n");
    waitForEvent();
    return *frame;
}
