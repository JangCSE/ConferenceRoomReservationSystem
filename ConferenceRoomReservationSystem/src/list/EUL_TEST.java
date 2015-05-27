package list;

import junit.framework.TestCase;

import org.junit.Test;

import user.EPuser;

public class EUL_TEST extends TestCase {

	@Test
	public void testAdd() {
		EnterpriseUserList EUL = new EnterpriseUserList();
		EPuser temp = new EPuser("id", "password", "name", "email", "contact");
		EUL.add(temp);

		assertEquals(temp, EUL.getList().get(0));
	}

	@Test
	public void testFindByID() {
		EnterpriseUserList EUL = new EnterpriseUserList();
		EPuser temp = new EPuser("id", "password", "name", "email", "contact");
		EUL.add(temp);

		assertEquals(temp, EUL.findByID("id"));
	}

	@Test
	public void testFindByKey() {
		EnterpriseUserList EUL = new EnterpriseUserList();
		EPuser temp = new EPuser("id", "password", "name", "email", "contact");
		temp.setKey(100);
		EUL.add(temp);

		assertEquals(temp, EUL.findByKey(100));
	}

	@Test
	public void testIsItDuplicated() {
		EnterpriseUserList EUL = new EnterpriseUserList();
		EPuser temp = new EPuser("id", "password", "name", "email", "contact");
		EUL.add(temp);
		
		assertEquals(true, EUL.isItDuplicated("id"));
		assertEquals(false, EUL.isItDuplicated("id2"));
	}

	@Test
	public void testDeleteByKey() {
		EnterpriseUserList EUL = new EnterpriseUserList();
		EPuser temp = new EPuser("id", "password", "name", "email", "contact");
		temp.setKey(100);
		EUL.add(temp);
		
		EUL.deleteByKey(1);
		assertEquals(true, EUL.isItDuplicated("id"));
		
		EUL.deleteByKey(100);
		assertEquals(false, EUL.isItDuplicated("id"));
	}

}