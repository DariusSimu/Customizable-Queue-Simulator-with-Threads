package ro.tuc.BusinessLayer;

import ro.tuc.DataModels.Client;
import ro.tuc.DataModels.ClientQueue;

import java.util.List;

public interface Strategy {
    public void addClientToQ(List<ClientQueue> queues, Client client);
}
