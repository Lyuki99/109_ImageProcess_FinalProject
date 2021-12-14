package ImageProcess;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class ImageProcess {

	private JFrame frame;
	static File OriganImg;
	static String OriganImgPathString="";
	static JButton btn_SaltAndPepper;
	static String str_GrayScale="";
	static JButton btn_MinFilter;
	static JButton btn_MaxFilter;
	static JButton btn_MeanFilter;
	static JButton btn_MedianFilter;
	static String str_SaltAndPepperString;
	static JButton btn_LaplacianBinarization;
	static JButton btn_Laplacian;
	static String str_Laplacian;
	static JButton btn_SobelBinarization;
	static JButton btn_Sobel;
	static String str_Sobel;
	static String str_SobelHorizontal;
	static String str_SobelVertical;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImageProcess window = new ImageProcess();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ImageProcess() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u5F71\u50CF\u8655\u7406\u671F\u672B");
		frame.setBounds(100, 100, 853, 646);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 839, 36);
		frame.getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("\u539F\u5716\u5716\u6A94");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 35, 839, 466);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lb_Image = new JLabel("");
		panel_1.add(lb_Image);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 499, 839, 41);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_1 = new JLabel("\u9078\u64C7\u6A94\u6848");
		lblNewLabel_1.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		panel_2.add(lblNewLabel_1);
		
		JLabel lb_FilePath = new JLabel("");
		panel_2.add(lb_FilePath);
		
		JButton btnNewButton = new JButton("\u700F\u89BD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser(OriganImg);
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				FileFilter filter = new FileNameExtensionFilter("JPEG File","jpg");
				fc.setFileFilter(filter);
				fc.showOpenDialog(frame);
				OriganImg = fc.getSelectedFile();
				lb_FilePath.setText(OriganImg.getPath());
				
				
				try {
					Image img = ImageIO.read(new File(OriganImg.getAbsolutePath()));
					Image img2 = img.getScaledInstance(700, 500, Image.SCALE_AREA_AVERAGING);
					ImageIcon icon= new ImageIcon(img2);
					lb_Image.setIcon(icon);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		panel_2.add(btnNewButton);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(-1, 539, 840, 75);
		frame.getContentPane().add(panel_3);
		
		JButton btn_Grayscale = new JButton("\u7070\u968E");
		btn_Grayscale.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		btn_Grayscale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Grayscale grayscale = new Grayscale(OriganImg.getAbsolutePath());
					grayscale.setVisible(true);
				} catch (NullPointerException e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "請先選擇一張圖片","錯誤",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel_3.add(btn_Grayscale);
		
		JButton btn_Negative = new JButton("\u8CA0\u7247");
		btn_Negative.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Negative negative = new Negative(OriganImg.getAbsolutePath());
					negative.setVisible(true);
				} catch (NullPointerException e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "請先選擇一張圖片","錯誤",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btn_Negative.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		panel_3.add(btn_Negative);
		
		JButton btn_Gamma = new JButton("\u4F3D\u746A\u6821\u6B63");
		btn_Gamma.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		btn_Gamma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					GammaCorrection gc = new GammaCorrection(OriganImg.getAbsolutePath());
					gc.setVisible(true);
				} catch (NullPointerException e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "請先選擇一張圖片","錯誤",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel_3.add(btn_Gamma);
		
		btn_MinFilter = new JButton("\u6700\u5C0F\u503C\u6FFE\u6CE2\u5668");
		btn_MinFilter.setEnabled(false);
		btn_MinFilter.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		btn_MinFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MinFilter minFilter = new MinFilter(str_SaltAndPepperString);
				minFilter.setVisible(true);
			}
		});
		
		btn_SaltAndPepper = new JButton("\u80E1\u6912\u9E7D\u96DC\u8A0A");
		btn_SaltAndPepper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaltAndPepper sap = new SaltAndPepper(str_GrayScale);
				sap.setVisible(true);
			}
		});
		btn_SaltAndPepper.setEnabled(false);
		btn_SaltAndPepper.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		panel_3.add(btn_SaltAndPepper);
		panel_3.add(btn_MinFilter);
		
		btn_MaxFilter = new JButton("\u6700\u5927\u503C\u6FFE\u6CE2\u5668");
		btn_MaxFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MaxFilter maxFilter = new MaxFilter(str_SaltAndPepperString);
				maxFilter.setVisible(true);
			}
		});
		btn_MaxFilter.setEnabled(false);
		btn_MaxFilter.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		panel_3.add(btn_MaxFilter);
		
		btn_MeanFilter = new JButton("\u5E73\u5747\u503C\u6FFE\u6CE2\u5668");
		btn_MeanFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MeanFilter meanFilter = new MeanFilter(str_SaltAndPepperString);
				meanFilter.setVisible(true);
			}
		});
		btn_MeanFilter.setEnabled(false);
		btn_MeanFilter.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		panel_3.add(btn_MeanFilter);
		
		btn_MedianFilter = new JButton("\u4E2D\u4F4D\u6578\u6FFE\u6CE2\u5668");
		btn_MedianFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MedianFilter medianFilter = new MedianFilter(str_SaltAndPepperString);
				medianFilter.setVisible(true);
			}
		});
		btn_MedianFilter.setEnabled(false);
		btn_MedianFilter.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		panel_3.add(btn_MedianFilter);
		
		btn_Sobel = new JButton("\u7D22\u8C9D\u723E\u6FFE\u6CE2\u5668");
		btn_Sobel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sobel sobel = new Sobel(str_GrayScale);
				sobel.setVisible(true);
			}
		});
		btn_Sobel.setEnabled(false);
		btn_Sobel.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		panel_3.add(btn_Sobel);
		
		btn_SobelBinarization = new JButton("\u7D22\u8C9D\u723E\u4E8C\u503C\u5316");
		btn_SobelBinarization.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SobelBinarization sb = new SobelBinarization(str_SobelHorizontal,str_SobelVertical,str_Sobel);
				sb.setVisible(true);
			}
		});
		btn_SobelBinarization.setEnabled(false);
		btn_SobelBinarization.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		panel_3.add(btn_SobelBinarization);
		
		btn_Laplacian = new JButton("\u62C9\u666E\u62C9\u65AF\u6FFE\u6CE2\u5668");
		btn_Laplacian.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Laplacian laplacian = new Laplacian(str_GrayScale);
				laplacian.setVisible(true);
			}
		});
		btn_Laplacian.setEnabled(false);
		btn_Laplacian.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		panel_3.add(btn_Laplacian);
		
		btn_LaplacianBinarization = new JButton("\u62C9\u666E\u62C9\u65AF\u4E8C\u503C\u5316");
		btn_LaplacianBinarization.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LaplacianBinarization lfb = new LaplacianBinarization(str_Laplacian);
				lfb.setVisible(true);
			}
		});
		btn_LaplacianBinarization.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		btn_LaplacianBinarization.setEnabled(false);
		panel_3.add(btn_LaplacianBinarization);
	}
}
