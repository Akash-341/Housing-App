package com.example.realestate.BaseUrl;

public class AllUrl {

    public static String server="http://api.thakkers.in/api/v1/";


    //Form's API
    public static String BUYER_COMMERCIAL=server+"AddCommercialBuyer";

    public static String BUYER_RESIDENTIAL=server+"AddBuyer";

    public static String LICENSEE_COMMERCIAL=server+"AddCommercialLicensee";

    public static String LICENSEE_RESIDENTIAL=server+"AddLicensee";

    public static String LICENSOR_COMMERCIAL=server+"AddCommercialLicensor";

    public static String LICENSOR_RESIDENTIAL=server+"AddLicensor";

    public static String SELLER_RESIDENTIAL=server+"AddSeller";

    public static String SELLER_COMMERCIAL=server+"AddCommercialSeller";


    //Entries API

    public static String BUYER_COMMERCIAL_ENTRIES=server+" ";

    public static String BUYER_RESIDENTIAL_ENTRIES=server+" ";

    public static String LICENSEE_COMMERCIAL_ENTRIES=server+" ";

    public static String LICENSEE_RESIDENTIAL_ENTRIES=server+" ";

    public static String LICENSOR_COMMERCIAL_ENTRIES=server+" ";

    public static String LICENSOR_RESIDENTIAL_ENTRIES=server+" ";

    public static String SELLER_RESIDENTIAL_ENTRIES=server+" ";

    public static String SELLER_COMMERCIAL_ENTRIES=server+" ";

    //Location API
    public static String GET_LOCATION_LIST=server+"GetLocationNameList";
}
