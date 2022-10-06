package Helper;

/**
 * <p>
 * A tuple of two elements.
 * </p>
 *
 */
public final class Pair<KEY, VALUE> {

	/**
	 * 
	 */
	private final KEY key;
	private final VALUE value;

	/**
	 * <p>
	 * Constructs a new pair with specified values.
	 * </p>
	 * 
	 * @param <KEY>   the first value, we define it as a key of tuple.
	 * @param <VALUE> the second value, we define it as value of corresponding key
	 *                in tuple.
	 * 
	 */
	public Pair(final KEY key, final VALUE value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * 
	 * @return KEY
	 * 
	 */
	public KEY getKey() {
		return this.key;
	}

	/**
	 * 
	 * @return value of the KEY
	 * 
	 */
	public VALUE getValue() {
		return this.value;
	}

}