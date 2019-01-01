package a06;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.JLayeredPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This program creates the class Face which extends JFrame.
 * Face creates a graphical user interface which displays a face with eyes, a nose, a mouth, and a background 
 * image. This program also displays a menu with check boxes for each facial feature. When a feature's check 
 * box is selected and the submit button is pressed, the image changes. If no boxes are selected, the background
 * image changes. The program cycles through 4 eye, 4 nose, 4 mouth, and 3 background images.
 * @author Kate Sanders
 */
public class Face extends JFrame 
{
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Face frame = new Face();
					frame.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Face()
	{
		makeMainWindow();
		makeHeading(); 
		
		//menu
		JPanel menuPanel = makeMenuPanel();
		makeChooseLabel(menuPanel);
		JCheckBox chckbxEyes = makeCheckBox(menuPanel, "Eyes");
		JCheckBox chckbxNose = makeCheckBox(menuPanel, "Nose");
		JCheckBox chckbxMouth = makeCheckBox(menuPanel, "Mouth");
		JButton btnUpdate = makeUpdateButton(menuPanel);
		
		//potato & facial feature images
		JLayeredPane layeredPaneImages = makeImageDisplay();
		ArrayList<JLabel> potatoes = createPotatoes(layeredPaneImages); //make potatoes
		ArrayList<JLabel> eyes = createFeatures(layeredPaneImages, "eyes", 200, 163, 200, 104); //make eyes
		ArrayList<JLabel> noses = createFeatures(layeredPaneImages, "nose", 210, 272, 185, 120); //make noses
		ArrayList<JLabel> mouths = createFeatures(layeredPaneImages, "mouth", 166, 400, 275, 167); //make mouths	
		
		//submit button function
		btnUpdate.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//change eyes
				if (chckbxEyes.isSelected())
					changeFeature(layeredPaneImages, eyes);
				//change nose
				if (chckbxNose.isSelected())
					changeFeature(layeredPaneImages, noses);
				//change mouth
				if (chckbxMouth.isSelected())
					changeFeature(layeredPaneImages, mouths);
				//change face (potato)
				if( (!chckbxEyes.isSelected())&&(!chckbxNose.isSelected())&&(!chckbxMouth.isSelected()) )
				{
					changePotato(layeredPaneImages, potatoes);
				}		
			}

			/**
			 * Cycle through facial feature images
			 * @param layeredPane 
			 * @param feature
			 */
			private void changeFeature(JLayeredPane layeredPaneImages, ArrayList<JLabel> feature) 
			{
				for (int i = 0; i < feature.size(); i++)
				{				
					layeredPaneImages.setLayer( feature.get(i), layeredPaneImages.getLayer(feature.get(i))+1 );
					if (layeredPaneImages.getLayer(feature.get(i)) == 5)
						layeredPaneImages.setLayer(feature.get(i), 1);	
				}
			}
			
			/**
			 * Cycle through potato images
			 * @param layeredPane
			 * @param potatoes
			 */
			private void changePotato(JLayeredPane layeredPaneImages, ArrayList<JLabel> potatoes) 
			{
				for (int i = 0; i < potatoes.size(); i++)
				{				
					layeredPaneImages.setLayer( potatoes.get(i), layeredPaneImages.getLayer(potatoes.get(i))+1 );
					if (layeredPaneImages.getLayer(potatoes.get(i)) == 4)
						layeredPaneImages.setLayer(potatoes.get(i), 1);					
				}
			}
		});
	}

	/**
	 * Adds a heading to the top of the display
	 */
	private void makeHeading() 
	{
		JLabel lblHeading = new JLabel("Face Project");
		lblHeading.setOpaque(true);
		lblHeading.setBackground(Color.RED);
		lblHeading.setForeground(Color.WHITE);
		lblHeading.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeading.setFont(new Font("Comic Sans MS", Font.BOLD, 35));
		contentPane.add(lblHeading, BorderLayout.NORTH);
	}

	/**
	 * Sets up the main window for the program
	 */
	private void makeMainWindow() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 896, 849);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setTitle("Face Project");
	}
	
	/**
	 * Creates the menuPanel
	 * @return JPanel menuPanel - panel to display the menu
	 */
	private JPanel makeMenuPanel() 
	{
		JPanel menuPanel = new JPanel();
		menuPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		menuPanel.setBackground(Color.WHITE);
		contentPane.add(menuPanel, BorderLayout.WEST);
		menuPanel.setLayout(new GridLayout(14, 1, 10, 0));
		return menuPanel;
	}
	
	/**
	 * Creates a label that says "choose:" and adds it to the menu
	 * @param JPanel menuPanel - JPanel to display the menu
	 */
	private void makeChooseLabel(JPanel menuPanel) 
	{
		JLabel lblYouChoose = new JLabel("Choose:");
		lblYouChoose.setHorizontalTextPosition(SwingConstants.CENTER);
		lblYouChoose.setHorizontalAlignment(SwingConstants.CENTER);
		lblYouChoose.setForeground(Color.DARK_GRAY);
		lblYouChoose.setOpaque(true);
		lblYouChoose.setBackground(Color.WHITE);
		lblYouChoose.setFont(new Font("Forte", Font.BOLD, 30));
		menuPanel.add(lblYouChoose);
	}

	/**
	 * Creates a check box 
	 * @param JPanel menuPanel - JPanel to display the menu
	 * @param String text - name of the facial feature (eyes, nose, or mouth)
	 * @return jCheckBox chckbxNew - check box for text entered
	 */
	private JCheckBox makeCheckBox(JPanel menuPanel, String text) 
	{
		JCheckBox chckbxNew = new JCheckBox(text);
		chckbxNew.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chckbxNew.setBackground(Color.WHITE);
		chckbxNew.setMargin(new Insets(2, 20, 2, 40));
		chckbxNew.setFont(new Font("Kristen ITC", Font.PLAIN, 20));
		menuPanel.add(chckbxNew);
		return chckbxNew;
	}
	
	/**
	 * Creates an update button
	 * @param JPanel menuPanel - JPanel to display the menu
	 * @return JButton btnUpdate - JButton to update features
	 */
	private JButton makeUpdateButton(JPanel menuPanel) 
	{
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setMargin(new Insets(2, 2, 2, 2));
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.setForeground(new Color(255, 255, 255));
		btnUpdate.setBackground(Color.LIGHT_GRAY);
		btnUpdate.setFont(new Font("Forte", Font.BOLD, 20));
		menuPanel.add(btnUpdate);
		return btnUpdate;
	}
	
	/**
	 * Creates a layeredPane where images are displayed
	 * @return JLayeredPane layeredPaneImages - layeredPane where images are displayed
	 */
	private JLayeredPane makeImageDisplay() 
	{
		JLayeredPane layeredPaneImages = new JLayeredPane();
		contentPane.add(layeredPaneImages, BorderLayout.CENTER);	
		return layeredPaneImages;
	}
	
	/**
	 * Creates an array of JLabels which displays potato images, assigns each image to a layer and displays it.
	 * @param JLayeredPane layeredPaneImages - layeredPane where the images are displayed
	 * @return ArrayList<JLabels> potatoes - ArrayList of JLabels which contains images of potatoes
	 */
	private ArrayList<JLabel> createPotatoes(JLayeredPane layeredPaneImages)
	{
		ArrayList<JLabel> potatoes = new ArrayList<JLabel>();
		for (int i = 1; i < 4; i++)
		{
			JLabel lblPotatoPicture = new JLabel("");
			layeredPaneImages.setLayer(lblPotatoPicture, i);
			lblPotatoPicture.setIcon(new ImageIcon(Face.class.getResource("/a06/img/potato" + i + ".png")));
			lblPotatoPicture.setBounds(69, 26, 470, 653);
			layeredPaneImages.add(lblPotatoPicture);
			potatoes.add(lblPotatoPicture);
		}
		return potatoes;
	}

	/**
	 * Creates an array of JLabels which display facial feature images, assigns each image to a layer and
	 * displays it.
	 * @param layeredPaneImages - layeredPane where the images are displayed
	 * @param String FeatureName - name of the facial feature (eyes, nose, or mouth)
	 * @param int bound1 - x coordinate
	 * @param int bound2 - y coordinate
	 * @param int bound3 - image width
	 * @param int bound4 - image height
	 * @return ArrayList<JLabels> features - ArrayList of JLabels which contains images of facial features
	 */
	private ArrayList<JLabel> createFeatures(JLayeredPane layeredPaneImages, String featureName, int bound1, int bound2, int bound3, int bound4) 
	{
		ArrayList<JLabel> features = new ArrayList<JLabel>();
		for (int i = 1; i < 5; i++)
		{
			JLabel lblfeature = new JLabel("");
			lblfeature.setHorizontalAlignment(SwingConstants.CENTER);
			layeredPaneImages.setLayer(lblfeature, i);
			lblfeature.setIcon(new ImageIcon(Face.class.getResource("/a06/img/" + featureName + i + ".png")));
			lblfeature.setBounds(bound1, bound2, bound3, bound4);
			layeredPaneImages.add(lblfeature);
			features.add(lblfeature);
		}
		return features;
	}
}