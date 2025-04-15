package ro.tuc.GUI;

import ro.tuc.DataModels.Client;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SimulationPage extends JFrame {
    private final JLabel timeLabel;
    private final JPanel queuePanel;
    private final JTextArea clientArea;
    private final JButton statsButton;
    private float avgWaitTime = 0f;
    private float avgServiceTime = 0f;
    private int peakHour = 0;

    public SimulationPage() {
        StyleMethods style = new StyleMethods();
        setTitle("Simulation Page");
        setSize(700, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new BorderLayout());
        timeLabel = new JLabel("Sim Time: 0");
        timeLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        timeLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        statsButton = new JButton("Stats");
        statsButton.setVisible(false);
        statsButton.setFocusable(false);
        style.styleButton(statsButton);

        statsButton.addActionListener(e -> {
            statsButton.addActionListener(ee -> showStats(avgWaitTime,avgServiceTime,peakHour));
        });

        topPanel.add(timeLabel, BorderLayout.WEST);
        topPanel.add(statsButton, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        queuePanel = new JPanel();
        queuePanel.setLayout(new BoxLayout(queuePanel, BoxLayout.Y_AXIS));
        JScrollPane queueScroll = new JScrollPane(queuePanel);
        queueScroll.setBorder(BorderFactory.createTitledBorder("Queues"));
        add(queueScroll, BorderLayout.CENTER);

        clientArea = new JTextArea();
        clientArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        clientArea.setEditable(false);
        clientArea.setLineWrap(true);
        clientArea.setWrapStyleWord(true);

        JScrollPane clientScroll = new JScrollPane(clientArea);
        clientScroll.setBorder(BorderFactory.createTitledBorder("Remaining Clients"));
        clientScroll.setPreferredSize(new Dimension(700, 120));
        add(clientScroll, BorderLayout.SOUTH);

        setVisible(true);
    }
    public void setSimTime(int time) {
        SwingUtilities.invokeLater(() -> timeLabel.setText("Sim Time: " + time));
    }
    public void setStats(float waitTime, float serviceTime, int peakHour) {
        this.avgWaitTime = waitTime;
        this.avgServiceTime = serviceTime;
        this.peakHour = peakHour;
    }
    public void showStats(float avgWaitTime, float avgServiceTime, int peakHour) {
        String message = String.format(
                "Simulation Stats:\n\n" +
                        "• Average Wait Time: %.2f\n" +
                        "• Average Service Time: %.2f\n" +
                        "• Peak Hour: %d",
                avgWaitTime, avgServiceTime, peakHour
        );

        SwingUtilities.invokeLater(() ->
                JOptionPane.showMessageDialog(this, message, "Simulation Stats", JOptionPane.INFORMATION_MESSAGE)
        );
    }
    public void showStatsButton(boolean show) {
        SwingUtilities.invokeLater(() -> statsButton.setVisible(show));
    }
    public void setQueueStatuses(List<String> queues) {
        SwingUtilities.invokeLater(() -> {
            queuePanel.removeAll();

            for (String q : queues) {
                JPanel queueCard = new JPanel();
                queueCard.setLayout(new BoxLayout(queueCard, BoxLayout.Y_AXIS));
                queueCard.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.GRAY),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)
                ));
                queueCard.setBackground(new Color(245, 245, 245)); // light gray

                JTextArea textArea = new JTextArea(q);
                textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
                textArea.setEditable(false);
                textArea.setOpaque(false);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);

                queueCard.add(textArea);
                queuePanel.add(Box.createVerticalStrut(10));
                queuePanel.add(queueCard);
                if (q.contains("is empty")) {
                    queueCard.setBackground(new Color(255, 240, 240));
                } else {
                    queueCard.setBackground(new Color(240, 255, 240));
                }

            }
            queuePanel.revalidate();
            queuePanel.repaint();
        });
    }
    public void setRemainingClients(List<Client> clients) {
        SwingUtilities.invokeLater(() -> {
            clientArea.setText("");
            for (Client client : clients) {
                clientArea.append(client.toString() + " ");
            }
        });
    }
}
