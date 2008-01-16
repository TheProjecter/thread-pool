package com.google.threadpool;

public interface IThreadListener {
	/**
	 * Operaci�n realizada por el hilo en su ejecuci�n
	 */
	public void onRun();
	/**
	 * Operaci�n realizada por el hilo en caso de cancelaci�n del mismo
	 */
	public void onAbort();
	
}
