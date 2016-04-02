package lab2.main.main;

import lab2.main.consumer.CPU;
import lab2.main.producer.CPUProcess;
import lab2.main.queues.CPUQueue1;
import lab2.main.queues.CPUQueue2;
import lab2.main.queues.QueueInterface;


public class Main {

    // processes param
    private static final int NUMBER_OF_PROCESSES_1 = 10;
    private static final long MAX_PROCESSING_TIME_1 = 4000;
    private static final int MAX_WAITING_TIME_1 = 1000;


    private static final int NUMBER_OF_PROCESSES_2 = 10;
    private static final long MAX_PROCESSING_TIME_2 = 4000;
    private static final int MAX_WAITING_TIME_2 = 1000;

    // n : 1 (queue1 : queue2)
    private static final int N = 10;

    private static final int FIRST_QUEUE_CAPACITY = 3;


    public static void main(String[] args) {


        QueueInterface firstQueue = new CPUQueue1(FIRST_QUEUE_CAPACITY); // with fixed capacity
        QueueInterface secondQueue = new CPUQueue2(); // with non-fixed capacity


        CPU mainCPU = new CPU(firstQueue, secondQueue, N);


        CPUProcess firstProcesses = new CPUProcess(NUMBER_OF_PROCESSES_1, MAX_PROCESSING_TIME_1, MAX_WAITING_TIME_1,
                firstQueue, mainCPU);


        CPUProcess secondProcesses = new CPUProcess(NUMBER_OF_PROCESSES_2, MAX_PROCESSING_TIME_2, MAX_WAITING_TIME_2,
                secondQueue, mainCPU);


        // started threads
        mainCPU.start();
        firstProcesses.start();
        secondProcesses.start();


    }


}
