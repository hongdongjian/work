package com.swagger.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by hongdongjian on 2017/8/2.
 */
@Repository
public class BaseDao<T, PK extends Serializable>{

    private Class<T> entityClass;
    protected SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public BaseDao() {
        this.entityClass = null;
        Class c = getClass();
        Type type = c.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] parameterizedType = ((ParameterizedType) type)
                    .getActualTypeArguments();
            this.entityClass = (Class<T>) parameterizedType[0];
        }
    }

    @Resource
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession(){
        Session s = sessionFactory.getCurrentSession();
        return s;
    }

    @SuppressWarnings("unchecked")
    public T get(PK id) {
        Assert.notNull(id, "id is required");
        return (T) getSession().get(entityClass, id);
    }

    @SuppressWarnings("unchecked")
    public T load(PK id) {
        Assert.notNull(id, "id is required");
        return (T) getSession().load(entityClass, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> get(PK[] ids) {
        Assert.notEmpty(ids, "ids must not be empty");
        String hql = "from " + entityClass.getName()
                + " as model where model.id in(:ids)";
        return getSession().createQuery(hql).setParameterList("ids", ids)
                .list();
    }

    @SuppressWarnings("unchecked")
    public T get(String propertyName, Object value) {
        Assert.hasText(propertyName, "propertyName must not be empty");
        Assert.notNull(value, "value is required");
        String hql = "from " + entityClass.getName() + " as model where model."
                + propertyName + " = ?";
        return (T) getSession().createQuery(hql).setParameter(0, value)
                .uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<T> getList(String propertyName, Object value) {
        Assert.hasText(propertyName, "propertyName must not be empty");
        Assert.notNull(value, "value is required");
        String hql = "from " + entityClass.getName() + " as model where model."
                + propertyName + " = ?";
        return getSession().createQuery(hql).setParameter(0, value).list();
    }

    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        String hql = "from " + entityClass.getName();
        return getSession().createQuery(hql).list();
    }

    @SuppressWarnings("unchecked")
    public List<T> getList(String sql,int firstResult,int maxResult) {
        Query q = getSession().createQuery(sql);
        q.setFirstResult(firstResult);
        q.setMaxResults(maxResult);

        return q.list();
    }

    @SuppressWarnings("unchecked")
    public List<T> getList(String sql) {
        Query q = getSession().createQuery(sql);
        return q.list();
    }


    public Long getTotalCount() {
        String hql = "select count(*) from " + entityClass.getName();
        return (Long) getSession().createQuery(hql).uniqueResult();
    }

    public Long getCount(String hql){
        return (Long) getSession().createQuery(hql).uniqueResult();
    }
    public boolean isExist(String propertyName, Object value) {
        String hql = "select count(*) from "+entityClass.getName()+" where "+propertyName+"='" + value+"'";
        Long num=(Long)getSession().createQuery(hql).uniqueResult();
        if(num>0){
            return false;
        }
        return true;
    }
    public boolean isExistInt(String propertyName, Integer value) {
        String hql = "select count(*) from "+entityClass.getName()+" where "+propertyName+"='" + value+"'";
        Long num=(Long)getSession().createQuery(hql).uniqueResult();
        if(num>0){
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    public PK save(T entity){
        Assert.notNull(entity, "entity is required");
        return (PK) getSession().save(entity);
    }

    public void update(T entity) {
        Assert.notNull(entity, "entity is required");
        getSession().update(entity);
    }

    public void delete(T entity) {
        Assert.notNull(entity, "entity is required");
        getSession().delete(entity);
    }

    public void delete(PK id){
        Assert.notNull(id, "id is required");
        T entity = load(id);
        getSession().delete(entity);
    }

    public void delete(PK[] ids) {
        Assert.notEmpty(ids, "ids must not be empty");
        for (PK id : ids) {
            T entity = load(id);
            getSession().delete(entity);
        }
    }

    public void flush() {
        getSession().flush();
    }

    public void clear() {
        getSession().clear();
    }

    public void evict(Object object) {
        Assert.notNull(object, "object is required");
        getSession().evict(object);
    }

}
