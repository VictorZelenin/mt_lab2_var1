package lab2.main.consumer;


import lab2.main.item.Process;
import lab2.main.queues.QueueInterface;

/**
 *
 * клас, який моделює роботу CPU, представляє із себе окремий потік, який приймає процес на опрацювання
 *
 */
public class CPU extends Thread {

    private Process cpuProcess; // current process
    private volatile boolean isBusy = false;
    private static int ID = 0;
    private int cpuID;

    private final QueueInterface firstQueue; // зберігає посилання на 1 чергу
    private final QueueInterface secondQueue; // зберігає посилання на 2 чергу

    private int counter;
    private int n;


    public CPU(QueueInterface firstQueue, QueueInterface secondQueue, int n) {

        cpuID = ++ID;

        this.firstQueue = firstQueue;
        this.secondQueue = secondQueue;

        this.n = n;

    }


    public boolean isBusy() {
        return isBusy;
    }



    /**
     * @param process -- current process
     * @throws RuntimeException
     */
    public void loadCpuProcess(Process process) {


        if (process == null) {
            throw new RuntimeException("Serious Exception!!!");
        }

        cpuProcess = process;

        synchronized (this) {

            System.out.println(this + " notified");
            notify(); // повідомляємо, що прийшов новий процес

        }


    }


    @Override
    public void run() {


        while (!isInterrupted()) {


            if (cpuProcess != null) {

                executeProcess(cpuProcess);

                cpuProcess = null;


            } else if (firstQueue.getQueueSize() != 0 && counter < n) {

                executeProcess(firstQueue.poll());

                counter++;

            } else if (secondQueue.getQueueSize() != 0) {

                executeProcess(secondQueue.poll());
                counter = 0;

            } else {

                synchronized (this) { // синхронізуємо по CPU, чекаємо  поки не отримаємо новий процес

                    try {

                        System.out.println(this + " is waiting for");
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }


        }


    }

    /**
     * @param process -- виконуваний процес
     *
     *                опрацьвує поточний процес
     */
    private void executeProcess(Process process) {


        isBusy = true;

        System.out.println(this + " has started " + process);

        process.executeProcess();

        System.out.println(this + " has finished " + process);

        isBusy = false;


    }


    @Override
    public String toString() {
        return "CPU-" + cpuID;
    }


    // testing unit
    public static void main(String... args) {


    }


}
