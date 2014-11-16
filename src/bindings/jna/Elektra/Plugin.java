package Elektra;

public interface Plugin {
	public int open(Key errorKey);
	public int get(KeySet ks, Key parentKey);
	public int set(KeySet ks, Key parentKey);
	public int error(KeySet ks, Key parentKey);
	public int close(Key parentKey);
}