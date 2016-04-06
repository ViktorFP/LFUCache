import java.util.List;
import java.util.Map.Entry;

import by.epamlab.cache.LFUCache;
import by.epamlab.exceptions.DataException;

public class Runner {
	public static void main(String[] args) {
		// ---set data to cache
		LFUCache cache;
		try {
			cache = new LFUCache(4, 0.8f);
			final String[] data = { "a", "b", "c", "d", "c", "d", "c", "c",
					"c", "d", "d", "b", "b", "a", "a", "d", "c", "e" };
			for (String str : data) {
				cache.put(str);
			}
			// ---print all data---
			List<String>[] arrayData = cache.getArray();
			int i = -1;
			for (List<String> list : arrayData) {
				i++;
				for (Object str : list) {
					System.out.println(str + " " + i);
				}
			}
			
			System.out.println("--------------------------");
			for(Entry<String, Integer> entry:cache.getCache().entrySet()){
				System.out.println(entry.getKey()+" "+entry.getValue());
			}
		} catch (DataException e) {
			System.out.println(e.getMessage());
		}
	}
}
