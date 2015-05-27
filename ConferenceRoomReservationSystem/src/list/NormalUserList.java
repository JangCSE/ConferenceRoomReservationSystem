package list;

import java.util.Iterator;

import user.NMuser;

public class NormalUserList extends GenericList<NMuser> {

	public NormalUserList() {
		
	}
	
	@Override
	public void deleteByKey(int k) {
		Iterator<NMuser> itr = this.getList().iterator();
		NMuser temp;

		while (itr.hasNext()) {
			temp = itr.next();

			if (temp.getKey() == k) {
				itr.remove();
				break;
			}
		}
	}

	@Override
	public NMuser findByID(String ID) {
		Iterator<NMuser> itr = this.getList().iterator();
		NMuser temp;

		while (itr.hasNext()) {
			temp = itr.next();

			if (temp.getId().equals(ID)) {
				return temp;
			}
		}

		return null;
	}

	@Override
	public NMuser findByKey(int k) {
		Iterator<NMuser> itr = this.getList().iterator();
		NMuser temp;

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
		NMuser temp = findByID(id);

		if (temp != null)
			return true;
		else
			return false;
	}

}