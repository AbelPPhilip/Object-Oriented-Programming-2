package tests;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import implementation.BinarySearchTree;
import implementation.TreeIsEmptyException;
import implementation.TreeIsFullException;

/* The following directive executes tests in sorted order */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class StudentTests {
	
	/* Remove the following test and add your tests */
	@Test
	public void test1() throws TreeIsEmptyException {
		System.out.println("======== Marker #1 ========");
		Comparator<String> comparator = String.CASE_INSENSITIVE_ORDER;
		int maxEntries = 5;
		BinarySearchTree<String, Integer> bst = new BinarySearchTree<String, Integer>(comparator, maxEntries);
		System.out.println(bst);
		System.out.println("Empty Tree?: " + bst.isEmpty());
		System.out.println("\n======== Marker #2 ========");
		try {
			bst.add("Oliver", 1000).add("Arlene", 50000).add("Terry", 60).add("Ann",20).add("Zladin", 40).add("Bob",20).add("Ann",30);
		} catch (TreeIsFullException e) {
			System.out.println("full tree");
		}
		System.out.println(bst);
		System.out.println("Full Tree?: " + bst.isFull());
		System.out.println("Size: " + bst.size());
		bst.delete("Terry");
		bst.delete("Bob");
		System.out.println(bst);

	}
}
