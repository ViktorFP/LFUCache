package by.epamlab.cache;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import by.epamlab.Constants;
import by.epamlab.exceptions.DataException;

public class LFUCache {
	private final int MAX_SIZE_CACHE;
	private final int DELETE_VALUE;
	private final int BUSKET_COUNT;

	private int count = 0;
	private Map<String, Integer> cache;
	private List<String>[] array;

	@SuppressWarnings("unchecked")
	public LFUCache(int maxSize, float evictionFactor) throws DataException {
		if (maxSize < 1) {
			throw new DataException(Constants.MAX_SIZE_EXCEPTION);
		}
		if (evictionFactor > 1 || evictionFactor <= 0) {
			throw new DataException(Constants.EVICTION_FACTOR_EXCEPTION);
		}

		MAX_SIZE_CACHE = maxSize;
		DELETE_VALUE = (int) (MAX_SIZE_CACHE * evictionFactor);
		cache = new HashMap<>();
		BUSKET_COUNT = MAX_SIZE_CACHE + 1;
		array = new List[BUSKET_COUNT];
		for (int i = 0; i < BUSKET_COUNT; i++) {
			array[i] = new LinkedList<String>();
		}
	}

	public void put(String entry) {
		int idx = 0;
		if (cache.containsKey(entry)) {
			idx = cache.get(entry);
			array[idx].remove(entry);
			count--;
			if (idx < BUSKET_COUNT - 1) {
				idx++;
			}
		}
		checkSize();
		cache.put(entry, idx);
		array[idx].add(entry);
		count++;
	}

	public List<String>[] getArray() {
		return array;
	}

	private void checkSize() {
		if (count == MAX_SIZE_CACHE) {
			int value = DELETE_VALUE;
			for (List<String> list : array) {
				Iterator<String> iterator = list.listIterator();
				while (iterator.hasNext()) {
					iterator.next();
					iterator.remove();
					value--;
					if (value == 0) {
						return;
					}
				}
			}
		}
	}
}
