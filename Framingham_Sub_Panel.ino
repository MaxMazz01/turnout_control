int state;
int turnout;

void setup() {
  // put your setup code here, to run once:
  //initialize all the pins used
  for(int i = 5; i <= 12; i++) {
    pinMode(i, OUTPUT);
    //i'm too lazy to implement pull-up resistors, so set all the pins to high as well
    digitalWrite(i, HIGH);
  }
  Serial.begin(9600);
}

void loop() {
  // put your main code here, to run repeatedly:
  if(Serial.available() > 0) {
    //c indicates a command being sent
    if(Serial.peek() == 'c') {
      Serial.read();
      turnout = Serial.parseInt();
      state = Serial.parseInt();

      //FRA 1
      if(turnout == 1) {
        if(state == 1) {
          digitalWrite(12, LOW);
          delay(100);
          digitalWrite(12, HIGH);
        }
        else if(state == 0) {
          digitalWrite(11, LOW);
          delay(100);
          digitalWrite(11, HIGH);
        }
      }

      //MED 1
      if(turnout == 3) {
        if(state == 1) {
          digitalWrite(10, LOW);
          delay(100);
          digitalWrite(10, HIGH);
        }
        else if(state == 0) {
          digitalWrite(9, LOW);
          delay(100);
          digitalWrite(9, HIGH);
        }
      }

      //MED 2
      if(turnout == 4) {
        if(state == 1) {
          digitalWrite(7, LOW);
          delay(100);
          digitalWrite(7, HIGH);
        }
        else if(state == 0) {
          digitalWrite(8, LOW);
          delay(100);
          digitalWrite(8, HIGH);
        }
      }

      //MED 3
      if(turnout == 5) {
        if(state == 1) {
          digitalWrite(5, LOW);
          delay(100);
          digitalWrite(5, HIGH);
        }
        else if(state == 0) {
          digitalWrite(6, LOW);
          delay(100);
          digitalWrite(6, HIGH);
        }
      }
    }
    while(Serial.available() > 0) {
      Serial.read();
    }
  }
}
