package ua.training.util;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CollectionUtilTest {

    @Test
    public void shouldReturnFalseWhenCollectionIsNotEmpty() {
        // GIVEN
        Collection<String> fruits =
                Lists.newArrayList("Banana", "Apple", "Raspberry", "Orange");

        // WHEN
        boolean nullOrEmpty = CollectionUtil.isNullOrEmpty(fruits);

        // THEN
        assertFalse(nullOrEmpty);
    }

    @Test
    public void shouldReturnTrueWhenCollectionIsEmpty() {
        // GIVEN
        Collection collection = Lists.newArrayList();

        // WHEN
        boolean nullOrEmpty = CollectionUtil.isNullOrEmpty(collection);

        // THEN
        assertTrue(nullOrEmpty);
    }

    @Test
    public void shouldReturnTrueWhenCollectionIsNull() {
        // GIVEN
        Collection collection = null;

        // WHEN
        boolean nullOrEmpty = CollectionUtil.isNullOrEmpty(collection);

        // THEN
        assertTrue(nullOrEmpty);
    }

}