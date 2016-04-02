package lab2.main.queues;

import lab2.main.item.Process;

import java.util.LinkedList;
import java.util.Queue;


/**
 *
 *
 * Queue with non-fixed capacity
 *
 *
 *
 * */
public class CPUQueue2 implements QueueInterface{

    private Queue<Process> processQueue;



    public CPUQueue2() {
        processQueue = new LinkedList<>();
    }




    public synchronized void add(Process process) {

        synchronized (this) {

            System.out.println(process + "  added to queue2");

            processQueue.offer(process);

        }

    }

    public synchronized Process poll() {

        if (processQueue.size() <= 0) {
            throw new RuntimeException();

        }


        Process polledProcess;
        synchronized (this) {

            polledProcess = processQueue.poll();

            System.out.println(this + " getQueueSize: " + this.getQueueSize());

            System.out.println(polledProcess + " polled from queue2");

        }


        return polledProcess;
    }

    public synchronized int getQueueSize() {

        return processQueue.size();

    }

    @Override
    public String toString() {
        return "Queue-2";
    }

}
