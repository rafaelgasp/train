import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Tempo extends JFrame{
	JPanel p;
	int sec = 1;
	JLabel lsec;
	Timer t = new Timer(1000, new EvtTempo());
	String jogo;
	public Tempo(ImageIcon img, String jogo){
		super("Train - Principal");
		this.setLayout(null);
		this.setResizable(false);
		this.setSize(1024, 768);
		this.setUndecorated(true);
		this.jogo = jogo;
		this.setIconImage(new ImageIcon(getClass().getResource("img/icon.png")).getImage());
		
		JLabel lblFundo = new JLabel(img);
		lblFundo.setBounds(0, 0, 1024, 768);
		
		JLabel lblTimer = new JLabel(new ImageIcon(getClass().getResource("img/Train/timer.gif")));
		lblTimer.setBounds(0, 0, 1024, 768);
		this.add(lblTimer);
		
		p = new JPanel();
		p.setBackground(new Color(255,255,255,125));
		p.setBounds(0, 0, 1024, 768);
		this.add(p);
		
		this.add(lblFundo);
		
		t.start();
	}
	
	private class EvtTempo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(sec > 3){
				if(jogo.equals("Fácil")){
					Facil frm = new Facil();
					frm.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					frm.setVisible(true);
					frm.setLocationRelativeTo(null); 
				}
				if(jogo.equals("Médio")){
					Médio frm = new Médio();
					frm.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					frm.setVisible(true);
					frm.setLocationRelativeTo(null); 
				}
				if(jogo.equals("Dificil")){
					Dificil frm = new Dificil();
					frm.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					frm.setVisible(true);
					frm.setLocationRelativeTo(null); 
				}
				t.stop();
				t.removeActionListener(this);
				Tempo.this.dispose();
			}else{
				sec++;
			}
		}		
	}
}
