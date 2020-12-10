package com.chenhl.sshforward.sshforward;


import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.util.Properties;

/**
 * @author :   陈泓霖
 * @date :     2020/12/7 0007 10:50
 * @description : ssh代理连接
 */
public class SSHConnection {

    private final static String S_PATH_FILE_PRIVATE_KEY = "/Users/陈泓霖/.ssh/id_rsa";
    private final static String S_PATH_FILE_KNOWN_HOSTS = "/Users/陈泓霖/.ssh/known_hosts";
    private final static String S_PASS_PHRASE = "";
    private final static int LOCAl_PORT = 3307;
    private final static int REMOTE_PORT = 1111;
    private final static int SSH_REMOTE_PORT = 22;
    private final static String SSH_USER = "root";
    private final static String SSH_PASSWORD = "********";
    private final static String SSH_REMOTE_SERVER = "88.88.88.88";
    private final static String MYSQL_REMOTE_SERVER = "****";

    private Session sesion;

    public void closeSSH ()
    {
        sesion.disconnect();
    }
    public static void main(String[] args) throws Throwable {

        System.out.println(new SSHConnection().sesion.getHost());
    }

    public SSHConnection() throws Throwable
    {

        JSch jsch = null;

        jsch = new JSch();
//        jsch.setKnownHosts(S_PATH_FILE_KNOWN_HOSTS);
        //jsch.addIdentity(S_PATH_FILE_PRIVATE_KEY);

        sesion = jsch.getSession(SSH_USER, SSH_REMOTE_SERVER, SSH_REMOTE_PORT);

        sesion.setPassword(SSH_PASSWORD);

        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        sesion.setConfig(config);

        sesion.connect();

        sesion.setPortForwardingL(LOCAl_PORT, MYSQL_REMOTE_SERVER, REMOTE_PORT);
        System.out.println("SSHConnection--运行");
    }
}