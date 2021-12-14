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
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class GammaCorrection extends JFrame {

	private JPanel contentPane;
	private JTextField tf_FileName;
	static BufferedImage GammaImg;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GammaCorrection frame = new GammaCorrection("");
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
	public GammaCorrection(String origanImgPathString) {
		setTitle("\u4F3D\u99AC\u6821\u6B63\uFF08Gamma correction\uFF09");
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
		
		JLabel lblNewLabel = new JLabel("\u4F3D\u99AC\u6821\u6B63\uFF08Gamma correction\uFF09");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(-2, 36, 427, 520);
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lb_OriganImg = new JLabel("");
		panel_1.add(lb_OriganImg);
		try {
			Image img = ImageIO.read(new File(origanImgPathString));
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
		
		JLabel lb_GammaCorrection = new JLabel("");
		panel_2.add(lb_GammaCorrection);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 556, 434, 32);
		contentPane.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNewLabel_3 = new JLabel("\u5132\u5B58\u4F4D\u7F6E");
		lblNewLabel_3.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		panel_3.add(lblNewLabel_3);
		
		JLabel lb_SavePath = new JLabel("");
		panel_3.add(lb_SavePath);
		lb_SavePath.setText(new File(origanImgPathString).getParent()+"\\");
		
		tf_FileName = new JTextField();
		tf_FileName.setText("Gamma.jpg");
		panel_3.add(tf_FileName);
		tf_FileName.setColumns(10);
		
		JButton btn_Save = new JButton("\u5132\u5B58");
		btn_Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ImgFunction.saveImg(GammaImg, lb_SavePath.getText()+tf_FileName.getText());
					JOptionPane.showMessageDialog(null, "儲存成功","完成",JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_Save.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		btn_Save.setEnabled(false);
		panel_3.add(btn_Save);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(435, 556, 404, 32);
		contentPane.add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JLabel lblNewLabel_1 = new JLabel("Gamma\u503C(0-2)");
		panel_4.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		
		JLabel lb_GammaValue = new JLabel("0.50");
		
		JSlider slider_GammaValue = new JSlider();
		slider_GammaValue.setMinimum(1);
		slider_GammaValue.setMaximum(200);
		panel_4.add(slider_GammaValue);
		slider_GammaValue.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				lb_GammaValue.setText(String.format("%.2f", slider_GammaValue.getValue()/100.0));
			}
		});
		
		
		panel_4.add(lb_GammaValue);
		lb_GammaValue.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		
		JButton btn_Process = new JButton("\u8F49\u63DB");
		btn_Process.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					GammaImg = ImgFunction.Gamma(new File(origanImgPathString), Double.parseDouble(lb_GammaValue.getText()));
					Image img2 = GammaImg.getScaledInstance(700, 500, Image.SCALE_AREA_AVERAGING);
					ImageIcon icon= new ImageIcon(img2);
					lb_GammaCorrection.setIcon(icon);
					btn_Save.setEnabled(true);
					tf_FileName.setText(String.format("Gamma_%d.jpg", slider_GammaValue.getValue()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_4.add(btn_Process);
		btn_Process.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
	}
}
