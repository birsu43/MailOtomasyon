package Class;

public class Mails {
private static String mail_konu;
	
	public static void setMail_Konu(String konu) {
		Mails.mail_konu = konu;
	}

	public static String getMail_konu() {
		return mail_konu;
	}
}
