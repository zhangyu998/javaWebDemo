package zy.service.impl;

import zy.dao.UserDao;
import zy.dao.impl.UserDaoImpl;
import zy.domain.PageBean;
import zy.domain.User;
import zy.service.UserService;

import java.util.List;
import java.util.Map;

/**
 * @author zhangyu
 * @date 2019-08-09-10:23
 */
public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        //调用Dao完成查询
        return dao.findAll();
    }

    @Override
    public User login(User user) {
        return dao.findUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public void addUser(User user) {
        dao.add(user);
    }

    @Override
    public void deleteUser(String id) {
        dao.delete(Integer.parseInt(id));
    }

    @Override
    public User findUserById(String id) {
        return dao.findById(Integer.parseInt(id));
    }

    @Override
    public void updateUser(User user) {
        dao.update(user);
    }

    @Override
    public void delSelectedUser(String[] ids) {
        if(ids != null && ids.length > 0){
            //1.遍历数组
            for (String id : ids) {
                //2.调用dao删除
                dao.delete(Integer.parseInt(id));
            }
        }

    }

    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {

        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        if(currentPage <=0) {
            currentPage = 1;
        }
        //1.创建空的PageBean对象
        PageBean<User> pb = new PageBean<User>();
        //2.设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);

        //3.调用dao查询总记录数
        int totalCount = dao.findTotalCount(condition);
        pb.setTotalCount(totalCount);
        //4.调用dao查询List集合
        //计算开始的记录索引
        int start = (currentPage - 1) * rows;
        List<User> list = dao.findByPage(start,rows,condition);
        pb.setList(list);

        //5.计算总页码
        int totalPage = (totalCount % rows)  == 0 ? totalCount/rows : (totalCount/rows) + 1;
        pb.setTotalPage(totalPage);


        return pb;
    }

    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows) {
        int currentPage=Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        //当在第一页再点上一页时
        if (currentPage<=0){
            currentPage=1;
        }


        PageBean<User> pb=new PageBean<User>();

        int totalCount = dao.findTotalCount();
        pb.setTotalCount(totalCount);

        //当在最后一页再点击下一页时
        if (currentPage>((totalCount%rows)==0 ?totalCount/rows :totalCount/rows+1))
        {
            currentPage=10;
        }

        pb.setCurrentPage(currentPage);
        pb.setRows(rows);



        int start = (currentPage - 1) * rows;
        List<User> list = dao.findByPage(start,rows);
        pb.setList(list);

        //5.计算总页码
        int totalPage=(totalCount%rows)==0 ?totalCount/rows :totalCount/rows+1;
        pb.setTotalPage(totalPage);



        return pb;
    }
}
