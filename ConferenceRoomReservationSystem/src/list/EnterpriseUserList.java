package list;

import java.util.Iterator;

import user.EPuser;

public class EnterpriseUserList extends GenericList<EPuser> {

	public EnterpriseUserList() {

	}

	@Override
	public void deleteByKey(int k) {
		Iterator<EPuser> itr = this.getList().iterator();
		EPuser temp;

		while (itr.hasNext()) {
			temp = itr.next();

			if (temp.getKey() == k) {
				itr.remove();
				break;
			}
		}
	}

	@Override
	public EPuser findByID(String ID) {
		Iterator<EPuser> itr = this.getList().iterator();
		EPuser temp;

		while (itr.hasNext()) {
			temp = itr.next();

			if (temp.getId().equals(ID)) {
				return temp;
			}
		}

		return null;
	}

	@Override
	public EPuser findByKey(int k) {
		Iterator<EPuser> itr = this.getList().iterator();
		EPuser temp;

		while (itr.hasNext()) {
			temp = itr.next();

			if (temp.getKey() == k) {
				return temp;
			}
		}

		return null;
	}

	@Override
	public boolean isItDuplicated(String id) {
		// search by id
		EPuser temp = findByID(id);

		return temp != null;
	}

}