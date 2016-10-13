import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class Help extends JFrame{
	ImageIcon[] fundo = new ImageIcon[5];
	JLabel lblFundo;
	JButton next, back, out;
	int y;
	public Help(){
		super("Train - Help");
		this.setLayout(null);
		this.setResizable(false);
		this.setSize(1024, 768);
		this.setUndecorated(true);
		this.setIconImage(new ImageIcon(getClass().getResource("img/icon.png")).getImage());
		
		for (int i = 0; i < 5; i++){
			if(i == 1){
				fundo[i] = new ImageIcon(getClass().getResource("help/Train/"+(i+1)+".gif"));	
			}else{
				fundo[i] = new ImageIcon(getClass().getResource("help/Train/"+(i+1)+".png"));	
			}					
		}
		
		y = 0;
		
		lblFundo = new JLabel(fundo[y]);
		lblFundo.setBounds(0, 0, 1024, 768);
		
		next = new JButton(new ImageIcon(getClass().getResource("help/Train/avancar.png")));
		next.setBounds(700, 680, 106, 81);
		next.setContentAreaFilled(false);
		next.setBorderPainted(false);
		next.setFocusable(false);
		next.setToolTipText("Avançar um slide");
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// TODO Auto-generated method stub
				if(y == 3){
					y++;
					next.setVisible(false);
					trocarFundo();
				}else{
					if(y < 5){
						y++;
						trocarFundo();
					}	
				}					
				back.setVisible(true);
			}
		});
		this.add(next);
		
		back = new JButton(new ImageIcon(getClass().getResource("help/Train/voltar.png")));
		back.setBounds(200, 680, 106, 81);
		back.setContentAreaFilled(false);
		back.setBorderPainted(false);
		back.setToolTipText("Voltar um slide");
		back.setFocusable(false);
		back.setVisible(false);
		back.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
				// TODO Auto-generated method stub
				if(y == 1){
					y--;
					back.setVisible(false);
					trocarFundo();
				}else{
					if(y > 0){
						y--;
						trocarFundo();
					}	
				}
				next.setVisible(true);
			}
		});
		this.add(back);
		

		ImageIcon sair = new ImageIcon(getClass().getResource("img/Train/btnSair.png"));
		JButton btnSair = new JButton(sair);
		btnSair.setContentAreaFilled(false);
		btnSair.setFocusable(false);
		btnSair.setBorderPainted(false);
		btnSair.setPressedIcon(new ImageIcon(getClass().getResource("img/Train/btnSair_true.png")));
		btnSair.setRolloverIcon(new ImageIcon(getClass().getResource("img/Train/btnSair_pass.png")));
		btnSair.setBounds(1010 - sair.getIconWidth(), 750 - sair.getIconHeight(), sair.getIconWidth(), sair.getIconHeight());
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(JOptionPane.showConfirmDialog(null, " Deseja Realmente Voltar? ", "Train", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
					Help.this.dispose();
					Aplicacao frm = new Aplicacao();
			        frm.setSize(1024,768);
					frm.setLocationRelativeTo(null);
					frm.setVisible(true);
					frm.setResizable(false);
				}
			}
		});
		this.add(btnSair);
		
		this.add(lblFundo);
	}
	
	public void trocarFundo(){
		lblFundo.setIcon(fundo[y]);
		this.invalidate();
	}
}
