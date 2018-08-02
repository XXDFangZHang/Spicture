package cn.cjh;

import sun.nio.cs.FastCharsetProvider;

import javax.management.monitor.MonitorSettingException;
import java.sql.SQLException;

public class TransactionDemo extends  BaseDao {


     public void bank(){
         BankDaoImpl dao=new BankDaoImpl();
         try {
             connection.setAutoCommit(false); //关闭自动提交事务
             dao.fromToBank("小黑",-1000);
             if (true){
                 throw  new Exception("转账失败========");
             }
             dao.fromToBank("小白",1000);
             connection.commit();
         } catch (Exception e) {
             try {
                 connection.rollback();
             } catch (SQLException e1) {
                 e1.printStackTrace();
             }
             e.printStackTrace();
         }
     }


    public static void main(String[] args) {
        TransactionDemo demo=new TransactionDemo();
        demo.bank();
    }
}
