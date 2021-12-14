package ImageProcess;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;

public class Sobel extends JFrame {

	private JPanel contentPane;
	private JTextField tf_FileName;
	static BufferedImage sobelImg;
	static BufferedImage sobelHorizontalImg;
	static BufferedImage sobelVerticalImg;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sobel frame = new Sobel("");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param str_GrayScale 
	 */
	public Sobel(String str_GrayScale) {
		setTitle("\u7D22\u8C9D\u723E\u908A\u7DE3\u5075\u6E2C( Sobel Filter )");
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
		
		JLabel lblNewLabel = new JLabel("\u7D22\u8C9D\u723E\u908A\u7DE3\u5075\u6E2C( Sobel Filter )");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(-2, 36, 427, 522);
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lb_GrayscaleImg = new JLabel("");
		panel_1.add(lb_GrayscaleImg);
		try {
			Image img = ImageIO.read(new File(str_GrayScale));
			Image img2 = img.getScaledInstance(700, 500, Image.SCALE_AREA_AVERAGING);
			ImageIcon icon= new ImageIcon(img2);
			lb_GrayscaleImg.setIcon(icon);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(425, 36, 414, 522);
		contentPane.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lb_SobelHorizontal = new JLabel("");
		panel_2.add(lb_SobelHorizontal);
		
		JLabel lb_SobelVertical = new JLabel("");
		panel_2.add(lb_SobelVertical);
		
		JLabel lb_Sobel = new JLabel("");
		panel_2.add(lb_Sobel);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 556, 839, 32);
		contentPane.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_4 = new JLabel("\u5132\u5B58\u4F4D\u7F6E");
		lblNewLabel_4.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		panel_3.add(lblNewLabel_4);
		
		JLabel lb_SavePath = new JLabel("");
		panel_3.add(lb_SavePath);
		lb_SavePath.setText(new File(str_GrayScale).getParent()+"\\");
		
		tf_FileName = new JTextField();
		tf_FileName.setText("Sobel.jpg");
		panel_3.add(tf_FileName);
		tf_FileName.setColumns(10);
		
		JButton btn_Save = new JButton("\u5132\u5B58");
		btn_Save.setEnabled(false);
		btn_Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ImgFunction.saveImg(sobelImg, lb_SavePath.getText()+tf_FileName.getText());
					ImgFunction.saveImg(sobelHorizontalImg, lb_SavePath.getText()+"H"+tf_FileName.getText());
					ImgFunction.saveImg(sobelVerticalImg, lb_SavePath.getText()+"V"+tf_FileName.getText());
					ImageProcess.btn_SobelBinarization.setEnabled(true);
					ImageProcess.str_Sobel = lb_SavePath.getText()+tf_FileName.getText();
					ImageProcess.str_SobelHorizontal = lb_SavePath.getText()+"H"+tf_FileName.getText();
					ImageProcess.str_SobelVertical = lb_SavePath.getText()+"V"+tf_FileName.getText();
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
					sobelHorizontalImg = ImgFunction.Sobel(new File(str_GrayScale),0);
					Image img2 = sobelHorizontalImg.getScaledInstance(400, 170, Image.SCALE_AREA_AVERAGING);
					ImageIcon icon= new ImageIcon(img2);
					lb_SobelHorizontal.setIcon(icon);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					sobelVerticalImg = ImgFunction.Sobel(new File(str_GrayScale),1);
					Image img2 = sobelVerticalImg.getScaledInstance(400, 170, Image.SCALE_AREA_AVERAGING);
					ImageIcon icon= new ImageIcon(img2);
					lb_SobelVertical.setIcon(icon);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				sobelImg = ImgFunction.Sobel(sobelHorizontalImg,sobelVerticalImg);
				Image img2 = sobelImg.getScaledInstance(400, 170, Image.SCALE_AREA_AVERAGING);
				ImageIcon icon= new ImageIcon(img2);
				lb_Sobel.setIcon(icon);
				btn_Save.setEnabled(true);
			}
		});
		btn_Process.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		panel_3.add(btn_Process);
		
		
	}

}
