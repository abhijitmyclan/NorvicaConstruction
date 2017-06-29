package com.example.android.norvicaconstruction.network;


import com.example.android.norvicaconstruction.models.Logindata;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    /*
    Retrofit get annotation with our URL
    And our method that will return us the List of ContactList
    */
    @POST("login.php")
    @FormUrlEncoded
    Call<Logindata> getLogin(@Field("email") String mobile, @Field("password") String password);;

//    @POST("insert.php")
//    @FormUrlEncoded
//    Call<Register> savePost(@Field("name") String title,
//                            @Field("email") String body
//    );
//
//    @POST("slider.php")
//    Call<SliderResponse> getSlider();
//
//        @POST("services.php")
//    Call<Serviceresponse> getServices();
//
//        @POST("pricing_list.php")
//        @FormUrlEncoded
//    Call<PricingResponse> getPricingList(@Field("pricing_type") String s);
//        @POST("type.php")
//    Call<PricingTypeResponse> getPricingTypes();
//
//
//    @POST("about.php")
//    Call<Car_About_response> callabout();

//    Call<Place_Order_Response> callPlaceorder(@Field("order") String s);
//    Call<Place_Order_Response> callPlaceorderr(@Body JSONObject orderClass);
//    Call<Place_Order_Response> postItemsMethod(@Field("order") JSONArray tabs);

}
