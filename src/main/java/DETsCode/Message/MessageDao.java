package DETsCode.Message;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import DETsCode.Chat.Chat;
import DETsCode.Message.Message;
import DETsCode.Payment.Payment;
import DETsCode.Payment.PaymentDao;
import DETsCode.db.DatabaseConnection;

public class MessageDao {
    private final  DatabaseConnection conn;
    private static MessageDao instance;   
            public MessageDao() {
                this.conn = DatabaseConnection.getInstance();
                
            }
            public static MessageDao getInstance() {
                if (instance == null) {
                    instance = new MessageDao();
                }
                return instance;
            }
            public List<Message> getallMessages() {
                   List<Message> messages = new ArrayList<>();
                   try {
                    PreparedStatement stmt = conn.getConnection().prepareStatement( "SELECT * FROM Message");
                    ResultSet rs = stmt.executeQuery();
                        while (rs.next()) {
                            int messageId = rs.getInt("messageid");
                            int chatid = rs.getInt("chatid"); 
                            int senderid = rs.getInt("senderid");
                            int receiverid = rs.getInt("receiverid");
                            LocalDateTime timestamp = rs.getTimestamp("timestamp").toLocalDateTime();
                            Message message= new Message(messageId, chatid, senderid, receiverid, timestamp);
                            messages.add(message);
                        }
                    } catch (SQLException e) {
                    e.printStackTrace();
                    }
                return messages;
            }
            public static List<Message> getMessagesByChatId( int chatid) {
                List<Message> messages = new ArrayList<>();
                try {
                 PreparedStatement stmt = conn.getConnection().prepareStatement( "SELECT * FROM Message where chatid= ?");
         stmt.setInt(1,chatid);
         ResultSet rs = stmt.executeQuery();
         
             while (rs.next()) {
                 int messageId = rs.getInt("messageid");
                 int senderid = rs.getInt("senderid");
                 int receiverid = rs.getInt("receiverid");
                 LocalDateTime timestamp = rs.getTimestamp("timestamp").toLocalDateTime();
                 Message message= new Message(messageId, chatid, senderid, receiverid, timestamp);
                 messages.add(message);
             }
         } catch (SQLException e) {
         e.printStackTrace();
         }
     return messages;
  }
  public boolean addmessage(Message message) {
            try {
                String query = "INSERT INTO Message(messageId, chatid, senderid, receiverid, timestamp) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement stmt = conn.getConnection().prepareStatement(query);
                stmt.setInt(1, message.getMessageid());
                stmt.setInt(2, message.getChatid());
                stmt.setInt(3, message.getSenderid());
                stmt.setInt(4, message.getReceiverid());
                stmt.setTimestamp(5, java.sql.Timestamp.valueOf(message.getTimestamp()));

    
                int rowsAffected = stmt.executeUpdate();
                return rowsAffected > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
            

}

        
    }
    

