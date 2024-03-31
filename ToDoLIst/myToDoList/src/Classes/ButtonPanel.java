package Classes;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    private JButton addTask;
    private JButton clearTask;

    //Constructor
    ButtonPanel() {
        this.setPreferredSize(new Dimension(500, 50));
        this.setBackground(Color.red);

        addTask = new JButton("Add your task");
        addTask.setFont(new Font("Broadway", Font.PLAIN, 20));
        this.add(addTask);

        clearTask = new JButton("Clear completed task");
        clearTask.setFont(new Font("Broadway", Font.PLAIN, 20));
        this.add(clearTask);
    }


        public JButton getNewTask()
        {
            return addTask;
        }

        public JButton getClearTask()
        {
            return clearTask;
        }

    }



