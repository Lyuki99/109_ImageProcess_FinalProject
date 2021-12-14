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
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Laplacian extends JFrame {

	private JPanel contentPane;
	private JTextField tf_FileName;
	static BufferedImage lfImg;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Laplacian frame = new Laplacian("");
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
	public Laplacian(String str_GrayScale) {
		setTitle("\u62C9\u666E\u62C9\u65AF\u908A\u7DE3\u5075\u6E2C( Laplacian Filter )");
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
		
		JLabel lblNewLabel = new JLabel("\u62C9\u666E\u62C9\u65AF\u908A\u7DE3\u5075\u6E2C( Laplacian Filter )");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
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
		
		JLabel lb_LaplacianImg = new JLabel("");
		panel_2.add(lb_LaplacianImg);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 556, 434, 32);
		contentPane.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNewLabel_3 = new JLabel("\u5132\u5B58\u4F4D\u7F6E");
		lblNewLabel_3.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		panel_3.add(lblNewLabel_3);
		
		JLabel lb_SavePath = new JLabel("");
		panel_3.add(lb_SavePath);
		lb_SavePath.setText(new File(str_GrayScale).getParent()+"\\");
		
		tf_FileName = new JTextField();
		tf_FileName.setText("Laplacian.jpg");
		panel_3.add(tf_FileName);
		tf_FileName.setColumns(10);
		
		JButton btn_Save = new JButton("\u5132\u5B58");
		btn_Save.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		btn_Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ImgFunction.saveImg(lfImg, lb_SavePath.getText()+tf_FileName.getText());
					ImageProcess.btn_LaplacianBinarization.setEnabled(true);
					ImageProcess.str_Laplacian = lb_SavePath.getText()+tf_FileName.getText();
					JOptionPane.showMessageDialog(null, "儲存成功","完成",JOptionPane.INFORMATION_MESSAGE);
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
		
		JRadioButton box_FourConnectivity = new JRadioButton("\u56DB\u9023\u901A");
		box_FourConnectivity.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		box_FourConnectivity.setSelected(true);
		panel_4.add(box_FourConnectivity);
		
		JRadioButton box_EightConnectivity = new JRadioButton("\u516B\u9023\u901A");
		box_EightConnectivity.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		panel_4.add(box_EightConnectivity);
		
		ButtonGroup group = new ButtonGroup();
		group.add(box_FourConnectivity);
		group.add(box_EightConnectivity);
		
		
		JButton btn_Process = new JButton("\u8F49\u63DB");
		btn_Process.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int mode=0;
				String fileNameString="";
				if(box_FourConnectivity.isSelected()) {
					mode=0;
					fileNameString = "FourConnectivity";
				}else if(box_EightConnectivity.isSelected()) {
					mode=1;
					fileNameString = "EightConnectivity";
				}
				try {
					lfImg = ImgFunction.Laplacian(new File(str_GrayScale),mode);
					Image img2 = lfImg.getScaledInstance(700, 500, Image.SCALE_AREA_AVERAGING);
					ImageIcon icon= new ImageIcon(img2);
					lb_LaplacianImg.setIcon(icon);
					btn_Save.setEnabled(true);
					tf_FileName.setText(String.format("Laplacian_%s.jpg", fileNameString));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_Process.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		panel_4.add(btn_Process);
	}
}
