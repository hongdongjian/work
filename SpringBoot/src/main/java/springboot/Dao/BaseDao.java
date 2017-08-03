package springboot.Dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;


/**
 * 数据访问层的基本类
 * Created by lenovo on 2017/3/13.
 */
public class BaseDao<PK extends Serializable, T>{

    private final Class<T> baseClass;


    /**
     * 通过@Resource,默认按byName自动注入，从上下文找到唯一匹配的bean进行装配
     */
    @Resource
    protected SessionFactory sessionFactory;

    /**
     * 将baseClass初始为当前类
     */
    @SuppressWarnings("unchecked")
    protected BaseDao() {
        this.baseClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    /**
     * 从seeionfactory中获得session对象
     * opensession和getCurrentSession两种方法
     * getCurrentSession不需要手动释放，会自动释放
     * @return
     */
    protected Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }

    public T getByKey(PK key) {
        return (T) getSession().get(baseClass, key);
    }

    public T getByProperties(String propertiesName, Object properties) {
        String hql="from " + baseClass.getName() + " where " + propertiesName + " = ?";
        Query query=getSession().createQuery(hql);
        query.setParameter(0,properties);
        return (T)query.uniqueResult();
    }

    public PK save(T entity) {
        return  (PK) getSession().save(entity);
    }

    public void update(T entity) {
        getSession().update(entity);
    }

    public void delete(T entity) {
        getSession().delete(entity);
    }

    public Criteria createEntityCriteria() {
        return getSession().createCriteria(baseClass);
    }

    public List<T> getAll() {
        String hql = "from "+baseClass.getName();
        Query query = getSession().createQuery(hql);
        return query.list();
    }

    public Long getTotalCount() {
        String hql = "select count(*) from "+baseClass.getName();
        Query query = getSession().createQuery(hql);
        return (Long) query.uniqueResult();
    }
}
