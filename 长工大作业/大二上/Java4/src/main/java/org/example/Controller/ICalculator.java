package org.example.Controller;

/*** Created by YunFeng on 2014/12/6 0009.

 * Student Number：

 * Teacher:Yongfeng Huang

 * University:DHU

 *Java's homework of No,7 weekend;*/

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ICalculator {
    public static void main(String args[]){

        WinGrid ICalc= new WinGrid();

        ICalc.setTitle("计算器1.0");//程序标题

        }

}
class WinGrid extends JFrame {

    JPanel calc, screen;//创建两个面板，放置按键和显示器

    JButton[] buttons = new JButton[16];//创建16个按键

    JButton clear = new JButton("CE");//创建清除键

    JTextField screen_txt = new JTextField("0");//创建显示器

    String[] str = {"7", "8", "9", "+", "4", "5", "6","-", "1", "2", "3", "*", "0", ".", "=", "/"};//计算器的按钮

    double result = 0;//结果

    boolean IsClick = false;//是否点击过

    char operator = '=';//操作符

    boolean IsPoint = false;//输入的是否是小数点

    NumberListener numberListener= new NumberListener();//创建一个数字监视器

    OperatorListener operatorListener = new OperatorListener();//创建一个操作符监视器

    ClearListener clearListener = new ClearListener();//创建一个清零操作监视器

    WinGrid() {

        calc= new JPanel(new GridLayout(4, 4));

        screen= new JPanel(new BorderLayout());/*以上为初始化两个面板布局*/

        for (int i = 0; i < 16; i++) {

            buttons[i]= new JButton(str[i]);if (i == 3 || i == 7 || i == 11 || i == 14 || i == 15) {

                buttons[i].addActionListener(operatorListener);//操作符注册操作符的监视器

            } else buttons[i].addActionListener(numberListener);//数字及小数点注册数字的监视器

            calc.add(buttons[i]);

        }//将按钮添加到操作面板

        clear.addActionListener(clearListener);//注册清零监视器

        screen.add(screen_txt, BorderLayout.CENTER);//将显示器添加到显示区域

        screen.add(clear, BorderLayout.EAST);//将清零键添加到显示器右边

        add(screen, BorderLayout.NORTH);//将显示器添加到BorderLayout的北方区域,即上方

        add(calc, BorderLayout.CENTER);//将操作区添加到中央区域

        setBounds(450, 150, 240, 240);//设置计算器大小，位置

        setVisible(true);//设置可见性

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置退出方式

        validate();//使设置生效(根据课本232页)

    }
    class ClearListener implements ActionListener {//清零计数器的实现

        public void actionPerformed(ActionEvent e) {

            screen_txt.setText("");

        }

    }class NumberListener implements ActionListener {//数字监视器的实现，用于处理数字按钮的监听

        public void actionPerformed(ActionEvent e) {if (!IsClick) {//没有点击过的操作

            screen_txt.setText("");

            IsClick= true;

        }

            String s= screen_txt.getText();//读取显示器的内容

            s += e.getActionCommand();//显示器内容+鼠标点击的内容

            screen_txt.setText(s);//显示器显示s

        }

    }
    class OperatorListener implements ActionListener {//操作符监听的实现，用于处理点击操作符的事件

        public void actionPerformed(ActionEvent e) {if (!IsClick) return;//如果数字没有被点击过直接点击操作符，返回

            String s = screen_txt.getText();//读取显示器内容

            double num = Double.parseDouble(s);//转换成数字

            IsClick = false;//点击过操作符后将不能被再次点击操作符

            switch (operator) {//处理操作符的运算

                case '+': {

                    result= num +result;break;

                }case '-': {

                    result= result -num;break;

                }case '*': {

                    result*=num ;break;

                }case '/': {if (num == 0) result = 0;else result/=num;break;

                }case '=': {

                    result=num;break;

                }

            }

            screen_txt.setText(String.valueOf( result));//显示器显示结果

            String op = e.getActionCommand();//监听读取操作符

            operator = op.charAt(0);//设置操作符

        }

    }

}
