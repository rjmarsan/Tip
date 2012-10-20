package com.rjmetro.tip;

import java.util.ArrayList;

public class DataManager {

	public static interface DataListener {
		public void updatedData(Data newdata, DataManager dataman);
	}
	
	Data data;
	
	
	private ArrayList<DataListener> listeners = new ArrayList<DataListener>();
	
	public void addListener(DataListener listener) {
		listeners.add(listener);
	}
	public void removeListener(DataListener listener) {
		listeners.remove(listener);
	}
	
	
	public void notifyChange() {
		for (DataListener l : listeners) {
			l.updatedData(data, this);
		}
	}
	
	
}
