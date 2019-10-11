package com.t3h.appdc.api;

import com.t3h.appdc.model.Comment;
import com.t3h.appdc.model.Notifi;
import com.t3h.appdc.model.User;
import com.t3h.appdc.model.Pets;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    @POST("get_pets.php")
    Call<ArrayList<Pets>> getPets();
    @POST("get_baiviet.php")
    Call<ArrayList<Pets>> getPost();

    @POST("get_pet_share.php")
    Call<ArrayList<Pets>> getPetsShare();

    @FormUrlEncoded
    @POST("add_pet.php")
    Call<Pets> insertPet(
            @Field("key") String key,
            @Field("des") String des,
            @Field("name") String name,
            @Field("species") String species,
            @Field("breed") String breed,
            @Field("gender") int gender,
            @Field("birth") String birth,
            @Field("picture") String picture);


    @FormUrlEncoded
    @POST("add_post.php")
    Call<ResponseBody> insertBaiviet(
            @Field("key") String key,
            @Field("id_user") String id_user,
            @Field("id_pet") int id_pet,
            @Field("title") String title,
            @Field("description") String description);


    @FormUrlEncoded
    @POST("update_pet.php")
    Call<Pets> updatePet(
            @Field("key") String key,
            @Field("id") int id,
            @Field("name") String name,
            @Field("species") String species,
            @Field("breed") String breed,
            @Field("gender") int gender,
            @Field("birth") String birth,
            @Field("picture") String picture);

    @FormUrlEncoded
    @POST("delete_pet.php")
    Call<Pets> deletePet(
            @Field("key") String key,
            @Field("id") int id,
            @Field("picture") String picture);

    @FormUrlEncoded
    @POST("update_love.php")
    Call<Pets> updateLove(
            @Field("key") String key,
            @Field("id") int id,
            @Field("love") boolean love);

    @POST("login.php")
    @FormUrlEncoded
    Call<ArrayList<User>> login(@Field("username") String username,
                                @Field("password") String password);

    @FormUrlEncoded
    @POST("regester.php")
    Call<ResponseBody> regester(
            @Field("username") String username,
            @Field("password") String password,
            @Field("email") String email,
            @Field("phone") String phone);

    @POST("update_user.php")
    @FormUrlEncoded
    Call<ResponseBody> updateUser(@Field("username") String username,
                                  @Field("fullname") String fullname,
                                  @Field("email") String email,
                                  @Field("phone") String phone,
                                  @Field("address") String address,
                                  @Field("face") String face,
                                  @Field("birth") String birth
                                  );

    @POST("change_pass.php")
    @FormUrlEncoded
    Call<ResponseBody> changePass(@Field("passnew") String passnew,
                                  @Field("userid") String userid);

    @POST("get_comment.php")
    @FormUrlEncoded
    Call<ArrayList<Comment>> getComment(@Field("idbaiviet") String idbaiviet);

    @POST("add_comment.php")
    @FormUrlEncoded
    Call<ArrayList<Comment>> addComment(@Field("username") String username,
                                        @Field("comment") String comment,
                                        @Field("baivietid") String baivietid
                                        );
    @POST("get_notification.php")
    @FormUrlEncoded
    Call<ArrayList<Notifi>> getNotifi(@Field("login_name") String login_name);

    @POST("get_user_withbaiviet.php")
    @FormUrlEncoded
    Call<ArrayList<User>> getUserWithBaiViet(@Field("id_baiviet") String id_baiviet);

    @POST("add_thongbao.php")
    @FormUrlEncoded
    Call<ResponseBody> addNotifi(@Field("mess") String mess,
                                      @Field("user_id") String user_id,
                                      @Field("baivietid") String baivietid,
                                      @Field("user_bi_like") String user_bi_like
                                    );
}
