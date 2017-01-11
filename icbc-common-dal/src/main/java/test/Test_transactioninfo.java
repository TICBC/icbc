package test;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * Created by Jian on 2017/1/10.
 */
public class Test_transactioninfo {

    public static void main(String[] args){
        //mybatis的配置文件
        String resource = "tiger/common/dal/persistence/mapper/TransactioninfoMapper.xml";
        //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
        InputStream is = Test_transactioninfo.class.getClassLoader().getResourceAsStream(resource);
        //构建sqlSession的工厂

        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
        //Reader reader = Resources.getResourceAsReader(resource);
        //
        //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);构建sqlSession的工厂
        //创建能执行映射文件中sql的sqlSession
        SqlSession session = sessionFactory.openSession();
        /**
         * 映射sql的标识字符串，
         * me.gacl.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
         * getUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
         */
        String statement = "me.gacl.mapping.userMapper.getUser";//映射sql的标识字符串
        //执行查询返回一个唯一user对象的sql

    }
}
