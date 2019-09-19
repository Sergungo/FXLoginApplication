package sample;

public enum User {
    User1("PIANO", "0000", 10000), User2("BASS", "5555", 20000);

    private String userName;
    private String password;
    private int timer;

    User(String userName, String password, int timer) {
        this.userName = userName;
        this.password = password;
        this.timer = timer;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getTimer() {
        return timer;
    }
}
