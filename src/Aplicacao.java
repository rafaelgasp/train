import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

public class Aplicacao extends JFrame{
	JButton btnFacil, btnMedio, btnDificil, btnAjuda, btnRecorde;
	public Aplicacao(){
		super("Train - Principal");
		this.setLayout(null);
		this.setResizable(false);
		this.setSize(1024, 768);
		//this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		this.setIconImage((new ImageIcon(getClass().getResource(("img/icon.png")))).getImage());
		this.addWindowListener(new WindowListener() {
			
			
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				//Pontuacao.fecharJogo(FrmCliente.loginCrianca, Pontuacao.getCodAtividade("Train"));
				Aplicacao.this.dispose();
				//FrmCliente.colocarBotoesJogo();
			}
			
			
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//Pontuacao.iniciarJogo(FrmCliente.loginCrianca, Pontuacao.getCodAtividade("Train"));
		
		JLabel lblFundo = new JLabel(new ImageIcon(getClass().getResource("img/Train/fundoprincipal.png")));
		lblFundo.setBounds(0, 0, 1024, 768);
		
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
				if (JOptionPane.showConfirmDialog(null, "Deseja Retornar ao Central-KIDS?", "Train!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
					//Pontuacao.fecharJogo(FrmCliente.loginCrianca, Pontuacao.getCodAtividade("Train"));
					Aplicacao.this.dispose();
					//FrmCliente.colocarBotoesJogo();
				}
			}
		});
		this.add(btnSair);
		
		ImageIcon a = new ImageIcon(getClass().getResource("img/Train/ajuda_true.png"));
		btnAjuda = new JButton(new ImageIcon(getClass().getResource("img/Train/ajuda.png")));
		btnAjuda.setBounds(5, 768 - a.getIconHeight(), a.getIconWidth(), a.getIconHeight());
		btnAjuda.setRolloverIcon(a);
		btnAjuda.setFocusable(false);
		btnAjuda.setContentAreaFilled(false);
		btnAjuda.setBorderPainted(false);
		btnAjuda.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Help frm = new Help();
				frm.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				frm.setVisible(true);
				frm.setLocationRelativeTo(Aplicacao.this); 
				Aplicacao.this.dispose();
			}
		});
		this.add(btnAjuda);
		
		ImageIcon b = new ImageIcon(getClass().getResource("img/Train/recorde_true.png"));
		btnRecorde = new JButton(new ImageIcon(getClass().getResource("img/Train/recorde.png")));
		btnRecorde.setBounds(1000 - b.getIconWidth(), 768 - b.getIconHeight(), b.getIconWidth(), b.getIconHeight());
		btnRecorde.setRolloverIcon(b);
		btnRecorde.setFocusable(false);
		btnRecorde.setContentAreaFilled(false);
		btnRecorde.setBorderPainted(false);
		btnRecorde.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Recorde frm = new Recorde();
				frm.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				frm.setVisible(true);
				frm.setLocationRelativeTo(Aplicacao.this); 
				Aplicacao.this.dispose();
			}
		});
		this.add(btnRecorde);
		
		ImageIcon f = new ImageIcon(getClass().getResource("img/Train/facil.png"));
		btnFacil = new JButton(f);
		btnFacil.setBounds(20, 275, f.getIconWidth(), f.getIconHeight());
		btnFacil.setFocusable(false);
		btnFacil.setContentAreaFilled(false);
		btnFacil.setBorderPainted(false);
		btnFacil.addActionListener(new ActionListener() {
			
		
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Tempo frm = new Tempo(new ImageIcon(getClass().getResource("img/Train/fundo.png")), "Fácil");
				frm.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				frm.setVisible(true);
				frm.setLocationRelativeTo(Aplicacao.this); 
				Aplicacao.this.dispose();
			}
		});
		this.add(btnFacil);
		
		ImageIcon m = new ImageIcon(getClass().getResource("img/Train/medio.png"));
		btnMedio = new JButton(m);
		btnMedio.setBounds(1000 - m.getIconWidth(), 275, m.getIconWidth(), m.getIconHeight());
		btnMedio.setFocusable(false);
		btnMedio.setContentAreaFilled(false);
		btnMedio.setBorderPainted(false);
		btnMedio.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Tempo frm = new Tempo(new ImageIcon(getClass().getResource("img/Train/fundo2.png")), "Médio");
				frm.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				frm.setVisible(true);
				frm.setLocationRelativeTo(Aplicacao.this); 
				Aplicacao.this.dispose();
			}
		});
		this.add(btnMedio);
		
		ImageIcon d = new ImageIcon(getClass().getResource("img/Train/dificil.png"));
		btnDificil = new JButton(d);
		btnDificil.setBounds(670 - d.getIconWidth(), 500, d.getIconWidth(), d.getIconHeight());
		btnDificil.setFocusable(false);
		btnDificil.setContentAreaFilled(false);
		btnDificil.setBorderPainted(false);
		btnDificil.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Tempo frm = new Tempo(new ImageIcon(getClass().getResource("img/Train/fundo3.png")), "Dificil");
				frm.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				frm.setVisible(true);
				frm.setLocationRelativeTo(Aplicacao.this); 
				Aplicacao.this.dispose();
			}
		});
		this.add(btnDificil);
		
		
		
		this.add(lblFundo);
	}
	
	public static void main(String[] args){
		Aplicacao ap = new Aplicacao();
		ap.setVisible(true);
	}
}
