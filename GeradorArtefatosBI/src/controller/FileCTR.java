package controller;

import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import objects.Column;
import objects.ConexaoDB;
import objects.Schema;
import objects.Table;

public class FileCTR {

	private static final String CAMINHO_RESOURCES = "src" + File.separator + "resources";
	private static final String FILE_CONNECTION = "conexão.data";

	public static void writeFile(List<ConexaoDB> obj) {
		FileOutputStream fout;
		ObjectOutputStream oout;
		try {
			fout = new FileOutputStream("." + File.separator + CAMINHO_RESOURCES + File.separator + FILE_CONNECTION);
			oout = new ObjectOutputStream(fout);
			oout.writeObject(obj);
		} catch (Exception e) {
			System.out.println("Não foi possivel salvar o arquivo de conexão.");
		}
	}

	public static void writeFile(File file, byte data[], Component component) {
		FileOutputStream out;
		boolean error = false;
		try {
			out = new FileOutputStream(file);
			out.write(data);
			out.close();
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(component, "Error ao salvar arquivo.");
			error = true;
		}
		if (!error) {
			JOptionPane.showMessageDialog(component, "Arquivo salvo com sucesso!");
		}
	}

	@SuppressWarnings({ "resource" })
	public static Object readFile(int fileIndex) {
		FileInputStream fin;
		ObjectInputStream oin;

		switch (fileIndex) {
		case 1:
			try {
				fin = new FileInputStream("." + File.separator + CAMINHO_RESOURCES + File.separator + FILE_CONNECTION);
				oin = new ObjectInputStream(fin);
				return oin.readObject();
			} catch (Exception e) {
				System.out.println("Não foi possivel ler o arquivo de conexão.");
				break;
			}
		}
		return new ArrayList<>();
	}

	public static FileFilter getFileFilter(int type) {
		return new FileFilter() {
			@Override
			public String getDescription() {
				switch (type) {
				case 1:
					return "SQL Files";

				case 2:
					return "XML Files";

				default:
					return "Todos";
				}
			}

			@Override
			public boolean accept(File f) {
				if (f.isDirectory()) {
					return true;
				}

				String extension = FileCTR.getExtension(f);
				if (extension != null) {
					if (extension.equals("sql") || extension.equals("txt") || extension.equals("xml")) {
						return true;
					}
				} else {
					return false;
				}
				return false;
			}
		};
	}

	private static String getExtension(File f) {
		String ext = null;
		String s = f.getName();
		int i = s.lastIndexOf('.');

		if (i > 0 && i < s.length() - 1) {
			ext = s.substring(i + 1).toLowerCase();
		}
		return ext;
	}

	public static void readSqlFile(File file, List<Table> scripts) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		StringTokenizer st = null;
		Table script = new Table();
		while (scanner.hasNextLine()) {
			String lines = scanner.nextLine();
			if (lines.startsWith("CREATE TABLE")) {
				StringTokenizer st1 = new StringTokenizer(lines, "(");
				if (!script.getName().isEmpty()) {
					scripts.add(script);
					script = new Table();
				}
				script.setName(st1.nextToken().substring(13));
			}

			st = new StringTokenizer(lines);

			if (!lines.equals("") && lines.indexOf("CREATE") == -1 && lines.indexOf("ALTER") == -1
					&& lines.indexOf("DROP") == -1 && !lines.equals("(") && !lines.equals(");")) {
				if (lines.startsWith(" ") || !lines.startsWith(" ")) {
					Column column = new Column();
					column.setName(st.nextToken());
					column.setType(st.nextToken().replaceAll(",", ""));
					script.setColumn(column);
				}
			}
		}
	}

	public static void createSchema(File file, List<Table> tables) {
		Schema schema = new Schema();
		schema.setTables(new ArrayList<>(tables));

		for (Table t : tables) {
			if (t.isCube()) {
				schema.addCube(t);
			} else if (t.isAggregation()) {
				schema.addAggregator(t);
			} else {
				schema.addDimension(t);
			}
		}

		Properties p = new Properties();
		p.setProperty("file.resource.loader.path", file.getParent());

		VelocityEngine ve = new VelocityEngine();
		ve.init(p);

		Template template = ve.getTemplate(file.getName());

		VelocityContext context = new VelocityContext();
		context.put("schema", schema);

		StringWriter writer = new StringWriter();

		template.merge(context, writer);

		JFileChooser f = new JFileChooser();
		int returnVal = f.showSaveDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				FileWriter fw = new FileWriter(f.getSelectedFile());
				fw.write(writer.toString());
				fw.close();
				JOptionPane.showMessageDialog(null, "Schema Create!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
