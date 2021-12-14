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
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MeanFilter extends JFrame {

	private JPanel contentPane;
	private JTextField tf_FileName;
	static BufferedImage mfImg;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MeanFilter frame = new MeanFilter("");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param str_SaltAndPepperString 
	 */
	public MeanFilter(String str_SaltAndPepperString) {
		setTitle("\u5E73\u5747\u503C\u6FFE\u6CE2\u5668( Mean Filter )");
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
		
		JLabel lblNewLabel = new JLabel("\u5E73\u5747\u503C\u6FFE\u6CE2\u5668( Mean Filter )");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(-2, 36, 427, 520);
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lb_SAPImg = new JLabel("");
		panel_1.add(lb_SAPImg);
		try {
			Image img = ImageIO.read(new File(str_SaltAndPepperString));
			Image img2 = img.getScaledInstance(700, 500, Image.SCALE_AREA_AVERAGING);
			ImageIcon icon= new ImageIcon(img2);
			lb_SAPImg.setIcon(icon);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(425, 36, 414, 520);
		contentPane.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lb_MinFilterImg = new JLabel("");
		panel_2.add(lb_MinFilterImg);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 556, 434, 32);
		contentPane.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNewLabel_2 = new JLabel("\u5132\u5B58\u4F4D\u7F6E");
		lblNewLabel_2.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		panel_3.add(lblNewLabel_2);
		
		JLabel lb_SavePath = new JLabel("");
		panel_3.add(lb_SavePath);
		lb_SavePath.setText(new File(str_SaltAndPepperString).getParent()+"\\");
		
		JButton btn_Save = new JButton("\u5132\u5B58");
		btn_Save.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		btn_Save.setEnabled(false);
		btn_Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ImgFunction.saveImg(mfImg, lb_SavePath.getText()+tf_FileName.getText());
					JOptionPane.showMessageDialog(null, "儲存成功","完成",JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		tf_FileName = new JTextField();
		tf_FileName.setText("MeanFilter.jpg");
		panel_3.add(tf_FileName);
		tf_FileName.setColumns(10);
		panel_3.add(btn_Save);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(435, 556, 404, 32);
		contentPane.add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JLabel lblNewLabel_4 = new JLabel("Window\u5927\u5C0F(\u5947\u6578)");
		lblNewLabel_4.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		panel_4.add(lblNewLabel_4);
		
		JSlider slider_WindowSize = new JSlider();
		
		JLabel lb_WindowSize = new JLabel("3");
		slider_WindowSize.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				lb_WindowSize.setText(String.format("%d", slider_WindowSize.getValue()%2==1? slider_WindowSize.getValue():slider_WindowSize.getValue()+1));
			}
		});
		slider_WindowSize.setMaximum(8);
		slider_WindowSize.setValue(3);
		slider_WindowSize.setMinimum(3);
		panel_4.add(slider_WindowSize);
		

		panel_4.add(lb_WindowSize);
		
		JButton btn_Process = new JButton("\u8F49\u63DB");
		btn_Process.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		btn_Process.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mfImg = ImgFunction.MeanFilter(new File(str_SaltAndPepperString), Integer.parseInt(lb_WindowSize.getText()));
					Image img2 = mfImg.getScaledInstance(700, 500, Image.SCALE_AREA_AVERAGING);
					ImageIcon icon= new ImageIcon(img2);
					lb_MinFilterImg.setIcon(icon);
					btn_Save.setEnabled(true);
					tf_FileName.setText(String.format("MeanFilter_%s.jpg", lb_WindowSize.getText()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_4.add(btn_Process);
	}

}
