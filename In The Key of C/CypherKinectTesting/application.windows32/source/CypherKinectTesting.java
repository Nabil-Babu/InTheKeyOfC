import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import pKinect.PKinect; 
import pKinect.SkeletonData; 
import arb.soundcipher.*; 
import arb.soundcipher.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class CypherKinectTesting extends PApplet {







PKinect kinect;                                                                               //Chris programmed the following code while parts of it were revised by Nabil Babu....{
ArrayList<SkeletonData> bodies;                  
int[] ValidJoints = {3, 6, 9, 12, 14, 16, 18};  
Visualizer vis;                                 
int counter = 0;                                                            
public void setup()                                    
{                                                  
  size(1366, 768);                                  
  background(255);                              
  frameRate(24);
  kinect = new PKinect(this);
  bodies = new ArrayList<SkeletonData>();
  smooth();
  vis = new Visualizer(); 
}

public void draw()
{
  if(bodies.size() == 0){
    vis.update(-100, 0, -10, 3, 0);
    vis.update(-100, 0, -10, 6, 0);
    vis.update(-100, 0, -10, 9, 0);
    vis.update(-100, 0, -10, 12, 0);
    vis.update(-100, 0, -10, 16, 0);
    vis.update(-100, 0, -10, 14, 0);
    vis.update(-100, 0, -10, 18, 0);
    
    
  }
  
  if(bodies.size() > 0){
   
    float headx = map(bodies.get(0).skeletonPositions[3].x, 0, 1, width/4, width*3/4); 
    float heady = map(bodies.get(0).skeletonPositions[3].y, 0, 1, height/2, height);
    float headz = map(bodies.get(0).skeletonPositions[3].z, -1, 1, 50, 0);

    float lwristx = map(bodies.get(0).skeletonPositions[6].x, 0, 1, width/4, width*3/4); 
    float lwristy = map(bodies.get(0).skeletonPositions[6].y, 0, 1, height/2, height);
    float lwristz = map(bodies.get(0).skeletonPositions[6].z, -1, 1, 50, 0);
    
    float rwristx = map(bodies.get(0).skeletonPositions[9].x, 0, 1, width/4, width*3/4); 
    float rwristy = map(bodies.get(0).skeletonPositions[9].y, 0, 1, height/2, height);
    float rwristz = map(bodies.get(0).skeletonPositions[9].z, -1, 1, 50, width/4);
    
    float lkneex = map(bodies.get(0).skeletonPositions[12].x, 0, 1, width/4, width*3/4); 
    float lkneey = map(bodies.get(0).skeletonPositions[12].y, 0, 1, height/2, height); 
    float lkneez = map(bodies.get(0).skeletonPositions[12].z, -1, 1, 50, 0);
    
    float rkneex = map(bodies.get(0).skeletonPositions[16].x, 0, 1, width/4, width*3/4); 
    float rkneey = map(bodies.get(0).skeletonPositions[16].y, 0, 1, height/2, height); 
    float rkneez = map(bodies.get(0).skeletonPositions[16].z, -1, 1, 50, 0);
    
    float lfootx = map(bodies.get(0).skeletonPositions[14].x, 0, 1, width/4, width*3/4); 
    float lfooty = map(bodies.get(0).skeletonPositions[14].y, 0, 1, height/2, height); 
    float lfootz = map(bodies.get(0).skeletonPositions[14].z, -1, 1, 50, 0);
    
    float rfootx = map(bodies.get(0).skeletonPositions[18].x, 0, 1, width/4, width*3/4); 
    float rfooty = map(bodies.get(0).skeletonPositions[18].y, 0, 1, height/2, height);
    float rfootz = map(bodies.get(0).skeletonPositions[18].z, -1, 1, 50, 0);              //..... }
    
    vis.update(headx, heady, headz, 3, bodies.size());                                    // Nabil Babu wrote the following code.... {
    vis.update(lwristx, lwristy, lwristz, 6, bodies.size());
    vis.update(rwristx, rwristy, rwristz, 9, bodies.size());
    vis.update(lkneex, lkneey, lkneez, 12, bodies.size());
    vis.update(rkneex, rkneey, rkneez, 16, bodies.size());
    vis.update(lfootx, lfooty, lfootz, 14, bodies.size());
    vis.update(rfootx, rfooty, rfootz, 18, bodies.size());                                // .......}  
    
    if(counter % 65 == 0){                                                                // Chris Elcombe wrote the following code..... {
      if(vis.isMoving()){
      vis.makeNote();
      }
    }
    
    counter++;
  }                                                                                      // .....}
  
  
 background(255);                                                                        // All the code below were provided by the Creators of the PKinect Library 
                                                                                         // But modified and edited by Chris Elcombe 
  vis.draw();
  for (int i=0; i<bodies.size(); i++) 
  {
    drawSkeleton(bodies.get(i));
    drawPosition(bodies.get(i));
  }
}

public void drawPosition(SkeletonData _s) 
{
  noStroke();
  fill(0, 100, 255);
}

public void drawSkeleton(SkeletonData _s) 
{
  // Body
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_HEAD, 
  PKinect.NUI_SKELETON_POSITION_SHOULDER_CENTER);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_SHOULDER_CENTER, 
  PKinect.NUI_SKELETON_POSITION_SHOULDER_LEFT);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_SHOULDER_CENTER, 
  PKinect.NUI_SKELETON_POSITION_SHOULDER_RIGHT);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_SHOULDER_CENTER, 
  PKinect.NUI_SKELETON_POSITION_SPINE);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_SHOULDER_LEFT, 
  PKinect.NUI_SKELETON_POSITION_SPINE);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_SHOULDER_RIGHT, 
  PKinect.NUI_SKELETON_POSITION_SPINE);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_SPINE, 
  PKinect.NUI_SKELETON_POSITION_HIP_CENTER);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_HIP_CENTER, 
  PKinect.NUI_SKELETON_POSITION_HIP_LEFT);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_HIP_CENTER, 
  PKinect.NUI_SKELETON_POSITION_HIP_RIGHT);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_HIP_LEFT, 
  PKinect.NUI_SKELETON_POSITION_HIP_RIGHT);

  // Left Arm
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_SHOULDER_LEFT, 
  PKinect.NUI_SKELETON_POSITION_ELBOW_LEFT);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_ELBOW_LEFT, 
  PKinect.NUI_SKELETON_POSITION_WRIST_LEFT);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_WRIST_LEFT, 
  PKinect.NUI_SKELETON_POSITION_HAND_LEFT);

  // Right Arm
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_SHOULDER_RIGHT, 
  PKinect.NUI_SKELETON_POSITION_ELBOW_RIGHT);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_ELBOW_RIGHT, 
  PKinect.NUI_SKELETON_POSITION_WRIST_RIGHT);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_WRIST_RIGHT, 
  PKinect.NUI_SKELETON_POSITION_HAND_RIGHT);

  // Left Leg
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_HIP_LEFT, 
  PKinect.NUI_SKELETON_POSITION_KNEE_LEFT);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_KNEE_LEFT, 
  PKinect.NUI_SKELETON_POSITION_ANKLE_LEFT);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_ANKLE_LEFT, 
  PKinect.NUI_SKELETON_POSITION_FOOT_LEFT);

  // Right Leg
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_HIP_RIGHT, 
  PKinect.NUI_SKELETON_POSITION_KNEE_RIGHT);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_KNEE_RIGHT, 
  PKinect.NUI_SKELETON_POSITION_ANKLE_RIGHT);
  DrawBone(_s, 
  PKinect.NUI_SKELETON_POSITION_ANKLE_RIGHT, 
  PKinect.NUI_SKELETON_POSITION_FOOT_RIGHT);
}

public void DrawBone(SkeletonData _s, int _j1, int _j2) 
{
  noFill();
  stroke(0, 0, 255);
  if (_s.skeletonPositionTrackingState[_j1] != PKinect.NUI_SKELETON_POSITION_NOT_TRACKED &&
    _s.skeletonPositionTrackingState[_j2] != PKinect.NUI_SKELETON_POSITION_NOT_TRACKED) {
    line(_s.skeletonPositions[_j1].x*width/2 + width/4, 
    _s.skeletonPositions[_j1].y*height/2+height/2, 
    _s.skeletonPositions[_j2].x*width/2 + width/4, 
    _s.skeletonPositions[_j2].y*height/2+height/2);
  }
}

public void appearEvent(SkeletonData _s) 
{
  if (_s.trackingState == PKinect.NUI_SKELETON_NOT_TRACKED) 
  {
    return;
  }
  synchronized(bodies) {
   
       bodies.add(0 ,_s);
  }
}

public void disappearEvent(SkeletonData _s) 
{
  synchronized(bodies) {
    for (int i=bodies.size()-1; i>=0; i--) 
    {
      if (_s.dwTrackingID == bodies.get(i).dwTrackingID) 
      {
       bodies.remove(i);
       bodies = new ArrayList<SkeletonData>();
       bodies.clear();
      }
    }
  }
}

public void moveEvent(SkeletonData _b, SkeletonData _a) 
{
  if (_a.trackingState == PKinect.NUI_SKELETON_NOT_TRACKED) 
  {
    return;
  }
  synchronized(bodies) {
    for (int i=bodies.size()-1; i>=0; i--) 
    {
      if (_b.dwTrackingID == bodies.get(i).dwTrackingID) 
      {
        bodies.get(i).copy(_a);
        break;
      }
    }
  }
}
public class Joint{                              //This Entire sub class was written by Chris Elcombe and then edited and modified by Nabil Babu
 float sx, sy, sz;
 float dx, dy, dz;
 float px, py, pz;
 float initTime;
 int size;
 int r, g, b, a;
 final int TIMEOUT = 3000; // in millis 
 boolean isDead;
 String shape;
 final int ALPHA_DEC = 5;
 int maxBar;
 
 Joint(float x, float y, float z){
  this.sx = x;
  this.sy = y;
  this.sz = z;
  isDead = false;
  px = x;
  py = y;
  pz = z;
  dx = random(5);
  dy = random(10) - 5;
  dz = 1;
  initTime = millis();
  size = (int) random(30) + 20;
  r = (int) random(255);
  g = (int) random(255);
  b = (int) random(255);
  a = 255;

  maxBar =(((int) (random(5))) + 1) * 20;
  
  int temp = (int) random(3);
  if(temp > 1){
   shape = "rect"; 
  }else{
   shape = "elli"; 
  }
 } 
 
 public void isTimed(){
   if(millis() > initTime + TIMEOUT){
     isDead = true;
   }
 }
 
 public void update(){
   
   if(py + dy * 0.5f <= maxBar){ // hit bar, stop vertical, move constant horizontal
    py = maxBar; 
    px -= 20;
   }else{ // else do normal stuff
   px = px + dx * 0.5f;
   py = py + dy * 0.5f;
   a = a - ALPHA_DEC;
   }
   
 }
 
 public void drawShape(){
   fill(r, g, b, a);
   stroke(1);
   line(px + size/2, py, px + size/2 + 1, py - 50);
   ellipse(px, py, size, size);
   
 }
 
 public void outOfBounds(){
   if(px + size / 2 < 0){
    isDead = true; 
   }else if(px - size /2 > width){
     isDead = true;
   }else if(py + size / 2 < 0){
     isDead = true; 
   }
 }
 
 public void updateVelocity(float x, float y, float z){
   dx = x - sx + random(30) - 15;
   dy = -1 * abs(y - sy) - 30;
   dz = z - sz;
 }
 
 
}
                //All Imports, Fields and Attributes were written and coded by both Chris Elcombe and Nabil Babu
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




public void draw(){    //This Function was written by Chris Elcombe 
   
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
  float[] durations = {(random(0.5f)+0.5f),(random(0.5f)+0.5f),(random(0.5f)+0.5f),(random(0.5f)+0.5f),(random(0.5f)+0.5f),(random(0.5f)+0.5f),(random(0.5f)+0.5f)};
  
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
  
  pitches[0] = map(hx, -2.2f, 2.2f, 40, 80);
  dynamics[0] = map(hy, -1.6f, 1.6f, -20, 150);
  
  pitches[1] = map(lwx, -2.2f, 2.2f, 40, 80);
  dynamics[1] = map(lwy, -1.6f, 1.6f, -20, 150);
  
  pitches[2] = map(rwx, -2.2f, 2.2f, 40, 80);
  dynamics[2] = map(rwy, -1.6f, 1.6f, -20, 150);
  
  pitches[3] = map(lkx, -2.2f, 2.2f, 40, 80);
  dynamics[3] = map(lky, -1.6f, 1.6f, -20, 150);
  
  pitches[4] = map(rkx, -2.2f, 2.2f, 40, 80);
  dynamics[4] = map(rky, -1.6f, 1.6f, -20, 150);
  
  pitches[5] = map(lfx, -2.2f, 2.2f, 40, 80);
  dynamics[5] = map(lfy, -1.6f, 1.6f, -20, 150);
  
  pitches[6] = map(rfx, -2.2f, 2.2f, 40, 80);
  dynamics[6] = map(rfy, -1.6f, 1.6f, -20, 150);
  
  sc.playPhrase(pitches, dynamics, durations);
}
 

public boolean isMoving(){ //This Function was written by Chris Elcombe
  if(isMovingHead || isMovingLWrist || isMovingRWrist || isMovingLKnee || isMovingRKnee || isMovingLFoot || isMovingRFoot){
   return true; 
  }
  return false;
}



}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--hide-stop", "CypherKinectTesting" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
