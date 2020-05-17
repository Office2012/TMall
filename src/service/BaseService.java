package service;

import java.util.List;
import util.Page;
/*
 * @Description 公共的业务方法
 */
public interface BaseService {
    public List list();
    public List listByPage(Page page);
    public int total();
    public List listByParent(Object parent);
    public List list(Page page, Object parentObject);
    public int total(Object parentObject);
    public Integer save(Object object);
    public void delete(Object object);
    public Object get(Class clazz, int id);
    public Object get(int id);
    public void update(Object object);
    public List list(Object... pairParams);
}
