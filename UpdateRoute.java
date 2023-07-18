import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class UpdateRoute extends JFrame
{
	public void Ur()
	{
		JFrame un = new JFrame("UPDATE ROUTE");
		un.setBounds(300,100,600,500);
		un.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		un.setVisible(true);
		
		Container c1 =un.getContentPane();
        c1.setLayout(null);
		
		Font font = new Font("Trebuchet",Font.BOLD,15);
		Font font1 = new Font("Butler",Font.BOLD,15);
		
		JLabel b1 = new JLabel("Bus Number");
		b1.setBounds(30,50,200,40);
		c1.add(b1);
		JLabel b2 = new JLabel("Source");
		b2.setBounds(30,130,200,40);
		c1.add(b2);
		JLabel b3 = new JLabel("Destination");
		b3.setBounds(300,130,200,40);
		c1.add(b3);
		JLabel b4 = new JLabel("Fare(INR)");
		b4.setBounds(30,210,200,40);
		c1.add(b4);
		
		b1.setFont(font);
		b2.setFont(font);
		b3.setFont(font);
		b4.setFont(font);
		
		JTextField t1 = new JTextField();
        t1.setBounds(30,80,200,30);
		c1.add(t1);
		JTextField t2 = new JTextField();
        t2.setBounds(30,160,200,30);
		c1.add(t2);
		JTextField t3 = new JTextField();
        t3.setBounds(300,160,200,30);
		c1.add(t3);
		JTextField t4 = new JTextField();
        t4.setBounds(30,240,200,30);
		c1.add(t4);
		
		JButton bb1 = new JButton("ENTER");
		bb1.setBounds(140,325,200,30);
		bb1.setFont(font);
		c1.add(bb1);
		
		JButton bb2 = new JButton("Clear");
		bb2.setBounds(190,390,100,30);
		bb2.setFont(font);
		c1.add(bb2);
		
		bb2.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event)
		{
			t1.setText("");
            t2.setText("");
            t3.setText(""); 			
		    t4.setText("");
			
		}});

		
		bb1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event) 
		{
			Connection c=null;
			Statement st = null;
			ResultSet rs = null;
			PreparedStatement pst = null;
			String s1,s2,s3,s4,s5;
            s1=t1.getText();
            s2=t2.getText();
            s3=t3.getText();
            s4=t4.getText();
			
            
			try
            {
				String url = "jdbc:postgresql://localhost:5432/proj";
				String username = "postgres";
                String password1 = "Ravindra@1975";
                Class.forName("org.postgresql.Driver");
                c = DriverManager.getConnection(url, username, password1);
                c.setAutoCommit(false);
             String sql1 = "Select * from bus1 where bus_no='"+s1+"';";
             String sql = "UPDATE bus1 SET bus_no='"+s1+"',source='"+s2+"',destination='"+s3+"',fare='"+s4+"' WHERE bus_no='"+s1+"';";
             
			 pst = c.prepareStatement(sql1);
			 rs = pst.executeQuery();
			  
			 if(rs.next())
			 {
				 st = c.createStatement();
			     st.executeUpdate(sql);
			     c.commit();
			 }
			 else
			 {
				JOptionPane.showMessageDialog(null,"Invalid Bus Entered!"); 
			 }
			}
            catch(Exception sqlExcptn)
            {
              System.out.println(sqlExcptn);
            }
		}});
		c1.revalidate(); 
	}
	
	public static void main(String args[])
	{
		UpdateRoute r = new UpdateRoute();
		r.Ur();
	}
}


	