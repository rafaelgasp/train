
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class Recorde extends JFrame{
	JComboBox cb;
	JLabel lblFundo, lbln[] = new JLabel[8], lblnivel[] = new JLabel[8], lblpontos[] = new JLabel[8];
	Font f = new Font("Trebuchet MS", Font.PLAIN, 40);
	Color c = Color.white;
	public Recorde(){
		super("Train - Recordes");
		this.setLayout(null);
		this.setResizable(false);
		this.setSize(1024, 768);
		this.setUndecorated(true);
		this.setIconImage(new ImageIcon(getClass().getResource("img/icon.png")).getImage());
		
		lblFundo = new JLabel(new ImageIcon(getClass().getResource("img/Train/recorde_fundo.png")));
		lblFundo.setBounds(0, 0, 1024, 768);
		
		for(int i = 0; i < 8; i++){
			lbln[i] = new JLabel();
			lblnivel[i] = new JLabel();
			lblpontos[i] = new JLabel();
			
			lbln[i].setForeground(c);
			lbln[i].setFont(f);
			this.add(lbln[i]);
			
			lblnivel[i].setFont(f);
			lblnivel[i].setForeground(c);
			this.add(lblnivel[i]);
			
			lblpontos[i].setFont(f);
			lblpontos[i].setForeground(c);
			this.add(lblpontos[i]);
			
		}
		
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
				if(JOptionPane.showConfirmDialog(null, " Deseja Realmente Voltar? ", "Book Order!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
					Recorde.this.dispose();
					Aplicacao frm = new Aplicacao();
			        frm.setSize(1024,768);
					frm.setLocationRelativeTo(Recorde.this);
					frm.setVisible(true);
					frm.setResizable(false);
				}
			}
		});
		this.add(btnSair);
		
		cb = new JComboBox(new String[]{"Fácil", "Médio", "Difícil"});
		cb.setBounds(380, 710, 150, 50);
		cb.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
		cb.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//carregarRecorde(cb.getSelectedIndex()+1);
			}
		});
		this.add(cb);
		
		//carregarRecorde(1);
		
		this.add(lblFundo);
	}
	
	public void limparRecorde(){
		for(int i = 0; i < 8; i++){
			lbln[i].setText("");			
			lblnivel[i].setText("");			
			lblpontos[i].setText("");
		}
	}
	
	/*public void carregarRecorde(int d){
		limparRecorde();
		Principal.Banco b = new Principal.Banco();
		b.conectar();
		try {
			ResultSet r = b.stm.executeQuery("SELECT tbpontuacao.nivel, tbpontuacao.numPontos, DATE_FORMAT(tbpontuacao.dataPontuacao, '%d/%m/%Y') as 'dataPontuacao', tbaluno.loginAluno, tbdificuldade.descricaoDificuldade, tbatividade.descricaoAtividade, tbdisciplina.descricaoDisciplina FROM tbpontuacao join tbaluno on tbpontuacao.codAluno = tbaluno.codAluno join tbdificuldade on tbpontuacao.codDificuldade = tbdificuldade.codDificuldade join tbatividade on tbatividade.codAtividade = tbpontuacao.codAtividade join tbdisciplina on tbatividade.codDisciplina = tbdisciplina.codDisciplina where tbpontuacao.codAtividade = 13 and tbpontuacao.codDificuldade = "+d+" order by tbpontuacao.numPontos desc limit 8");
			int x = 20;
			int y = 170;
			int i = 1;
			while (r.next()){
				lbln[i-1].setText(String.valueOf(i));
				lbln[i-1].setBounds(x + 40, y, 500, 50);
				
				lblnivel[i-1].setText(r.getString("loginAluno"));
				lblnivel[i-1].setBounds(x + 280, y, 500, 50);
				
				lblpontos[i-1].setText(r.getString("numPontos"));
				lblpontos[i-1].setBounds(x + 800, y, 500, 50);
								
				this.invalidate();
				this.repaint();
				
				y+=65;
				i++;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		b.fechar();
	}*/
	
}
