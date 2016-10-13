import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Facil extends JFrame{
	JLabel lblFundo, lblPontos, lbl50, lblp50, tempo;
	JLabel lblCarro[] = new JLabel[3];
	JLabel lblLetra[] = new JLabel[3];
	boolean ver[] = new boolean[3];
	Timer t[] = new Timer[6], rt;
	int pontos = 0, alpha = 255, y = 60;
	String letras[] = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	Random rand = new Random();
	RndTempo rdT = new RndTempo();
	ImageIcon carro = new ImageIcon(getClass().getResource("img/Train/car1_facil2.png"));
	public Facil(){
		super("Facil");
		this.setLayout(null);
		this.setResizable(false);
		this.setSize(1024, 768);
		this.setUndecorated(true);
		this.setIconImage(new ImageIcon(getClass().getResource("img/icon.png")).getImage());
		
		lblFundo = new JLabel(new ImageIcon(getClass().getResource("img/Train/fundo.png")));
		lblFundo.setBounds(0, 0, 1024, 768);
		
		tempo = new JLabel();
		tempo.setBounds(480, -5, 200, 52);
		tempo.setForeground(new Color(107, 92, 22));
		tempo.setFont(new Font("Tahoma", Font.BOLD, 50));
		
		for (int i = 0; i<3; i++){
			lblCarro[i] = new JLabel(carro);
			lblLetra[i] = new JLabel();
		}
		
		lblPontos = new JLabel(String.valueOf(pontos));
		lblPontos.setFont(new Font("Arial", Font.BOLD, 45));
		lblPontos.setForeground(Color.white);
		lblPontos.setBounds(190,-12, 500,80);
		
		lbl50 = new JLabel();
		lbl50.setText("+50");
		lbl50.setFont(new Font("Arial", Font.BOLD, 40));
		lbl50.setVisible(false);
		
		lblp50 = new JLabel();
		lblp50.setText("-50");
		lblp50.setFont(new Font("Arial", Font.BOLD, 40));
		lblp50.setVisible(false);
		
		t[0] = new Timer(-2, new EvtMoverCarro1());
		t[1] = new Timer(-2, new EvtMoverCarro2());
		t[2] = new Timer(-2, new EvtMoverCarro3());
		t[3] = new Timer(10, new EvtPontos());
		t[4] = new Timer(10, new EvtPontosNegativo());
		t[5] = new Timer(1000, new EvtTempo());
		
		t[5].start();
		
		rt = new Timer(100, rdT);
		rt.setInitialDelay(0);
		rt.start();
		
		ImageIcon sair = new ImageIcon(getClass().getResource("img/Train/btnSair.png"));
		JButton btnSair = new JButton(sair);
		btnSair.setContentAreaFilled(false);
		btnSair.setFocusable(false);
		btnSair.setBorderPainted(false);
		btnSair.setPressedIcon(new ImageIcon(getClass().getResource("img/Train/btnSair_true.png")));
		btnSair.setRolloverIcon(new ImageIcon(getClass().getResource("img/Train/btnSair_pass.png")));
		btnSair.setBounds(1010 - sair.getIconWidth(), 0, sair.getIconWidth(), sair.getIconHeight());
		btnSair.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				for (int i = 0; i < t.length; i++){
					t[i].stop();
				}
				if(JOptionPane.showConfirmDialog(null, " Deseja Realmente Voltar? ", "Train!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
					for (int i = 0; i < t.length; i++){
						t[i] = null;
					}
					System.gc();
					Facil.this.dispose();
					Aplicacao frm = new Aplicacao();
			        frm.setSize(1024,768);
					frm.setLocationRelativeTo(Facil.this);
					frm.setVisible(true);
					frm.setResizable(false);
				}else{
					for (int i = 0; i < t.length; i++){
						t[i].restart();
					}
				}
			}
		});
		this.add(btnSair);
		
		this.add(tempo);
		this.add(lbl50);
		this.add(lblp50);
		this.add(lblPontos);
		this.add(lblFundo);
		this.addKeyListener(new EvtDigitar());
	}
	
	public void moverCarro(int indice){
		if (lblCarro[indice].getX() + lblCarro[indice].getWidth() == 0){
			t[indice].stop();
			lblCarro[indice].setVisible(false);
			lblLetra[indice].setVisible(false);
			lblp50.setBounds(20, lblLetra[indice].getY(), lblLetra[indice].getWidth(), lblLetra[indice].getHeight());
			if(pontos >= 50){
				pontos -= 50;
				lblp50.setText("- 50");
			}else{
				pontos -= pontos;
				lblp50.setText("- 0");
			}
			t[4].restart();
			lblp50.setVisible(true);
			lblCarro[indice]= null;
			lblLetra[indice]= null;
			Runtime.getRuntime().gc();
			lblCarro[indice] = new JLabel(carro);
			lblLetra[indice] = new JLabel();
			addCarro(randomLetra(), indice + 1);
			lblCarro[indice].setLocation(lblCarro[indice].getX() + lblCarro[indice].getWidth(), lblCarro[indice].getY());
			lblLetra[indice].setLocation(lblLetra[indice].getX() + lblCarro[indice].getWidth(), lblLetra[indice].getY());
			lblCarro[indice].setVisible(true);
			lblLetra[indice].setVisible(true);
			lblPontos.setText(String.valueOf(pontos));
			t[indice].restart();
		}else{
			lblCarro[indice].setLocation(lblCarro[indice].getX() - 1, lblCarro[indice].getY()); 
			lblLetra[indice].setLocation(lblLetra[indice].getX() - 1, lblLetra[indice].getY());
		}	
	}
	
	public String randomLetra(){
		String l = letras[rand.nextInt(letras.length)];
		for (int i = 0; i < 3; i ++){
			if (l.equals(lblLetra[i].getText())){
				return randomLetra();
			}
		}
		return l;
	}
	
	private class RndTempo implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int r = rand.nextInt(3);
			if (r == 0 && ver[0] == false){
				addCarro(randomLetra(), 1);
				Facil.this.invalidate();
				Facil.this.validate();
				Facil.this.repaint();
				lblCarro[0].setVisible(true);
				lblLetra[0].setVisible(true);
				t[0].restart();
				ver[0] = true;
			}
			if (r == 1 && ver[1] == false){
				addCarro(randomLetra(), 2);
				Facil.this.invalidate();
				Facil.this.validate();
				Facil.this.repaint();
				lblCarro[1].setVisible(true);
				lblLetra[1].setVisible(true);
				t[1].restart();
				ver[1] = true;
			}
			if (r == 2 && ver[2] == false){
				addCarro(randomLetra(), 3);
				Facil.this.invalidate();
				Facil.this.validate();
				Facil.this.repaint();
				lblCarro[2].setVisible(true);
				lblLetra[2].setVisible(true);
				t[2].restart();
				ver[2] = true;
			}
			int y = 0;
			for (int i = 0; i < 3; i++){
				if (ver[i] == true){
					y+=1;
				}
			}
			if (y == 3){
				for (int i = 0; i < 3; i++){
					ver[i] = false;
				}
				rt.stop();
			}
		}		
	}
	
	private class EvtMoverCarro1 implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			moverCarro(0);
		}		
	}
	
	private class EvtMoverCarro2 implements ActionListener{

		
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			moverCarro(1);
		}		
	}
	
	private class EvtMoverCarro3 implements ActionListener{

		
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			moverCarro(2);
		}		
	}
	
	public void addCarro(String letra, int nivel){
		if (nivel == 1){
			lblLetra[nivel-1].setBounds(1165 - carro.getIconWidth(), 35, carro.getIconWidth(), carro.getIconHeight());
			lblLetra[nivel-1].setFont(new Font("Trebuchet MS", 0, 90));
			lblLetra[nivel-1].setForeground(Color.white);
			lblLetra[nivel-1].setText(letra);
			lblLetra[nivel-1].setVisible(false);
			this.remove(lblFundo);
			this.add(lblLetra[nivel-1]);
			this.add(lblFundo);
			
			lblCarro[nivel-1].setBounds(1000 - carro.getIconWidth(), 30, carro.getIconWidth(), carro.getIconHeight());
			lblCarro[nivel-1].setVisible(false);
			this.remove(lblFundo);
			this.add(lblCarro[nivel-1]);
			this.add(lblFundo);
		}
		if (nivel == 2){
			lblLetra[nivel-1].setText(letra);
			lblLetra[nivel-1].setBounds(1165 - carro.getIconWidth(), 275, carro.getIconWidth(), carro.getIconHeight());
			lblLetra[nivel-1].setFont(new Font("Trebuchet MS", 0, 90));
			lblLetra[nivel-1].setForeground(Color.white);
			lblLetra[nivel-1].setVisible(false);
			this.remove(lblFundo);
			this.add(lblLetra[nivel-1]);
			this.add(lblFundo);
			
			lblCarro[nivel-1].setBounds(1000 - carro.getIconWidth(), 270, carro.getIconWidth(), carro.getIconHeight());
			lblCarro[nivel-1].setVisible(false);
			this.remove(lblFundo);
			this.add(lblCarro[nivel-1]);
			this.add(lblFundo);
		}
		if (nivel == 3){
			lblLetra[nivel-1].setText(letra);
			lblLetra[nivel-1].setBounds(1165 - carro.getIconWidth(), 510, carro.getIconWidth(), carro.getIconHeight());
			lblLetra[nivel-1].setFont(new Font("Trebuchet MS", 0, 90));
			lblLetra[nivel-1].setForeground(Color.white);
			lblLetra[nivel-1].setVisible(false);
			this.remove(lblFundo);
			this.add(lblLetra[nivel-1]);
			this.add(lblFundo);
			
			lblCarro[nivel-1].setBounds(1000 - carro.getIconWidth(), 500, carro.getIconWidth(), carro.getIconHeight());
			lblCarro[nivel-1].setVisible(false);
			this.remove(lblFundo);
			this.add(lblCarro[nivel-1]);
			this.add(lblFundo);
		}
	}
	
	public void verificar(char tecla){
		for (int i = 0; i < 3; i++){
			if (String.valueOf(tecla).equalsIgnoreCase(lblLetra[i].getText())){
				alpha = 255;
				t[i].stop();
				lblCarro[i].setVisible(false);
				lblLetra[i].setVisible(false);
				lbl50.setBounds(lblLetra[i].getBounds());
				t[3].restart();
				lbl50.setVisible(true);
				lblCarro[i]= null;
				lblLetra[i]= null;
				Runtime.getRuntime().gc();
				lblCarro[i] = new JLabel(carro);
				lblLetra[i] = new JLabel();
				addCarro(randomLetra(), i + 1);
				lblCarro[i].setLocation(lblCarro[i].getX() + lblCarro[i].getWidth(), lblCarro[i].getY());
				lblLetra[i].setLocation(lblLetra[i].getX() + lblCarro[i].getWidth(), lblLetra[i].getY());
				lblCarro[i].setVisible(true);
				lblLetra[i].setVisible(true);
				pontos += 50;
				lblPontos.setText(String.valueOf(pontos));
				t[i].restart();
			}
		}
	}
	
	private class EvtPontos implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (alpha > 0){
				alpha-=5;
				lbl50.setLocation(lbl50.getX(), lbl50.getY() - 1);
				lbl50.setForeground(new Color(233, 222, 54, alpha));
			}else{
				lbl50 = null;
				Runtime.getRuntime().gc();
				alpha = 255;
				lbl50 = new JLabel();
				lbl50.setText("+50");
				lbl50.setFont(new Font("Arial", Font.BOLD, 40));
				lbl50.setVisible(false);
				Facil.this.add(lbl50);
				Facil.this.validate();
				t[3].stop();
			}
		}		
	}
	
	private class EvtPontosNegativo implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (alpha > 0){
				alpha-=5;
				lblp50.setLocation(lblp50.getX(), lblp50.getY() - 1);
				lblp50.setForeground(new Color(255,20,20, alpha));
			}else{
				lblp50 = null;
				Runtime.getRuntime().gc();
				alpha = 255;
				lblp50 = new JLabel();
				lblp50.setText("-50");
				lblp50.setFont(new Font("Arial", Font.BOLD, 40));
				lblp50.setVisible(false);
				Facil.this.add(lblp50);
				Facil.this.validate();
				t[4].stop();
			}
		}		
	}
	
	private class EvtTempo implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (y >= 0){
				if (y > 59){
					tempo.setText("01:" + String.valueOf(y - 60));
					if (y - 60 < 10){
						tempo.setText("01:0" + String.valueOf(y - 60));
					}
				}else{
					if (y >= 10){
						tempo.setText("00:" + String.valueOf(y));
					}else{
						tempo.setText("00:0" + String.valueOf(y));
					}
				}				
				y--;
			}else{
				t[5].stop();
				for (int i = 0; i < t.length; i++){
					t[i].stop();
					t[i] = null;
				}
				for (int i = 0; i < lblCarro.length; i++){
					lblCarro[i].setVisible(false);
					lblLetra[i].setVisible(false);
					lblCarro[i] = null;
					lblLetra[i] = null;
				}
				System.gc();
				JOptionPane.showMessageDialog(null, "O Tempo Acabou!\nSua Pontuação é: "+pontos, "Atenção",
						JOptionPane.INFORMATION_MESSAGE);
				if(JOptionPane.showConfirmDialog(null, " Deseja Tentar Novamente? ", "Train!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
					Facil.this.dispose();
					Facil frm = new Facil();
					frm.setDefaultCloseOperation(EXIT_ON_CLOSE);
					frm.setVisible(true);
					frm.setLocationRelativeTo(null);
				}else{
					Facil.this.dispose();
					Aplicacao frm = new Aplicacao();
			        frm.setSize(1024,768);
					frm.setLocationRelativeTo(Facil.this);
					frm.setVisible(true);
					frm.setResizable(false);
				}
			}
		}		
	}
	
	private class EvtDigitar implements KeyListener {

		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stu
			verificar(e.getKeyChar());
		}

		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
		}
		
	}
}
