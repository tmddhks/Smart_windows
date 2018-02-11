
#include <DHT.h>
#include <DHT_U.h>
#include <Servo.h>
#define DHTPIN A1
#define DHTTYPE DHT11
DHT dht(DHTPIN, DHTTYPE);
int Raindrops_pin = A2;   //빗방울센서 핀을 A2로 설정
int Buzzer = 13;          //Buzzer 핀을 13번 핀으로 설정
int Magnet = 2;           //Magnet 핀을 2번 핀으로 설정

class Motor
{
  Servo servo;  // servo
  int pos;      //현재 서보 위치
  int increment;  //각각 간격의 증가량
  int updateInterval; //업데이트 사이간 간격
  unsigned long lastUpdate; //마지막 업데이트 위치

  public:
    Motor(int interval)
    {
       updateInterval = interval;
       increment = 1;
    }
    void Attach(int pin)
    {
      servo.attach(pin);
    }
    void Detach()
    {
       servo.detach();
    }
    void Update(unsigned long currentMillis)
    {
      if((currentMillis - lastUpdate)>updateInterval)   //시간 업데이트
      {
        lastUpdate = millis();
        pos += increment;
        servo.write(pos);
        if((pos>=90)||(pos<=0)) //회전 종료
        {
          //목적지로 되돌아가기
          increment = - increment;
        }
      }
    }
};
Motor motor1(25);
Motor motor2(35);

void setup()
{
  pinMode(Magnet, INPUT_PULLUP); //Magnet 핀을 디지털 2번 핀에 입력으로 설정
  pinMode(Buzzer, OUTPUT);  //Buzzer핀을 출력으로 설정
  motor1.Attach(6);         //디지털 pwm 6번 핀에 연결
  motor2.Attach(9);         //디지털 pwm 9번 핀에 연결
  //비교 레지스터를 설정하여 카운트 중간에 다른 인터럽트를 생성
  OCR0A = 0xAF;
  TIMSK0 |= _BV(OCIE0A);
}
//밀리초단위로 인터럽트를 부릅니다.
SIGNAL(TIMER0_COMPA_vect)
{
  unsigned long currentMillis = millis();
  Serial.println(digitalRead(2));
  motor1.Update(currentMillis);
  if(digitalRead(2) == HIGH)       //문이 외압에 열리면
  {
    motor2.Update(currentMillis);
    tone(2,520);      //Buzzer가 0.1초 간격으로 경고음 울림
    delay(100);
    noTone(2);
    delay(1);
  }
}
void loop()
{
  int h = dht.readHumidity();   //변수 h에 습도 값을 저장
  int t = dht.readTemperature();    //변수 t에 온도 값을 저장
  Serial.print("Humidity: ");     //문자열 Humidity: 를 출력
  Serial.print(h);                //변수 h(습도)를 출력
  Serial.print("%\t");            //%를 출력
  Serial.print("Temperature: ");  //문자열 Temperature: 를 출력
  Serial.print(t);                //변수 t(온도)를 출력
  Serial.println(" C");           //C를 출력
  unsigned long currentMillis = millis();
  Serial.println(analogRead(A2));
  millis();
  if(analogRead(A2) < 500)   //센서 출력값이 500 미만이면 (빗방울이 감지되면)
  {
    motor1.Update(currentMillis);   //모터가 작동되어 창문이 닫힌다.
  }   
 }
