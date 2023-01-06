package views;

import Dao.busquedaController;
import Dao.huespedesController;
import Dao.reservasController;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SingleSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloH;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//EventQueue.invokeLater(new Runnable() {
		//	public void run() {
		//		try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
		//		} catch (Exception e) {
		//			e.printStackTrace();
		//		}
		//	}
		//});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 600, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);
                
		
		
		
		tbReservas = new JTable()
                        {  
                            public boolean isCellEditable(int row,int column){  
                            Object o = getValueAt(row,column);  
                            if(column==0 || column == 3) return false;  
                            return true;  
                          }  
                        }; 
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), tbReservas, null);
                modelo = (DefaultTableModel) tbReservas.getModel();
                
                modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		            
                
                tbHuespedes = new JTable(){  
                            public boolean isCellEditable(int row,int column){  
                            Object o = getValueAt(row,column);  
                            if(column==0 || column ==6) return false;  
                            return true;  
                          }  
                        }; 
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
                
		
		modeloH = (DefaultTableModel) tbHuespedes.getModel();
		modeloH.addColumn("Numero de Huesped");
                modeloH.addColumn("Nombre");
		modeloH.addColumn("Apellido");
		modeloH.addColumn("Fecha de Nacimiento");
		modeloH.addColumn("Nacionalidad");
		modeloH.addColumn("Telefono");
		modeloH.addColumn("Numero de Reserva");
		
                
                
                panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), tbHuespedes, null);
                
                
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                                //detectar cual tab esta activo
                            
                            if(!txtBuscar.getText().equalsIgnoreCase("")){    
                                
                                busquedaController buscar = new busquedaController();
                                if (panel.getSelectedIndex()==0){
                                    int dato=0;
                                    if (txtBuscar.getText().matches(".*[0-9].*")){
                                        dato =Integer.parseInt(txtBuscar.getText());
                                    
                                    try {
                                        
                                        List<Map<String, String>> lista=  buscar.getReservas(dato);
                                    if (lista.size()!=0){
                                      modelo.setRowCount(0);
                                      modelo.addRow(
                                        new Object[] {
                                        "ID",
                                        "fechaEntrada",
                                        "fechaSalida",
                                        "valor",
                                        "formaDePago"
                                        });
                                      lista.forEach(item -> modelo.addRow(
                                        new Object[] {
                                        item.get("ID"),
                                        item.get("fechaEntrada"),
                                        item.get("fechaSalida"),
                                        item.get("valor"),
                                        item.get("formaDePago")
                                        }));
                                        
                                    }else{
                                        modelo.setRowCount(0);
                                        JOptionPane.showMessageDialog(null, "No se ha encontrado registro para ese criterio de busqueda");
                                    }
                                    }catch (SQLException ex) {
                                        Logger.getLogger(Busqueda.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }else {
                                            JOptionPane.showMessageDialog(null, "Debe Ingresar un número de Reserva");    
                                }
                                    
                                }else{
                                    
                                    try {
                                        
                                        List<Map<String, String>> lista=  buscar.getHuesped(txtBuscar.getText());
                                        
                                    if (lista.size()!=0){  
                                        modeloH.setRowCount(0);
                                        modeloH.addRow(new Object[] {
                                        "id",
                                        "nombre",
                                        "apellido",
                                        "fechaNacimiento",
                                        "nacionalidad",
                                        "telefono",
                                        "idReserva"
                                                
                                        });
                                        lista.forEach(item -> modeloH.addRow(
                                        new Object[] {
                                        item.get("id"),
                                        item.get("nombre"),
                                        item.get("apellido"),
                                        item.get("fechaNacimiento"),
                                        item.get("nacionalidad"),
                                        item.get("telefono"),
                                        item.get("idReserva")
                                                
                                        }));
                                    
                                    }else{
                                        modeloH.setRowCount(0);
                                        JOptionPane.showMessageDialog(null, "No se ha encontrado registro para ese criterio de busqueda");
                                    }
                                    }catch (SQLException ex) {
                                        Logger.getLogger(Busqueda.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                
                        }else{
                            JOptionPane.showMessageDialog(null, "Debe ingresar algún valor a buscar");
                        }
                     }
		});
                
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                                //obtener id  de fila editada
                            
                            if (panel.getSelectedIndex()==0){
                                int milisecondsByDay = 24*60*60*1000;
                                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                                
                                int id = Integer.parseInt(modelo.getValueAt(tbReservas.getSelectedRow(),0).toString());
                                Date fEntrada=null,fSalida=null;
                                float valor =0;
                                try {
                                    fEntrada = date.parse(modelo.getValueAt(tbReservas.getSelectedRow(),1).toString());
                                    fSalida = date.parse(modelo.getValueAt(tbReservas.getSelectedRow(),2).toString());
                                    valor = ((fSalida.getTime() -fEntrada.getTime())/milisecondsByDay)*ReservasView.valorXDia;
                                    modelo.setValueAt(valor, tbReservas.getSelectedRow(),3);
                                } catch (ParseException ex) {
                                    Logger.getLogger(Busqueda.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                int fPago = Integer.parseInt(modelo.getValueAt(tbReservas.getSelectedRow(), 4).toString());
                           
                                reservasController control = new reservasController();
                                try {
                                    control.modificar(id, date.format(fEntrada), date.format(fSalida), valor, fPago);
                                    JOptionPane.showMessageDialog(null, "El Registro ha sido modificado");
                                    
                                } catch (SQLException ex) {
                                    Logger.getLogger(Busqueda.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                             
                            }else{
                                int id = Integer.parseInt(modeloH.getValueAt(tbHuespedes.getSelectedRow(),0).toString());
                                String nombre= modeloH.getValueAt(tbHuespedes.getSelectedRow(),1).toString();
                                String apellido = modeloH.getValueAt(tbHuespedes.getSelectedRow(),2).toString();
                                String fNacimiento = modeloH.getValueAt(tbHuespedes.getSelectedRow(),3).toString();
                                String nacionalidad = modeloH.getValueAt(tbHuespedes.getSelectedRow(),4).toString();
                                String telefono = modeloH.getValueAt(tbHuespedes.getSelectedRow(),5).toString();
                                
                           
                                huespedesController control = new huespedesController();
                                try {
                                    control.modificar(id, nombre,apellido,fNacimiento,nacionalidad,telefono);
                                    JOptionPane.showMessageDialog(null, "El Registro ha sido modificado");
                                    
                                } catch (SQLException ex) {
                                    Logger.getLogger(Busqueda.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            
                        }
                });
                
		contentPane.add(btnEditar);
		
                
                
		JLabel lblEditar = new JLabel("CONFIRMAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
                JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                                //obtener id  de fila a eliminar 
                            
                            if (panel.getSelectedIndex()==0){
                                int fila =tbReservas.getSelectedRow();
                                int id = Integer.parseInt(modelo.getValueAt(fila,0).toString());
                                
                                reservasController control = new reservasController();
                                try {
                                    int resultado =control.eliminar(id);
                                    
                                    if (resultado !=0){
                                       //actualizo el modelo para que refresque la tabla
                                        huespedesController con = new huespedesController();
                                        con.eliminarXReserva(id);
                                        modelo.removeRow(fila);
                                        modelo.fireTableRowsDeleted(fila, fila);
                                        JOptionPane.showMessageDialog(null, "El Registro ha sido eliminado");
                                        txtBuscar.setText("");
                                        
                                    }else{
                                        JOptionPane.showMessageDialog(null, "El Registro no se ha podido eliminar reintente");
                                    }
                                } catch (SQLException ex) {
                                    Logger.getLogger(Busqueda.class.getName()).log(Level.SEVERE, null, ex);
                                }
                             
                            }else{
                                huespedesController control = new huespedesController();
                                int fila =tbHuespedes.getSelectedRow();
                                int id = Integer.parseInt(modeloH.getValueAt(fila,0).toString());
                                int idReserva = Integer.parseInt(modeloH.getValueAt(fila,6).toString());                                
                                try {
                                    new reservasController().eliminar(idReserva);
                                    int resultado =control.eliminarXId(id);
                                    
                                    if (resultado !=0){
                                       //actualizo el modelo para que refresque la tabla
                                        modeloH.removeRow(fila);
                                        modeloH.fireTableRowsDeleted(fila, fila);
                                        JOptionPane.showMessageDialog(null, "El Registro ha sido eliminado");
                                        txtBuscar.setText("");
                                        
                                    }else{
                                        JOptionPane.showMessageDialog(null, "El Registro no se ha podido eliminar reintente");
                                    }
                                } catch (SQLException ex) {
                                    Logger.getLogger(Busqueda.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            
                        }
                });
                
                contentPane.add(btnEliminar);
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
                
                panel.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        txtBuscar.setText("");
                        modelo.setRowCount(0);
                        modeloH.setRowCount(0);
                    }
                });
        
      
}
//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
            
            
}
