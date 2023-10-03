import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThreadA {
    private static Thread thread1;
    private static Thread thread2;
    private static JSlider slider;
    private static JSpinner prioritySpinner1;
    private static JSpinner prioritySpinner2;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Thread Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 2, 10, 10));

        slider = new JSlider(0, 100);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        prioritySpinner1 = new JSpinner(new SpinnerNumberModel(5, 1, 10, 1));
        prioritySpinner2 = new JSpinner(new SpinnerNumberModel(5, 1, 10, 1));

        topPanel.add(new JLabel("Priority 1:"));
        topPanel.add(prioritySpinner1);
        topPanel.add(new JLabel("Priority 2:"));
        topPanel.add(prioritySpinner2);

        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        middlePanel.add(slider);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton startButton1 = new JButton("Start Thread 1");
        startButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int priority1 = (int) prioritySpinner1.getValue();
                startOrSetPriority(thread1, priority1, 10);
            }
        });

        JButton startButton2 = new JButton("Start Thread 2");
        startButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int priority2 = (int) prioritySpinner2.getValue();
                startOrSetPriority(thread2, priority2, 90);
            }
        });

        bottomPanel.add(startButton1);
        bottomPanel.add(startButton2);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(middlePanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void startOrSetPriority(Thread thread, int priority, int targetValue) {
        if (thread == null || !thread.isAlive()) {
            thread = new Thread(() -> {
                while (true) {
                    slider.setValue(targetValue);
                }
            });
            thread.setPriority(priority);
            thread.start();
        } else {
            thread.setPriority(priority);
        }
    }
}
