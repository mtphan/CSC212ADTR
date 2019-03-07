package edu.smith.cs.csc212.adtr;

/**
 * A class that stores two items (key,value) used in MapADT.
 * 
 * @author jfoley
 *
 * @param <KeyType> - the type of the key.
 * @param <ValueType> - the type of the value.
 */
public class Pair<KeyType, ValueType> {
	/**
	 * This is the contents of the key object.
	 */
	private KeyType key;
	/**
	 * This is the contents of the value object.
	 */
	private ValueType value;

	/**
	 * This Pair is created from two variables: a key and a value.
	 * 
	 * @param k - the key part of this pair.
	 * @param v - the value part of this pair.
	 */
	public Pair(KeyType k, ValueType v) {
		this.key = k;
		this.value = v;
	}

	/**
	 * Change the value in this Pair.
	 * 
	 * @param value - the value to set it to.
	 */
	public void setValue(ValueType value) {
		this.value = value;
	}

	/**
	 * Get the key for our usage.
	 * 
	 * @return the key stored in this pair.
	 */
	public KeyType getKey() {
		return this.key;
	}

	/**
	 * Get the value for our usage.
	 * 
	 * @return the value stored in this pair.
	 */
	public ValueType getValue() {
		return this.value;
	}
	
	@Override
	public String toString() {
		return getKey() + "=" + getValue();
	}
		
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		
		if (obj instanceof Pair) {
			Pair<?, ?> toCompare = (Pair<?, ?>) obj;
			return (this.getKey().equals(toCompare.getKey()) && this.getValue().equals(toCompare.getValue()));
		}
		return super.equals(obj);
	}
}
