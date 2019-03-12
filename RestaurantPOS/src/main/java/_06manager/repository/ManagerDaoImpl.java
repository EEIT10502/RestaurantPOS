package _06manager.repository;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _00model.ManagerBean;


@Repository
public class ManagerDaoImpl implements ManagerDao{

	@Autowired
	SessionFactory factory;
	
	
	public ManagerDaoImpl() {
		
	}



	@Override
	public ManagerBean checkIDPassword(String mAccount, String mPwd) {
		
		ManagerBean managerBean =null;

		Session session = factory.getCurrentSession();
		String hql="FROM ManagerBean m WHERE m.account = :account and m.password = :password";
		try {
			managerBean = (ManagerBean) session.createQuery(hql)
									   .setParameter("account", mAccount)
									   .setParameter("password", mPwd)
									   .uniqueResult();
		} catch (NoResultException e) {
			e.printStackTrace();
			System.out.println("EmployeeDaoImpl checkIDPassword 錯誤!"+e.getMessage());
			managerBean = null;
		}

		return managerBean;
	}

}
