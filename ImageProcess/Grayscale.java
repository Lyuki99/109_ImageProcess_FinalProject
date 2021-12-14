package ImageProcess;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;

public class Grayscale extends JFrame {

	private JPanel contentPane;
	static String OriganImgPathString ;
	private JTextField tf_FileName;
	static BufferedImage grayImage;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Grayscale frame = new Grayscale("");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param origanImgPathString
	 */
	public Grayscale(String origanImgPathString) {
		setTitle("\u8F49\u63DB\u7070\u968E");
		
		OriganImgPathString = origanImgPathString;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 853, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 839, 36);
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("\u8F49\u63DB\u7070\u968E");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(-2, 36, 427, 520);
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lb_OriganImg = new JLabel("");
		panel_1.add(lb_OriganImg);
		try {
			Image img = ImageIO.read(new File(OriganImgPathString));
			Image img2 = img.getScaledInstance(700, 500, Image.SCALE_AREA_AVERAGING);
			ImageIcon icon= new ImageIcon(img2);
			lb_OriganImg.setIcon(icon);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(425, 36, 414, 520);
		contentPane.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lb_GrayscaleImg = new JLabel("");
		panel_2.add(lb_GrayscaleImg);
		
		JLabel lb_ProccessImg = new JLabel("");
		panel_2.add(lb_ProccessImg);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 553, 839, 35);
		contentPane.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_1 = new JLabel("\u5132\u5B58\u4F4D\u7F6E");
		lblNewLabel_1.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		panel_3.add(lblNewLabel_1);
		
		JLabel lb_SavePath = new JLabel("");
		lb_SavePath.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		panel_3.add(lb_SavePath);
		lb_SavePath.setText(new File(origanImgPathString).getParent()+"\\");
		
		tf_FileName = new JTextField();
		tf_FileName.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		tf_FileName.setText("Grayscale.jpg");
		panel_3.add(tf_FileName);
		tf_FileName.setColumns(10);
		
		JButton btn_Save = new JButton("\u5132\u5B58");
		btn_Save.setEnabled(false);
		btn_Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ImgFunction.saveImg(grayImage, lb_SavePath.getText()+tf_FileName.getText());
					ImageProcess.btn_SaltAndPepper.setEnabled(true);
					ImageProcess.btn_Laplacian.setEnabled(true);
					ImageProcess.btn_Sobel.setEnabled(true);
					ImageProcess.str_GrayScale = lb_SavePath.getText()+tf_FileName.getText();
					JOptionPane.showMessageDialog(null, "儲存成功","完成",JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_Save.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		panel_3.add(btn_Save);
		
		JButton btn_Process = new JButton("\u8F49\u63DB");
		btn_Process.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					grayImage = ImgFunction.Gray(new File(origanImgPathString));
					Image img2 = grayImage.getScaledInstance(700, 500, Image.SCALE_AREA_AVERAGING);
					ImageIcon icon= new ImageIcon(img2);
					lb_GrayscaleImg.setIcon(icon);
					btn_Save.setEnabled(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_Process.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		panel_3.add(btn_Process);
	}
}
