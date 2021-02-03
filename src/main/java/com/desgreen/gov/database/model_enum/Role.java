package com.desgreen.gov.database.model_enum;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

public class Role {
	/**
	 * boleh tidak ditambahkan ROLE_ 
	 * misal ADMIN menjadi ROLE_ADMIN
	 * penambahan pada:
	 *  	authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
	 * 	oleh karena untuk menyamakan dengan konsep @Secure("ROLE_ADMIN") 
	 *  vaadin maka sebaiknya ditambahkan
	 */
	public static final String USER 	= "ROLE_USER";
	public static final String ADMIN 	= "ROLE_ADMIN";
	public static final String GUEST 	= "ROLE_GUEST"; //as default
	public static final String USER1 	= "ROLE_USER1"; //as default
	public static final String USER2 	= "ROLE_USER2"; //as default

	//Menu digunakan suatu penanda MNU
	public static final String ROLE_BAPPEDA 	= "ROLE_BAPPEDA";

	public static final String ROLE_PUPR = "ROLE_PUPR";
	public static final String ROLE_PENDIDIKAN = "ROLE_PENDIDIKAN";
	public static final String ROLE_KESEHATAN = "ROLE_KESEHATAN";
	public static final String ROLE_PERKIMTAN = "ROLE_PERKIMTAN";
	public static final String ROLE_SOSIAL = "ROLE_SOSIAL";
	public static final String ROLE_NAKERTRANS = "ROLE_NAKERTRANS";
	public static final String ROLE_PANGAN = "ROLE_PANGAN";
	public static final String ROLE_BPBD = "ROLE_BPBD";
	public static final String ROLE_LINGK = "ROLE_LINGK";
	public static final String ROLE_PERHUB = "ROLE_PERHUB";
	public static final String ROLE_KOMINFO = "ROLE_KOMINFO";
	public static final String ROLE_PERIKANAN = "ROLE_PERIKANAN";
	public static final String ROLE_PARIWISATA = "ROLE_PARIWISATA";
	public static final String ROLE_PERTANIAN = "ROLE_PERTANIAN";
	public static final String ROLE_KOPERASI = "ROLE_KOPERASI";


	public static final String ROLE_BULABRT = "ROLE_BULABRT";
	public static final String ROLE_BULA = "ROLE_BULA";
	public static final String ROLE_GOROMTMR = "ROLE_GOROMTMR";
	public static final String ROLE_PULAUGOROM = "ROLE_PULAUGOROM";
	public static final String ROLE_KIANDARAT = "ROLE_KIANDARAT";
	public static final String ROLE_SERAMTMR = "ROLE_SERAMTMR";
	public static final String ROLE_KILMURY = "ROLE_KILMURY";
	public static final String ROLE_SIRITAUNWIDATMR = "ROLE_SIRITAUNWIDATMR";
	public static final String ROLE_SIWALALAT = "ROLE_SIWALALAT";
	public static final String ROLE_TUTUKTOLU = "ROLE_TUTUKTOLU";
	public static final String ROLE_PULAUPJG = "ROLE_PULAUPJG";
	public static final String ROLE_TELUKWARU = "ROLE_TELUKWARU";
	public static final String ROLE_TEOR = "ROLE_TEOR";
	
	public static final String ROLE_KESUIWATUBELA = "ROLE_KESUIWATUBELA";
	public static final String ROLE_WAKATE = "ROLE_WAKATE";
	public static final String ROLE_WERINAMA = "ROLE_WERINAMA";

	private Role() {
	}

	// public static String[] getAllRoles() {
	// 	// return new String[] { USER, ADMIN, MNU_ADMIN_1 };
	// 	String allRoles[] = ArrayUtils.addAll(getAuthRoles() );		
	// 	return allRoles;
	// }

	// public static List getAllRolesList() {
	// 	List<String> list = new ArrayList<>(); 
  
    //     // Iterate through the array 
    //     for (String t : getAllRoles()) { 
    //         list.add(t); 
	// 	} 
	// 	return list;
	// }
	public static String[] getAuthRoles() {
		return new String[] { USER, ADMIN, GUEST, USER1, USER2, ROLE_BAPPEDA, ROLE_PENDIDIKAN,
			ROLE_KESEHATAN, ROLE_PERKIMTAN, ROLE_SOSIAL, ROLE_NAKERTRANS, ROLE_PANGAN, ROLE_LINGK,
			ROLE_PERHUB, ROLE_KOMINFO, ROLE_PERIKANAN, ROLE_PARIWISATA, ROLE_PERTANIAN, ROLE_KOPERASI, ROLE_BPBD, ROLE_PUPR,
			ROLE_BULABRT,ROLE_BULA, ROLE_GOROMTMR, ROLE_PULAUGOROM, ROLE_KIANDARAT, ROLE_SERAMTMR, ROLE_KILMURY, ROLE_SIRITAUNWIDATMR, 
			ROLE_SIWALALAT, ROLE_TUTUKTOLU, ROLE_PULAUPJG, ROLE_TELUKWARU, ROLE_TEOR, ROLE_KESUIWATUBELA, ROLE_WERINAMA
		 };
	}

}
