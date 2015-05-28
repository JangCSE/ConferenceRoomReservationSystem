package list;

import static org.junit.Assert.*;

import org.junit.Test;

import user.NMuser;

public class NUL_TEST {

	@Test
	public void testAdd() {
		NormalUserList NUL = new NormalUserList();
		NMuser temp = new NMuser("id", "password", "name", "email", "contact");
		NUL.add(temp);

		assertEquals(temp, NUL.getList().get(0));
	}

	@Test
	public void testFindByID() {
		NormalUserList NUL = new NormalUserList();
		NMuser temp = new NMuser("id", "password", "name", "email", "contact");
		NUL.add(temp);

		assertEquals(temp, NUL.findByID("id"));
	}

	@Test
	public void testFindByKey() {
		NormalUserList NUL = new NormalUserList();
		NMuser temp = new NMuser("id", "password", "name", "email", "contact");
		temp.setKey(100);
		NUL.add(temp);

		assertEquals(temp, NUL.findByKey(100));
	}

	@Test
	public void testIsItDuplicated() {
		NormalUserList NUL = new NormalUserList();
		NMuser temp = new NMuser("id", "password", "name", "email", "contact");
		NUL.add(temp);
		
		assertEquals(true, NUL.isItDuplicated("id"));
		assertEquals(false, NUL.isItDuplicated("id2"));
	}

	@Test
	public void testDeleteByKey() {
		NormalUserList NUL = new NormalUserList();
		NMuser temp = new NMuser("id", "password", "name", "email", "contact");
		temp.setKey(100);
		NUL.add(temp);
		
		NUL.deleteByKey(1);
		assertEquals(true, NUL.isItDuplicated("id"));
		
		NUL.deleteByKey(100);
		assertEquals(false, NUL.isItDuplicated("id"));
	}

}
