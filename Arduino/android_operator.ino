#include <Stepper.h>
#include <DHT.h>
#include <Servo.h>

#define rain_sensor A0
#define magnet A1

#define DHTPIN 2
#define dust_in 3
#define dust_out 5
#define servo 6
#define buzzer 7

#define stepsPerRevolution 115   //바퀴 수 조절 변수
#define DHTTYPE DHT11

DHT dht(DHTPIN, DHTTYPE);                                //온-습도 센서 인자값 확인

Stepper DoorWindow(stepsPerRevolution, 8,9,10,11);      //창문 작동 모터 핀

//전역 변수
boolean lock_con = false;         //false는 열려있는 상태, true는 닫혀있는 상태
boolean window_con = true;        //true는 열려있는 상태, false는 닫혀있는 상태

//온습도 확인 클래스
class HumTemp
{
  float h = dht.readHumidity();
  float t = dht.readTemperature();
  float f = dht.readTemperature(true);
public:
  HumTemp() {}

  //온-습도 센서가 정상적으로 작동하는지 확인하는 함수
  void CheckCalc()
  {
    if (isnan(h) || isnan(t)){Serial.println("Failed to read from DHT sensor!");}
  }

  //온-습도 센서에서 받아들인 정보를 시리얼 창으로 출력해주는 함수
  void CalcView()
  {
    CheckCalc();
    float hif = dht.computeHeatIndex(f, h);
    float hic = dht.computeHeatIndex(t, h, false);
    String ht = "습도 : " + (String)h + " 온도 : " + (String)t + " 체감온도 : " + (String)hic + " : ";
    Serial.println(ht);
    delay(3000);
  }
};

//창문 작동 클래스
class Door_Operator
{
public:
  Door_Operator() {}
    //창문을 닫는 함수
  void ClosedDoor()
  {
    if(!lock_con)
    {
      int motorSpeed = 30;//map(4000, 0, 1023, 0, 10);
      DoorWindow.setSpeed(motorSpeed);
      DoorWindow.step(stepsPerRevolution);
      window_con = false;
    }
  }
  //창문을 여는 함수
  void OpenDoor()
  {
    if(!lock_con)
    {
      int motorSpeed = 30;//map(4000, 0, 1023, 0,10);
      DoorWindow.setSpeed(motorSpeed);
      DoorWindow.step(-stepsPerRevolution);
      window_con = true;
    }
  }
  void Commander(char val)
  {
      if (val == '4'){ClosedDoor();}
      else if(val == '6'){OpenDoor();}
  }
};

//잠금모터 작동 클래스
class Lock
{
  Servo lockmotor;                           //서보모터 클래스 선언
  int angle;                                  //작동하는 각도
public:
  Lock() {}
  void Attach(){lockmotor.attach(servo);}
  void Detach(){lockmotor.detach();}
  void Commander(char val)
  {
      if (val == '2'){unLockWindow();}
      else if(val == '8'){LockWindow();}
  }
  void LockWindow()
  {
      for (angle = 180; angle > 90; angle--)
      {
        lockmotor.write(angle);
      }
      lock_con = true;
  }
  void unLockWindow()
  {
      for (angle = 90; angle < 180; angle++)
      {
        lockmotor.write(angle);
      }
      lock_con = false;
  }
  void Unauthorized()
  {
    Serial.print("  magnet : ");
    Serial.println(analogRead(magnet));
    if ((lock_con == true) && analogRead(magnet)> 200){tone(buzzer, 1000, 500); delay(1000);}
    else if ((lock_con == true) && analogRead(magnet) <200){noTone(buzzer);}
  }
};

Lock security;
Door_Operator oper;

class RainDrop
{
  int s_read = analogRead(rain_sensor);
  public:
   RainDrop() {}
   boolean Judge()
   {
    if(s_read<500) return true;
    return false;
   }

  //비가왔을 때 창문을 닫거나 날씨가 좋을때 창문을 여는 함수
  void RainnyDay()
  {
     if(window_con ==true && Judge() ==true){oper.ClosedDoor(); security.LockWindow();}
  }
};

//미세먼지 확인 센서 클래스
class DustStatic
{
    int dustPos;
    float ratio = 0;
    float concentration = 0;
    float pcsPerCF = 0;
    float ugm3 = 0;
    unsigned long lps = 0;
    unsigned long duration = 0;
    unsigned long starttime = 0;
    unsigned long sampletime_ms = 3000;
    
 public:
    DustStatic(int pin) : dustPos(pin) 
    {
       
    }
   
    //센서에서 읽어들인 값을 duration 변수에 저장하는 함수
    void DustReading(){duration = pulseIn(dustPos, LOW);}

    //DustReading함수에서 받아들인 값을 우리가 볼 수 있는 측정치인 ugm3값으로 환산해주는 계산 함수
    float DustCalc()
    {
      DustReading();
      lps += duration;
      ratio = lps / (sampletime_ms * 10.0);
      concentration = 1.1 * pow(ratio, 3) - 3.8*pow(ratio, 2) + 520 * ratio + 0.62;
      pcsPerCF = concentration * 100;
      ugm3 = pcsPerCF / 13000;
      lps = 0;
      return ugm3;
    } 
};

void setup() 
{
    Serial.begin(9600);
    security.Attach();
    dht.begin();
    pinMode(rain_sensor, INPUT);
    pinMode(dust_in, INPUT);
    pinMode(dust_out, INPUT);
}

void loop() 
{   
    HumTemp ht;
    RainDrop rd;
    DustStatic in(dust_in);
    DustStatic out(dust_out);
    float compare = 15.0;
    float inside = in.DustCalc();
    float outside = out.DustCalc();
    security.Unauthorized();
    /*if(window_con ==false && lock_con == false)
   {
       if(inside > outside && outside < compare) {oper.OpenDoor();delay(1000);}
       else if(inside > outside && outside >compare) {}
       else if(inside < outside && outside <compare) {}
       else if(inside < outside && outside >compare) {}
    }
    else if(window_con == true && lock_con ==  false)
    { 
       if(inside < outside && outside > compare) {oper.ClosedDoor(); security.LockWindow();delay(1000);}
       else if(inside < outside && outside <compare) {}
       else if(inside > outside && outside >compare) {}
       else if(inside > outside && outside >compare) {}
    }
    else if(window_con == false && lock_con == true){}
    else if(window_con == true && lock_con == true) {}
    ht.CalcView();
    String dust = "inside : " + (String)inside + " outside : " + (String)outside;
    Serial.println(dust); */
   if(rd.Judge())
   {
      while(!Serial.available())
      {
        ht.CalcView();
        rd.RainnyDay();
        String dust = "inside : " + (String)inside + " outside : " + (String)outside;
        Serial.print(dust);
        break;
      }
   }
   while(Serial.available())
   {
        char val = Serial.read();
        security.Commander(val);
        oper.Commander(val);
        break;
   }
}
