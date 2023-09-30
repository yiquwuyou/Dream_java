package WorkerSystem;

import WorkerManager.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.io.*;

public class WorkerSystem {

    public static void main(String[] args) {
        WorkerManager wm = new WorkerManager();
        //创建窗口
        JFrame frame = new JFrame("职工信息管理系统");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 创建一个面板，并设置GridLayout
        JPanel panel = new JPanel();
        GridLayout gridLayout = new GridLayout(0, 1); // 单列网格布局，垂直排列
        panel.setLayout(gridLayout);
        // 创建多个按钮
        JButton button1 = new JButton("增加职工信息");
        JButton button2 = new JButton("显示职工信息");
        JButton button3 = new JButton("删除离职职工");
        JButton button4 = new JButton("修改职工信息");
        JButton button5 = new JButton("查找职工信息");
        JButton button6 = new JButton("按照编号排序");
        JButton button7 = new JButton("清空所有文档");
        JButton button8 = new JButton("退出管理程序");
        // 将按钮添加到面板
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);
        panel.add(button6);
        panel.add(button7);
        panel.add(button8);
        // 将面板添加到窗口
        frame.add(panel);
        //设置窗口大小
        frame.setSize(300, 600);
        //显示窗口
        frame.setVisible(true);
        //处理事件
        //增加职工信息
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在这里创建一个对话框，以便用户输入数据
                int numToAdd = Integer.parseInt(JOptionPane.showInputDialog(frame, "请输入要添加的职工数量:"));
                if (numToAdd <= 0) {
                    JOptionPane.showMessageDialog(frame, "输入有误，请输入大于0的数量。");
                    return;
                }

                Worker[] newspace = new Worker[numToAdd + wm.Workernum];
                for (int i = 0; i < wm.Workernum; i++) {
                    newspace[i] = wm.workerarray[i];
                }
                for (int i = 0; i < numToAdd; i++) {
                    String name = JOptionPane.showInputDialog(frame, "请输入第" + (i + 1) + "个职工的姓名:");
                    String did = JOptionPane.showInputDialog(frame, "请输入第" + (i + 1) + "个职工的部门:");
                    int age = Integer.parseInt(JOptionPane.showInputDialog(frame, "请输入第" + (i + 1) + "个职工的年龄:"));
                    int id = Integer.parseInt(JOptionPane.showInputDialog(frame, "请输入第" + (i + 1) + "个职工的编号:"));

                    if ("员工".equals(did)) {
                        Worker worker = new Employee(name, did, age, id);
                        newspace[i + wm.Workernum] = worker;
                    } else if ("经理".equals(did)) {
                        Worker worker = new Manager(name, did, age, id);
                        newspace[i + wm.Workernum] = worker;
                    } else if ("老板".equals(did)) {
                        Worker worker = new Boss(name, did, age, id);
                        newspace[i + wm.Workernum] = worker;
                    } else {
                        JOptionPane.showMessageDialog(frame, "部门输入有误，使用默认部门“员工”。");
                        Worker worker = new Employee(name, "员工", age, id);
                        newspace[i + wm.Workernum] = worker;
                    }
                }
                wm.Workernum += numToAdd;
                for (int j = 0; j < wm.Workernum; j++) {
                    wm.workerarray[j] = newspace[j];
                }
                try(BufferedWriter writer = new BufferedWriter(new FileWriter(wm.FilePath)))
                {
                    for(int i = 0;i<wm.Workernum;i++)
                    {
                        writer.write(wm.workerarray[i].Showinfo());
                        writer.newLine();
                    }
                    writer.close();
                }
                catch(IOException a)
                {
                   JOptionPane.showMessageDialog(frame,"文件写入错误"+a.getMessage());
                }
                JOptionPane.showMessageDialog(frame, "添加成功！");
                wm.WorkerIsEmploy = false;
            }
        });
        //显示职工信息
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 创建新的窗口来显示职工信息
                JFrame infoFrame = new JFrame("职工信息");
                infoFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

                // 创建文本区域来显示职工信息
                JTextArea infoTextArea = new JTextArea(10, 40);
                infoTextArea.setEditable(false); // 设置为不可编辑
                JScrollPane infoScrollPane = new JScrollPane(infoTextArea);

                // 添加文本区域到新窗口
                infoFrame.add(infoScrollPane);
                infoFrame.setSize(400, 300); // 设置新窗口大小

                // 在新窗口中显示职工信息
                for (int i = 0; i < wm.Workernum; i++) {
                    infoTextArea.append(wm.workerarray[i].Showinfo() + "\n");
                }
                // 设置新窗口可见
                infoFrame.setVisible(true);
            }
        });
        //删除离职职工
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(frame,"请输入要修改的员工姓名：");
                int temp = wm.Find(name);
                if(temp == -1)
                {
                    JOptionPane.showMessageDialog(frame,"未找到该员工！");
                }
                else
                {
                    for(temp = wm.Find(name);temp<wm.Workernum-1;temp++)
                    {
                        wm.workerarray[temp] = wm.workerarray[temp + 1];
                    }
                    wm.Workernum--;
                    //保存
                    try(BufferedWriter writer = new BufferedWriter(new FileWriter(wm.FilePath)))
                    {
                        for(int i = 0;i<wm.Workernum;i++)
                        {
                            writer.write(wm.workerarray[i].Showinfo());
                            writer.newLine();
                        }
                        writer.close();
                    }
                    catch(IOException a)
                    {
                        JOptionPane.showMessageDialog(frame,"文件写入错误"+a.getMessage());
                    }
                    JOptionPane.showMessageDialog(frame, "删除成功！");
                }
            }
        });
        //修改职工信息
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        //查找职工信息
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        //按照编号排序
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        //清空所有文档
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        //退出管理程序
        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
