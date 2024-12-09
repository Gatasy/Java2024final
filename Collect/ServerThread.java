import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

// 多线程
public class ServerThread extends Thread {   //用于服务端与数据库传输数据的类
    db database;
    Socket socket;
    DataInputStream in;
    DataOutputStream out;

    public ServerThread(db database, Socket socket) {
        this.database = database;
        this.socket = socket;
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            try {
                String s_command = in.readUTF();
                String s_sql = in.readUTF();
                if (s_command.equals("query")) out.writeUTF(database.db_query(s_sql));
                if (s_command.equals("count")) out.writeInt(database.db_count(s_sql));  //计数
                if (s_command.equals("execute")) out.writeUTF(database.db_execute(s_sql));  //运行
                if (s_command.equals("query_m")) {                             //导向读取一行数据
                    String[] result = database.db_query_m(s_sql);
                    for (String s : result) {
                        out.writeUTF(s);
                    }
                }
                if (s_command.endsWith("query_m_r")) {                        //导向读取符合条件的全部数据
                    int r = Integer.parseInt(s_command.substring(0, s_command.indexOf("query_m_r")));
                    String[] result = database.db_query_m_r(s_sql, r);
                    for (String s : result) {
                        out.writeUTF(s);
                    }
                }
            } catch (IOException e) {
                System.out.println("客户离开");
                break;
            }
        }
    }
}