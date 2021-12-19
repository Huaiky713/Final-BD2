package ar.unrn.model;

public class Counter {
	private String _id;
	private int count = 0;

	public Counter(String _id, int count) {
		super();
		this._id = _id;
		this.count = count;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
