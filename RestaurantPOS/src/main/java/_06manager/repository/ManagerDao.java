package _06manager.repository;

import _00model.ManagerBean;

public interface ManagerDao {

	public ManagerBean checkIDPassword(String mAccount,String mPwd);
}
