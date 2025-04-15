package ro.tuc.GUI;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainPage extends JFrame {
    private final JLabel title;
    private final JLabel clientLabel;
    private final JLabel queueLabel;
    private final JLabel minArvLabel;
    private final JLabel maxArvLabel;
    private final JLabel minServLabel;
    private final JLabel maxServLabel;
    private final JLabel simulationLabel;
    private final JLabel strategyLabel;
    private final JTextField clientField;
    private final JTextField queueField;
    private final JTextField minArvField;
    private final JTextField maxArvField;
    private final JTextField minServField;
    private final JTextField maxServField;
    private final JTextField simulationField;
    private final JComboBox strategyComboBox;
    private final JButton startButton;

    public MainPage() {
        StyleMethods style = new StyleMethods();
        StyleMethods altStyle = new StyleMethods(){
            @Override
            public void styleTextField(JTextField textField) {
                textField.setBackground(Color.LIGHT_GRAY);
                textField.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true));
                textField.setPreferredSize(new Dimension(100,30));
            }
        };
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        title = new JLabel("Setup");
        clientLabel = new JLabel("Client Number");
        queueLabel = new JLabel("Queue Number");
        minArvLabel = new JLabel("Min Arrival");
        maxArvLabel = new JLabel("Max Arrival");
        minServLabel = new JLabel("Min Service");
        maxServLabel = new JLabel("Max Service");
        simulationLabel = new JLabel("Simulation Time");
        strategyLabel = new JLabel("Choose Strategy");
        clientField = new JTextField();
        queueField = new JTextField();
        minServField= new JTextField();
        maxServField = new JTextField();
        minArvField = new JTextField();
        maxArvField = new JTextField();
        simulationField = new JTextField();
        String[] options = new String[]{"Time","Size"};
        strategyComboBox = new JComboBox<>(options);
        startButton = new JButton("Start");

        style.styleTitle(title);

        style.styleButton(startButton);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(title);

        altStyle.styleTextField(clientField);
        style.styleLabel(clientLabel);
        altStyle.styleTextField(queueField);
        style.styleLabel(queueLabel);

        style.styleLabel(minArvLabel);
        altStyle.styleTextField(minArvField);
        style.styleLabel(maxArvLabel);
        altStyle.styleTextField(maxArvField);
        altStyle.styleTextField(minServField);
        style.styleLabel(minServLabel);
        altStyle.styleTextField(maxServField);
        style.styleLabel(maxServLabel);
        style.styleLabel(simulationLabel);
        altStyle.styleTextField(simulationField);
        style.styleLabel(strategyLabel);
        style.styleComboBox(strategyComboBox);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(8, 2, 10, 10));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        infoPanel.add(clientLabel);
        infoPanel.add(clientField);
        infoPanel.add(queueLabel);
        infoPanel.add(queueField);
        infoPanel.add(minArvLabel);
        infoPanel.add(minArvField);
        infoPanel.add(maxArvLabel);
        infoPanel.add(maxArvField);
        infoPanel.add(minServLabel);
        infoPanel.add(minServField);
        infoPanel.add(maxServLabel);
        infoPanel.add(maxServField);
        infoPanel.add(simulationLabel);
        infoPanel.add(simulationField);
        infoPanel.add(strategyLabel);
        infoPanel.add(strategyComboBox);


        JPanel startPanel = new JPanel();
        startPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        startPanel.add(startButton);

        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(titlePanel);
        mainPanel.add(infoPanel);
        mainPanel.add(startPanel);
        mainPanel.add(Box.createVerticalStrut(30));

        add(mainPanel);
        setTitle("Main Page");
        setSize(300, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void addStartButtonListener(ActionListener actionListener) {
        startButton.addActionListener(actionListener);
    }
    public int getMinArvTime(){
        return Integer.parseInt(minArvField.getText());
    }
    public int getMaxArvTime(){
        return Integer.parseInt(maxArvField.getText());
    }
    public int getMinServTime(){
        return Integer.parseInt(minServField.getText());
    }
    public int getMaxServTime(){
        return Integer.parseInt(maxServField.getText());
    }
    public int getClientNumber(){
        return Integer.parseInt(clientField.getText());
    }
    public int getQueueNumber(){
        return Integer.parseInt(queueField.getText());
    }
    public int getSimulationTime(){
        return Integer.parseInt(simulationField.getText());
    }
    public String getStrategy(){
        return strategyComboBox.getSelectedItem().toString();
    }
}
