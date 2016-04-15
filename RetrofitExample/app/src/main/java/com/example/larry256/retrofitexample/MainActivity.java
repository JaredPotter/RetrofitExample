package com.example.larry256.retrofitexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.List;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://battleship.pixio.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            APIService service = retrofit.create(APIService.class);

            // POST
            ApiPost(service);

            // GET
            ApiGet(service);

        } catch (Exception e) {
            System.out.println(e);
        }

        setContentView(R.layout.activity_main);
    }

    public void ApiGet(APIService service)
    {
        // GET EXAMPLE
        Call<List<Game>> call = service.getGames();
        call.enqueue(new Callback<List<Game>>() {

            @Override
            public void onResponse(Response<List<Game>> response, Retrofit retrofit) {
                System.out.println();
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println();
            }
        });
    }

    public void ApiPost(APIService service)
    {
        // POST EXAMPLE
        CreateGame newGame = new CreateGame();
        Call<CreateGame> call = service.postGame(newGame);
        call.enqueue(new Callback<CreateGame>() {

            @Override
            public void onResponse(Response<CreateGame> response, Retrofit retrofit) {
                System.out.println();
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println();
            }
        });
    }

    public interface APIService {

        @POST("games")
        Call<CreateGame> postGame(
                @Body CreateGame game
        );

        @GET("games")
        Call<List<Game>> getGames();
    }

    public class Game {
        public String id;
        public String name;
        public String status;
    }

    public class CreateGame {
        public String gameName = "exampleGameName";
        public String playerName = "examplePlayerName";
    }
}
