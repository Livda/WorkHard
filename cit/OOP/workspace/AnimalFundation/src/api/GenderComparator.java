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
public class GenderComparator implements Comparator<Animal> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Animal o1, Animal o2) {
		if (o1.getGender()){
			if (o2.getGender()){
				return o1.compareTo(o2);
			} else {
				return 1;
			}
		} else {
			if (o2.getGender()){
				return -1;
			} else {
				return o1.compareTo(o2);
			}
		}
	}

}
