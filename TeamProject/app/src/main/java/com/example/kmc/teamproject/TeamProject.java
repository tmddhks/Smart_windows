package com.example.kmc.teamproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.kmc.teamproject.activity.WeatherActivity;

public class TeamProject extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.next);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TeamProject.this, WeatherActivity.class);
                startActivity(intent);
            }
        });
        Button btn2 = (Button) findViewById(R.id.retrofit);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TeamProject.this, RetrofitActivity.class);
                startActivity(intent);
            }
        });
    }
}


//    public TeamProject() throws ParserConfigurationException, IOException, SAXException {
//    }
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//
//    private static String urlStr = "http://www.kma.go.kr/weather/forecast/timeseries.jsp?searchType=INTEREST&dongCode=4139058900";
//
//    DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
//    DocumentBuilder builder = builderFactory.newDocumentBuilder();
//
//    URL urlForHttp = new URL(urlStr);
//
//    InputStream instream = getInputStreamUsingHTTP(urlForHttp);
//
//    private InputStream getInputStreamUsingHTTP(URL urlForHttp) {
//
//    }
//
//    Document document = builder.parse(instream);
//
//if(document != null) {
//        NodeList list = document.getElementsByTagName("data");
//
//        for (int i = 0; i < list.getLength(); i++) {
//            System.out.println("=========================");
//
//            for (int k = 0; k < list.item(i).getChildNodes().getLength(); k++) {
//                if (list.item(i).getChildNodes().item(k).getNodeType() == Node.ELEMENT_NODE) {
//                    System.out.print(k+":"+list.item(i).getChildNodes().item(k).getNodeName()+"====>");
//                    System.out.println(list.item(i).getChildNodes().item(k).getTextContent());
//
//                }
//            }
//        }
//    }
//}

//    double latitude;
//    double longitude;
//    TextView latText, lonText, weather;
//    Button button;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_team_project);
//
//        initView();
//
//        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
//
//    }
//
//    private void initView() {
//        //뷰세팅
//        latText = (TextView) findViewById(R.id.latitude);
//        lonText = (TextView) findViewById(R.id.longitude);
//        weather = (TextView) findViewById(R.id.weather);
//        button = (Button) findViewById(R.id.button);
//        button.setOnClickListener(this);
//    }
//
//
//    @Override
//    public void onLocationChanged(Location location) {
//        /*현재 위치에서 위도경도 값을 받아온뒤 우리는 지속해서 위도 경도를 읽어올것이 아니니
//        날씨 api에 위도경도 값을 넘겨주고 위치 정보 모니터링을 제거한다.*/
//        latitude = location.getLatitude();
//        longitude = location.getLongitude();
//        //위도 경도 텍스트뷰에 보여주기
//        latText.setText(String.valueOf(latitude));
//        lonText.setText(String.valueOf(longitude));
//        //날씨 가져오기 통신
//        getWeather(latitude, longitude);
//        //위치정보 모니터링 제거
//        locationManager.removeUpdates(TeamProject.this);
//    }
//
//    @Override
//    public void onStatusChanged(String provider, int status, Bundle extras) {
//
//    }
//
//    @Override
//    public void onProviderEnabled(String provider) {
//
//    }
//
//    @Override
//    public void onProviderDisabled(String provider) {
//
//    }
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            //버튼 클릭시 현재위치의 날씨를 가져온다
//            case R.id.button:
//                if (locationManager != null) {
//                    requestLocation();
//                }
//
//                break;
//        }
//    }
//
//    private void requestLocation() {
//        //사용자로 부터 위치정보 권한체크
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 0);
//        } else {
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 1, this);
//            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 500, 1, this);
//
//        }
//    }
//
//
//
//    private interface ApiService {
//        //베이스 Url
//        String uri = "http://http://api.openweathermap.org/data/";
//        String api = "9f168a26e28ce7070474c22388ed8145";
//
//        //get 메소드를 통한 http rest api 통신
//        @GET("weather/current/hourly")
//        Call<JsonObject> getHourly(@Header("api") String api, @Query("version") int version,
//                                   @Query("lat") double lat, @Query("lon") double lon);
//
//    }
//
//    private void getWeather(double latitude, double longitude) {
//        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
//                .baseUrl(ApiService.uri)
//                .build();
//        ApiService apiService = retrofit.create(ApiService.class);
//        Call<JsonObject> call = apiService.getHourly(ApiService.api, 1, latitude, longitude);
//        call.enqueue(new Callback<JsonObject>() {
//            @Override
//            public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {
//                if (response.isSuccessful()) {
//                    //날씨데이터를 받아옴
//                    JsonObject object = response.body();
//                    if (object != null) {
//                        //데이터가 null 이 아니라면 날씨 데이터를 텍스트뷰로 보여주기
//                        weather.setText(object.toString());
//                    }
//
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable t) {
//            }
//        });
//    }
//}