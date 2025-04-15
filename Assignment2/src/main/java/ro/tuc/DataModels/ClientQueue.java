package ro.tuc.DataModels;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientQueue implements Runnable {
    private String queueName;
    private List<Client> queueClients =new ArrayList<Client>();
    private AtomicInteger waitTime= new AtomicInteger(0);
    private boolean running = true;
    private int clientWaitTime=0;
    private int totalClients=0;
    private float avgClientWaitTime=0;

    @Override
    public void run() {
        this.queueName=Thread.currentThread().getName();
        while (running) {
            for(Client client : queueClients) {
                if(client!=queueClients.getFirst()){
                    client.setWaitingTime(client.getWaitingTime()+1);
                }
            }
            if (!queueClients.isEmpty()) {
                Client current = queueClients.get(0);
                current.setServiceTime(current.getServiceTime()-1);
                if(current.getServiceTime()==0){
                    clientWaitTime+=current.getWaitingTime();
                    queueClients.remove(current);
                }
                waitTime.addAndGet(-1);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
        if(totalClients!=0){
            avgClientWaitTime=clientWaitTime/totalClients;
        }
    }
    public String getMessage(){
        if(queueClients.isEmpty()){
            return queueName +" is empty";
        }
        else{
            return "Clients in "+queueName+"\n"+queueClients+"\n"
                    +"Serving " + queueClients.getFirst() + "\nTotal Wait Time: "+waitTime.get();
        }
    }
    public float getAvgClientWaitTime(){
        return avgClientWaitTime;
    }
    public void setTotalClients(int totalClients){
        this.totalClients=totalClients;
    }
    public int getTotalClients(){
        return totalClients;
    }
    public void addClient(Client client) {
        queueClients.add(client);
        waitTime.addAndGet(client.getServiceTime());
    }
    public int getQueueSize() {
        return queueClients.size();
    }
    public AtomicInteger getWaitTime() {
        return waitTime;
    }
    public void stop() {
        running = false;
    }
    public boolean isEmpty() {
        return queueClients.isEmpty();
    }
}
