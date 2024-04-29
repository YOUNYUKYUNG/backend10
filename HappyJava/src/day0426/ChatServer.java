package day0426;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class ChatServer {
    //룸 목록
    static Map<Integer, List<ChatThread>> rooms = new HashMap<>();//룸 목록에 쓰일
    static int roomNumber = 1;
    static List<ChatThread> chatClients = new ArrayList<>();


    public static void main(String[] args) {


        //1. 서버소켓을 생성!!
        try (ServerSocket serverSocket = new ServerSocket(12345);) {
            System.out.println("서버가 준비되었습니다.");
            //여러명의 클라이언트의 정보를 기억할 공간

            while (true) {
                //2. accept() 를 통해서 소켓을 얻어옴.   (여러명의 클라이언트와 접속할 수 있도록 구현)
                Socket socket = serverSocket.accept();
                //Thread 이용!!
                //여러명의 클라이언트의 정보를 기억할 공간
                new ChatThread(socket).start();

            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    static synchronized void addClient (ChatThread chatThread){
        chatClients.add(chatThread);
    }
    static synchronized int createRoom(){
        return roomNumber++;
    }
}

class ChatThread extends Thread {
    //생성자를 통해서 클라이언트 소켓을 얻어옴.
    private Socket socket;
    private String nickname;
    private Map<String, PrintWriter> chatClient;//방에안들어온사람까지 다 담김
    private BufferedReader in;
    private PrintWriter out;
    private int roomId = -1;

    //private HashMap<Object, Object> userEnteredRooms;

    public ChatThread(Socket socket) {


        this.socket = socket;
        chatClient = new HashMap<>();
        //클라이언트가 생성될 때 클라이언트로 부터 아이디를 얻어오게 하고 싶어요.
        //각각 클라이언트와 통신 할 수 있는 통로얻어옴.
        //???어느방???

        ChatServer.addClient(this);//chatclients에 들어감

        try {
            out = new PrintWriter(socket.getOutputStream(), true);//연결된 클라이언트에 데이터를 보내는 데 사용
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));//클라이언트로부터 데이터를 받는 데 사용

            nickname = in.readLine(); //이때..  모든 사용자에게 id님이 입장했다라는 정보를 알려줌.
            broadcast(nickname + "닉네임의 사용자가 연결했습니다.\n");
            System.out.println(nickname + " 닉네임의 사용자가 연결했습니다.");
            System.out.println(nickname + " 님의 IP주소는 " + socket.getLocalAddress().getHostAddress() + "\n");

            out.println("--------------------");
            out.println("명령 모음\n");
            out.println("1. 방 목록 보기 : /list");
            out.println("2. 방 생성 : /create");
            out.println("3. 방 입장 : /join [방번호]");
            out.println("4. 방 나가기 : /exit");
            out.println("5. 접속종료 : /bye");
            out.println("--------------------");

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        //연결된 클라이언트가 메시지를 전송하면, 그 메시지를 받아서 다른 사용자들에게 보내줌
        String msg = null;
        try {
            while ((msg = in.readLine()) != null) {
                if ("/list".equalsIgnoreCase(msg))
                    roomList();

                if ("/create".equalsIgnoreCase(msg))
                    createRoom();

                if ("/join".equalsIgnoreCase(msg))
                    joinRoom(msg);

                if ("/exit".equalsIgnoreCase(msg))
                    //exitRoom();

                if ("/bye".equalsIgnoreCase(msg))
                    break;

                if (msg.indexOf("/to") == 0)
                    sendMsg(msg);
                else
                    broadcast(nickname + " : " + msg);
            }
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        } finally {
            synchronized (chatClient) {
                chatClient.remove(nickname);
            }
            broadcast(nickname + "닉네임의 사용자가 연결을 끊었습니다.");

            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    //대화방 생성 코드(생성 시 동시 호출 가능성.. )
    public synchronized void createRoom() {
        chatClient.put(nickname, out);
        roomId = ChatServer.createRoom();//-1이 1로 변화
        ChatServer.rooms.put(roomId, Collections.singletonList(this));
        System.out.println("방 번호 [" + roomId + "]가 생성되었습니다.");
    }


    private void joinRoom(String msg) {
        int firstSpaceIndex = msg.indexOf(" ");
        if (firstSpaceIndex == -1) return;
        List<ChatThread> listRoom = ChatServer.rooms.get(roomId);
        if (!listRoom.contains(roomId))
            out.println("해당 번호의 방이 존재하지 않습니다.");
        listRoom.add((ChatThread) chatClient);
        out.println(nickname + "님이 방에 입장했습니다.");
    }


    //방 목록

    private void roomList() {
        if (ChatServer.rooms.isEmpty()) {
            out.println("현재 생성된 방이 없습니다.");
        } else {
            out.println("현재 생성된 방 목록 : ");
            for (Integer roomNumber : ChatServer.rooms.keySet()) {
                out.println(roomNumber + "번 방");
            }
        }
    }


    //메시지를 특정 사용자에게만 보내는 메서드
    public void sendMsg(String msg) {
        int firstSpaceIndex = msg.indexOf(" ");
        if (firstSpaceIndex == -1) return;

        int secondSpaceIndex = msg.indexOf(" ", firstSpaceIndex + 1);
        if (secondSpaceIndex == -1) return;

        String to = msg.substring(firstSpaceIndex + 1, secondSpaceIndex);
        String message = msg.substring(secondSpaceIndex + 1);

        //to(수신자)에게 메시지 전송.
        PrintWriter pw = chatClient.get(to);
        if (pw != null) {
            pw.println(nickname + "님으로부터 온 비밀 메시지 : " + message);
        } else {
            System.out.println("오류 : 수신자 " + to + " 님을 찾을 수 없습니다.");
        }
    }

    //메시지를 전체 사용자에게 보내는 메서드
    public void broadcast(String msg) {

        synchronized (chatClient) {
            Iterator<PrintWriter> it = chatClient.values().iterator();
            while (it.hasNext()) {
                PrintWriter out = it.next();
                try {
                    out.println(msg);
                } catch (Exception e) {
                    it.remove();  //브로드케스트 할 수 없는 사용자를 제거한다.
                    e.printStackTrace();
                }
            }
        }
    }
}
