package Classes;

import javax.swing.*;
import java.awt.*;

public class TitleBar extends JPanel {
    //Constructor
    TitleBar()
    {
        this.setPreferredSize(new Dimension(500,80));

        JLabel titleText = new JLabel("To Do List");
        titleText.setPreferredSize(new Dimension(250,80));
        titleText.setFont(new Font("Broadway",Font.ITALIC,25));
        titleText.setHorizontalAlignment(JLabel.CENTER);

        this.add(titleText);

    }
}
