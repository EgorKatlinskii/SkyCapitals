package Models.UserAccount;

/*создание аккаунта*/
class AccountСreation {
    private int userId;
    private String userName;
    private String userSurname;
    private String ostOffice;

    public AccountСreation(int userId, String userName, String userSurname, String ostOffice) {
        this.userId = userId;
        this.userName = userName;
        this.userSurname = userSurname;
        this.ostOffice = ostOffice;
    }
}
