#include <Stepper.h>
#include <Servo.h>
#include "DHT.h"

#define rain_sensor A0
#define magnet A1
#define DHTPIN 2
#define dust_in 3
#define dust_out 5
#define servo 6
#define buzzer 7


#define DHTTYPE DHT11
#define stepsPerRevolution_open 400
#define stepsPerRevolution_closed 400
DHT dht(DHTPIN, DHTTYPE);
Stepper myStepper_o(stepsPerRevolution_open, 8,9,10,11);
Stepper myStepper_c(stepsPerRevolution_closed, 8,9,10,11);
Servo myservo;

String input = "";
String cmd = "temp";
String response = "";
String recipt = "";

boolean window_con = true; //닫힌상태
boolean lock_con = true;   //잠금상태

class Stepper_for_Open
{
public:
   int sensorReading = analogRead(A0);
   
  void OpenDoor()
  {
    int motorSpeed = map(sensorReading, 0, 1023, 0, 10);
    myStepper_o.setSpeed(motorSpeed);
    myStepper_o.step(-stepsPerRevolution_open);
    delay(1000);
    window_con = true;
//    Serial.print("[");
//    Serial.print(window_con);
//    Serial.println("]");
  }
  void ClosedDoor()
  {
    int motorSpeed = map(sensorReading, 0, 1023, 0, 10);
    myStepper_o.setSpeed(motorSpeed);
    myStepper_o.step(stepsPerRevolution_closed);
    delay(1000);
    window_con = false;
//    Serial.print("[");
//    Serial.print(window_con);
//    Serial.println("]");
  }
};

class RainDrop
{
public:
  const int sensorMin = 0;
  const int sensorMax = 1024;
  int state;
  Stepper_for_Open sfo;
  int RainReading()
  {
    return analogRead(rain_sensor);
  }
  void RainSearch()
  {
      if (window_con == true && RainReading() < 500)
      {
        FloodSensor();
        sfo.ClosedDoor();
      }
      else if(window_con == true && RainReading() > 500)
      {  
        ClearSensor();  //동작안함
      }
      else if(window_con == false && RainReading() <500)
      {
        FloodSensor();  //동작안함  
      }
      else if(window_con == false && RainReading() >500)
      {
        ClearSensor(); //동작안함
        sfo.OpenDoor();
      }
  }
  void FloodSensor()
  {
    //Serial.println( "비가옵니다." );
  }
  void ClearSensor()
  {
    //Serial.println( "맑은 날씨입니다." );
  }
};
class Dust
{
private:
  int dustPos;
  float ratio = 0;
  float concentration = 0;
  float pcsPerCF = 0;
  float ugm3 = 0;
public:
  Dust(int pin) : dustPos(pin)
  {}
  Stepper_for_Open sfo;
  unsigned long lps = 0;
  unsigned long duration = 0;
  unsigned long starttime = 0;
  unsigned long sampletime_ms = 3000;
  void DustReading()
  {
    duration = pulseIn(dustPos, LOW);
  }
  float DustCalc()
  {
    lps += duration;
    ratio = lps / (sampletime_ms * 10.0);
    concentration = 1.1 * pow(ratio, 3) - 3.8*pow(ratio, 2) + 520 * ratio + 0.62;
    pcsPerCF = concentration * 100;
    ugm3 = pcsPerCF / 13000;
    lps = 0;
    return ugm3;
  }
  void DustCompare()
  {
    recipt = DustCalc() ;
    Serial.print(":");
    Serial.print(recipt);
    if (ugm3 >= 0 && ugm3 < 5.0)
    {
      DustClear();
    }
   /* else if (ugm3 >= 30.0 && ugm3 < 80.0)
    {
      DustFine();
    }*/
    else if (ugm3 >= 5.0 && ugm3 < 15.0)
    {
      DustBad();
      //sfo.ClosedDoor();
    }
    else if (ugm3 >= 15.0)
    {
      DustWorst();
      //sfo.ClosedDoor();
    }
    else
    {
      //Serial.println( "미세먼지 측정이 안되고 있습니다." );
    }
  }
  void DustClear()
  {
    //Serial.println( "미세먼지 농도가 맑습니다." );
  }
  void DustFine()
  {
   // Serial.println( "미세먼지 농도가 보통입니다." );
  }
  void DustBad()
  {
    //Serial.println( "미세먼지 농도가 나쁩니다." );
  }
  void DustWorst()
  {
    //Serial.println( "미세먼지 농도가 매우 나쁩니다.");
  }
};

class HumTemp
{
public:
  float h = dht.readHumidity();
  float t = dht.readTemperature();
  float f = dht.readTemperature(true);

  void CheckCalc()
  {
    if (isnan(h) || isnan(t))
    {
      //Serial.println("Failed to read from DHT sensor!");
    }
  }

  void CalcView()
  {
    CheckCalc();
    float hif = dht.computeHeatIndex(f, h);
    float hic = dht.computeHeatIndex(t, h, false);

    response = (String)h + ":" + (String)t + ":" + (String)hic;
    Serial.print(response);
  }
};

class Lock
{
public:
  int angle = 0;

  void LockWindo()
  {
    for (angle = 90; angle > 0; angle--)
    {
      myservo.write(angle);
      delay(25);
      lock_con = false;
    }
  }
  void unLockWindo()
  {
    for (angle = 0; angle< 90; angle++)
    {
      myservo.write(angle);
      delay(25);
      lock_con = true;
    }
  }
};

class Warning
{
public:
  Lock lc;
  int m = analogRead(magnet);

  void WarningBell()
  {
    if (m == HIGH && lc.angle == 89)
    {
      tone(buzzer, 520);
      delay(50000);
      noTone(buzzer);
      delay(1000);
    }
  }
};

Dust dusti(dust_in);
Dust dusto(dust_out);


void setup()
{
  Serial.begin(9600);
  dht.begin();
  myStepper_o.setSpeed(120);
  myStepper_c.setSpeed(120);
  pinMode(rain_sensor, INPUT);
  pinMode(dust_in, INPUT);
  pinMode(dust_out, INPUT);
  myservo.attach(servo);
  
}
void loop()
{
    Lock lc;
    Warning wn;
    HumTemp ht;
    Dust dusti(dust_in);
    Dust dusto(dust_out);
    RainDrop rain;
    Stepper_for_Open sfo;

    if(lock_con == false)
    {
      lc.unLockWindo();
    }
    if(lock_con == true)
    {
      lc.LockWindo();
    }
    ht.CalcView();
    rain.RainSearch();
    dusti.DustReading();
    dusti.DustCompare();
    dusto.DustReading();
    dusto.DustCompare();
    Serial.println(" ");
    if(window_con == true)
    {
      if (dusti.DustCalc() < dusto.DustCalc() && dusto.DustCalc() >= 5.0)
      {
        sfo.ClosedDoor();
      }
      else if(dusti.DustCalc() <dusto.DustCalc() && dusto.DustCalc() <5.0)
      {
        //동작안함
      }
      else if(dusti.DustCalc() > dusto.DustCalc() && dusto.DustCalc() >= 5.0)
      {
        //동작안함
      }
      else if(dusti.DustCalc() > dusto.DustCalc() && dusto.DustCalc() < 5.0)
      {
        //동작안함
      }
    }
    else if (window_con == false)
    {
      if(dusti.DustCalc() > dusto.DustCalc() && dusti.DustCalc() >= 5.0 && dusto.DustCalc() <5.0)
      {
         sfo.OpenDoor();
      }
      else if(dusti.DustCalc() > dusto.DustCalc() && dusti.DustCalc() < 5.0 && dusto.DustCalc() <5.0)
      {
        //동작안함
      }
      else if(dusti.DustCalc() < dusto.DustCalc() && dusti.DustCalc() <5.0 && dusto.DustCalc() >= 5.0)
      {
        sfo.ClosedDoor();
      }
      else if(dusti.DustCalc() < dusto.DustCalc() && dusti.DustCalc() >=5.0 && dusto.DustCalc() >=5.0)
      {
        //동작안함
      }
    }
}

