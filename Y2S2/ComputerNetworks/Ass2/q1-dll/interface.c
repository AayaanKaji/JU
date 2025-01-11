#include "sender.c"
#include "receiver.c"

void sendAndReceiveMessage(const char *message) {
    int message_length = strlen(message);
    int num_frames = (message_length + FRAME_SIZE - 1) / FRAME_SIZE;
    int seq_number = 0;

    for (int i = 0; i < num_frames; ++i) {
        char frame_data[FRAME_SIZE + 1] = {0};
        strncpy(frame_data, message + i * FRAME_SIZE, FRAME_SIZE);

        Frame frame = makeFrame(seq_number, frame_data);
        Frame rec_frame = sendFrame(&frame);

        receiveFrame(&rec_frame);

        seq_number++;
    }
}

int main() {
    char message[MSG_SIZE];
    getData(message);
    sendAndReceiveMessage(message);
    return 0;
}
