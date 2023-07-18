import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DeleteRoute extends JFrame
{
	public void del()
	{
		JFrame cn =new JFrame("DELETE ROUTE");
		cn.setBounds(150,150,500,600);
		cn.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		cn.setVisible(true);
		
		Container c3 = cn.getContentPane();
		c3.setLayout(null);
		
		Font font = new Font("Trebuchet",Font.BOLD,15);
		JLabel cl = new JLabel("ENTER THE BUS NUMBER");
		cl.setBounds(40,50,300,40);
		cl.setFont(font);
        c3.add(cl);
		
		final JTextField ctext = new JTextField();
        ctext.setBounds(10,100,300,40);
		ctext.setForeground(Color.BLACK);
		c3.add(ctext);

		final JLabel next = new JLabel();
   		next.setBounds(10,250,500,30);
		next.setFont(font);
		c3.add(next);

		JButton bb1 = new JButton("NEXT");
		bb1.setBounds(50,175,200,30);
		bb1.setFont(font);
        c3.add(bb1);
		
		JButton bb2 = new JButton("Clear");
		bb2.setBounds(100,215,100,30);
		bb2.setFont(font);
		c3.add(bb2);
		
		bb2.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event)
		{
			ctext.setText("");
		}});
		
		bb1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event)
		{			
			Connection c=null;
			PreparedStatement pst = null;
			Statement st = null;
			ResultSet rs = null;
			
			String s1,password;
			s1=ctext.getText();
			
			try
            {
				String url = "jdbc:postgresql://localhost:5432/proj";
				String username = "postgres";
                String password1 = "Ravindra@1975";
                Class.forName("org.postgresql.Driver");
                c = DriverManager.getConnection(url, username, password1);
                c.setAutoCommit(false);
                System.out.println("Hellp");
                st = c.createStatement(); 
                String sql = "DELETE FROM bus1 WHERE bus_no='"+s1+"';";
                String sql1 = "select * from bus1 where bus_no='"+s1+"';";
			 
			 pst = c.prepareStatement(sql1);
             rs = pst.executeQuery();
             c.commit();
			 
			 st = c.createStatement();
			 st.executeUpdate(sql);
			 c.commit();
			 
			 if(s1.equals(""))
			 {
			   JOptionPane.showMessageDialog(null,"Enter Bus Number!"); 
			 }
			 else if(rs.next())
			 {
			   next.setEnabled(true);
			   String data1 = "The Deleted Bus is " + ctext.getText();
			   JOptionPane.showMessageDialog(null,data1); 
			   //next.setText(data1);	
			 }
			 else
			 {
			   JOptionPane.showMessageDialog(null,"Bus not Found!");
			 }
			}
			catch(Exception sqlExcptn)
            {
              System.out.println(sqlExcptn);
            }
			
		}});
		//c3.revalidate();
	   
	}
    public static void main(String args[])
	{
		DeleteRoute d = new DeleteRoute();
		d.del();
	}
	
}
/*import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DeleteRoute extends JFrame {

    public void del() {
        setTitle("DELETE ROUTE");
        setBounds(150, 150, 500, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        add(mainPanel);

        Font font = new Font("Trebuchet", Font.BOLD, 15);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        mainPanel.add(inputPanel);
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        mainPanel.add(centerPanel);

        JLabel cl = new JLabel("ENTER THE BUS NUMBER");
        cl.setFont(font);
        centerPanel.add(cl);

        final JTextField ctext = new JTextField(20);
        ctext.setForeground(Color.BLACK);
        centerPanel.add(ctext);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        mainPanel.add(buttonPanel);

        final JLabel next = new JLabel();
        next.setFont(font);
        mainPanel.add(next);

        JButton bb1 = new JButton("NEXT");
        bb1.setFont(font);
        buttonPanel.add(bb1);

        JButton bb2 = new JButton("Clear");
        bb2.setFont(font);
        buttonPanel.add(bb2);

        bb2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                ctext.setText("");
            }
        });

        bb1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Connection conn = null;
                PreparedStatement pst = null;
                Statement st = null;
                ResultSet rs = null;

                String s1;
                s1 = ctext.getText();

                try {
                    Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                    String msAccDB = "C:/Users/Jayesh/Desktop/MiniProjectFiles/BusManagementSystemDatabase.accdb";
                    String dbURL = "jdbc:ucanaccess://" + msAccDB;
                    conn = DriverManager.getConnection(dbURL);
                    String sql = "DELETE FROM BUSES WHERE NUMBER='" + s1 + "';";
                    String sql1 = "select * from Buses where NUMBER='" + s1 + "';";

                    pst = conn.prepareStatement(sql1);
                    rs = pst.executeQuery();

                    st = conn.createStatement();
                    st.executeUpdate(sql);                    if (s1.equals("")) {
                        JOptionPane.showMessageDialog(null, "Enter Bus Number!");
                    } else if (rs.next()) {
                        next.setEnabled(true);
                        String data1 = "The Deleted Bus is " + ctext.getText();
                        JOptionPane.showMessageDialog(null,data1); 
                    } else {
                        JOptionPane.showMessageDialog(null,"Bus not Found!");
                    }
                } catch(Exception sqlExcptn) {
                    System.out.println(sqlExcptn);
                } finally {
                    try {
                        if (rs != null) {
                            rs.close();
                        }
                        if (pst != null) {
                            pst.close();
                        }
                        if (conn != null) {
                            conn.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        DeleteRoute dr = new DeleteRoute();
        dr.del);
    }
}*/
	