package com.example.demo;

/**
 * ThreadLocal创建的变量只能被当前线程访问
 */
public class ThreadLocalTest {
    static class T1 implements Runnable{
        private static Integer num = new Integer(0);

        @Override
        public void run() {
            setNum();
        }

        public static void setNum() {
            for (int i = 0; i < 500; i++) {
                num ++;
            }
            System.out.println(num);
        }


        public static void main(String[] args) throws InterruptedException {
            T1 t1 = new T1();

            Thread thread1 = new Thread(t1);
            Thread thread2 = new Thread(t1);

            thread1.start();
            thread2.start();
        }


    }


    static class T2 implements Runnable{
        private static ThreadLocal<Integer> num = new ThreadLocal<Integer>() {
            public Integer initialValue() {
                return 0;
            }
        };

        @Override
        public void run() {
            setNum();
        }

        public static void setNum() {
            Integer integer = 0;
            for (int i = 0; i < 500; i++) {
                integer ++;
            }
            num.set(integer);

            System.out.println(num.get());
        }


        public static void main(String[] args) throws InterruptedException {
            T2 t2 = new T2();

            Thread thread1 = new Thread(t2);
            Thread thread2 = new Thread(t2);

            thread1.start();
            thread2.start();

        }
    }
}


//
//    docker run --name mycat -p 8066:8066 -p 9066:9066 -v /usr/local/mycat/conf/conf/:/usr/local/mycat/conf -e MYSQL_ROOT_PASSWORD=root -d mycat-1.6
//
//
//        docker run -p 3307:3306 --name mysql \
//        -v /srv/mysql/conf:/etc/mysql/conf.d \
//        -v /srv/mysql/logs:/logs \
//        -v /srv/mysql/data:/var/lib/mysql \
//        -e MYSQL_ROOT_PASSWORD=root -d mysql:5.7



//关于MyCAT的配置其实是蛮简单的，最主要的是熟悉各配置文件的规则。以上用户名，密码，如何分库，都是在配置文件中定义的，后续，有时间再一一详表。
//
//关于配置文件，conf目录下主要以下三个需要熟悉。
//
//server.xml是Mycat服务器参数调整和用户授权的配置文件
//
//schema.xml是逻辑库定义和表以及分片定义的配置文件
//
//rule.xml是分片规则的配置文件




































