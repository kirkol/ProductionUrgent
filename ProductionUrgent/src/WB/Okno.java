package WB;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JTextArea;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.WindowConstants;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.border.LineBorder;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
  

public class Okno extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private SimpleDateFormat formatDaty = new SimpleDateFormat("yyyy-MM-dd");
	private Calendar date = Calendar.getInstance();
	private String dzisiaj = formatDaty.format(date.getTime());
	private String DodaneDoPilnych = "";
	private JCheckBox czyPilne;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				Connection connection = db.RCPdatabaseConnection.dbConnector("tosia", "1234");
				try {
					Okno frame = new Okno(connection);
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the frame.
	 */
	public Okno(final Connection connection) {
		setResizable(false);
		
		setBackground(Color.WHITE);
		setTitle("Pilne na Produkcji");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 469, 315);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblMojeMenu = new JLabel("Pilne na Produkcji");
		lblMojeMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMojeMenu.setFont(new Font("Century", Font.BOLD, 24));
		
		JLabel lblProjekt = new JLabel("Projekt 500:");
		lblProjekt.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblTyp = new JLabel("Komentarz:");
		lblTyp.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode()==KeyEvent.VK_ENTER){
					System.out.println("aaa");
					SetAsUrgent(connection);
				}
			}
		});
		textField.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textArea.setBackground(Color.WHITE);
		textArea.setLineWrap(true);
		textArea.setBorder(new LineBorder(Color.LIGHT_GRAY));
		
		czyPilne = new JCheckBox("Pilne");
		czyPilne.setFont(new Font("Tahoma", Font.BOLD, 16));
		czyPilne.setBackground(Color.WHITE);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SetAsUrgent(connection);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblNewLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/fire.png")).getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING);
		lblNewLabel.setBounds(450, 0, 200, 200);
		lblNewLabel.setIcon(new ImageIcon(img));
		
		JButton btnUsu = new JButton("USU\u0143");
		btnUsu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RemoveFromurgent(connection);
			}
		});
		btnUsu.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblProjekt, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblTyp, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
								.addComponent(czyPilne, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnUsu, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblMojeMenu, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel, 0, 0, Short.MAX_VALUE)
						.addComponent(lblMojeMenu, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProjekt)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnUsu, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblTyp, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
							.addComponent(czyPilne, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addGap(106)))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		
		}
	
	// dodaje projekt do tabeli pilneDoCzesiowej (projekt na liscie produkcyjnej/Czesiowej bedzie mial zalaczony komentarz i/lub oznaczenie jako pilne)
	void SetAsUrgent(Connection connection){
		
		boolean pilnosc;
		String nrProjektu = null;
		
		if(textField.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Nie wype³niono pola projektu 500");
		}else{
			nrProjektu = textField.getText();
			if(!nrProjektu.startsWith("500/")){
				nrProjektu = "500/" + nrProjektu;
			}
			pilnosc = czyPilne.isSelected();
			try{
				String query= "INSERT INTO pilnedoczesiowej (pilne, zamowienie, komentarz) VALUES ("+pilnosc+", '"+nrProjektu+"', '"+textArea.getText()+"')";
				PreparedStatement pst=connection.prepareStatement(query);
				pst.executeQuery();
				pst.close();
			}catch (Exception e){
				e.printStackTrace();
			}		
		}
		
		int Delay = 900;
		JOptionPane pane = new JOptionPane("Dodano projekt "+nrProjektu, JOptionPane.INFORMATION_MESSAGE);
		JDialog dialog = pane.createDialog(null, "Title");
		dialog.setModal(false);
		dialog.setVisible(true);
		
		new Timer(Delay, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dialog.setVisible(false);
			}
		}).start();
	}
	
	// usuwa projekt z tabeli pilneDoCzesiowej
	void RemoveFromurgent(Connection connection){
		
		String nrProjektu = null;
		
		if(textField.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Nie wype³niono pola projektu 500");
		}else{
			nrProjektu = textField.getText();
			if(!nrProjektu.startsWith("500/")){
				nrProjektu = "500/" + nrProjektu;
			}
			try{
				String query= "DELETE FROM pilnedoczesiowej WHERE zamowienie = '"+nrProjektu+"'";
				PreparedStatement pst=connection.prepareStatement(query);
				pst.executeQuery();
				pst.close();
			}catch (Exception e){
				e.printStackTrace();
			}		
		}
		int Delay = 900;
		JOptionPane pane = new JOptionPane("Usunieto projekt "+nrProjektu, JOptionPane.INFORMATION_MESSAGE);
		JDialog dialog = pane.createDialog(null, "Title");
		dialog.setModal(false);
		dialog.setVisible(true);
		
		new Timer(Delay, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dialog.setVisible(false);
			}
		}).start();
	}
	
}


