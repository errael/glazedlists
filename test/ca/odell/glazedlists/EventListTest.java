/**
 * Glazed Lists
 * http://glazedlists.dev.java.net/
 *
 * COPYRIGHT 2003 O'DELL ENGINEERING LTD.
 */
package ca.odell.glazedlists;

// the Glazed Lists
import ca.odell.glazedlists.event.*;
// for being a JUnit test case
import junit.framework.*;
// standard collections
import java.util.*;

/**
 * Verifies that EventList matches the List API.
 *
 * @author <a href="mailto:jesse@odel.on.ca">Jesse Wilson</a>
 */
public class EventListTest extends TestCase {

    /**
     * Prepare for the test.
     */
    public void setUp() {
    }

    /**
     * Clean up after the test.
     */
    public void tearDown() {
    }

    /**
     * Validates that removeAll() works.
     *
     * @see <a href="https://glazedlists.dev.java.net/issues/show_bug.cgi?id=169">Bug 169</a>
     */
    public void testRemoveAll() {
        List jesse = new ArrayList(); jesse.addAll(Arrays.asList(new Character[] { new Character('J'), new Character('E'), new Character('S'), new Character('S'), new Character('E') }));
        List wilson = Arrays.asList(new Character[] { new Character('W'), new Character('I'), new Character('L'), new Character('S'), new Character('O'), new Character('N') });

        // create the reference list
        List jesseArrayList = new ArrayList();
        jesseArrayList.addAll(jesse);
        jesseArrayList.removeAll(wilson);
        
        // test the BasicEventList list
        List jesseBasicEventList = new BasicEventList();
        jesseBasicEventList.addAll(jesse);
        jesseBasicEventList.removeAll(wilson);
        assertEquals(jesseArrayList, jesseBasicEventList);
        
        // test the SortedList list
        List jesseSortedList = new SortedList(new BasicEventList(), null);
        jesseSortedList.addAll(jesse);
        jesseSortedList.removeAll(wilson);
        assertEquals(jesseArrayList, jesseSortedList);
    }

    /**
     * Validates that retainAll() works.
     */
    public void testRetainAll() {
        List jesse = new ArrayList(); jesse.addAll(Arrays.asList(new Character[] { new Character('J'), new Character('E'), new Character('S'), new Character('S'), new Character('E') }));
        List wilson = Arrays.asList(new Character[] { new Character('W'), new Character('I'), new Character('L'), new Character('S'), new Character('O'), new Character('N') });

        // create the reference list
        List jesseArrayList = new ArrayList();
        jesseArrayList.addAll(jesse);
        jesseArrayList.retainAll(wilson);
        
        // test the BasicEventList list
        List jesseBasicEventList = new BasicEventList();
        jesseBasicEventList.addAll(jesse);
        jesseBasicEventList.retainAll(wilson);
        assertEquals(jesseArrayList, jesseBasicEventList);
        
        // test the SortedList list
        List jesseSortedList = new SortedList(new BasicEventList(), null);
        jesseSortedList.addAll(jesse);
        jesseSortedList.retainAll(wilson);
        assertEquals(jesseArrayList, jesseSortedList);
    }

    /**
     * Validates that contains() works with null.
     */
    public void testContainsNull() {
        // get all different list types
        List listTypes = new ArrayList();
        listTypes.add(new ArrayList());
        listTypes.add(new BasicEventList());
        listTypes.add(new SortedList(new BasicEventList()));
        
        // test all different list types
        for(Iterator i = listTypes.iterator(); i.hasNext(); ) {
            List list = (List)i.next();
            
            // test a list that doesn't contain nulls
            list.clear();
            list.addAll(Arrays.asList(new String[] { "Molson", "Sleeman", "Labatts", "Western" }));
            assertEquals(false, list.contains(null));
            assertEquals(true,  list.contains("Western"));
            
            // test a list that does contain nulls
            list.clear();
            list.addAll(Arrays.asList(new String[] { null, "Sleeman", null, "Western" }));
            assertEquals(true, list.contains(null));
            assertEquals(true, list.contains("Western"));
            assertEquals(false, list.contains("Molson"));
        }
    }

    /**
     * Validates that containsAll() works with null.
     */
    public void testContainsAllNull() {
        // get all different list types
        List listTypes = new ArrayList();
        listTypes.add(new ArrayList());
        listTypes.add(new BasicEventList());
        listTypes.add(new SortedList(new BasicEventList()));
        
        // test all different list types
        for(Iterator i = listTypes.iterator(); i.hasNext(); ) {
            List list = (List)i.next();
            
            // test a list that doesn't contain nulls
            list.clear();
            list.addAll(Arrays.asList(new String[] { "Molson", "Sleeman", "Labatts", "Western" }));
            assertEquals(true, list.containsAll(Arrays.asList(new String[] { "Sleeman", "Molson" })));
            assertEquals(false, list.containsAll(Arrays.asList(new String[] { "Molson", null })));
            assertEquals(false, list.containsAll(Arrays.asList(new String[] { "Molson", "Busch" })));
            
            // test a list that does contain nulls
            list.clear();
            list.addAll(Arrays.asList(new String[] { null, "Sleeman", null, "Western" }));
            assertEquals(false, list.containsAll(Arrays.asList(new String[] { "Sleeman", "Molson" })));
            assertEquals(true, list.containsAll(Arrays.asList(new String[] { "Sleeman", "Western" })));
            assertEquals(true, list.containsAll(Arrays.asList(new String[] { "Western", null })));
            assertEquals(true, list.containsAll(Arrays.asList(new String[] { null, null })));
        }
    }

    /**
     * Validates that indexOf() works with null.
     */
    public void testIndexOfNull() {
        // get all different list types
        List listTypes = new ArrayList();
        listTypes.add(new ArrayList());
        listTypes.add(new BasicEventList());
        listTypes.add(new SortedList(new BasicEventList()));
        
        // test all different list types
        for(Iterator i = listTypes.iterator(); i.hasNext(); ) {
            List list = (List)i.next();
            
            // test a list that doesn't contain nulls
            list.clear();
            list.addAll(Arrays.asList(new String[] { "Molson", "Sleeman", "Labatts", "Western" }));
            assertTrue(-1 == list.indexOf(null));
            assertTrue(-1 != list.indexOf("Western"));
            
            // test a list that does contain nulls
            list.clear();
            list.addAll(Arrays.asList(new String[] { null, "Sleeman", null, "Western" }));
            assertTrue(-1 != list.indexOf(null));
            assertTrue(-1 != list.indexOf("Western"));
            assertTrue(-1 == list.indexOf("Molson"));
        }
    }



    /**
     * Validates that lastIndexOf() works with null.
     */
    public void testLastIndexOfNull() {
        // get all different list types
        List listTypes = new ArrayList();
        listTypes.add(new ArrayList());
        listTypes.add(new BasicEventList());
        listTypes.add(new SortedList(new BasicEventList()));
        
        // test all different list types
        for(Iterator i = listTypes.iterator(); i.hasNext(); ) {
            List list = (List)i.next();
            
            // test a list that doesn't contain nulls
            list.clear();
            list.addAll(Arrays.asList(new String[] { "Molson", "Sleeman", "Labatts", "Western" }));
            assertTrue(-1 == list.lastIndexOf(null));
            assertTrue(-1 != list.lastIndexOf("Western"));
            
            // test a list that does contain nulls
            list.clear();
            list.addAll(Arrays.asList(new String[] { null, "Sleeman", null, "Western" }));
            assertTrue(-1 != list.lastIndexOf(null));
            assertTrue(-1 != list.lastIndexOf("Western"));
            assertTrue(-1 == list.lastIndexOf("Molson"));
        }
    }

    /**
     * Validates that remove() works with null.
     */
    public void testRemoveNull() {
        // get all different list types
        List listTypes = new ArrayList();
        listTypes.add(new ArrayList());
        listTypes.add(new BasicEventList());
        listTypes.add(new SortedList(new BasicEventList()));
        
        // test all different list types
        for(Iterator i = listTypes.iterator(); i.hasNext(); ) {
            List list = (List)i.next();
            
            // test a list that doesn't contain nulls
            list.clear();
            list.addAll(Arrays.asList(new String[] { "Molson", "Sleeman", "Labatts", "Western" }));
            assertEquals(false, list.remove(null));
            assertEquals(true,  list.remove("Sleeman"));
            
            // test a list that does contain nulls
            list.clear();
            list.addAll(Arrays.asList(new String[] { null, "Sleeman", null, "Western" }));
            assertEquals(true, list.remove(null));
            assertEquals(true, list.remove("Western"));
            assertEquals(false, list.remove("Molson"));
        }
    }

    /**
     * Validates that removeAll() works with null.
     */
    public void testRemoveAllNull() {
        // get all different list types
        List listTypes = new ArrayList();
        listTypes.add(new ArrayList());
        listTypes.add(new BasicEventList());
        listTypes.add(new SortedList(new BasicEventList()));
        
        // test all different list types
        for(Iterator i = listTypes.iterator(); i.hasNext(); ) {
            List list = (List)i.next();
            
            // test a list that doesn't contain nulls
            list.clear();
            list.addAll(Arrays.asList(new String[] { "Molson", "Sleeman", "Labatts", "Western" }));
            assertEquals(true, list.removeAll(Arrays.asList(new String[] { "Western", null })));
            assertEquals(false,  list.removeAll(Arrays.asList(new String[] { null, "Busch" })));
            
            // test a list that does contain nulls
            list.clear();
            list.addAll(Arrays.asList(new String[] { null, "Sleeman", null, "Western" }));
            assertEquals(true, list.removeAll(Arrays.asList(new String[] { "Western", "Busch" })));
            assertEquals(true, list.removeAll(Arrays.asList(new String[] { "Sleeman", null })));
            assertEquals(false, list.removeAll(Arrays.asList(new String[] { "Western", null })));
        }
    }

    /**
     * Validates that retainAll() works with null.
     */
    public void testRetainAllNull() {
        // get all different list types
        List listTypes = new ArrayList();
        listTypes.add(new ArrayList());
        listTypes.add(new BasicEventList());
        listTypes.add(new SortedList(new BasicEventList()));
        
        // test all different list types
        for(Iterator i = listTypes.iterator(); i.hasNext(); ) {
            List list = (List)i.next();
            
            // test a list that doesn't contain nulls
            list.clear();
            list.addAll(Arrays.asList(new String[] { "Molson", "Sleeman", "Labatts", "Western" }));
            assertEquals(true,  list.retainAll(Arrays.asList(new String[] { "Western", null })));
            assertEquals(true, list.retainAll(Arrays.asList(new String[] { "Moslon", null })));
            
            // test a list that does contain nulls
            list.clear();
            list.addAll(Arrays.asList(new String[] { null, "Sleeman", null, "Western" }));
            assertEquals(true,  list.retainAll(Arrays.asList(new String[] { "Western", null })));
            assertEquals(true, list.retainAll(Arrays.asList(new String[] { "Moslon", null })));
        }
    }


    /**
     * Validates that hashCode() works with null.
     */
    public void testHashCodeNull() {
        // get all different list types
        List listTypes = new ArrayList();
        listTypes.add(new ArrayList());
        listTypes.add(new BasicEventList());
        listTypes.add(new SortedList(new BasicEventList()));
        
        // test all different list types
        for(Iterator i = listTypes.iterator(); i.hasNext(); ) {
            List list = (List)i.next();
            List copy = new ArrayList();
            
            // test a list that doesn't contain nulls
            list.clear();
            copy.clear();
            list.addAll(Arrays.asList(new String[] { "Molson", "Sleeman", "Labatts", "Western" }));
            copy.addAll(list);
            assertEquals(copy.hashCode(), list.hashCode());
            assertTrue(list.equals(copy));
            copy.set(0, "Busch");
            assertFalse(list.equals(copy));
            
            // test a list that does contain nulls
            list.clear();
            copy.clear();
            list.addAll(Arrays.asList(new String[] { null, "Sleeman", null, "Western" }));
            copy.addAll(list);
            assertEquals(copy.hashCode(), list.hashCode());
            assertTrue(list.equals(copy));
            copy.set(0, "Busch");
            assertFalse(list.equals(copy));
        }
    }
}
