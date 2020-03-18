/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Kadir
 */
public class Database extends JPanel implements ActionListener{
    
    
        
    private JButton jbtn;
    private JButton jbtn1;
    private JButton jbtn2;
    private JButton jbtn3;
    private JLabel jlbl;
    private JLabel jlbl1;
    private JLabel jlbl2;
    private JLabel jlbl3;
    private JLabel jlbl4;
    private JTextField txt;
    private JTextField txt1;
    private JTextField txt2;
    private JTextField txt3;
    private JTable table;
    public Database(){
    setLayout(new FlowLayout());
    jlbl = new JLabel("movie ıd");
    jlbl1 = new JLabel("movie name");
    jlbl2 = new JLabel("rating");
    jlbl3 = new JLabel("year");
    jlbl4 = new JLabel("MovieId MOVİE NAME  RATE             YEAR");
    jlbl4.setVisible(false);
    txt = new JTextField(35);
    txt1 = new JTextField(35);
    txt2 = new JTextField(35);
    txt3 = new JTextField(35);
    jbtn= new JButton("İNSERT");
    jbtn1= new JButton("UPDATE");
    jbtn2= new JButton("DELETE");
    jbtn3= new JButton("READ");
    table = new JTable();
    jbtn.addActionListener(this);
    jbtn1.addActionListener(this);
    jbtn2.addActionListener(this);
    jbtn3.addActionListener(this);
    add(jlbl);
    add(txt);
    add(jlbl1);
    add(txt1);
    add(jlbl2);
    add(txt2);
    add(jlbl3);
    add(txt3);
    add(jbtn);
    add(jbtn1);
    add(jbtn2);    
    add(jbtn3);  
    add(jlbl4);
    add(table);
    }
  
        static Connection con=null;
        static Statement stat=null;
        static String sql="";
        static String driver="com.mysql.cj.jdbc.Driver";
        static String url="jdbc:mysql://localhost:3306/movies?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        static String user="sa";
        static String password="1234";

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame data = new JFrame();
        data.setVisible(true);
        data.setSize(400,500);
        data.setDefaultCloseOperation(EXIT_ON_CLOSE);
        data.setTitle("MOVİES");
        Database d = new Database();
        data.add(d);
    }
        
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==jbtn){
         try{
        Class.forName(driver);
        Connection con=DriverManager.getConnection(url,user,password);
        String sql="insert into imdb"+"(movieId,moviename,rate,year)"+"values(?,?,?,?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1,txt.getText());
        pst.setString(2,txt1.getText());
        pst.setString(3,txt2.getText());
        pst.setString(4,txt3.getText());
        pst.executeUpdate();
        
        JOptionPane.showMessageDialog(this,"İnserted");
         }
         catch(Exception e){
         }
        }
        else if(ae.getSource()==jbtn1){
          try{
        Class.forName(driver);
        Connection con=DriverManager.getConnection(url,user,password);
        String sql="update imdb set rate=? where movieId=?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1,txt2.getText());
        pst.setString(2,txt.getText());
        pst.executeUpdate();
        JOptionPane.showMessageDialog(this,"Updated");
         }
         catch(Exception e){
         }
        }
        else if(ae.getSource()==jbtn2){
          try{
        Class.forName(driver);
        Connection con=DriverManager.getConnection(url,user,password);
        String sql="Delete from imdb where movieId=?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1,txt.getText());
        pst.executeUpdate();
        JOptionPane.showMessageDialog(this,"Deleted");
         }
         catch(Exception e){
         }
        }
        else if(ae.getSource()==jbtn3){
            try{
                jlbl4.setVisible(true);
        Class.forName(driver);
        Connection con=DriverManager.getConnection(url,user,password);
        String sql="Select * from imdb order by rate desc";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        table.setModel(DbUtils.resultSetToTableModel(rs));
        JOptionPane.showMessageDialog(this,"Table Model");
        
         }
         catch(Exception e){
         }
        
        }
        
        
    }
    
}
