package ro.tuc.GUI;

import ro.tuc.BusinessLayer.SimulationManager;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainController {
    private MainPage mainPage;
    public MainController(MainPage mainPage) {
        this.mainPage = mainPage;
        mainPage.addStartButtonListener(new StartButtonListener());
    }

    public class StartButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int clients=mainPage.getClientNumber();
            int queues=mainPage.getQueueNumber();
            int minArv=mainPage.getMinArvTime();
            int maxArv=mainPage.getMaxArvTime();
            int minServ=mainPage.getMinServTime();
            int maxServ=mainPage.getMaxServTime();
            int simTime=mainPage.getSimulationTime();
            //pre-set tests
            /*
            int clients=0;
            int queues=0;
            int minArv=0;
            int maxArv=0;
            int minServ=0;
            int maxServ=0;
            int simTime=0;
            String test="Test3";
            switch (test) {
                case "Test1":
                    clients = 4;
                    queues = 2;
                    minArv = 2;
                    maxArv = 30;
                    minServ = 2;
                    maxServ = 4;
                    simTime = 60;
                    break;
                case "Test2":
                    clients = 50;
                    queues = 5;
                    minArv = 2;
                    maxArv = 40;
                    minServ = 1;
                    maxServ = 7;
                    simTime = 60;
                    break;
                case "Test3":
                    clients = 1000;
                    queues = 20;
                    minArv = 10;
                    maxArv = 100;
                    minServ = 3;
                    maxServ = 9;
                    simTime = 200;
                    break;
                default:
                    break;
            }
            */
            String strat=mainPage.getStrategy();
            SimulationManager manager=new SimulationManager();
            manager.setClientNumber(clients);
            manager.setQueueNumber(queues);
            manager.setMinArv(minArv);
            manager.setMaxArv(maxArv);
            manager.setMinServ(minServ);
            manager.setMaxServ(maxServ);
            manager.setSimTime(simTime);
            manager.setStrategy(strat);
            Thread managerThread=new Thread(manager);
            managerThread.start();
        }
    }
}
