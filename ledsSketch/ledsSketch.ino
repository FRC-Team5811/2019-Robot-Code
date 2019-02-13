#include "FastLED.h"
#include "Wire.h"

#define NUM_LEDS 100
#define DATA_PIN 6
#define BUFSIZE 255
#define FRAMES_PER_SECOND 120

volatile int x;
uint8_t gHue = 0;

CRGB leds[NUM_LEDS];

char buffer[BUFSIZE];
int readIdx = 0;
int writeIdx = 0;

int r = 0;
int g = 0;
int b = 0;
int weed = 0;
  int flashCounter = 0;
int switcher;

enum ModeEnum { kRGB, kHSV };
ModeEnum mode = kRGB;

void eatBuf(int i) {
  readIdx = (readIdx + i) % BUFSIZE;
}

void patternRainbow(){
    fill_rainbow(leds, NUM_LEDS, gHue, 7);
    gHue+=50;
}
void patternFade(){
     fadeToBlackBy( leds, NUM_LEDS, 20);
  int pos = beatsin16(13,0,NUM_LEDS);
  leds[pos] += CHSV( gHue, 255, 192);
}
void patternTest(){
    for(int i = 0; i<NUM_LEDS;i++){
          leds[i].setRGB( r, g, b);
      }
      r += switcher * random(1,30);
      g += switcher * random(1,30);
      b += switcher * random(1,30);
      if(r > random(150, 200) && g > random(150,200) && b > random(150,200)){
        switcher = -1;
      }
      else if(r < 3 && g < 3 && b < 3){
        switcher = 1;
      }
    
}
void pattern420(){
     for(int i = 0; i<NUM_LEDS;i++){
          leds[i].setRGB( 94, 55, 13);
      }
      //weed += 100;
}

char getBuf(int i) {
  return buffer[(readIdx + i) % BUFSIZE];
}

void receiveEvent(int howMany) {
  while (1 < Wire.available()) { // loop through all but the last
    char c = Wire.read(); // receive byte as a character
  //  Serial.print(c);         // print the character
  }
  x = Wire.read();    // receive byte as an integer
 // Serial.println(x);         // print the integer
}

void setup() {
  Wire.begin(58);
  Wire.onReceive(receiveEvent);
  /*Wire.onReceive(handler);*/
  Serial.begin(9600);


  FastLED.addLeds<NEOPIXEL, DATA_PIN>(leds, NUM_LEDS);
  FastLED.setBrightness(100);
}

void loop() {
  int count = 0;
  
  int idx = 0;
  int offset = 0;
        for(int i = 0; i<NUM_LEDS;i++){
           leds[i] = CRGB::Black;
      }
  switch(x){
    case 1:
      //lighting profile 1
      for(int i = 0; i<NUM_LEDS;i++){
          leds[i] = CRGB::Green;
      }
      Serial.println("Lighting profile 1");
      break;
   case 2:
      //lighting Profile 2
      for(int i = 0; i<NUM_LEDS;i++){
           leds[i] = CRGB::Yellow;
      }
      Serial.println("Lighting profile 2");
      break;
   case 3:
       //lighting Profile 3
      for(int i = 0; i<NUM_LEDS;i++){
           leds[i] = CRGB::Purple;
      }
      Serial.println("Lighting profile 3");
      break;
   case 4:
    patternRainbow();
    break;
    case 5:
      if(flashCounter % 2 == 0){
          for(int i = 0; i<NUM_LEDS;i++){
             leds[i] = CRGB::White;
             delay(50);
          }
       }else{
        for(int i = 0; i<NUM_LEDS;i++){
             leds[i] = CRGB::Black;
             delay(50);
        }
      }
      flashCounter += 1;
      if(flashCounter > 10){
        break;
      }
    
    case 6:
    patternTest();
    break;
    case 7:
    pattern420();
    break;
    case 10:
            for(int i = 0; i<NUM_LEDS;i++){
           leds[i] = CRGB::Black;
      }
      Serial.println("Lighting profile 3");
      break;
    default:
       for(int i = 0; i<NUM_LEDS;i++){
           leds[i] = CRGB::Black;
       }
  } 

    FastLED.show();
    FastLED.delay(FRAMES_PER_SECOND);
}
