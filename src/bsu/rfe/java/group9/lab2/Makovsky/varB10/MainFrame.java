package bsu.rfe.java.group9.lab2.Makovsky.varB10;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import static java.lang.Math.*;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 320;
    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    private JTextField textFieldResult;
    private JTextField textFieldMemoryValue;
    private ButtonGroup FormulaRadioButtons = new ButtonGroup();
    private ButtonGroup VariablesRadioButtons = new ButtonGroup();
    private Box hboxFormulaType = Box.createHorizontalBox();
    private Box hboxVariableType = Box.createHorizontalBox();
    private int formulaId = 1;
    private Double mem1 = 0.0, mem2 = 0.0, mem3 = 0.0;
    private int variableId = 1;
    private JRadioButton memButton1, memButton2, memButton3;

    public Double calculate1(Double x, Double y, Double z) {
        return (sin(PI * y * y) + log(y * y)) / (sin(PI * z * z) + sin(x) + log(z * z) + x * x + pow(E, cos(z * x)));
    }

    public Double calculate2(Double x, Double y, Double z) {
        return pow(sin(pow(z, y)), 2) / sqrt(1 + x * x * x);
    }

    private void addFormulaRadioButton(String buttonName, final int formulaId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                MainFrame.this.formulaId = formulaId;
                rootPane.updateUI();
            }
        });
        FormulaRadioButtons.add(button);
        hboxFormulaType.add(button);
    }

    private void addVariablesRadioButton(String buttonName, final int variableId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                MainFrame.this.variableId = variableId;
                updateMemoryValueField();
                rootPane.updateUI();
            }
        });
        VariablesRadioButtons.add(button);
        hboxVariableType.add(button);
    }

    private void updateMemoryValueField() {
        switch (variableId) {
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


    public MainFrame() {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH) / 2,
                (kit.getScreenSize().height - HEIGHT) / 2);

        hboxFormulaType.add(Box.createHorizontalGlue());
        addFormulaRadioButton("Формула 1", 1);
        addFormulaRadioButton("Формула 2", 2);
        FormulaRadioButtons.setSelected( FormulaRadioButtons.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());
        hboxFormulaType.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());

        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());

        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForX);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldX);
        hboxVariables.add(Box.createHorizontalStrut(30));
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldY);
        hboxVariables.add(Box.createHorizontalStrut(30));
        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldZ);
        hboxVariables.add(Box.createHorizontalGlue());

        hboxVariableType.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        hboxVariableType.add(Box.createHorizontalGlue());
        addVariablesRadioButton("Переменная 1", 1);
        addVariablesRadioButton("Переменная 2", 2);
        addVariablesRadioButton("Переменная 3", 3);
        VariablesRadioButtons.setSelected( VariablesRadioButtons.getElements().nextElement().getModel(), true);
        hboxVariableType.add(Box.createHorizontalGlue());


        JLabel labelForResult = new JLabel("Результат:");
        textFieldResult = new JTextField("0", 15);
        textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());

        JLabel labelForMemoryValue = new JLabel("Значение M:");
        textFieldMemoryValue = new JTextField("0", 30);
        textFieldMemoryValue.setMaximumSize(textFieldMemoryValue.getPreferredSize());

        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(labelForMemoryValue);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldMemoryValue);
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.RED));


        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
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
            if (variableId == 1) mem1 = 0.0;
            else if (variableId == 2) mem2 = 0.0;
            else mem3 = 0.0;
            updateMemoryValueField();
        });

        JButton buttonMPlus = new JButton("M+");
        buttonMPlus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                    Double result = Double.parseDouble(textFieldResult.getText());
                    switch(variableId) {
                        case 1:
                            mem1 += result;
                            break;
                        case 2:
                            mem2 += result;
                            break;
                        case 3:
                            mem3 += result;
                            break;
                    }
                    updateMemoryValueField();
                    textFieldResult.setText(String.valueOf(result));
            }
        });


        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonMC);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonMPlus);
        hboxButtons.setBorder(BorderFactory.createLineBorder(Color.GREEN));

        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxVariables);
        contentBox.add(hboxVariableType);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);

    }


    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
