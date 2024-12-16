package Test_package;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static java.lang.Math.*;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 420; // Increased height for new elements
    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    private JTextField textFieldResult;
    private JTextField textFieldMemoryValue;
    private ButtonGroup radioButtons = new ButtonGroup();
    private Box hboxFormulaType = Box.createHorizontalBox();
    private int formulaId = 1;
    private Double mem1 = 0.0, mem2 = 0.0, mem3 = 0.0;
    private int activeMemory = 1;
    private JRadioButton memButton1, memButton2, memButton3;

    private Double calculate1(Double x, Double y, Double z) {
        return (sin(PI * y * y) + log(y * y)) / (sin(PI * z * z) + sin(x) + log(z * z) + x * x + pow(E, cos(z * x)));
    }

    private Double calculate2(Double x, Double y, Double z) {
        return pow(sin(pow(z, y)), 2) / sqrt(1 + x * x * x);
    }


    public MainFrame() {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH) / 2,
                (kit.getScreenSize().height - HEIGHT) / 2);

        // Formula Selection Panel
        JPanel formulaPanel = new JPanel();
        formulaPanel.setBorder(BorderFactory.createTitledBorder("Выбор формулы"));
        formulaPanel.setLayout(new GridLayout(1, 2));

        JRadioButton formulaButton1 = new JRadioButton("Формула 1");
        JRadioButton formulaButton2 = new JRadioButton("Формула 2");
        formulaButton1.addActionListener(e -> formulaId = 1);
        formulaButton2.addActionListener(e -> formulaId = 2);

        ButtonGroup formulaButtonGroup = new ButtonGroup();
        formulaButtonGroup.add(formulaButton1);
        formulaButtonGroup.add(formulaButton2);
        formulaButton1.setSelected(true); // Formula 1 selected by default


        formulaPanel.add(formulaButton1);
        formulaPanel.add(formulaButton2);



        // Variables Input Panel
        JPanel variablesPanel = new JPanel();
        variablesPanel.setBorder(BorderFactory.createTitledBorder("Переменные"));
        variablesPanel.setLayout(new GridLayout(3, 2));


        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 10);
        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 10);
        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("0", 10);


        variablesPanel.add(labelForX);
        variablesPanel.add(textFieldX);
        variablesPanel.add(labelForY);
        variablesPanel.add(textFieldY);
        variablesPanel.add(labelForZ);
        variablesPanel.add(textFieldZ);


        // Memory Selection Panel (Reusing existing code)
        JPanel memoryPanel = new JPanel();
        memoryPanel.setBorder(BorderFactory.createTitledBorder("Выбор памяти"));
        memoryPanel.setLayout(new GridLayout(1,3));

        memButton1 = new JRadioButton("Переменная 1");
        memButton2 = new JRadioButton("Переменная 2");
        memButton3 = new JRadioButton("Переменная 3");
        memButton1.addActionListener(e -> { activeMemory = 1; updateMemoryValueField(); });
        memButton2.addActionListener(e -> { activeMemory = 2; updateMemoryValueField(); });
        memButton3.addActionListener(e -> { activeMemory = 3; updateMemoryValueField(); });

        ButtonGroup memoryButtonGroup = new ButtonGroup();
        memoryButtonGroup.add(memButton1);
        memoryButtonGroup.add(memButton2);
        memoryButtonGroup.add(memButton3);
        memButton1.setSelected(true);

        memoryPanel.add(memButton1);
        memoryPanel.add(memButton2);
        memoryPanel.add(memButton3);

        // Result and Memory Value Display (Reusing existing code with modifications)
        JLabel labelForResult = new JLabel("Результат:");
        textFieldResult = new JTextField("0", 15);
        JLabel labelForMemoryValue = new JLabel("Значение M:");
        textFieldMemoryValue = new JTextField("0", 10);
        updateMemoryValueField(); // Initialize memory value display


        JPanel resultPanel = new JPanel();
        resultPanel.setBorder(BorderFactory.createTitledBorder("Результат"));
        resultPanel.setLayout(new GridLayout(1,4));
        resultPanel.add(labelForResult);
        resultPanel.add(textFieldResult);
        resultPanel.add(labelForMemoryValue);
        resultPanel.add(textFieldMemoryValue);


        // Buttons (Reusing existing code)
        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(ev -> {
            try {
                Double x = Double.parseDouble(textFieldX.getText());
                Double y = Double.parseDouble(textFieldY.getText());
                Double z = Double.parseDouble(textFieldZ.getText());
                Double result;
                if (formulaId == 1)
                    result = calculate1(x, y, z);
                else
                    result = calculate2(x, y, z);
                textFieldResult.setText(result.toString());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(MainFrame.this,
                        "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(ev -> {
            textFieldX.setText("0");
            textFieldY.setText("0");
            textFieldZ.setText("0");
            textFieldResult.setText("0");
        });


        JButton buttonMC = new JButton("MC");
        buttonMC.addActionListener(e -> {
            if (activeMemory == 1) mem1 = 0.0;
            else if (activeMemory == 2) mem2 = 0.0;
            else mem3 = 0.0;
            updateMemoryValueField();
            textFieldResult.setText("0");
        });

        JButton buttonMPlus = new JButton("M+");
        buttonMPlus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                Double result = Double.parseDouble(textFieldResult.getText());
                if (activeMemory == 1) mem1 += result;
                else if (activeMemory == 2) mem2 += result;
                else mem3 += result;
                updateMemoryValueField();
                textFieldResult.setText(String.valueOf(result));
            }
        });

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1,4));
        buttonsPanel.add(buttonCalc);
        buttonsPanel.add(buttonReset);
        buttonsPanel.add(buttonMC);
        buttonsPanel.add(buttonMPlus);



        // Main Layout
        JPanel contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.add(formulaPanel);
        contentPane.add(variablesPanel);
        contentPane.add(memoryPanel);
        contentPane.add(resultPanel);
        contentPane.add(buttonsPanel);



        setContentPane(contentPane);


    }

    private void updateMemoryValueField() {
        switch (activeMemory) {
            case 1:
                textFieldMemoryValue.setText(String.valueOf(mem1));
                break;
            case 2:
                textFieldMemoryValue.setText(String.valueOf(mem2));
                break;
            case 3:
                textFieldMemoryValue.setText(String.valueOf(mem3));
                break;
        }
    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
