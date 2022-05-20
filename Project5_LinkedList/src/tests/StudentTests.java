package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;

import org.junit.Test;

import listClasses.BasicLinkedList;
import listClasses.SortedLinkedList;

/**
 * 
 * You need student tests if you are looking for help during office hours about
 * bugs in your code.
 * 
 * @author UMCP CS Department
 *
 */
public class StudentTests {

	@Test
	public void test() {
		
		BasicLinkedList<String> list = new BasicLinkedList<String>();
        list.addToEnd("Hello");
        list.addToFront("Hi");
        list.addToEnd("Bye");
        list.addToFront("Begin");
        list.remove("Hi", String.CASE_INSENSITIVE_ORDER);
        list.addToFront("Take Care");
        list.addToFront("Baby");
        list.addToEnd("End");
        System.out.println("Retrieving last element");
        System.out.println(list.retrieveLastElement());
        System.out.println("\nLIST");
        BasicLinkedList<String> r = list.getReverseList();
        for (String s : r) {
        	System.out.println(s);
        }
        
        System.out.println("\nREAL ORDER");
        list.remove("Baby", String.CASE_INSENSITIVE_ORDER);
        list.remove("Bye", String.CASE_INSENSITIVE_ORDER);
        for (String each : list) {
        	System.out.println(each);
        }
         
        
	}
	
	@Test
	public void constructorTesting() {
		BasicLinkedList<String> list = new BasicLinkedList<String>();
		assertEquals(list.getFirst(),null);
		assertEquals(list.getLast(),null);
		assertEquals(list.getSize(),0);
		assertEquals(list.retrieveFirstElement(),null);
		assertEquals(list.retrieveLastElement(),null);
		BasicLinkedList<Integer> list1 = new BasicLinkedList<Integer>();
		assertEquals(list1.getFirst(),null);
		assertEquals(list1.getLast(),null);
		assertEquals(list1.getSize(),0);
		BasicLinkedList<Float> list2 = new BasicLinkedList<Float>();
		assertEquals(list2.getFirst(),null);
		assertEquals(list2.getLast(),null);
		assertEquals(list2.getSize(),0);
		
		
	}
	
	@Test
	public void adding() {
		BasicLinkedList<String> list = new BasicLinkedList<String>();
		list.addToEnd("Start");
		assertEquals(list.getFirst(),"Start");
		assertEquals(list.getLast(),"Start");
		assertEquals(list.getSize(),1);
		assertEquals(list.toString(),"Start");
		list.addToEnd("Start1");
		list.addToEnd("Start3");
        list.addToFront("Mid");
        //System.out.println(list.toString());
        assertEquals(list.toString(),"Mid Start Start1 Start3");
        list.addToEnd("End");
        assertEquals(list.getLast(),"End");
	}
	
	@Test
	public void retrieving() {
		BasicLinkedList<String> list = new BasicLinkedList<String>();
		list.addToEnd("Start");
		assertEquals(list.getFirst(),"Start");
		assertEquals(list.getLast(),"Start");
		assertEquals(list.getSize(),1);
		assertEquals(list.toString(),"Start");
		list.addToEnd("Start1");
		list.addToEnd("Start3");
        list.addToFront("Mid");
        //System.out.println(list.toString());
        assertEquals(list.toString(),"Mid Start Start1 Start3");
        list.addToEnd("End");
        assertEquals(list.getLast(),"End");
        //Retrieving First Value
        assertEquals("First Value: ", list.retrieveFirstElement(),"Mid");
        //Printing out list 
        assertEquals("Printing List", list.toString(),"Start Start1 Start3 End");
        list.addToEnd("Ending");
        //System.out.println(list.toString());
        assertEquals("Removing Last",list.retrieveLastElement(),"Ending");
        assertEquals("List: ",list.toString(),"Start Start1 Start3 End");
        
        BasicLinkedList<Integer> intList = new BasicLinkedList<Integer>();
        intList.addToFront(0);
        intList.addToEnd(1);
        System.out.println(intList.toString());
        System.out.println(intList.getFirst());
        System.out.println(intList.getLast());
        //assertEquals(intList.getFirst(),(int) 0);
        
        SortedLinkedList<String> sorted = new SortedLinkedList<String>(String.CASE_INSENSITIVE_ORDER);
		sorted.add("abel");
		sorted.add("amen");
		sorted.add("aah");
		sorted.add("baby");
		sorted.add("test");
		sorted.add("aaah");
		//System.out.println(sorted);
		assertEquals(sorted.toString(),"aaah aah abel amen baby test");
		
		

	}
	
	@Test 
	public void removing() {
		BasicLinkedList<String> list = new BasicLinkedList<String>();
		list.addToEnd("Start");
		list.addToEnd("Start1");
		list.addToEnd("Start3");
        list.addToFront("Mid");
        list.addToEnd("End");
        //list.remove("Mid", String.CASE_INSENSITIVE_ORDER);
        list.remove("Start3", String.CASE_INSENSITIVE_ORDER);
        list.remove("End",String.CASE_INSENSITIVE_ORDER);
        //System.out.println(list.toString());
        assertEquals(list.toString(),"Mid Start Start1");
        //THE following element is not present in the list
        list.remove("Test",String.CASE_INSENSITIVE_ORDER);
        IntegerComparator i = new IntegerComparator();
        BasicLinkedList<Integer> intList = new BasicLinkedList<Integer>();
        intList.addToEnd(1);
        intList.addToEnd(2);
		intList.addToEnd(3);
        intList.addToFront(0);
        intList.addToEnd(4);
        intList.remove(0, i);
        assertEquals(intList.toString(),"1 2 3 4");
        intList.remove(4, i);
        assertEquals(intList.toString(),"1 2 3");
        
        BasicLinkedList<String> alist = new BasicLinkedList<String>();
        alist.addToEnd("a");
        alist.addToEnd("b");
        alist.addToEnd("c");
        alist.addToFront("d");
        alist.addToEnd("a");
        alist.addToEnd("a");
        System.out.println(alist.toString());
        alist.remove("a", String.CASE_INSENSITIVE_ORDER);
        System.out.println(alist.toString());
        
        
        
        SortedLinkedList<String> sorted = new SortedLinkedList<String>(String.CASE_INSENSITIVE_ORDER);
		sorted.add("abel");
		sorted.add("amen");
		sorted.add("aah");
		sorted.add("baby");
		sorted.add("test");
		sorted.add("zebra");
		sorted.add("aaah");
		assertEquals(sorted.toString(),"aaah aah abel amen baby test zebra");
		sorted.remove("baby");
		//System.out.println(sorted.toString());
		assertEquals(sorted.toString(),"aaah aah abel amen test zebra");
		sorted.remove("test");
		//System.out.println(sorted.toString());
		assertEquals(sorted.toString(),"aaah aah abel amen zebra");
		sorted.remove("zebra");
		//System.out.println(sorted.toString());
		assertEquals(sorted.toString(),"aaah aah abel amen");
		sorted.remove("aaah");
		//System.out.println(sorted.toString());
		assertEquals(sorted.toString(),"aah abel amen");
		sorted.remove("amen");
		assertEquals(sorted.toString(),"aah abel");
		sorted.remove("aah");
		assertEquals(sorted.toString(),"abel");  
	}
	
	@Test
	public void reverseArrayList() {
		BasicLinkedList<String> list = new BasicLinkedList<String>();
		list.addToEnd("Start");
		list.addToEnd("Start1");
		list.addToEnd("Start3");
        list.addToFront("Mid");
        list.addToEnd("End");
        ArrayList<String> reversed = new ArrayList<String>();
        reversed = list.getReverseArrayList();
        System.out.println(reversed.toString());
        assertEquals(reversed.toString(),"[End, Start3, Start1, Start, Mid]");
        list.addToFront("Alahabad");
        reversed = list.getReverseArrayList();
        System.out.println(reversed.toString());
        assertEquals(reversed.toString(),"[End, Start3, Start1, Start, Mid, Alahabad]");
        
        SortedLinkedList<String>sorted = new SortedLinkedList<String>(String.CASE_INSENSITIVE_ORDER);
        sorted.add("a");
        sorted.add("b");
        sorted.add("c");
        sorted.add("ab");
        sorted.add("bc");
        sorted.add("ca");
        sorted.remove("c");
        assertEquals(sorted.toString(),"a ab b bc ca");
        assertEquals(sorted.getReverseArrayList().toString(),"[ca, bc, b, ab, a]");
        //SortedLinkedList<String>reverseSorted = (SortedLinkedList<String>) sorted.getReverseList();
        //System.out.println(reverseSorted.toString());
        //assertEquals(reverseSorted.toString(),"ca c bc b ab a");
        
        IntegerComparator i = new IntegerComparator();
        SortedLinkedList<Integer>sortedInt = new SortedLinkedList<Integer>(i);
        sortedInt.add(10);
        sortedInt.add(54);
        sortedInt.add(45);
        sortedInt.add(32);
        sortedInt.add(0);
        sortedInt.add(10);
        sortedInt.remove(32);
        assertEquals(sortedInt.toString(),"0 10 10 45 54");
        assertEquals(sortedInt.getReverseArrayList().toString(),"[54, 45, 10, 10, 0]");
	}
	
	@Test
	public void getReverseTesting() {
		SortedLinkedList<String>list = new SortedLinkedList<String>(String.CASE_INSENSITIVE_ORDER);
		list.add("ending");
		list.add("beginning");
		list.add("end");
		list.add("funny");
		list.add("abel");
		list.remove("funny");
		assertThrows(UnsupportedOperationException.class, () -> { list.addToFront("hello");} );
		assertEquals(list.toString(), "abel beginning end ending");
		assertEquals(list.getReverseList().toString(),"ending end beginning abel");
		
		BasicLinkedList<Integer> intList = new BasicLinkedList<Integer>();
		intList.addToEnd(0);
		intList.addToEnd(10);
		intList.addToEnd(35);
		assertEquals((int)intList.retrieveFirstElement(),0);
		assertEquals(intList.getReverseList().toString(),"35 10");
	}
	
	@Test 
	public void ExceptionTest() {
		SortedLinkedList<String>list = new SortedLinkedList<String>(String.CASE_INSENSITIVE_ORDER);
		list.add("ending");
		list.add("beginning");
		list.add("end");
		list.add("funny");
		list.add("abel");
		list.remove("funny");
		assertThrows(UnsupportedOperationException.class, () -> { list.addToFront("hello");} );
		assertThrows(UnsupportedOperationException.class, () -> { list.addToEnd("hey");} );

	}
	
	@Test
	public void Iterator() {
		SortedLinkedList<String>list = new SortedLinkedList<String>(String.CASE_INSENSITIVE_ORDER);
		list.add("ending");
		list.add("beginning");
		list.add("end");
		list.add("funny");
		list.add("abel");
		list.remove("funny");
		String result="";
		java.util.Iterator<String> it = list.iterator();
		while(it.hasNext()) {
	         Object element = it.next();
	         result+=element+" ";
	    }
		assertEquals(result,"abel beginning end ending ");
		
		BasicLinkedList<String> alist = new BasicLinkedList<String>();
        alist.addToEnd("a");
        alist.addToEnd("b");
        alist.addToEnd("c");
        alist.addToFront("d");
        alist.addToEnd("a");
        alist.addToEnd("a");
        String result1="";
		java.util.Iterator<String> it1 = alist.iterator();
		while(it1.hasNext()) {
	         Object element1 = it1.next();
	         result1+=element1+" ";
	    }
		assertEquals(result1,"d a b c a a ");
		
		BasicLinkedList<Integer> ilist = new BasicLinkedList<Integer>();
        ilist.addToEnd(1);
        ilist.addToEnd(2);
        ilist.addToEnd(3);
        ilist.addToFront(45);
        ilist.addToEnd(100);
        ilist.addToEnd(1355);
        String result2="";
		java.util.Iterator<Integer> it2 = ilist.iterator();
		while(it2.hasNext()) {
	         Object element2 = it2.next();
	         result2+=element2+" ";
	    }
	
		assertEquals(result2,"45 1 2 3 100 1355 ");	
	}
	
	public class IntegerComparator implements Comparator<Integer> {
		@Override
		public int compare(Integer o1, Integer o2) {
			// TODO Auto-generated method stub
			if (o1>o2) {
				return 1;
			}
			else if (o1<o2) {
				return -1;
			}
			else {
				return 0;
			}
		}
			
	}
	
	



}
