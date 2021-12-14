package ImageProcess;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSlider;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;

public class SaltAndPepper extends JFrame {

	private JPanel contentPane;
	private JTextField tf_FileName;
	static BufferedImage SAPImage;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaltAndPepper frame = new SaltAndPepper("");
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
	public SaltAndPepper(String str_GrayScale) {
		setTitle("\u80E1\u6912\u9E7D\u96DC\u8A0A( Salt and pepper )");
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
		
		JLabel lblNewLabel = new JLabel("\u80E1\u6912\u9E7D\u96DC\u8A0A( Salt and pepper )");
		lblNewLabel.setFont(new Font("�L�n������", Font.PLAIN, 14));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(-2, 36, 427, 520);
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
		panel_2.setBounds(425, 36, 414, 520);
		contentPane.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lb_SaltAndPepper = new JLabel("");
		panel_2.add(lb_SaltAndPepper);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 556, 434, 32);
		contentPane.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNewLabel_3 = new JLabel("\u5132\u5B58\u4F4D\u7F6E");
		panel_3.add(lblNewLabel_3);
		
		JLabel lb_SavePath = new JLabel("");
		panel_3.add(lb_SavePath);
		lb_SavePath.setText(new File(str_GrayScale).getParent()+"\\");
		
		tf_FileName = new JTextField();
		tf_FileName.setText("SaltAndPepper.jpg");
		panel_3.add(tf_FileName);
		tf_FileName.setColumns(10);
		
		JButton btn_Save = new JButton("\u5132\u5B58");
		btn_Save.setEnabled(false);
		btn_Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ImgFunction.saveImg(SAPImage, lb_SavePath.getText()+tf_FileName.getText());
					ImageProcess.btn_MaxFilter.setEnabled(true);
					ImageProcess.btn_MeanFilter.setEnabled(true);
					ImageProcess.btn_MedianFilter.setEnabled(true);
					ImageProcess.btn_MinFilter.setEnabled(true);
					ImageProcess.str_SaltAndPepperString = lb_SavePath.getText()+tf_FileName.getText();
					JOptionPane.showMessageDialog(null, "�x�s���\","����",JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_3.add(btn_Save);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(435, 556, 404, 32);
		contentPane.add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JLabel lblNewLabel_5 = new JLabel("\u96DC\u8A0A\u7A0B\u5EA6(1-10)");
		panel_4.add(lblNewLabel_5);
		
		JLabel lb_Value = new JLabel("5");
		
		JSlider slider_Value = new JSlider();
		slider_Value.setValue(3);
		slider_Value.setMaximum(10);
		slider_Value.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				lb_Value.setText(String.format("%d", slider_Value.getValue()));
			}
		});
		slider_Value.setMinimum(1);
		panel_4.add(slider_Value);
		
		
		panel_4.add(lb_Value);
		
		JButton btn_Process = new JButton("\u8F49\u63DB");
		btn_Process.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SAPImage = ImgFunction.SaltAndPepper(new File(str_GrayScale), Integer.parseInt(lb_Value.getText()));
					Image img2 = SAPImage.getScaledInstance(700, 500, Image.SCALE_AREA_AVERAGING);
					ImageIcon icon= new ImageIcon(img2);
					lb_SaltAndPepper.setIcon(icon);
					btn_Save.setEnabled(true);
					tf_FileName.setText(String.format("SaltAndPepper_%d.jpg", slider_Value.getValue()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_4.add(btn_Process);
	}

}
