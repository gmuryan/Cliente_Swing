package vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import DTO.InsumoDTO;
import DTO.PrendaDTO;
import DTO.ProveedorDTO;
import DTO.VariedadPrendaDTO;
import businessDelegates.InsumoDelegate;
import businessDelegates.PrendaDelegate;
import businessDelegates.ProveedorDelegate;

public class AgregarVariedadPrendaPantalla extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private VariedadDelegate variedadBD;
	private PrendaDelegate prendaBD;
	private List<PrendaDTO> prendas;
//	private List<VariedadPrendaDTO> variedades;

	public AgregarVariedadPrendaPantalla() {
		super();
		try {
//			variedadBD = VariedadDelegate.GetInstancia();
			prendaBD = PrendaDelegate.GetInstancia();
			prendas = prendaBD.getAllPrendas();
//			variedades = variedadBD.getAllVariedades();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		crearPantalla();
	}

	private void crearPantalla() {

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setTitle("Asociar Variedad a Prenda");

		PrendaDTO prendasArray[] = new PrendaDTO[prendas.size()];
		int j = 0;
		for (PrendaDTO p : prendas) {
			prendasArray[j] = p;
			j++;
		}
		final JComboBox comboBox = new JComboBox(prendasArray);
		getContentPane().add(comboBox);
		comboBox.setBounds(80, 10, 210, 30);
		JLabel proveedorLabel = new JLabel("Prendas:");
		getContentPane().add(proveedorLabel);
		proveedorLabel.setBounds(10, 10, 120, 30);
		
//		VariedadPrendaDTO variedadesArray[] = new VariedadPrendaDTO()[variedades.size()];
//		int j = 0;
//		for (VariedadPrendaDTO v : variedades) {
//			variedadesArray[j] = v;
//			j++;
//		}
//		
//		final JComboBox variComboBox = new JComboBox(variedadesArray);
//		getContentPane().add(comboBox);
//		comboBox.setBounds(80, 40, 210, 30);
//		JLabel variLabel = new JLabel("Variedades:");
//		getContentPane().add(proveedorLabel);
//		proveedorLabel.setBounds(10, 40, 120, 30);


		JLabel precioLabel = new JLabel();
		getContentPane().add(precioLabel);
		precioLabel.setText("Precio:");
		precioLabel.setBounds(10, 70, 120, 30);

		final JTextField precio = new JTextField();
		getContentPane().add(precio);
		precio.setBounds(80, 70, 210, 30);
		
		
		JButton asociar = new JButton();
		getContentPane().add(asociar);
		asociar.setText("Asociar");
		asociar.setBounds(120, 100, 100, 30);

		asociar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				VariedadPrendaDTO variedad = (VariedadPrendaDTO) variComboBox.getSelectedItem();
				ProveedorDTO proveedor = (ProveedorDTO) comboBox.getSelectedItem();
				Float precioFloat = Float.parseFloat(precio.getText());
				/*try {
					prendaBD.asociarVariedad ();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
				
			}
		});

		pack();
		setSize(600, 300);

	}

}
