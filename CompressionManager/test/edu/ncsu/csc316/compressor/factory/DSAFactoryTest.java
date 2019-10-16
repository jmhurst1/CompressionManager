/**
 * 
 */
package edu.ncsu.csc316.compressor.factory;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.list.ArrayBasedList;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.SkipListMap;
import edu.ncsu.csc316.dsa.map.UnorderedArrayMap;
import edu.ncsu.csc316.dsa.queue.ArrayBasedQueue;
import edu.ncsu.csc316.dsa.queue.Queue;
import edu.ncsu.csc316.dsa.sorter.MergeSorter;
import edu.ncsu.csc316.dsa.sorter.RadixSorter;
import edu.ncsu.csc316.dsa.sorter.Sorter;
import edu.ncsu.csc316.dsa.stack.LinkedStack;
import edu.ncsu.csc316.dsa.stack.Stack;

/**
 * Tests DSAFactory
 * @author Jason
 *
 */
public class DSAFactoryTest {

	/**
	 * Sets up for the tests
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.compressor.factory.DSAFactory#getUnorderedMap()}.
	 */
	@Test
	public void testGetUnorderedMap() {
		Map<String, String> m = DSAFactory.getUnorderedMap();
		Map<String, String> m2 = new UnorderedArrayMap<String, String>();
		assertEquals(m.size(), 0);
		assertEquals(m.toString(), m2.toString());
		assertEquals(m.getClass(), m2.getClass());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.compressor.factory.DSAFactory#getOrderedMap()}.
	 */
	@Test
	public void testGetOrderedMap() {
		Map<String, String> m = DSAFactory.getOrderedMap();
		Map<String, String> m2 = new SkipListMap<String, String>();
		assertEquals(m.size(), 0);
		assertEquals(m.toString(), m2.toString());
		assertEquals(m.getClass(), m2.getClass());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.compressor.factory.DSAFactory#getIndexedList()}.
	 */
	@Test
	public void testGetIndexedList() {
		List<String> l = DSAFactory.getIndexedList();
		List<String> l2 = new ArrayBasedList<String>();
		assertEquals(l.size(), 0);
		assertEquals(l.getClass(), l2.getClass());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.compressor.factory.DSAFactory#getPositionalList()}.
	 */
	@Test
	public void testGetPositionalList() {
		PositionalList<String> l = DSAFactory.getPositionalList();
		PositionalList<String> l2 = new PositionalLinkedList<String>();
		assertEquals(l.size(), 0);
		assertEquals(l.getClass(), l2.getClass());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.compressor.factory.DSAFactory#getComparisonSorter()}.
	 */
	@Test
	public void testGetComparisonSorter() {
		Sorter<String> s = DSAFactory.getComparisonSorter();
		Sorter<String> s2 = new MergeSorter<String>();
		assertEquals(s.getClass(), s2.getClass());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.compressor.factory.DSAFactory#getNonComparisonSorter()}.
	 */
	@Test
	public void testGetNonComparisonSorter() {
		Sorter<Student> s = DSAFactory.getNonComparisonSorter();
		Sorter<Student> s2 = new RadixSorter<Student>();
		assertEquals(s.getClass(), s2.getClass());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.compressor.factory.DSAFactory#getStack()}.
	 */
	@Test
	public void testGetStack() {
		Stack<String> s = DSAFactory.getStack();
		Stack<String> s2 = new LinkedStack<String>();
		assertEquals(s.size(), 0);
		assertEquals(s.getClass(), s2.getClass());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.compressor.factory.DSAFactory#getQueue()}.
	 */
	@Test
	public void testGetQueue() {
		Queue<String> q = DSAFactory.getQueue();
		Queue<String> q2 = new ArrayBasedQueue<String>();
		assertEquals(q.size(), 0);
		assertEquals(q.getClass(), q2.getClass());
	}

}
