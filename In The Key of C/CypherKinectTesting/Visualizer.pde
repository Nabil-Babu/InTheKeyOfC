import arb.soundcipher.*;                //All Imports, Fields and Attributes were written and coded by both Chris Elcombe and Nabil Babu
SoundCipher sc = new SoundCipher(this);

float oldHead = 0;
float oldLWrist = 0;
float oldRWrist = 0;
float oldLKnee = 0;
float oldRKnee = 0;
float oldLFoot = 0;
float oldRFoot = 0;

float newHead = 0;
float newLWrist = 0;
float newRWrist = 0;
float newLKnee = 0;
float newRKnee = 0;
float newLFoot = 0;
float newRFoot = 0;
final float Threash = 350;

boolean isMovingHead = false;
boolean isMovingLWrist = false;
boolean isMovingRWrist = false;
boolean isMovingRKnee = false;
boolean isMovingLKnee = false;
boolean isMovingRFoot = false;
boolean isMovingLFoot = false;

final int LINE1 = 20;
final int LINE2 = 40;
final int LINE3 = 60;
final int LINE4 = 80;
final int LINE5 = 100;

class Visualizer{
Joint[] body; 
/*
0 = head
1 = left elbow
2 = left wrist
3 = right elbow 
4 = right wrist
5 = left knee
6 = right knee
7 = left foot
8 = right foot
*/

public Visualizer(){ //This Function was written by Chris Elcombe and Nabil Babu
  body = new Joint[9];
  for(int i = 0; i < 9; i++){
     body[i] = (new Joint(-40,-40,0));
  }
  sc.instrument(0);
}




void draw(){    //This Function was written by Chris Elcombe 
   
  for(int i = 0; i < body.length; i++){
   drawStaff();
   body[i].isTimed();
   body[i].outOfBounds();
   body[i].update();
   body[i].drawShape();
  
  }
}

public void drawStaff(){      //This Function was written by Chris Elcombe
 line(0, LINE1, width, LINE1); 
 line(0, LINE2, width, LINE2); 
 line(0, LINE3, width, LINE3); 
 line(0, LINE4, width, LINE4); 
 line(0, LINE5, width, LINE5); 
}

public void update(float x, float y, float z, int jointNum, int numBodies){ //This Function was written by Chris Elcombe 
  if(jointNum == 3){ // headbody
    if(body[0].isDead == true){
      if(numBodies > 0){
       newHead = sqrt(bodies.get(0).skeletonPositions[3].x * bodies.get(0).skeletonPositions[3].x + bodies.get(0).skeletonPositions[3].y * bodies.get(0).skeletonPositions[3].y + bodies.get(0).skeletonPositions[3].z * bodies.get(0).skeletonPositions[3].z);
       if(abs(newHead - oldHead) > Threash){
         body[0] = new Joint(x, y, z);
         oldHead = newHead;
         isMovingHead = true;
       }else{
        isMovingHead = false; 
       }
      }
  }else{
      body[0].updateVelocity(x, y, z);
    }

  }
  else if(jointNum == 6){ // l wrist
   if(body[2].isDead == true){
      if(numBodies > 0){
       newLWrist = sqrt(bodies.get(0).skeletonPositions[6].x * bodies.get(0).skeletonPositions[6].x + bodies.get(0).skeletonPositions[6].y * bodies.get(0).skeletonPositions[6].y + bodies.get(0).skeletonPositions[6].z * bodies.get(0).skeletonPositions[6].z);
       if(abs(newLWrist - oldLWrist) > Threash){
         body[2] = new Joint(x, y, z);
         oldLWrist = newLWrist;
         isMovingLWrist = true;
       }else{
        isMovingLWrist = false; 
       }
      }
  }
    else{
      body[2].updateVelocity(x, y, z);
    }
  }
  else if(jointNum == 9){ // r wrist
    if(body[4].isDead == true){
      if(numBodies > 0){
       newRWrist = sqrt(bodies.get(0).skeletonPositions[9].x * bodies.get(0).skeletonPositions[9].x + bodies.get(0).skeletonPositions[9].y * bodies.get(0).skeletonPositions[9].y + bodies.get(0).skeletonPositions[9].z * bodies.get(0).skeletonPositions[9].z);
        if(abs(newRWrist - oldRWrist) > Threash){
      body[4] = new Joint(x, y, z);
      oldRWrist = newRWrist;
      isMovingRWrist = true;
        }else{
         isMovingRWrist = false; 
        }
      }
    }else{
      body[4].updateVelocity(x, y, z);
    }
  
  }
  else if(jointNum == 12){ // l knee
    if(body[5].isDead == true){
       if(numBodies > 0){
       newLKnee = sqrt(bodies.get(0).skeletonPositions[12].x * bodies.get(0).skeletonPositions[12].x + bodies.get(0).skeletonPositions[12].y * bodies.get(0).skeletonPositions[12].y + bodies.get(0).skeletonPositions[12].z * bodies.get(0).skeletonPositions[12].z);
        if(abs(newLKnee - oldLKnee) > Threash){
      body[5] = new Joint(x, y, z);
       oldLKnee = newLKnee;
       isMovingLKnee = true;
        }else{
         isMovingLKnee = false; 
        }
       }
    }else{
      body[5].updateVelocity(x, y, z);
    }
  }
  else if(jointNum == 14){ // l foot
    if(body[6].isDead == true){
       if(numBodies > 0){
       newLFoot = sqrt(bodies.get(0).skeletonPositions[14].x * bodies.get(0).skeletonPositions[14].x + bodies.get(0).skeletonPositions[14].y * bodies.get(0).skeletonPositions[14].y + bodies.get(0).skeletonPositions[14].z * bodies.get(0).skeletonPositions[14].z);
        if(abs(newLFoot - oldLFoot) > Threash){
       body[6] = new Joint(x, y, z);
       oldLFoot = newLFoot;
       isMovingLFoot = true;
        }else{
         isMovingLFoot = false; 
        }
       }
    }else{
      body[6].updateVelocity(x, y, z);
    }
  }
  else if(jointNum == 16){ // r knee
    if(body[7].isDead == true){
       if(numBodies > 0){
       newRKnee = sqrt(bodies.get(0).skeletonPositions[16].x * bodies.get(0).skeletonPositions[16].x + bodies.get(0).skeletonPositions[16].y * bodies.get(0).skeletonPositions[16].y + bodies.get(0).skeletonPositions[16].z * bodies.get(0).skeletonPositions[16].z);
        if(abs(newRKnee - oldRKnee) > Threash){
      body[7] = new Joint(x, y, z);
      oldRKnee = newRKnee;
      isMovingRKnee = true;
        }else{
         isMovingRKnee = false; 
        }
       }
    }else{
      body[7].updateVelocity(x, y, z);
    }
  }
  else if(jointNum == 18){ // r foot
    if(body[8].isDead == true){
      if(numBodies > 0){
       newRFoot = sqrt(bodies.get(0).skeletonPositions[18].x * bodies.get(0).skeletonPositions[18].x + bodies.get(0).skeletonPositions[18].y * bodies.get(0).skeletonPositions[18].y + bodies.get(0).skeletonPositions[18].z * bodies.get(0).skeletonPositions[18].z);
        if(abs(newRFoot - oldRFoot) > Threash){
      
      body[8] = new Joint(x, y, z);
      oldRFoot = newRFoot;
      isMovingRFoot = true;
        }else{
         isMovingRFoot = false; 
        }
       }
    }else{
      body[8].updateVelocity(x, y, z);
    }
  }
}
public void makeNote(){ //This Function was written by Nabil Babu
  
  float[] pitches = new float[7];
  float[] dynamics = new float[7];
  float[] durations = {(random(0.5)+0.5),(random(0.5)+0.5),(random(0.5)+0.5),(random(0.5)+0.5),(random(0.5)+0.5),(random(0.5)+0.5),(random(0.5)+0.5)};
  
  float hx = bodies.get(0).skeletonPositions[3].x;
  float hy = bodies.get(0).skeletonPositions[3].y;
  
  float lwx = bodies.get(0).skeletonPositions[6].x;
  float lwy = bodies.get(0).skeletonPositions[6].y;
  
  float rwx = bodies.get(0).skeletonPositions[9].x;
  float rwy = bodies.get(0).skeletonPositions[9].y;
  
  float lkx = bodies.get(0).skeletonPositions[12].x;
  float lky = bodies.get(0).skeletonPositions[12].y;
  
  float rkx = bodies.get(0).skeletonPositions[14].x;
  float rky = bodies.get(0).skeletonPositions[14].y;
  
  float lfx = bodies.get(0).skeletonPositions[16].x;
  float lfy = bodies.get(0).skeletonPositions[16].y;
  
  float rfx = bodies.get(0).skeletonPositions[18].x;
  float rfy = bodies.get(0).skeletonPositions[18].y;
  
  pitches[0] = map(hx, -2.2, 2.2, 40, 80);
  dynamics[0] = map(hy, -1.6, 1.6, -20, 150);
  
  pitches[1] = map(lwx, -2.2, 2.2, 40, 80);
  dynamics[1] = map(lwy, -1.6, 1.6, -20, 150);
  
  pitches[2] = map(rwx, -2.2, 2.2, 40, 80);
  dynamics[2] = map(rwy, -1.6, 1.6, -20, 150);
  
  pitches[3] = map(lkx, -2.2, 2.2, 40, 80);
  dynamics[3] = map(lky, -1.6, 1.6, -20, 150);
  
  pitches[4] = map(rkx, -2.2, 2.2, 40, 80);
  dynamics[4] = map(rky, -1.6, 1.6, -20, 150);
  
  pitches[5] = map(lfx, -2.2, 2.2, 40, 80);
  dynamics[5] = map(lfy, -1.6, 1.6, -20, 150);
  
  pitches[6] = map(rfx, -2.2, 2.2, 40, 80);
  dynamics[6] = map(rfy, -1.6, 1.6, -20, 150);
  
  sc.playPhrase(pitches, dynamics, durations);
}
 

public boolean isMoving(){ //This Function was written by Chris Elcombe
  if(isMovingHead || isMovingLWrist || isMovingRWrist || isMovingLKnee || isMovingRKnee || isMovingLFoot || isMovingRFoot){
   return true; 
  }
  return false;
}



}
