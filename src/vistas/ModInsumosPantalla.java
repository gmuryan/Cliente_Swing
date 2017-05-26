package vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import businessDelegates.InsumoDelegate;
import modelo.Cliente;
import controlador.Controlador;

public class ModInsumosPantalla extends javax.swing.JFrame {
	
	private JTextField codt;
	private JButton mod;
	private JButton	buscar;
	private JLabel mensaje;
	private InsumoDelegate controlador;
	private ArrayList<JLabel> labels = new ArrayList<JLabel>();
	private ArrayList<JTextField> texts = new ArrayList<JTextField>();
	private String[] nombres = {"Codigo:", "Nombre:", "Descripcion:", "Stock Minimo:", "Cant Compra:"};

	
	public ModInsumosPantalla(InsumoDelegate controlador) {
		super();
		this.controlador = controlador;
		crearPantalla();
	}

	private void crearPantalla() {
		int i = 0;
		try 
		{
			
			setSize(500, 50 * (nombres.length + 2) + 70);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			setTitle("Modificar Insumo");
			
			JLabel dnib = new JLabel();
			getContentPane().add(dnib);
			dnib.setText("Codigo:");
			dnib.setBounds(21, 20, 70, 30);
			
			codt = new JTextField();
			getContentPane().add(codt);
			codt.setBounds(120, 20, 210, 30);
			
			for (i = 0; i < nombres.length; i++){
				JLabel l = new JLabel();
				getContentPane().add(l);
				l.setText(nombres[i]);
				l.setBounds(21, 50 * i + 70, 70, 30);
				l.setVisible(false);
				labels.add(l);
				
				JTextField t = new JTextField();
				getContentPane().add(t);
				t.setBounds(120, 50 * i + 70, 210, 30);
				t.setVisible(false);
				texts.add(t);
			}
			texts.get(0).setEnabled(false);
			
			mensaje = new JLabel();
			getContentPane().add(mensaje);
			mensaje.setBounds(21, 50 * i + 70, 200, 30);
			
			buscar = new JButton();
			getContentPane().add(buscar);
			buscar.setText("Buscar");
			buscar.setBounds(360, 20, 90, 30);
			buscar.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					
					String cod = codt.getText();
					
					if(isInteger(cod) && controlador.verificarInsumo(Integer.parseInt(cod))){
						InsumoDTO iv = controlador.solicitarInsumoView(Integer.parseInt(cod));
						for (JLabel l : labels) l.setVisible(true);
						for (JTextField t : texts) t.setVisible(true);
						texts.get(0).setText(cod);
						texts.get(1).setText(iv.nombre);
						texts.get(2).setText(iv.desc);
						texts.get(3).setText(Integer.toString(sv.stockMin));
						texts.get(4).setText(Integer.toString(iv.cantCompra));
						mod.setVisible(true);
						
						mensaje.setText("");
					} else {
						for (JLabel l : labels) l.setVisible(false);
						for (JTextField t : texts) t.setVisible(false);
						mod.setVisible(false);
						mensaje.setText("El insumo no existe.");
						mensaje.setForeground(Color.RED);
					}
				
				}
			});
			
			mod = new JButton();
			getContentPane().add(mod);
			mod.setText("Modificar");
			mod.setBounds(360, 50 * i + 20, 90, 30);
			mod.setVisible(false);
			
			mod.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent evt) 
				{
					boolean error = false;
					String texto = "El insumo se modific� con �xito.";
					mensaje.setForeground(Color.GREEN);
					
					String cod = texts.get(0).getText();
					
					if (!isInteger(texts.get(3).getText())){
						texto = "Stock deber�a ser un n�mero.";
						mensaje.setForeground(Color.RED);
						error = true;
					}
					
					if (!isInteger(texts.get(4).getText())){
						texto = "Cantidad Compra deber�a ser un n�mero.";
						mensaje.setForeground(Color.RED);
						error = true;
					}
					
					if (!isInteger(cod)){
						texto = "Numero deber�a ser un n�mero.";
						mensaje.setForeground(Color.RED);
						error = true;
					}
					
					if (!error){
							InsumoView iv = controlador.solicitarInsumoView(Integer.parseInt(cod));
							iv.nombre = texts.get(1).getText();
							iv.desc = texts.get(2).getText();
							iv.stockMin = Integer.parseInt(texts.get(3).getText());
							iv.cantCompra = texts.get(4).getText();
							controlador.modificarCliente(iv, Integer.parseInt(cod));
							for (JTextField t : texts) t.setText("");
					
					}
					
					mensaje.setText(texto);
					
				}
			});
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    return true;
	}

}