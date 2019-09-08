package netflixcreator.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Classe para lermos ficheiros properties
 * e usar funcoes uteis para extrairmos os dados nesses ficheiros
 * 
 * key     property
 *  |         |
 *  |         v
 *  v   |------------|
 * key1:arg1,arg2,arg3
 */
public class Configuration {

	private Properties prop = new Properties();

	/*
	 * Este Configuration nao deve ser singleton
	 * visto que cm temos varios ficheiros properties
	 * precisamos de uma instancia de Configuration para cada 1
	 */
	public Configuration() {
		try {
			prop.load(new FileInputStream("info.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Lista de parametros extraidos 
	 * de uma certa key
	 * Ex:
	 * no ficheiro estah: propery1:arg1;arg2;agr3
	 * Isto retorna: ["arg1","arg2","arg3"]
	 * @requires key != null
	 * @return Lista de parametros extraidos 
	 * de uma certa propriedade
	 */
	public String[] getStringArray(String key) {
		return getString(key).split(";");
	}
	
	public String getString(String key) {
		return prop.getProperty(key);
	}
	
	public int getInt(String key) {
		return Integer.parseInt(getString(key));
	}
	
	/**
	 * Adds one to the current number on that property
	 * @param key
	 */
	public void updateInt(String key) {
		// TODO
	}

	/**
	 * Instancia nova de uma classe
	 * @param className nome da classe
	 * @param defaultObject classe a usar
	 * caso classeName nao exista
	 * @param arguments lista de pares 
	 * de Classe e instancia da classe
	 * @return Instancia nova de uma classe
	 * com nome className
	 */
	@SuppressWarnings("unchecked")
	public <T> T createInstance(String className, T defaultObject) {
		Class<T> c = null;
		try {
			// extrai algo que nos vai dar o construtor
			c = (Class<T>) Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		T ins = defaultObject;
		try {
			
			Constructor<T> cons = c.getConstructor();
			ins = cons.newInstance();

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return ins;
	}

	public <T,E> List<T> getInstances(String chave, T defaultObject) {
		return Arrays.stream(this.getStringArray(chave))
				.map((className) -> this.createInstance(className, defaultObject))
				.collect(Collectors.toList());
	}

}
