package com.example.iterable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Spliterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BagTest {

    @Test
    @DisplayName("New bag should be empty")
    void newBagShouldBeEmpty() {
        Bag<String> bag = new Bag<>();

        assertTrue(bag.isEmpty());
        assertEquals(0, bag.size());
        assertFalse(bag.contains("x"));
    }

    @Test
    @DisplayName("Add should increase size and store element")
    void addShouldIncreaseSizeAndStoreElement() {
        Bag<String> bag = new Bag<>();

        bag.add("apple");

        assertFalse(bag.isEmpty());
        assertEquals(1, bag.size());
        assertTrue(bag.contains("apple"));
    }

    @Test
    @DisplayName("Bag should allow duplicates")
    void bagShouldAllowDuplicates() {
        Bag<String> bag = new Bag<>();

        bag.add("apple");
        bag.add("apple");
        bag.add("apple");

        assertEquals(3, bag.size());
        assertTrue(bag.contains("apple"));
    }

    @Test
    @DisplayName("Remove should remove only one occurrence of a duplicate")
    void removeShouldRemoveOneOccurrenceOnly() {
        Bag<String> bag = new Bag<>();

        bag.add("apple");
        bag.add("apple");
        bag.add("banana");

        assertTrue(bag.remove("apple"));
        assertEquals(2, bag.size());
        assertTrue(bag.contains("apple")); // one apple should remain
        assertTrue(bag.contains("banana"));

        assertTrue(bag.remove("apple"));
        assertEquals(1, bag.size());
        assertFalse(bag.contains("apple"));
        assertTrue(bag.contains("banana"));
    }

    @Test
    @DisplayName("Remove should return false when item is not present")
    void removeShouldReturnFalseWhenItemNotPresent() {
        Bag<String> bag = new Bag<>();

        bag.add("apple");
        bag.add("banana");

        assertFalse(bag.remove("orange"));
        assertEquals(2, bag.size());
        assertTrue(bag.contains("apple"));
        assertTrue(bag.contains("banana"));
    }

    @Test
    @DisplayName("Remove from empty bag should return false")
    void removeFromEmptyBagShouldReturnFalse() {
        Bag<Integer> bag = new Bag<>();

        assertFalse(bag.remove(10));
        assertTrue(bag.isEmpty());
        assertEquals(0, bag.size());
    }

    @Test
    @DisplayName("Contains should work for present and absent values")
    void containsShouldWorkCorrectly() {
        Bag<Integer> bag = new Bag<>();

        bag.add(1);
        bag.add(2);
        bag.add(3);

        assertTrue(bag.contains(1));
        assertTrue(bag.contains(2));
        assertTrue(bag.contains(3));
        assertFalse(bag.contains(4));
    }

    @Test
    @DisplayName("Bag should allow null values")
    void bagShouldAllowNullValues() {
        Bag<String> bag = new Bag<>();

        bag.add(null);

        assertEquals(1, bag.size());
        assertTrue(bag.contains(null));
        assertFalse(bag.isEmpty());
    }

    @Test
    @DisplayName("Bag should allow duplicate null values")
    void bagShouldAllowDuplicateNullValues() {
        Bag<String> bag = new Bag<>();

        bag.add(null);
        bag.add(null);

        assertEquals(2, bag.size());
        assertTrue(bag.contains(null));

        assertTrue(bag.remove(null));
        assertEquals(1, bag.size());
        assertTrue(bag.contains(null));

        assertTrue(bag.remove(null));
        assertEquals(0, bag.size());
        assertFalse(bag.contains(null));
        assertTrue(bag.isEmpty());
    }

    @Test
    @DisplayName("Iterator on empty bag should have no next element")
    void iteratorOnEmptyBagShouldHaveNoNext() {
        Bag<String> bag = new Bag<>();
        Iterator<String> iterator = bag.iterator();

        assertFalse(iterator.hasNext());
        NoSuchElementException exception =
        assertThrows(NoSuchElementException.class, iterator::next);

assertNotNull(exception);
    }

    @Test
    @DisplayName("Iterator should traverse in ArrayList insertion order")
    void iteratorShouldTraverseInArrayListOrder() {
        Bag<String> bag = new Bag<>();
        bag.add("first");
        bag.add("second");
        bag.add("third");

        Iterator<String> iterator = bag.iterator();

        assertTrue(iterator.hasNext());
        assertEquals("first", iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals("second", iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals("third", iterator.next());

        assertFalse(iterator.hasNext());
        NoSuchElementException exception =
        assertThrows(NoSuchElementException.class, iterator::next);
assertNotNull(exception);}

    @Test
    @DisplayName("Iterator should include duplicate values")
    void iteratorShouldIncludeDuplicateValues() {
        Bag<String> bag = new Bag<>();
        bag.add("x");
        bag.add("x");
        bag.add("y");

        List<String> values = new ArrayList<>();
        for (String item : bag) {
            values.add(item);
        }

        assertEquals(List.of("x", "x", "y"), values);
    }

    @Test
    @DisplayName("Iterator should include null values")
    void iteratorShouldIncludeNullValues() {
        Bag<String> bag = new Bag<>();
        bag.add("a");
        bag.add(null);
        bag.add("b");

        List<String> values = new ArrayList<>();
        for (String item : bag) {
            values.add(item);
        }

        assertEquals(3, values.size());
        assertEquals("a", values.get(0));
        assertNull(values.get(1));
        assertEquals("b", values.get(2));
    }

    @Test
    @DisplayName("Multiple iterators should operate independently")
    void multipleIteratorsShouldOperateIndependently() {
        Bag<Integer> bag = new Bag<>();
        bag.add(10);
        bag.add(20);
        bag.add(30);

        Iterator<Integer> it1 = bag.iterator();
        Iterator<Integer> it2 = bag.iterator();

        assertEquals(10, it1.next());
        assertEquals(10, it2.next());

        assertEquals(20, it1.next());
        assertEquals(20, it2.next());

        assertEquals(30, it1.next());
        assertEquals(30, it2.next());

        assertFalse(it1.hasNext());
        assertFalse(it2.hasNext());
    }

    @Test
    @DisplayName("forEach should visit every element in order")
    void forEachShouldVisitEveryElementInOrder() {
        Bag<String> bag = new Bag<>();
        bag.add("red");
        bag.add("green");
        bag.add("blue");

        List<String> visited = new ArrayList<>();
        bag.forEach(visited::add);

        assertEquals(List.of("red", "green", "blue"), visited);
    }

    @Test
    @DisplayName("forEach should throw NullPointerException for null action")
    void forEachShouldThrowForNullAction() {
        Bag<String> bag = new Bag<>();
        bag.add("test");
        
        NullPointerException exception =
        assertThrows(NullPointerException.class, () -> bag.forEach(null));
        assertNotNull(exception);   
    }

    @Test
    @DisplayName("Spliterator should traverse all elements in order")
    void spliteratorShouldTraverseAllElementsInOrder() {
        Bag<Integer> bag = new Bag<>();
        bag.add(1);
        bag.add(2);
        bag.add(3);

        Spliterator<Integer> spliterator = bag.spliterator();
        List<Integer> values = new ArrayList<>();

        spliterator.forEachRemaining(values::add);

        assertEquals(List.of(1, 2, 3), values);
    }

    @Test
    @DisplayName("Size and isEmpty should stay consistent through operations")
    void sizeAndIsEmptyShouldStayConsistent() {
        Bag<String> bag = new Bag<>();

        assertTrue(bag.isEmpty());
        assertEquals(0, bag.size());

        bag.add("a");
        assertFalse(bag.isEmpty());
        assertEquals(1, bag.size());

        bag.add("b");
        assertFalse(bag.isEmpty());
        assertEquals(2, bag.size());

        assertTrue(bag.remove("a"));
        assertFalse(bag.isEmpty());
        assertEquals(1, bag.size());

        assertTrue(bag.remove("b"));
        assertTrue(bag.isEmpty());
        assertEquals(0, bag.size());
    }

    @Test
    @DisplayName("Remove should work correctly with custom object equality")
    void removeShouldUseEqualsSemantics() {
        Bag<String> bag = new Bag<>();
        String str1 = "hello";
        String str2 = new StringBuilder().append("hello").toString();// creates a new String object with same content

        bag.add(str1);
        assertTrue(bag.contains(str2));
        assertTrue(bag.remove(str2));

        assertFalse(bag.contains("hello"));
        assertEquals(0, bag.size());
    }

    @Test
    @DisplayName("Mixed sequence of operations should produce correct final state")
    void mixedOperationsShouldProduceCorrectFinalState() {
        Bag<Integer> bag = new Bag<>();

        bag.add(1);
        bag.add(2);
        bag.add(2);
        bag.add(3);

        assertEquals(4, bag.size());
        assertTrue(bag.contains(2));

        assertTrue(bag.remove(2));
        assertEquals(3, bag.size());
        assertTrue(bag.contains(2));

        assertTrue(bag.remove(2));
        assertEquals(2, bag.size());
        assertFalse(bag.contains(2));

        assertFalse(bag.remove(2));
        assertEquals(2, bag.size());

        List<Integer> values = new ArrayList<>();
        for (Integer value : bag) {
            values.add(value);
        }

        assertEquals(List.of(1, 3), values);
    }
}


