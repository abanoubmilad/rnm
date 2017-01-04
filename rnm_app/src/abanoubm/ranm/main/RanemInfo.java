package abanoubm.ranm.main;

import java.util.ArrayList;

public class RanemInfo {
	public static String getArabicNum(int num) {
		char[] arr = { '٠', '١', '٢', '٣', '٤', '٥', '٦', '٧', '٨', '٩' };
		String arabic = "";

		while (num > 0) {
			arabic = arr[num % 10] + arabic;
			num /= 10;
		}

		return arabic;
	}
	public static ArrayList<String> menuItems = new ArrayList<String>() {
		{	    
			add("عرض ترانيم");
			add("بحث ترانيم");
			add("تواصل معنا");
		}
	};
	public static ArrayList<Trnema> searchResults;
	public static String[] letters = { "ا","ب","ت","ث","ج","ح",
			"خ","د","ذ","ر","ز","س","ش","ص","ض","ط","ظ",
			"ع","غ","ف","ق","ك","ل","م","ن","ه","و", "ي"
	};

}
