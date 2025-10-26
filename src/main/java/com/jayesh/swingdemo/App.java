package com.jayesh.swingdemo;



import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class App {

	int id;
	String name;
	String email;
	String contact;
	
	
	JFrame jframe;
	JPanel jpanel;
	
	JLabel lblHeading, lblId, lblName, lblEmail, lblContact, lblMessage;
	TextField txtId,txtName,txtEmail,txtContact;
	JButton btnInsert, btnUpdate, btnDelete, btnShow, btnReset;
	
	DBConnectivity dbConnectivity;
	
	public App() {
		
		
		dbConnectivity = new DBConnectivity();
		
		 jframe = new JFrame();
		 jpanel = new JPanel();
		 
		 lblId  = new JLabel();
		 txtId = new TextField();
		 
		 lblName  = new JLabel();
		 txtName = new TextField();
		 
		 lblEmail = new JLabel();
		 txtEmail = new TextField();
		 
		 lblEmail = new JLabel();
		 txtEmail = new TextField();
		 
		 lblContact = new JLabel();
		 txtContact = new TextField();
		 
		 lblMessage = new JLabel();
		 
		 btnInsert = new JButton("INSERT");
		 btnUpdate = new JButton("UPDATE");
		 btnDelete = new JButton("DELETE");
		 btnShow = new JButton("SHOW");
		 btnReset = new JButton("RESET");
		 
		 jpanel.setLayout(null);
		 
		 
		 
		 lblId.setText("Enter The ID: ");
		 lblId.setBounds(50, 50, 120, 20);
		 jpanel.add(lblId);
		 
		 txtId.setBounds(170, 50, 200, 20);
		 jpanel.add(txtId);
		 
		 
		 
		 lblName.setText("Enter The Name: ");
		 lblName.setBounds(50, 80, 120, 20);
		 jpanel.add(lblName);
		 
		 txtName.setBounds(170, 80, 200, 20);
		 jpanel.add(txtName);
		 
		 
		 
		 lblEmail.setText("Enter The Email: ");
		 lblEmail.setBounds(50, 110, 120, 20);
		 jpanel.add(lblEmail);
		 
		 txtEmail.setBounds(170, 110, 200, 20);
		 jpanel.add(txtEmail);
		 
		 
		 
		 lblContact.setText("Enter The Contact: ");
		 lblContact.setBounds(50, 140, 120, 20);
		 jpanel.add(lblContact);
		 
		 txtContact.setBounds(170, 140, 200, 20);
		 jpanel.add(txtContact);
		 
		 lblMessage.setText("");
		 lblMessage.setBounds(50, 180, 200, 20);
		 jpanel.add(lblMessage);
		 
		 btnInsert.setBounds(80, 220, 90, 20);
		 jpanel.add(btnInsert);
		 
		 btnUpdate.setBounds(190, 220, 90, 20);
		 jpanel.add(btnUpdate);
		 
		 btnDelete.setBounds(300, 220, 90, 20);
		 jpanel.add(btnDelete);
		 
		 btnShow.setBounds(140, 250, 90, 20);
		 jpanel.add(btnShow);
		 
		 btnReset.setBounds(250, 250, 90, 20);
		 jpanel.add(btnReset);
		 
		 
		 
		 jframe.add(jpanel);
	     jframe.setSize(500, 500);
	     jframe.setLocation(100, 100);
	     jframe.setTitle("ContactInfo");
	     jframe.setVisible(true);
	
	     
	     btnInsert.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if (txtId.getText().toString().isEmpty() ||
						txtName.getText().toString().isEmpty()||
						txtEmail.getText().toString().isEmpty()||
						txtContact.getText().toString().isEmpty()) {
					
					lblMessage.setText("All Field are Required");
				}else {
					
					id = Integer.parseInt(txtId.getText());
					name = txtName.getText().toString();
					email = txtEmail.getText().toString();
					contact = txtContact.getText().toString();
					
					dbConnectivity.getConnection();
					dbConnectivity.insertData(id, name, email, contact);
					dbConnectivity.closeConnection();
					
					lblMessage.setText("Contact Save Successfully");
					
				}
			}
		});
	   
	     btnReset.addActionListener(new ActionListener() {
			
	    	 
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
		
				txtId.setText("");
				txtName.setText("");
				txtEmail.setText("");
				txtContact.setText("");
				txtId.requestFocus();
			}
		});
	     
	     btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (txtId.getText().toString().isEmpty()) {
					
					lblMessage.setText("Id is Required for Update Record");
				}
				else {
					
					id = Integer.parseInt(txtId.getText());
					name = txtName.getText().toString();
					email = txtEmail.getText().toString();
					contact = txtContact.getText().toString();
					
					dbConnectivity.getConnection();
					dbConnectivity.updateData(id, name, email, contact);
					dbConnectivity.closeConnection();
					
					lblMessage.setText("Contact Update Successfully");
				}
				
			}
		});
	     
	     btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (txtId.getText().isEmpty()) {
					
					System.out.println("Id is Required for Deleted Record!!");
					lblMessage.setText("Id is Required for Deleted Record!!");
				}
				else {
					int id = Integer.parseInt(txtId.getText());
					
					dbConnectivity.getConnection();
					dbConnectivity.deleteData(id);
					dbConnectivity.closeConnection();
					
					lblMessage.setText("Contact Deleted Successfully !!");
					txtId.setText("");
					txtId.requestFocus();
					
				}
				
			}
		});
	     
	     btnShow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtId.getText().isEmpty()) {
					
					System.out.println("Id is Required for Show Records!!");
					lblMessage.setText("Id is Required for Show Records!!");
					
				}else {
					try {
						
						Driver d = new com.mysql.cj.jdbc.Driver();
						DriverManager.registerDriver(d);
						Connection con = 	DriverManager.getConnection("jdbc:mysql://localhost:3306/swingdemodb","root","Jayesh");
						Statement stmt = 	con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
						ResultSet rs = stmt.executeQuery("Select id,name,email,contact from contactinfo");
						
						
						id = Integer.parseInt(txtId.getText());
						
						int a =1;
						while (rs.next()) {
							if (id != rs.getInt(1)) {
								a++;
							}else {
								break;
							}	
						}
						rs.absolute(a);
						
						txtId.setText(String.valueOf(rs.getInt(1)));
						txtName.setText(rs.getString(2));
						txtEmail.setText(rs.getString(3));
						txtContact.setText(rs.getString(4));
						
						con.close();
						System.out.println("Contact Show successfully !!!");
						lblMessage.setText("Contact Show Successfully !!");
						
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
				}
			}
		});
	     
	
	}
	
    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        	new App();
        
           
    }
}
