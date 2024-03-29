package com.google.threadpool;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Hashtable;

/**
 * 
 * Class to implement methods to be used by the thread manager for printing
 * all tracing messages.
 * @author �lvaro
 *
 */
public class Trace {
	
	private static Hashtable<Integer, OutputStream> standarOutput;
	private static Hashtable<Integer, OutputStream> errorOutput;
	
	private static boolean isStandardPrintable;
	
	static {
		standarOutput = new Hashtable<Integer, OutputStream>();
		errorOutput = new Hashtable<Integer, OutputStream>();
		isStandardPrintable = true;
	}

	
	protected static void setStandardPrintable(boolean standarPrint) {
		isStandardPrintable = standarPrint ;
	}
	
	protected static boolean getStandardPrintable() {
		return isStandardPrintable;
	}
	/**
	 * 
	 * It prints all specified output error streams.
	 * If there is no specified output stream, the method prints
	 * using System.err object. 
	 * @param message String containing the message to print.
	 */
	protected static void err(String message) {
		Collection<OutputStream> collectionOut = errorOutput.values();
		byte[] stream = message.getBytes();
		
		for(OutputStream out : collectionOut){
			try{
				out.write(stream);
				out.flush();
			}catch(IOException ex){
				System.err.println(ex.getMessage());
			}
		}
		
		if(collectionOut.size()==0 && isStandardPrintable) {
			System.err.println(message);
		}
	}
	/**
	 * It prints all specified output streams.
	 * If there is no output stream, it prints using
	 * System.out object.
	 * @param message String containing the message to print.
	 */
	public static void print(String message) {
		Collection<OutputStream> collectionOut = standarOutput.values();
		byte[] stream = message.getBytes();
				
		for(OutputStream out : collectionOut){
			try{
				out.write(stream);
				out.flush();
			}catch(IOException ex){
				System.err.println(ex.getMessage());
			}
		}
		
		if(collectionOut.size()==0 && isStandardPrintable) {
			System.out.println(message);
		}
	}
	/**
	 * It adds a new output stream for printing messages.
	 * @param out Output stream
	 */
	protected static void addSalidaStandar(OutputStream out) {
				
		if(!standarOutput.containsKey(out.hashCode())) {
			standarOutput.put(out.hashCode(), out);
		}
	}
	/**
	 * It adds a new output error stream for printing messages.
	 * @param out Output error stream.
	 */
	protected static void addSalidaError(OutputStream out) {
		if( !errorOutput.containsKey(out.hashCode()) ){
			errorOutput.put(out.hashCode(), out);
		}
	}
	/**
	 * It removes the output stream from the list.
	 * @param out Stream to be deleted.
	 */
	protected static void removeSalidaStandar( OutputStream out ) {
		standarOutput.remove(out.hashCode());
	}
	/**
	 * It removes the output error stream from the list.
	 * @param out Stream to be deleted.
	 */
	protected static void removeSalidaError( OutputStream out ) {
		errorOutput.remove(out.hashCode());
	}
	/**
	 * It removes all output streams.
	 */
	protected static void removeAllSalidaStandar() {
		Collection<OutputStream> collectionSalida = standarOutput.values();
		
		for(OutputStream out : collectionSalida){
			removeSalidaStandar(out);
		}
	}
	/**
	 * It removes all output error streams.
	 */
	protected static void removeAllSalidaError() {
		Collection<OutputStream> collectionSalida = errorOutput.values();
		
		for(OutputStream out : collectionSalida){
			removeSalidaError(out);
		}
	}
}
