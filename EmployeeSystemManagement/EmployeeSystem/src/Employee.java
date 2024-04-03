import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;

public class Employee {
    private JTextField textName;
    private JTextField textMobileNumber;
    private JTextField textSalary;
    private JTextField textDepartment;
    private JButton saveButton;
    private JTable table1;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton searchButton;
    private JTextField textField1;
    JPanel panel;

    Connection con;
    PreparedStatement pst;

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/nonamecompany","root","admin");
            System.out.println("Success");
        } catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }

    }
    public Employee() {
        connect();
        table_load();
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String empname, department, contact;
                int salary;

                empname = textName.getText();
                department = textDepartment.getText();
                contact = textMobileNumber.getText();
                salary = Integer.parseInt(textSalary.getText());

                try {
                    pst = con.prepareStatement("insert into employee(empname, salary, department, contact) values (?,?,?,?)");
                    pst.setString(1, empname);
                    pst.setInt(2, salary);
                    pst.setString(3, department);
                    pst.setString(4, contact);
                    pst.executeUpdate();
                    table_load();
                    JOptionPane.showMessageDialog(null, "Added");

                    textName.setText("");
                    textSalary.setText("");
                    textDepartment.setText("");
                    textMobileNumber.setText("");
                } catch (SQLException e1) {

                }
            }




        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String id = textField1.getText();

                    pst = con.prepareStatement("select empname, salary, department, contact from employee where id = ? ");
                    pst.setString(1,id);
                    ResultSet resultSet = pst.executeQuery();

                    if(resultSet.next() == true)
                    {
                        String empname = resultSet.getString(1);
                        int salary = resultSet.getInt(2);
                        String department = resultSet.getString(3);
                        String contact = resultSet.getString(4);

                        textName.setText(empname);
                        textSalary.setText(String.valueOf(salary));
                        textDepartment.setText(department);
                        textMobileNumber.setText(contact);
                    }
                    else {
                        textName.setText("");
                        textSalary.setText("");
                        textDepartment.setText("");
                        textMobileNumber.setText("");
                        JOptionPane.showMessageDialog(null, "Invalid Employee ID.");
                    }

                }
                catch (SQLException e2){

                }
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id, empname, department, contact;
                int salary;

                empname = textName.getText();
                department = textDepartment.getText();
                contact = textMobileNumber.getText();
                salary = Integer.parseInt(textSalary.getText());
                id = textField1.getText();

                try {
                    pst = con.prepareStatement("update employee set empname = ?, salary = ?, department = ?, contact = ? where id = ?");
                    pst.setString(1, empname);
                    pst.setInt(2, salary);
                    pst.setString(3, department);
                    pst.setString(4, contact);
                    pst.setString(5, id);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Updates have been done.");

                    table_load();
                    textName.setText("");
                    textSalary.setText("");
                    textDepartment.setText("");
                    textMobileNumber.setText("");
                    textName.requestFocus();
                } catch (SQLException e1) {

                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id;
                id = textField1.getText();
                try {
                    pst = con.prepareStatement("delete from employee where id = ?");
                    pst.setString(1,id);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Record deleted.");
                    table_load();
                    textName.setText("");
                    textSalary.setText("");
                    textDepartment.setText("");
                    textMobileNumber.setText("");
                    textName.requestFocus();
                    textField1.setText("");
                }
                catch (SQLException ex3)
                {

                }

            }
        });
    }

    void table_load()
    {
        try
        {
            pst = con.prepareStatement("select * from employee");
            ResultSet resultSet = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(resultSet));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
