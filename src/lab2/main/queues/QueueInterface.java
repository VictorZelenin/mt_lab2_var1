package lab2.main.queues;

import lab2.main.item.Process;


/**
 *
 * Queue's interface
 *
 * */
public interface QueueInterface {

    void add(Process process);

    Process poll();

    int getQueueSize();

}
