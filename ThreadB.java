import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThreadB {
    private static Thread thread1;
    private static Thread thread2;
    private static JSlider slider;
    private static JButton stopButton1;
    private static JButton stopButton2;
    private static JSpinner prioritySpinner1;
    private static JSpinner prioritySpinner2;
    private static int semaphore = 1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Processes, Threads");
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        slider = new JSlider(0, 100);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setPreferredSize(new Dimension(250, slider.getPreferredSize().height));

        JPanel sliderPanel = new JPanel();
        sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.Y_AXIS));
        sliderPanel.add(slider);

        prioritySpinner1 = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        prioritySpinner2 = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));

        JPanel spinnerPanel = new JPanel();
        spinnerPanel.setLayout(new FlowLayout());
        spinnerPanel.add(new JLabel("Priority 1:"));
        spinnerPanel.add(prioritySpinner1);
        spinnerPanel.add(new JLabel("Priority 2:"));
        spinnerPanel.add(prioritySpinner2);

        JButton startButton1 = new JButton("START 1");
        JButton startButton2 = new JButton("START 2");
        stopButton1 = new JButton("STOP 1");
        stopButton2 = new JButton("STOP 2");

        Dimension buttonSize = new Dimension(120, 30);
        startButton1.setPreferredSize(buttonSize);
        startButton2.setPreferredSize(buttonSize);
        stopButton1.setPreferredSize(buttonSize);
        stopButton2.setPreferredSize(buttonSize);

        JPanel startButtonsPanel = new JPanel();
        startButtonsPanel.setLayout(new FlowLayout());
        startButtonsPanel.add(startButton1);
        startButtonsPanel.add(startButton2);

        JPanel stopButtonsPanel = new JPanel();
        stopButtonsPanel.setLayout(new FlowLayout());
        stopButtonsPanel.add(stopButton1);
        stopButtonsPanel.add(stopButton2);

        JLabel semaphoreStatusLabel = new JLabel("Semaphore Status: Free");

        startButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (semaphore == 1) {
                    semaphore = 0;
                    semaphoreStatusLabel.setText("Semaphore Status: Acquired by Thread 1");
                    int priority1 = 1;
                    prioritySpinner1.setValue(priority1);
                    stopButton2.setEnabled(false);
                    slider.setValue(10);

                    thread1 = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Thread.currentThread().setPriority(priority1);
                            while (!Thread.interrupted()) {
                                slider.setValue(10);
                            }
                        }
                    });
                    thread1.start();
                }
            }
        });

        startButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (semaphore == 1) {
                    semaphore = 0;
                    semaphoreStatusLabel.setText("Semaphore Status: Acquired by Thread 2");
                    int priority2 = 10;
                    prioritySpinner2.setValue(priority2);
                    stopButton1.setEnabled(false);
                    slider.setValue(90);

                    thread2 = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Thread.currentThread().setPriority(priority2);
                            while (!Thread.interrupted()) {
                                slider.setValue(90);
                            }
                        }
                    });
                    thread2.start();
                }
            }
        });

        stopButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (thread1 != null) {
                    thread1.interrupt();
                    semaphore = 1;
                    stopButton2.setEnabled(true);
                    semaphoreStatusLabel.setText("Semaphore Status: Free");
                }
            }
        });

        stopButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (thread2 != null) {
                    thread2.interrupt();
                    semaphore = 1;
                    stopButton1.setEnabled(true);
                    semaphoreStatusLabel.setText("Semaphore Status: Free");
                }
            }
        });

        frame.add(sliderPanel);
        frame.add(spinnerPanel);
        frame.add(startButtonsPanel);
        frame.add(stopButtonsPanel);
        frame.add(semaphoreStatusLabel);

        frame.setSize(300, 250);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
