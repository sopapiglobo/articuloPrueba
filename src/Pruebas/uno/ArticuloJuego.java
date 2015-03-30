package Pruebas.uno;
import java.io.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;

public class ArticuloJuego extends JFrame{
	private JLabel atri1, atri2, atri3, atri4, atri5, atri6;
	private JTextField txtCodigo, txtNombre, txtPrecio, txtCantidad;
	private JButton Agregar, Modifica, Buscar, Eliminar;
	private JComboBox Bcate;
	private JComboBox Bcate2;
	private JTable Tabla;
	private JScrollPane scroll;
	
	public ArticuloJuego(){
		Crear();
		Configuracion();
		Dibujar();
		CargarEnTabla();
	
	}
	private void Crear(){
		atri1 = new JLabel ("Codigo");
		atri2 = new JLabel ("Nombre");
		atri3 = new JLabel ("Categoria");
		atri4 = new JLabel ("Precio");
		atri5 = new JLabel ("Cantidad");
		atri6 = new JLabel ("Consola");
		txtCodigo = new JTextField ();
		txtNombre = new JTextField ();
		txtPrecio = new JTextField ();
		txtCantidad = new JTextField();
		Agregar = new JButton ("Agregar");
		Modifica = new JButton ("Modificar");
		Buscar = new JButton ("Buscar");
		Eliminar = new JButton("Eliminar");
		String Opciones[] = {"Seleccionar","Accion","RPG","Deportes","Shooter","Estrategia","Infantil","Auto"};
		Bcate = new JComboBox(Opciones);
		String Opciones2 []= {"Seleccionar","PS3","PC","XBOX","WII","PSP"};
		Bcate2 = new JComboBox(Opciones2);
		Tabla = new JTable(new DefaultTableModel(new String[][]{},new String[]{"Codigo","Nombre","Categoria","Consola","Precio","Cantidad"}));
		scroll = new JScrollPane(Tabla);
	}
	
	private void Configuracion(){
		setTitle("Mantenedor de Juegos");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);	
	}
	
	private void Dibujar(){
		getContentPane().setLayout(new BorderLayout());	
		JPanel NORTE = new JPanel(new GridLayout(7,2));
		NORTE.add(atri1); NORTE.add(txtCodigo);
		NORTE.add(atri2); NORTE.add(txtNombre);
		NORTE.add(atri3); NORTE.add(Bcate);
		NORTE.add(atri6); NORTE.add(Bcate2);
		NORTE.add(atri4); NORTE.add(txtPrecio);
		NORTE.add(atri5); NORTE.add(txtCantidad);
		NORTE.add(Modifica);NORTE.add(Agregar);
		JPanel CENTRO = new JPanel (new GridLayout(1,1));
		CENTRO.add(scroll);
		JPanel SUR = new JPanel (new GridLayout(1,5));
		SUR.add(new JLabel("")); SUR.add(Buscar); SUR.add(new JLabel("")); SUR.add(Eliminar);SUR.add(new JLabel(""));
		getContentPane().add(BorderLayout.NORTH,NORTE);
		getContentPane().add(BorderLayout.CENTER,CENTRO);
		getContentPane().add(BorderLayout.SOUTH,SUR);
	    pack();
		txtCodigo.addKeyListener(new KeyAdapter(){
				public void keyTyped(KeyEvent evt){
					if(!Character.isDigit(evt.getKeyChar())){
						evt.consume();
					}
				
				}
			}); 
		txtPrecio.addKeyListener(new KeyAdapter(){
				public void keyTyped(KeyEvent evt){
					if(!Character.isDigit(evt.getKeyChar())){
						evt.consume();
					}
				
				}
			}); 
		txtCantidad.addKeyListener(new KeyAdapter(){
				public void keyTyped(KeyEvent evt){
					if(!Character.isDigit(evt.getKeyChar())){
						evt.consume();
					}
				
				}
			}); 
		Agregar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				if(txtNombre.getText().isEmpty() || txtCodigo.getText().isEmpty() || txtPrecio.getText().isEmpty()
				   || txtCantidad.getText().isEmpty()|| Bcate.getSelectedIndex()==0 || Bcate2.getSelectedIndex()==0){
					JOptionPane.showMessageDialog(null,"Faltan Datos");
				}
				else{
					guardarDatos();
					txtNombre.setText("");txtCodigo.setText("");txtPrecio.setText("");
					txtCantidad.setText(""); 
					Bcate.setSelectedIndex(0);
					Bcate2.setSelectedIndex(0);
					txtCodigo.requestFocus();
					CargarEnTabla();
			
			}
		}
			});
			
		Buscar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
					txtNombre.setText("");txtCodigo.setText("");txtPrecio.setText("");
					txtCantidad.setText("");
					txtCodigo.requestFocus();
					BuscarDatos();
			
			}
		});
		Modifica.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				if(txtNombre.getText().isEmpty() || txtCodigo.getText().isEmpty() || txtPrecio.getText().isEmpty()
				   || txtCantidad.getText().isEmpty()|| Bcate.getSelectedIndex()==0 || Bcate2.getSelectedIndex()==0){
					JOptionPane.showMessageDialog(null,"Faltan Datos");
				}
				else{
					ModificarDatos();
					CargarEnTabla();
					txtNombre.setText("");txtCodigo.setText("");txtPrecio.setText("");
					txtCantidad.setText("");
					txtCodigo.requestFocus();
					Bcate.setSelectedIndex(0);
					Bcate2.setSelectedIndex(0);
				}
			}
		}); 
		Eliminar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
					txtNombre.setText("");txtCodigo.setText("");txtPrecio.setText("");
					txtCantidad.setText("");
					txtCodigo.requestFocus();
					EliminarDatos();
					CargarEnTabla();
					
					
			}
		});    
	}
	
	public void guardarDatos(){
		boolean flag=true;
		File archivo = new File("Data.txt");
		try{
			BufferedReader leer = new BufferedReader(new FileReader(archivo));
			String linea ;
			while ((linea = leer.readLine()) != null) {
					String [] fila = linea.split(",");
					if(fila[0].equals(txtCodigo.getText())){
						JOptionPane.showMessageDialog(null,"El codigo Ingresado ya existe");
						flag=false;
					}
			}
			Tabla.updateUI();
			leer.close();
		}catch (IOException ex) {
				System.out.println(ex.getMessage());
				}
		if(flag==true){
			try {
				PrintWriter escribe = new PrintWriter(new FileWriter(archivo, true));
				escribe.print(txtCodigo.getText().toUpperCase());
				escribe.println(","+txtNombre.getText()+","+Bcate.getSelectedItem()+","+Bcate2.getSelectedItem()+","+txtPrecio.getText()+","+txtCantidad.getText());
				escribe.close();
			} catch (IOException ex) {
				System.out.println(ex.getMessage());
				}
			JOptionPane.showMessageDialog(null,"Ingreso Correcto");
		}
	}
	
	public void CargarEnTabla(){
		File archivo = new File("Data.txt");
		if (archivo.exists()) {
			try {
				BufferedReader leer = new BufferedReader(new FileReader(archivo));
				String linea;
				DefaultTableModel modelo = (DefaultTableModel) Tabla.getModel();
				modelo.getDataVector().clear();
				while ((linea = leer.readLine()) != null) {
					String [] fila = linea.split(",");
					modelo.addRow(fila);
				}
				Tabla.updateUI();
				leer.close();
			}catch (IOException ex) {
				System.out.println(ex.getMessage());
				}
		} 
		else {
			JOptionPane.showMessageDialog(null,"no existe el archivo");
		}
	}
	
	public void BuscarDatos(){
		int flag=0;
		File archivo = new File("Data.txt");
		if (archivo.exists()) {
			try {
				BufferedReader leer = new BufferedReader(new FileReader(archivo));
				String linea;
				DefaultTableModel modelo = (DefaultTableModel) Tabla.getModel();
				modelo.getDataVector().clear();
				try{
					String Codigo = JOptionPane.showInputDialog(null,"Ingrese el codigo");
					int codigo = Integer.parseInt(Codigo);
					while ((linea = leer.readLine()) != null) {
						String [] fila = linea.split(",");
						int codigos = Integer.parseInt(fila[0]);
						if(codigos == codigo){
							modelo.addRow(fila);
							flag=1;
						}
					}
					if(flag==0){
						JOptionPane.showMessageDialog(null,"Error, Codigo no existe");
						CargarEnTabla();
					}
					Tabla.updateUI();
					leer.close();
				}catch (NumberFormatException ex){
				JOptionPane.showMessageDialog(null,"Error, Codigo no valido");
			}
			}catch (IOException ex) {
				System.out.println(ex.getMessage());
				}
		} 
		else {
			JOptionPane.showMessageDialog(null,"No existe el archivo");
		}
	}
	
	public void ModificarDatos(){
		boolean flag=true;
		File archivo = new File("Data.txt");
                ArrayList Array = new ArrayList();
                try {
                    BufferedReader leer = new BufferedReader(new FileReader(archivo));
                    String linea = "";
                    while ((linea=leer.readLine())!=null){
                        String[] fila = linea.split(",");
                        if (fila[0].equals(txtCodigo.getText())){
                            linea=(txtCodigo.getText()+","+txtNombre.getText()+","+Bcate.getSelectedItem()+","+Bcate2.getSelectedItem()+","+txtPrecio.getText()+","+txtCantidad.getText());
                            Array.add(linea);
                            JOptionPane.showMessageDialog(null,"Datos modificados exitosamente");
                            flag=false;
                        }
                        else
							 Array.add(linea);
                    }
                    leer.close();
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null,"Archivo No Existe");
                }
                catch (IOException ex){
                    JOptionPane.showMessageDialog(null,"Error de lectura");
                }
                
		try {
			PrintWriter escribe = new PrintWriter(new FileWriter(archivo,false));
                        for (int i = 0; i < Array.size(); i++) {
								 escribe.println(Array.get(i));
                        }
                        escribe.close();			
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		if(flag==true)
			JOptionPane.showMessageDialog(null,"No existe el codigo ingresado");
		
	}
	
	public void EliminarDatos(){   
				boolean flag=true;
                File archivo = new File("Data.txt");
                ArrayList Array = new ArrayList();
                try {
                    BufferedReader leer = new BufferedReader(new FileReader(archivo));
                    String linea = "";
                    String Codigo = JOptionPane.showInputDialog(null,"Ingrese el codigo");
                    while ((linea=leer.readLine())!=null){
                        String[] fila = linea.split(",");
                        if (!fila[0].equals(Codigo)){
                            Array.add(linea);
                        }
                        else
							flag=false;
                    }
                    leer.close();
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null,"Archivo No Existe");
                }
                catch (IOException ex){
                    JOptionPane.showMessageDialog(null,"Error de lectura");
                }
                
		try {
			PrintWriter escribe = new PrintWriter(new FileWriter(archivo,false));
                        for (int i = 0; i < Array.size(); i++) {
								 escribe.println(Array.get(i));
                        }
                        escribe.close();			
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		if(flag==true)
			JOptionPane.showMessageDialog(null,"No existe el codigo ingresado");
		else
			JOptionPane.showMessageDialog(null,"Datos Eliminados");
	}
	public static void main (String arg[]){
		new ArticuloJuego ();
	}
}
