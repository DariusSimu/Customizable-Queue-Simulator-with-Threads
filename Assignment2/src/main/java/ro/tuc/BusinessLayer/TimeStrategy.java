package ro.tuc.BusinessLayer;

import ro.tuc.DataModels.Client;
import ro.tuc.DataModels.ClientQueue;

import java.util.List;

public class TimeStrategy implements Strategy {
    @Override
    public void addClientToQ(List<ClientQueue> queues, Client client) {
        if (queues == null || queues.isEmpty()) {
            System.out.println("No available queues");
            return;
        }

        ClientQueue bestQueue = queues.get(0);
        for (ClientQueue queue : queues) {
            if (queue.getWaitTime().get() < bestQueue.getWaitTime().get()) {
                bestQueue = queue;
            }
        }
        bestQueue.addClient(client);
        bestQueue.setTotalClients(bestQueue.getTotalClients() + 1);
        //System.out.println("Client: "+ client +" added to: "+ Thread.currentThread().getName() +" with wait time: " + bestQueue.getQueueSize());
    }
}
