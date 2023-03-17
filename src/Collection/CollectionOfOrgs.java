package Collection;

import Organization.Organization;

import java.util.Iterator;
import java.util.Objects;
import java.util.Vector;

public class CollectionOfOrgs{

        private static Vector<Organization> organizationVector = new Vector<>();

    public static Vector<Organization> getOrganizationVector () {
        return organizationVector;
    }

    public static void setOrganizationVector (Vector <Organization> organizationVector) {
        CollectionOfOrgs.organizationVector = organizationVector;
    }
    public static boolean removeById(Long id){
        Iterator<Organization> iter = getOrganizationVector().iterator();
        int i = 0;
        while (iter.hasNext()) {
            if (Objects.equals(iter.next().getId(), id)) {
                iter.remove();
                i += 1;
                return true;
            }
        }
        if (i != 1) {
            return false;
        } return false;
    }

}
