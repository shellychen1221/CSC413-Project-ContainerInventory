import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class ContainerInventoryGUI extends JFrame implements ActionListener {
   private JLabel idLabel, weightLabel, senderLabel, receiverLabel, contentsLabel;
   private JTextField idField, weightField, senderField, receiverField, contentsField;
   private JButton saveButton;
   private String[] columnNames = {"ID Number", "Weight (kg)", "Name of sender", "Name of receiver", "Description of contents"};
   private File csvFile = new File("containerInformation.csv");
   private HashSet<Integer> idSet = new HashSet<Integer>();
   
   public ContainerInventoryGUI() {
      setTitle("Shipping Container Inventory");
      setSize(400, 250);
      setLayout(new GridLayout(6, 2));
      
      idLabel = new JLabel(" ID Number: ");
      add(idLabel);
      idField = new JTextField();
      add(idField);
      
      weightLabel = new JLabel(" Weight (kg): ");
      add(weightLabel);
      weightField = new JTextField();
      add(weightField);
      
      senderLabel = new JLabel(" Name of sender: ");
      add(senderLabel);
      senderField = new JTextField();
      add(senderField);
      
      receiverLabel = new JLabel(" Name of receiver: ");
      add(receiverLabel);
      receiverField = new JTextField();
      add(receiverField);
      
      contentsLabel = new JLabel(" Description of contents: ");
      add(contentsLabel);
      contentsField = new JTextField();
      add(contentsField);
      
      saveButton = new JButton("Save");
      saveButton.addActionListener(this);
      add(saveButton);
      
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   
   public void actionPerformed(ActionEvent e) {
      String id = idField.getText();
      String weight = weightField.getText();
      String sender = senderField.getText();
      String receiver = receiverField.getText();
      String contents = contentsField.getText();
      
      if (id.isEmpty() || weight.isEmpty() || sender.isEmpty() || receiver.isEmpty() || contents.isEmpty()) {
         JOptionPane.showMessageDialog(null, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
         return;
      }
      
      try {
         int idNum = Integer.parseInt(id);
         if (idSet.contains(idNum)) {
            JOptionPane.showMessageDialog(null, "ID number already exists.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
         }
         idSet.add(idNum);
         boolean fileExists = csvFile.exists();
         FileWriter csvWriter = new FileWriter(csvFile, true);
         if (!fileExists) {
             csvWriter.write("ID Number,Weight (kg),Name of sender,Name of receiver,Description of contents\n");
             
         }
         csvWriter.write(id + "," + weight + "," + sender + "," + receiver + "," + contents + "\n");
         csvWriter.close();
         
         JOptionPane.showMessageDialog(null, "Shipping container information saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
         
         idField.setText("");
         weightField.setText("");
         senderField.setText("");
         receiverField.setText("");
         contentsField.setText("");
      } catch (NumberFormatException ex) {
         JOptionPane.showMessageDialog(null, "ID number must be an integer.", "Error", JOptionPane.ERROR_MESSAGE);
      } catch (IOException ex) {
         JOptionPane.showMessageDialog(null, "Error occurred while writing to file.", "Error", JOptionPane.ERROR_MESSAGE);
      }
   }
   
   public static void main(String[] args) {
      new ContainerInventoryGUI();
   }
}
