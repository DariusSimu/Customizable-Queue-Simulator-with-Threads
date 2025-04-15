package ro.tuc.BusinessLayer;

import ro.tuc.DataModels.Client;
import ro.tuc.DataModels.ClientQueue;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<ClientQueue> queues=new ArrayList<>();
    private List<Thread> threads = new ArrayList<>();
    private int maxQueueNumber;
    private int maxQueueClients;
    private Strategy strategy;

    public Scheduler(int maxQueueNumber, int maxQueueClients, Strategy strategy) {
        this.maxQueueNumber = maxQueueNumber;
        this.maxQueueClients = maxQueueClients;
        this.strategy = strategy;
    }
    public void createThreads() {
        for (int i = 0; i < maxQueueNumber; i++) {
            ClientQueue queue = new ClientQueue();
            queues.add(queue);
            Thread thread = new Thread(queue);
            thread.setName("Queue " + i);
            threads.add(thread);
            thread.start();
        }
    }
    public float getAvgWaitingTime() {
        float avgWaitingTime = 0;
        for (ClientQueue queue : queues) {
            avgWaitingTime+=queue.getAvgClientWaitTime();
        }
        return avgWaitingTime/queues.size();
    }
    public int getClientCount() {
        int count = 0;
        for (ClientQueue queue : queues) {
            count+=queue.getQueueSize();
        }
        return count;
    }
    public List<String> getQueueMessages(){
        List<String> messages = new ArrayList<>();
        for (ClientQueue queue : queues) {
            messages.add(queue.getMessage());
        }
        return messages;
    }
    public boolean checkEmpty(){
        for(ClientQueue queue : queues){
            if(!queue.isEmpty())return false;
        }
        return true;
    }
    public void stopAll() {
        for (ClientQueue queue : queues) {
            queue.stop();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted while joining.");
            }
        }
        System.out.println("All queues have finished.");
    }
    public void addClient(Client client){
        strategy.addClientToQ(queues, client);
    }
}
