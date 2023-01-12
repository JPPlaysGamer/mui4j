package com.iei.mui4j.examples;

import java.awt.EventQueue;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.iei.mui4j.MUIApp;
import com.iei.mui4j.Updater.WrapperGetter;
import com.iei.mui4j.components.ComponentWrapper;
import com.iei.mui4j.components.ComponentWrapper.ComponentType;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class SwingExample extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingExample frame = new SwingExample();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public SwingExample() throws Exception {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnLangs = new JMenu("New menu");
		menuBar.add(mnLangs);
		
		JMenuItem mntmptbr = new JMenuItem("New menu item");
		mntmptbr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MUIApp.getInstance()
						.setNewLang(new Locale("pt", "BR"));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnLangs.add(mntmptbr);
		
		JMenuItem mntmenus = new JMenuItem("New menu item");
		mntmenus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MUIApp.getInstance()
						.setNewLang(new Locale("en", "US"));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnLangs.add(mntmenus);
		
		JMenuItem mntmeses = new JMenuItem("New menu item");
		mntmeses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MUIApp.getInstance()
						.setNewLang(new Locale("es", "ES"));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnLangs.add(mntmeses);
		
		JMenuItem mntmExit = new JMenuItem("New menu item");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		menuBar.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		WrapperGetter getter = new WrapperGetter() {
			
			@Override
			public List<ComponentWrapper> getWrappers() {
				// TODO Auto-generated method stub
				return Arrays.asList(
					new ComponentWrapper("mnLangs", ComponentType.SWING, mnLangs),
					new ComponentWrapper("mntmExit", ComponentType.SWING, mntmExit),
					new ComponentWrapper("mntmptbr", ComponentType.SWING, mntmptbr),
					new ComponentWrapper("mntmenus", ComponentType.SWING, mntmenus),
					new ComponentWrapper("mntmeses", ComponentType.SWING, mntmeses)
						
				);
			}
		};
		
		MUIApp.registerAndUpdate("Swing Example", ExampleConfig.class, getter);
	}
}
