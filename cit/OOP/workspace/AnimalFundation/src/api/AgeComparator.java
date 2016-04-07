/**
 *
 */
package api;

import java.util.Comparator;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 *
 */
public class AgeComparator implements Comparator<Animal> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Animal o1, Animal o2) {
		int diff = o1.getAge() - o2.getAge();
		if (diff > 0) {
			return -1;
		}
		else if (diff < 0){
			return 1;
		}
		else return o1.compareTo(o2);
	}

}
