package by.epamlab.cache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LFUCache {
	private final int MAX_SIZE_CACHE;
	private final float EVICTION_FACTOR;
	private final int BUSKET_COUNT;

	private Map<String, Integer> cache;
	// private List<Double> frequence;//???
	private List<String>[] array;

	@SuppressWarnings("unchecked")
	public LFUCache(int maxSize, float evictionFactor) {
		MAX_SIZE_CACHE = maxSize;
		EVICTION_FACTOR = evictionFactor;

		cache = new HashMap<>();
		// frequence=new LinkedList<>();// в которой будут ее entries
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
			if (idx < BUSKET_COUNT - 1) {
				idx++;
			}
		}
		cache.put(entry, idx);
		array[idx].add(entry);
	}

	// public Map<String, Integer> getCache() {
	// return cache;
	// }
	public List<String>[] getArray() {
		return array;
	}
	// The number of entries deleted would be = max size * eviction factor.
}
