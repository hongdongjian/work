package springboot.Service;

import org.hibernate.Criteria;
import springboot.Dao.BaseDao;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * 业务逻辑层的基本类
 * Created by lenovo on 2017/3/13.
 */

/**
 * 注解：transactional
 * 默认当抛出runtimeException时回滚
 * 使用@Transactional(rollbackFor=Exception.class)，抛出Exception就回滚
 * 使用@Transactional(rollbackFor=Exception.class), 抛出Exception不回滚
 * 注：IOException、SQLException、RuntimeException 继承 Exception
 */
@Transactional
public class BaseService<PK extends Serializable,T>{

    private BaseDao<PK, T> baseDao;

    public BaseDao<PK, T> getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(BaseDao<PK, T> baseDao) {
        this.baseDao = baseDao;
    }

    public T getByKey(PK key) {
        return baseDao.getByKey(key);
    }

    public T getByProperties(String propertiesName, String properties) {
        return baseDao.getByProperties(propertiesName,properties);
    }

    public PK sava(T entity) {
        return baseDao.save(entity);
    }

    public void update(T entity) {
        baseDao.update(entity);
    }

    public void delete(T entity) {
        baseDao.delete(entity);
    }

    public Criteria createEntityCriteria() {
        return baseDao.createEntityCriteria();
    }

    public List<T> getAll() {
        return baseDao.getAll();
    }

    public Long getTotalCount() {
        return baseDao.getTotalCount();
    }
}
