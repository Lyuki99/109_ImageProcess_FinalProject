package ImageProcess;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JSlider;

public class SobelBinarization extends JFrame {

	private JPanel contentPane;
	private JTextField tf_FileName;
	static BufferedImage sobelBImg;
	static BufferedImage sobelHorizontalBImg;
	static BufferedImage sobelVerticalBImg;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SobelBinarization frame = new SobelBinarization("","","");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param str_Sobel 
	 * @param str_SobelVertical 
	 * @param str_SobelHorizontal 
	 */
	public SobelBinarization(String str_SobelHorizontal, String str_SobelVertical, String str_Sobel) {
		setTitle("\u4E8C\u503C\u5316( Sobel )");
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
		
		JLabel lblNewLabel = new JLabel("\u4E8C\u503C\u5316( Sobel )");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(-2, 36, 427, 522);
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lb_SobelHorizontal = new JLabel("");
		panel_1.add(lb_SobelHorizontal);
		try {
			Image img = ImageIO.read(new File(str_SobelHorizontal));
			Image img2 = img.getScaledInstance(400, 170, Image.SCALE_AREA_AVERAGING);
			ImageIcon icon= new ImageIcon(img2);
			lb_SobelHorizontal.setIcon(icon);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel lb_SobelVertical = new JLabel("");
		panel_1.add(lb_SobelVertical);
		try {
			Image img = ImageIO.read(new File(str_SobelVertical));
			Image img2 = img.getScaledInstance(400, 170, Image.SCALE_AREA_AVERAGING);
			ImageIcon icon= new ImageIcon(img2);
			lb_SobelVertical.setIcon(icon);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel lb_Sobel = new JLabel("");
		panel_1.add(lb_Sobel);
		try {
			Image img = ImageIO.read(new File(str_Sobel));
			Image img2 = img.getScaledInstance(400, 170, Image.SCALE_AREA_AVERAGING);
			ImageIcon icon= new ImageIcon(img2);
			lb_Sobel.setIcon(icon);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(425, 36, 414, 522);
		contentPane.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lb_SobelHorizontalB = new JLabel("");
		panel_2.add(lb_SobelHorizontalB);
		
		JLabel lb_SobelVerticalB = new JLabel("");
		panel_2.add(lb_SobelVerticalB);
		
		JLabel lb_SobelB = new JLabel("");
		panel_2.add(lb_SobelB);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 556, 434, 32);
		contentPane.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNewLabel_4 = new JLabel("\u5132\u5B58\u4F4D\u7F6E");
		lblNewLabel_4.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		panel_3.add(lblNewLabel_4);
		
		JLabel lb_SavePath = new JLabel("");
		panel_3.add(lb_SavePath);
		lb_SavePath.setText(new File(str_Sobel).getParent()+"\\");
		
		
		
		JButton btn_Save = new JButton("\u5132\u5B58");
		btn_Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ImgFunction.saveImg(sobelBImg, lb_SavePath.getText()+tf_FileName.getText());
					ImgFunction.saveImg(sobelHorizontalBImg, lb_SavePath.getText()+"H"+tf_FileName.getText());
					ImgFunction.saveImg(sobelVerticalBImg, lb_SavePath.getText()+"V"+tf_FileName.getText());
					JOptionPane.showMessageDialog(null, "儲存成功","完成",JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		tf_FileName = new JTextField();
		tf_FileName.setText("SobelBinarization.jpg");
		panel_3.add(tf_FileName);
		tf_FileName.setColumns(10);
		
		btn_Save.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		panel_3.add(btn_Save);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(435, 556, 404, 32);
		contentPane.add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JLabel lblNewLabel_2 = new JLabel("\u9580\u6ABB\u503C(0-255)");
		lblNewLabel_2.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		panel_4.add(lblNewLabel_2);
		
		JLabel lb_GateValue = new JLabel("125");
		
		JSlider slider_GateValue = new JSlider();
		slider_GateValue.addChangeListener(new ChangeListener() {	
			@Override
			public void stateChanged(ChangeEvent e) {
				lb_GateValue.setText(slider_GateValue.getValue()+"");
			}
		});
		slider_GateValue.setValue(125);
		slider_GateValue.setMaximum(255);
		panel_4.add(slider_GateValue);
		
		
		panel_4.add(lb_GateValue);
		
		JButton btn_Process = new JButton("\u8F49\u63DB");
		panel_4.add(btn_Process);
		btn_Process.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					sobelHorizontalBImg = ImgFunction.Binarization(new File(str_SobelHorizontal),slider_GateValue.getValue());
					Image img2 = sobelHorizontalBImg.getScaledInstance(400, 170, Image.SCALE_AREA_AVERAGING);
					ImageIcon icon= new ImageIcon(img2);
					lb_SobelHorizontalB.setIcon(icon);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					sobelVerticalBImg = ImgFunction.Binarization(new File(str_SobelVertical),slider_GateValue.getValue());
					Image img2 = sobelVerticalBImg.getScaledInstance(400, 170, Image.SCALE_AREA_AVERAGING);
					ImageIcon icon= new ImageIcon(img2);
					lb_SobelVerticalB.setIcon(icon);		
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					sobelBImg = ImgFunction.Binarization(new File(str_Sobel),slider_GateValue.getValue());
					Image img2 = sobelBImg.getScaledInstance(400, 170, Image.SCALE_AREA_AVERAGING);
					ImageIcon icon= new ImageIcon(img2);
					lb_SobelB.setIcon(icon);
					btn_Save.setEnabled(true);
					tf_FileName.setText(String.format("SobelBinarization_%d.jpg",slider_GateValue.getValue()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_Process.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
	}

}
