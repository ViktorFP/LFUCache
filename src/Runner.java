import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import by.epamlab.cache.LFUCache;

public class Runner {
	public static void main(String[] args) {
		final String[] data = { "a", "b", "c", "d", "c", "d" ,"c","c","c", "d", "d", "b", "b", "a", "a"};

		// ---set data to cache
		LFUCache cache = new LFUCache(4, 0.9f);
		for (String str : data) {
			cache.put(str);
		}
		// ---print all data---
		// Map<String, Integer> cacheData = cache.getCache();
		// for (Entry<String, Integer> entry : cacheData.entrySet()) {
		// System.out.println(entry.getKey()+" "+entry.getValue());
		// }
		List<String>[] arrayData = cache.getArray();
		int i = -1;
		for (List list : arrayData) {
			i++;
			for (Object str : list) {
				System.out.println(str + " " + i);
			}
		}
	}
}
