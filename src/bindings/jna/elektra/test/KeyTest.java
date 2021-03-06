package elektra.test;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import elektra.Key;
import elektra.Key.KeyInvalidName;

public class KeyTest {
	
	static final String KEY_1_NAME = "/key_test/1/key_name";
	static final String KEY_1_NAME_PART_1 = "";
	static final String KEY_1_NAME_PART_2 = "key_test";
	static final String KEY_1_NAME_PART_3 = "1";
	static final String KEY_1_NAME_PART_4 = "key_name";
	static final String KEY_1_VALUE = "key_value_1";
	static final String KEY_1_META_1_NAME = "/key_test/meta/1";
	static final String KEY_1_META_1_VALUE = "meta_1_value";
	static final String KEY_1_META_2_NAME = "/key_test/meta/2";
	static final String KEY_1_META_2_VALUE = "meta_2_value";
	
	static final String KEY_2_NAME = "/key_test/2/key_name";
	static final String KEY_2_NAME_PART_1 = "";
	static final String KEY_2_NAME_PART_2 = "key_test";
	static final String KEY_2_NAME_PART_3 = "2";
	static final String KEY_2_NAME_PART_4 = "key_name";
	static final String KEY_2_VALUE = "false";

	static final String KEY_3_NAME = "/key_test/3/key_name";
	static final String KEY_3_VALUE = "1";

	static final String KEY_4_NAME = "/key_test/4/key_name";
	static final String KEY_4_VALUE = "32123";

	static final String KEY_5_NAME = "/key_test/5/key_name";
	static final String KEY_5_VALUE = "214748365";

	static final String KEY_6_NAME = "/key_test/6/key_name";
	static final String KEY_6_VALUE = "121424748365";

	static final String KEY_7_NAME = "/key_test/7/key_name";
	static final String KEY_7_VALUE = "121424748365123123123123123123354";

	static final String KEY_8_NAME = "/key_test/8/key_name";
	static final String KEY_8_VALUE = "123.456";

	static final String KEY_9_NAME = "/key_test/9/key_name";
	static final String KEY_9_VALUE = "12345.678911";
	
	static final String KEY_10_NAME = "/key_test/10/key_name";
	static final String KEY_10_VALUE = "blub";

	static final String KEY_11_NAME = "/key_test/10/key_name/sub";
	static final String KEY_11_VALUE = "blub2";
	
	static final String KEY_12_NAME = "/key_test/10/key_name/sub/1";
	static final String KEY_12_VALUE = "blub3";

	@Test
	public void test_createKey_shouldPass() {
		Key key = Key.create(KEY_1_NAME, Key.KEY_VALUE, KEY_1_VALUE, Key.KEY_END);
		assertEquals(KEY_1_NAME, key.toString());
		assertEquals(KEY_1_VALUE, key.getString());
	}
	
	@Test
	public void test_createKeyFromPointer_shouldPass() {
		Key key = Key.create(KEY_1_NAME, Key.KEY_VALUE, KEY_1_VALUE, Key.KEY_END);
		Key key2 = new Key(key.get());
		assertEquals(key.toString(), key2.toString());		// equal key name
		assertEquals(key.getString(), key2.getString());	// equal key value
		assertEquals(key.get(), key2.get());				// equal pointer
	}
	
	@Test
	public void test_keyNameIterator_shouldPass() {
		Key key = Key.create(KEY_1_NAME, Key.KEY_VALUE, KEY_1_VALUE, Key.KEY_END);
		Iterator<String> iterator = key.iterator();
		assertTrue(iterator.hasNext());
		assertEquals(KEY_1_NAME_PART_1, iterator.next());
		assertTrue(iterator.hasNext());
		assertEquals(KEY_1_NAME_PART_2, iterator.next());
		assertTrue(iterator.hasNext());
		assertEquals(KEY_1_NAME_PART_3, iterator.next());
		assertTrue(iterator.hasNext());
		assertEquals(KEY_1_NAME_PART_4, iterator.next());
		assertFalse(iterator.hasNext());
	}
	
	@Test
	public void test_keyWithBooleanValue_shouldPass() {
		Key key = Key.create(KEY_2_NAME, Key.KEY_VALUE, KEY_2_VALUE, Key.KEY_END);
		assertEquals(key.getBoolean(), false);
		key.setBoolean(true);
		assertEquals(key.getBoolean(), true);
		key.setString("false");
		assertEquals(key.getBoolean(), false);
		assertEquals(key.getString(), "false");
	}
	
	@Test
	public void test_keyWithByteValue_shouldPass() {
		Key key = Key.create(KEY_3_NAME, Key.KEY_VALUE, KEY_3_VALUE, Key.KEY_END);
		assertEquals(true, Byte.parseByte(KEY_3_VALUE) == key.getByte().byteValue());
	}
	
	@Test(expected = NumberFormatException.class)
	public void test_keyWithWrongByteValue_ShouldFail() {
		Key key = Key.create(KEY_4_NAME, Key.KEY_VALUE, KEY_4_VALUE, Key.KEY_END);
		// assert only to trigger key.getByte() function which throws exception
		assertEquals(false, Byte.parseByte(KEY_4_VALUE) == key.getByte().byteValue());
	}
	
	@Test
	public void test_keyWithShortValue_shouldPass() {
		Key key = Key.create(KEY_4_NAME, Key.KEY_VALUE, KEY_4_VALUE, Key.KEY_END);
		assertEquals(true, Short.parseShort(KEY_4_VALUE) == key.getShort().shortValue());
	}
	
	@Test(expected = NumberFormatException.class)
	public void test_keyWithTooBigShortValue_ShouldFail() {
		Key key = Key.create(KEY_5_NAME, Key.KEY_VALUE, KEY_5_VALUE, Key.KEY_END);
		// assert only to trigger key.getShort() function which throws exception
		assertEquals(false, Integer.parseInt(KEY_5_VALUE) == key.getShort().intValue());
	}
	
	@Test
	public void test_keyWithIntegerValue_shouldPass() {
		Key key = Key.create(KEY_5_NAME, Key.KEY_VALUE, KEY_5_VALUE, Key.KEY_END);
		assertEquals(true, Integer.parseInt(KEY_5_VALUE) == key.getInteger().intValue());
	}
	
	@Test(expected = NumberFormatException.class)
	public void test_keyWithTooBigIntegerValue_shouldFail() {
		Key key = Key.create(KEY_6_NAME, Key.KEY_VALUE, KEY_6_VALUE, Key.KEY_END);
		// assert only to trigger key.getInteger() function which throws exception
		assertEquals(false, Long.parseLong(KEY_6_VALUE) == key.getInteger().intValue());
	}
	
	@Test
	public void test_keyWithLongValue_shouldPass() {
		Key key = Key.create(KEY_6_NAME, Key.KEY_VALUE, KEY_6_VALUE, Key.KEY_END);
		assertEquals(true, Long.parseLong(KEY_6_VALUE) == key.getLong().longValue());
	}
	
	@Test(expected = NumberFormatException.class)
	public void test_keyWithTooBigLongValue_shouldFail() {
		Key key = Key.create(KEY_7_NAME, Key.KEY_VALUE, KEY_7_VALUE, Key.KEY_END);
		// assert only to trigger key.getLong() function which throws exception
		assertEquals(false, 1 == key.getLong().longValue());
	}
	
	@Test
	public void test_keyWithFloatValue_shouldPass() {
		Key key = Key.create(KEY_8_NAME, Key.KEY_VALUE, KEY_8_VALUE, Key.KEY_END);
		assertEquals(Float.parseFloat(KEY_8_VALUE), key.getFloat().floatValue(), 0.0f);
	}
	
	@Test
	public void test_keyWithDoubleValue_shouldPass() {
		Key key = Key.create(KEY_9_NAME, Key.KEY_VALUE, KEY_9_VALUE, Key.KEY_END);
		assertEquals(Double.parseDouble(KEY_9_VALUE), key.getDouble().doubleValue(), 0.0d);
	}
	
	@Test
	public void test_keyMetaInformation_shouldPass() {
		// setup key with meta
		Key key = Key.create(KEY_1_NAME, Key.KEY_VALUE, KEY_1_VALUE, Key.KEY_END);
		key.setMeta(KEY_1_META_1_NAME, KEY_1_META_1_VALUE);
		key.setMeta(KEY_1_META_2_NAME, KEY_1_META_2_VALUE);
		key.rewindMeta();
		
		// check meta
		Key meta_1 = key.currentMeta();
		assertEquals(KEY_1_META_1_NAME, meta_1.getName());
		assertEquals(KEY_1_META_1_VALUE, meta_1.getString());
		Key meta_2 = key.nextMeta();
		assertEquals(KEY_1_META_2_NAME, meta_2.getName());
		assertEquals(KEY_1_META_2_VALUE, meta_2.getString());
		
		// setup another key
		Key key2 = Key.create(KEY_2_NAME, Key.KEY_VALUE, KEY_2_VALUE, Key.KEY_END);
		key2.copyAllMeta(key);
		key2.rewindMeta();
		
		// check meta for second key
		Key meta_1_2 = key2.currentMeta();
		assertEquals(KEY_1_META_1_NAME, meta_1_2.getName());
		assertEquals(KEY_1_META_1_VALUE, meta_1_2.getString());
		Key meta_2_2 = key2.nextMeta();
		assertEquals(KEY_1_META_2_NAME, meta_2_2.getName());
		assertEquals(KEY_1_META_2_VALUE, meta_2_2.getString());
	}
	
	@Test
	public void test_keyCompare_shouldPass() {
		Key key = Key.create(KEY_1_NAME, Key.KEY_VALUE, KEY_1_VALUE, Key.KEY_END);
		Key key2 = Key.create(KEY_2_NAME, Key.KEY_VALUE, KEY_2_VALUE, Key.KEY_END);
		assertEquals(0, key.cmp(key));
		assertEquals(-1, key.cmp(key2));
		assertEquals(1, key2.cmp(key));
	}
	
	@Test
	public void test_keyRelation_shouldPass() {
		Key key = Key.create(KEY_10_NAME, Key.KEY_VALUE, KEY_10_VALUE, Key.KEY_END);
		Key key2 = Key.create(KEY_11_NAME, Key.KEY_VALUE, KEY_11_VALUE, Key.KEY_END);
		Key key3 = Key.create(KEY_12_NAME, Key.KEY_VALUE, KEY_12_VALUE, Key.KEY_END);
		assertEquals(0, key.rel(key));
		assertEquals(1, key.rel(key2));
		assertEquals(2, key.rel(key3));
		assertEquals(1, key2.rel(key3));
	}
	
	@Test
	public void test_keyIsBelow_shouldPass() {
		Key key = Key.create(KEY_10_NAME, Key.KEY_VALUE, KEY_10_VALUE, Key.KEY_END);
		Key key2 = Key.create(KEY_11_NAME, Key.KEY_VALUE, KEY_11_VALUE, Key.KEY_END);
		Key key3 = Key.create(KEY_12_NAME, Key.KEY_VALUE, KEY_12_VALUE, Key.KEY_END);
		assertEquals(true, key2.isBelow(key));
		assertEquals(true, key3.isBelow(key));
		assertEquals(true, key3.isBelow(key2));
		assertEquals(false, key.isBelow(key));
	}
	
	@Test
	public void test_keyIsBelowOrSame_shouldPass() {
		Key key = Key.create(KEY_10_NAME, Key.KEY_VALUE, KEY_10_VALUE, Key.KEY_END);
		Key key2 = Key.create(KEY_11_NAME, Key.KEY_VALUE, KEY_11_VALUE, Key.KEY_END);
		Key key3 = Key.create(KEY_12_NAME, Key.KEY_VALUE, KEY_12_VALUE, Key.KEY_END);
		assertEquals(true, key.isBelowOrSame(key));
		assertEquals(true, key2.isBelowOrSame(key));
		assertEquals(true, key2.isBelowOrSame(key2));
		assertEquals(true, key3.isBelowOrSame(key));
		assertEquals(true, key3.isBelowOrSame(key2));
		assertEquals(true, key3.isBelowOrSame(key3));
	}
	
	@Test
	public void test_keyIsDirectBelow_shouldPass() {
		Key key = Key.create(KEY_10_NAME, Key.KEY_VALUE, KEY_10_VALUE, Key.KEY_END);
		Key key2 = Key.create(KEY_11_NAME, Key.KEY_VALUE, KEY_11_VALUE, Key.KEY_END);
		Key key3 = Key.create(KEY_12_NAME, Key.KEY_VALUE, KEY_12_VALUE, Key.KEY_END);
		assertEquals(false, key.isDirectBelow(key));
		assertEquals(true, key2.isDirectBelow(key));
		assertEquals(false, key2.isDirectBelow(key2));
		assertEquals(false, key2.isDirectBelow(key3));
		assertEquals(true, key3.isDirectBelow(key2));
		assertEquals(false, key3.isDirectBelow(key));
	}
	
	@Test
	public void test_keyGetNameSize_shouldPass() {
		Key key = Key.create(KEY_1_NAME, Key.KEY_VALUE, KEY_1_VALUE, Key.KEY_END);
		Key key2 = Key.create(KEY_2_NAME, Key.KEY_VALUE, KEY_2_VALUE, Key.KEY_END);
		Key key3 = Key.create(KEY_3_NAME, Key.KEY_VALUE, KEY_3_VALUE, Key.KEY_END);
		assertEquals(KEY_1_NAME.length()+1, key.getNameSize());
		assertEquals(KEY_2_NAME.length()+1, key.getNameSize());
		assertEquals(KEY_3_NAME.length()+1, key.getNameSize());
	}
	
	@Test
	public void test_keySetName_shouldPass() {
		String new_keyname = "/some_random/test/stuff_or/whatever";
		Key key = Key.create(KEY_1_NAME, Key.KEY_VALUE, KEY_1_VALUE, Key.KEY_END);
		key.setName(new_keyname);
		assertEquals(new_keyname, key.getName());
	}
	
	@Test(expected = KeyInvalidName.class)
	public void test_keySetName_shouldFail() {
		String new_keyname = "some_random/test/stuff_or/whatever"; // initial slash missing
		Key key = Key.create(KEY_1_NAME, Key.KEY_VALUE, KEY_1_VALUE, Key.KEY_END);
		key.setName(new_keyname);
		assertEquals(new_keyname, key.getName());
	}
	
	@Test
	public void test_keyGetBaseName_shouldPass() {
		Key key = Key.create(KEY_1_NAME, Key.KEY_VALUE, KEY_1_VALUE, Key.KEY_END);
		Key key2 = Key.create(KEY_2_NAME, Key.KEY_VALUE, KEY_2_VALUE, Key.KEY_END);
		assertEquals(KEY_1_NAME_PART_4, key.getBaseName());
		assertEquals(KEY_2_NAME_PART_4, key2.getBaseName());
	}
	
	@Test
	public void test_keySetBaseName_shouldPass() {
		// note: slashes in basename will be escaped
		String new_basename = "/some_random/string";
		Key key = Key.create(KEY_1_NAME, Key.KEY_VALUE, KEY_1_VALUE, Key.KEY_END);
		key.setBaseName(new_basename);
		assertEquals(new_basename, key.getBaseName());
	}
	
	@Test
	public void test_keyAddBaseName_shouldPass() {
		// note: slashes in basename will be escaped
		String new_basename = "/some_random/string";
		String new_basename2 = "another_new/nice/basename";
		Key key = Key.create(KEY_1_NAME, Key.KEY_VALUE, KEY_1_VALUE, Key.KEY_END);
		key.addBaseName(new_basename);
		assertEquals(new_basename, key.getBaseName());
		key.addBaseName(new_basename2);
		assertEquals(new_basename2, key.getBaseName());
	}
	
	@Test
	public void test_keyGetValueSize_shouldPass() {
		Key key = Key.create(KEY_1_NAME, Key.KEY_VALUE, KEY_1_VALUE, Key.KEY_END);
		Key key2 = Key.create(KEY_2_NAME, Key.KEY_VALUE, KEY_2_VALUE, Key.KEY_END);
		Key key3 = Key.create(KEY_3_NAME, Key.KEY_VALUE, KEY_3_VALUE, Key.KEY_END);
		assertEquals(KEY_1_VALUE.length()+1, key.getValueSize());
		assertEquals(KEY_2_VALUE.length()+1, key2.getValueSize());
		assertEquals(KEY_3_VALUE.length()+1, key3.getValueSize());
	}
	
	@Test
	public void test_keyGetSetString_shouldPass() {
		String new_string = "some_random new key value.blub";
		Key key = Key.create(KEY_1_NAME, Key.KEY_VALUE, KEY_1_VALUE, Key.KEY_END);
		assertEquals(KEY_1_VALUE, key.getString());
		key.setString(new_string);
		assertEquals(new_string, key.getString());
	}

}
