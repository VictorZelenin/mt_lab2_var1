package lab2.main.queues;

import lab2.main.item.Process;

import java.util.LinkedList;
import java.util.Queue;


/**
 * Queue with fixed capacity
 */
public class CPUQueue1 implements QueueInterface {
    private Queue<Process> processQueue;

    private int capacity;


    public CPUQueue1(int capacity) {

        this.capacity = capacity;

        processQueue = new LinkedList<>();

    }

    @Override
    public void add(Process process) {


        if (processQueue.size() >= capacity) {

            System.out.println(process + " destroyed!!");

        } else {
            synchronized (this) {

                System.out.println(process + "  added to queue1");

                processQueue.offer(process);

            }

        }


    }

    public Process poll() {

        if (processQueue.size() <= 0) {
            throw new RuntimeException();

        }

        Process polledProcess;
        synchronized (this) {

            polledProcess = processQueue.poll();

            System.out.println(this + " getQueueSize: " + this.getQueueSize());

            System.out.println(polledProcess + " polled from queue1");

        }


        return polledProcess;
    }

    public int getQueueSize() {

        synchronized (this) {
            return processQueue.size();

        }

    }

    public int getCapacity() {
        return capacity;
    }


    @Override
    public String toString() {
        return "Queue-1";
    }


}
