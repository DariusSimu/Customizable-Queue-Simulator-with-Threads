package ro.tuc.BusinessLayer;

import ro.tuc.DataLayer.DataStorage;
import ro.tuc.DataModels.Client;
import ro.tuc.GUI.SimulationPage;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SimulationManager implements Runnable {
    private int clientNumber;
    private int queueNumber;
    private int minArv;
    private int maxArv;
    private  int minServ;
    private int maxServ;
    private int simTime;
    private AtomicInteger counter=new AtomicInteger(0);
    private String strategyName;
    private Strategy strategy;

    private Scheduler scheduler;
    private SimulationPage simulationPage=new SimulationPage();
    private List<Client> clientList=new ArrayList<>();

    private void chooseStrategy(){
        if(strategyName.equals("Time")){
            strategy=new TimeStrategy();
        }
        else{
            strategy=new SizeStrategy();
        }
    }
    private List<Client> generateClients() {
        List<Client> clients = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < clientNumber; i++) {
            int arvTime=rand.nextInt(maxArv-minArv) + minArv;
            int servTime=rand.nextInt(maxServ - minServ) + minServ;
            Client client=new Client(i+1,arvTime,servTime);
            clients.add(client);
        }
        Collections.sort(clients, Comparator.comparingInt(Client::getArrivalTime));
        return clients;
    }
    private float getAverageServTime() {
        int avg=0;
        for(Client client:clientList){
            avg+=client.getServiceTime();
        }
        return avg/clientNumber;
    }

    @Override
    public void run() {
        DataStorage storage=new DataStorage();
        boolean flag=true;
        float avgWaitTime=0;
        int peakHour=0;
        int maxClients=0;
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        clientList=generateClients();
        float avgServTime=getAverageServTime();
        chooseStrategy();
        scheduler=new Scheduler(queueNumber,clientNumber,strategy);
        scheduler.createThreads();
        Iterator<Client> iterator = clientList.iterator();
        Client currentClient = iterator.next();
        while(counter.get()<=simTime&&(!clientList.isEmpty()||!scheduler.checkEmpty())){
            if(scheduler.getClientCount()>maxClients){
                maxClients=scheduler.getClientCount();
                peakHour=counter.get();
            }
            while (currentClient != null && currentClient.getArrivalTime() == counter.get()) {
                scheduler.addClient(currentClient);
                iterator.remove();
                if (iterator.hasNext()) {
                    currentClient = iterator.next();
                } else {
                    currentClient = null;
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            simulationPage.setSimTime(counter.get());
            simulationPage.setQueueStatuses(scheduler.getQueueMessages());
            simulationPage.setRemainingClients(clientList);
            if(flag){
                storage.saveData(buildOutputLines(scheduler.getQueueMessages(),clientList,counter.get()),!flag);
                flag=false;
            }
            else{
                storage.saveData(buildOutputLines(scheduler.getQueueMessages(),clientList,counter.get()),!flag);
            }
            counter.incrementAndGet();
        }
        scheduler.stopAll();
        avgWaitTime= scheduler.getAvgWaitingTime();
        simulationPage.setStats(avgWaitTime,avgServTime,peakHour);
        simulationPage.showStatsButton(true);
        storage.saveData(buildStatsLines(avgWaitTime,avgServTime,peakHour),!flag);
    }
    private List<String> buildStatsLines(float avgWait,float avgServ, int peakHour){
        List<String> output = new ArrayList<>();
        output.add("Avg Waiting Time: "+avgWait);
        output.add("Avg Serv Time: "+avgServ);
        output.add("Peak Hour: "+peakHour);
        return output;
    }
    private List<String> buildOutputLines(List<String> stringList, List<Client> clients, int number) {
        List<String> output = new ArrayList<>();
        output.add("Sim Time: " + number);
        for(String string:stringList){
            output.add(string);
        }
        if (clients != null && !clients.isEmpty()) {
            output.add("Remaining Clients:");
            StringBuilder sb = new StringBuilder();
            for (Client c : clients) {
                sb.append(c.toString()).append(" ");
            }
            output.add(sb.toString().trim());
        }
        output.add("");
        return output;
    }
    public void setClientNumber(int clientNumber) {
        this.clientNumber = clientNumber;
    }
    public void setQueueNumber(int queueNumber) {
        this.queueNumber = queueNumber;
    }
    public void setMinArv(int minArv) {
        this.minArv = minArv;
    }
    public void setMaxArv(int maxArv) {
        this.maxArv = maxArv;
    }
    public void setMinServ(int minServ) {
        this.minServ = minServ;
    }
    public void setMaxServ(int maxServ) {
        this.maxServ = maxServ;
    }
    public void setSimTime(int simTime) {
        this.simTime = simTime;
    }
    public void setStrategy(String strategy) {
        this.strategyName = strategy;
    }
}
