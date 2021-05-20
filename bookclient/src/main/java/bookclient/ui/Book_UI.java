package bookclient.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bookclient.BookService;
import bookclient.helper.RetrofitClientCreator;
import bookclient.model.Book;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Book_UI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel de;
	private JLabel lblNewLabel;
	private JTextField txtId;
	private JLabel name;
	private JTextField txtName;
	private JLabel lblTitle;
	private JTextField txtTitle;
	private JTextField txtPrice;
	private JLabel lblPrice;
	private JLabel lblQuanlity;
	private JTextField txtQuanlity;
	private JTextField txtDescripstion;
	private JLabel lblDescripstion;
	private JButton btnAdd;
	private JButton btnRemove;
	private JButton btnGet;
	private JButton btnEdit;
	private Retrofit retrofit;
	private BookService bookService;
	private JButton btnGetById;
	private JButton btnExit;
	private JTextField txtIdBookSearct;
	private JButton btnClear;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Book_UI frame = new Book_UI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Book_UI() {
		retrofit = RetrofitClientCreator.getClient();
		bookService = retrofit.create(BookService.class);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 437, 439);
		contentPane.add(scrollPane);
		de = new DefaultTableModel(new Object[][] {},
				new String[] { "id", "name", "title", "price", "quanlity", "descripstion" });
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String id = (String) table.getValueAt(table.getSelectedRow(), 0);
				txtId.setText(id);
				String name = (String) table.getValueAt(table.getSelectedRow(), 1);
				txtName.setText(name);
				String title = (String) table.getValueAt(table.getSelectedRow(), 2);
				txtTitle.setText(title);
				String price = (String) table.getValueAt(table.getSelectedRow(), 3);
				txtPrice.setText(price);
				String quanlity = (String) table.getValueAt(table.getSelectedRow(), 4);
				txtQuanlity.setText(quanlity);
				String descripstion = (String) table.getValueAt(table.getSelectedRow(), 5);
				txtDescripstion.setText(descripstion);
			}
			// "id", "name", "title", "price", "quanlity", "descripstion"
		});
		table.setModel(de);
		scrollPane.setViewportView(table);

		lblNewLabel = new JLabel("Id");
		lblNewLabel.setBounds(457, 18, 67, 21);
		contentPane.add(lblNewLabel);

		txtId = new JTextField();
		txtId.setBounds(522, 18, 86, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);

		name = new JLabel("name");
		name.setBounds(618, 18, 67, 21);
		contentPane.add(name);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(683, 18, 86, 20);
		contentPane.add(txtName);

		lblTitle = new JLabel("title");
		lblTitle.setBounds(457, 50, 67, 21);
		contentPane.add(lblTitle);

		txtTitle = new JTextField();
		txtTitle.setColumns(10);
		txtTitle.setBounds(522, 50, 86, 20);
		contentPane.add(txtTitle);

		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(683, 50, 86, 20);
		contentPane.add(txtPrice);

		lblPrice = new JLabel("price");
		lblPrice.setBounds(618, 50, 67, 21);
		contentPane.add(lblPrice);

		lblQuanlity = new JLabel("quanlity");
		lblQuanlity.setBounds(457, 99, 67, 21);
		contentPane.add(lblQuanlity);

		txtQuanlity = new JTextField();
		txtQuanlity.setColumns(10);
		txtQuanlity.setBounds(522, 99, 86, 20);
		contentPane.add(txtQuanlity);

		txtDescripstion = new JTextField();
		txtDescripstion.setColumns(10);
		txtDescripstion.setBounds(683, 99, 86, 20);
		contentPane.add(txtDescripstion);

		lblDescripstion = new JLabel("descripstion");
		lblDescripstion.setBounds(618, 99, 67, 21);
		contentPane.add(lblDescripstion);

		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					bookService.addBook(new Book(Integer.parseInt(txtId.getText()), txtName.getText(),
							txtTitle.getText(), txtDescripstion.getText(), Integer.parseInt(txtPrice.getText()),
							Integer.parseInt(txtQuanlity.getText()))).enqueue(new Callback<Void>() {

								@Override
								public void onResponse(Call<Void> call, Response<Void> response) {
									getAll();

								}

								@Override
								public void onFailure(Call<Void> call, Throwable t) {

								}
							});
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Sai dữ liệu xin nhập đúng và đủ");
				}
			}
		});
		btnAdd.setBounds(457, 147, 151, 23);
		contentPane.add(btnAdd);

		btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					bookService.removeBook(Integer.parseInt(txtId.getText().trim())).enqueue(new Callback<Void>() {

								@Override
								public void onResponse(Call<Void> call, Response<Void> response) {
									getAll();

								}

								@Override
								public void onFailure(Call<Void> call, Throwable t) {

								}
							});
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Sai dữ liệu xin nhập đúng và đủ");
				}
			}
		});
		btnRemove.setBounds(632, 147, 137, 23);
		contentPane.add(btnRemove);

		btnGet = new JButton("Get All Book");
		btnGet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getAll();
			}

		});

		btnGet.setBounds(632, 191, 137, 23);
		contentPane.add(btnGet);

		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					bookService.editBook(new Book(Integer.parseInt(txtId.getText()), txtName.getText(),
							txtTitle.getText(), txtDescripstion.getText(), Integer.parseInt(txtPrice.getText()),
							Integer.parseInt(txtQuanlity.getText()))).enqueue(new Callback<Void>() {

								@Override
								public void onResponse(Call<Void> call, Response<Void> response) {
									getAll();

								}

								@Override
								public void onFailure(Call<Void> call, Throwable t) {

								}
							});
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Sai dữ liệu xin nhập đúng và đủ");
				}
			}

		});
		btnEdit.setBounds(457, 191, 151, 23);
		contentPane.add(btnEdit);
		
		btnGetById = new JButton("Get by id");
		btnGetById.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					bookService.getBookById(Integer.parseInt(txtIdBookSearct.getText())).enqueue(new Callback<Book>() {
						
						@Override
						public void onResponse(Call<Book> call, Response<Book> response) {
							JOptionPane.showMessageDialog(null,"Id: "+response.body().getId()+" Name: "+response.body().getName()+""
									+ " title: "+response.body().getTitle()+" price: "+response.body().getPrice()+" "
											+ "quanlity:  "+response.body().getQuanlity()+" descripstion: "+response.body().getDescription());
							
						}
						
						@Override
						public void onFailure(Call<Book> call, Throwable t) {
							 JOptionPane.showMessageDialog(null, "Lỗi");
							
						}
					});
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập id cần tìm!");
				}
			}
		});
		btnGetById.setBounds(457, 229, 151, 23);
		contentPane.add(btnGetById);
		
		btnExit = new JButton("exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(632, 229, 151, 23);
		contentPane.add(btnExit);
		
		txtIdBookSearct = new JTextField();
		txtIdBookSearct.setBounds(457, 263, 151, 30);
		contentPane.add(txtIdBookSearct);
		txtIdBookSearct.setColumns(10);
		
		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtId.setText("");
				txtDescripstion.setText("");
				txtIdBookSearct.setText("");
				txtQuanlity.setText("");
				txtPrice.setText("");
				txtName.setText("");
				txtTitle.setText("");
			}
		});
		btnClear.setBounds(632, 267, 151, 23);
		contentPane.add(btnClear);
	}

	public void getAll() {
		de.getDataVector().removeAllElements();
		Call<List<Book>> call = bookService.getBook();

		try {
			Response<List<Book>> response;
			response = call.execute();
//			 "id", "name", "title", "price", "quanlity", "descripstion" }
			response.body().forEach(x -> {
				Vector<String> vector = new Vector<String>();
				vector.addElement(String.valueOf(x.getId()));
				vector.addElement(String.valueOf(x.getName()));
				vector.addElement(String.valueOf(x.getTitle()));
				vector.addElement(String.valueOf(x.getPrice()));
				vector.addElement(String.valueOf(x.getQuanlity()));
				vector.addElement(String.valueOf(x.getDescription()));
				de.addRow(vector);
			});
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
}
